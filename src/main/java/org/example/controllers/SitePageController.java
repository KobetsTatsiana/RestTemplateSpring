package org.example.controllers;

import org.example.service.SitePageService;
import org.example.service.dto.SitePageServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pages")
public class SitePageController {
    private final SitePageService sitePageService;

    @Autowired
    public SitePageController(SitePageService sitePageService) {
        this.sitePageService = sitePageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SitePageServiceDto> getPageById(@PathVariable Long id) {
        try {
            SitePageServiceDto dto = sitePageService.getPageById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<SitePageServiceDto>> getAllPages() {
        List<SitePageServiceDto> dtos = sitePageService.getAllPages();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SitePageServiceDto> createPage(@RequestBody SitePageServiceDto dto) {
        SitePageServiceDto createdDto = sitePageService.createOrUpdatePage( dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SitePageServiceDto> updatePage(@PathVariable Long id, @RequestBody SitePageServiceDto dto) {
        dto.setId(id);
        SitePageServiceDto updatedDto = sitePageService.createOrUpdatePage(dto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id) {
        sitePageService.deletePage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
