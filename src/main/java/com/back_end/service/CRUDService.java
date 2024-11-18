package com.back_end.service;

import com.back_end.repository.CRUDRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class CRUDService<ID, T> {

    private final CRUDRepository<T, ID> repository;

    public T create(T entity){
        return this.repository.save(entity);
    }

    public List<T> findAll(){
        return this.repository.findAll();
    }

    public T findById(ID id){
        return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Register not found", id));
    }

    public T update(ID id, T entity){

        if(this.findById(id) == null){
            throw new ObjectNotFoundException("Register not found",id);
        }

        return this.repository.save(entity);
    }

    public void delete(ID id){
        this.repository.deleteById(id);
    }

}
