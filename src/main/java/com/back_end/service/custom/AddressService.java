package com.back_end.service.custom;

import com.back_end.domain.entities.Address;
import com.back_end.repository.CRUDRepository;
import com.back_end.service.CRUDService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService extends CRUDService<UUID, Address> {

    public AddressService(CRUDRepository<Address, UUID> repository) {
        super(repository);
    }

}
