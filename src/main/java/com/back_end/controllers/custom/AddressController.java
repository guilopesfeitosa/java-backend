package com.back_end.controllers.custom;

import com.back_end.controllers.CRUDController;
import com.back_end.domain.entities.Address;
import com.back_end.service.CRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("address")
public class AddressController extends CRUDController<UUID, Address> {

    public AddressController(CRUDService<UUID, Address> service){
        super(service);
    }

}
