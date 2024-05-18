package ru.selever.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.selever.models.Role;
import ru.selever.repository.RoleRepository;

import java.sql.Timestamp;

@Service
public class RoleService {
    Timestamp ts = new Timestamp(System.currentTimeMillis());

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getByName(String name){
        return roleRepository.findByName(name);
    }
}
