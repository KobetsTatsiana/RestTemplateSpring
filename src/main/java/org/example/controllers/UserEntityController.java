package org.example.controllers;

import org.example.service.UserEntityService;
import org.example.service.dto.UserEntityServiceDto;
import org.example.service.dto.UserSiteServiceDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntityController {
    private final UserEntityService service;

    @Autowired
    public UserEntityController(UserEntityService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntityServiceDto> getUserById(@PathVariable Long id) {
        try {
            UserEntityServiceDto dto = service.getUserById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/userSites")
    public ResponseEntity<List<UserSiteServiceDto>> getSitesByUserId(@PathVariable Long id) {
        try {
            UserEntityServiceDto dto = service.getUserById(id);
            return new ResponseEntity<>(dto.getUserSiteList(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<UserEntityServiceDto>> getAllUsers() {
        List<UserEntityServiceDto> dtos = service.getAllUsers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserEntityServiceDto> createUser(@RequestBody UserEntityServiceDto dto) {
        UserEntityServiceDto createdDto = service.saveUser(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntityServiceDto> updateUser(@PathVariable Long id, @RequestBody UserEntityServiceDto dto) {
        dto.setId(id);
        UserEntityServiceDto updatedDto = service.saveUser(dto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
