package com.back_end.controllers;

import com.back_end.service.CRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequiredArgsConstructor()
@RequestMapping()
public abstract class CRUDController<ID,T> {

    private final CRUDService<ID,T> service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public T create(@RequestBody T data){
        return service.create(data);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<T> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public T getById(@PathVariable ID id){
        return service.findById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@PathVariable ID id, @RequestBody T data){
        service.update(id, data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable ID id){
        service.delete(id);
    }
}
