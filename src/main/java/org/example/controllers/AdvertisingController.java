package org.example.controllers;

import org.example.service.AdvertisingService;
import org.example.service.dto.AdvertisingServiсeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertisings")
public class AdvertisingController {
    private final AdvertisingService advertisingService;

    @Autowired
    public AdvertisingController(AdvertisingService advertisingService) {
        this.advertisingService = advertisingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisingServiсeDto> getAdvertisingById(@PathVariable Long id) {
        try {
            AdvertisingServiсeDto dto = advertisingService.getAdvertisingById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<AdvertisingServiсeDto>> getAllAdvertising() {
        List<AdvertisingServiсeDto> dtos = advertisingService.getAllAdvertising();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AdvertisingServiсeDto> createAdvertising(@RequestBody AdvertisingServiсeDto dto) {
        AdvertisingServiсeDto createdDto = advertisingService.saveAdvertising(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvertisingServiсeDto> updateAdvertising(@PathVariable Long id, @RequestBody AdvertisingServiсeDto dto) {
        dto.setId(id);
        AdvertisingServiсeDto updatedDto = advertisingService.saveAdvertising(dto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvertising(@PathVariable Long id) {
        advertisingService.deleteAdvertising(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

