package org.example.service;

import org.example.model.UserEntity;
import org.example.model.UserSite;
import org.example.repository.UserEntityRepository;
import org.example.service.dto.UserEntityServiceDto;
import org.example.service.dto.mapper.UserEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityService {

    private final UserEntityRepository repository;
    private final UserEntityMapper mapper;


    @Autowired
    public UserEntityService(UserEntityRepository repository, UserEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public UserEntityServiceDto getUserById(Long id) {
        UserEntity user = repository.findByIdWithSites(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toDTO(user);
    }

    @Transactional
    public List<UserEntityServiceDto> getAllUsers() {
        List<UserEntity> userEntityList = repository.findAllWithSites();
        return userEntityList.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public UserEntityServiceDto saveUser(UserEntityServiceDto dto) {
        UserEntity updatingUser = mapper.toEntity(dto);

        if (updatingUser.getId() != null && repository.existsById(updatingUser.getId())) {
            UserEntity existingUser = repository.findByIdWithSites(updatingUser.getId()).orElse(null);

            existingUser.setName(updatingUser.getName());
            existingUser.setSurname(updatingUser.getSurname());
            existingUser.setAddress(updatingUser.getAddress());

            for (UserSite updatingSite : updatingUser.getUserSiteList()) {

                UserSite existingSite = existingUser.getUserSiteList().stream()
                        .filter(userSite -> userSite.getId().equals(updatingSite.getId()))
                        .findFirst().orElse(null);

                if (existingSite != null) {
                    existingSite.setNameSite(updatingSite.getNameSite());
                } else {
                    updatingSite.setUserId(existingUser);
                    existingUser.getUserSiteList().add(updatingSite);
                }
            }


            updatingUser = repository.save(existingUser);
        } else {
            if (updatingUser.getUserSiteList() != null) {
                for (UserSite userSite : updatingUser.getUserSiteList()) {
                    userSite.setUserId(updatingUser);
                }
            }
            updatingUser = repository.save(updatingUser);
        }

        return mapper.toDTO(updatingUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
