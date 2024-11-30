package com.goyanov.romashkaco.controllers;

import com.goyanov.romashkaco.services.BaseCrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseCrudController<ENT, ID, DTO>
{
    protected BaseCrudService<ENT, ID, DTO> service;

    public BaseCrudController(BaseCrudService<ENT, ID, DTO> service)
    {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ID id)
    {
        return ResponseEntity.ok(service.findById(id));
    }

    public abstract String getAddedMessage();
    public abstract String getUpdatedMessage();
    public abstract String getDeletedMessage();

    @PostMapping
    public ResponseEntity<?> add(@RequestBody DTO dto)
    {
        service.save(dto);
        return ResponseEntity.ok(getAddedMessage());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable ID id, @RequestBody DTO dto)
    {
        service.update(id, dto);
        return ResponseEntity.ok(getUpdatedMessage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ID id)
    {
        service.deleteById(id);
        return ResponseEntity.ok().body(getDeletedMessage());
    }
}
