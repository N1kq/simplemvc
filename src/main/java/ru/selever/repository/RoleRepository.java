package ru.selever.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.selever.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
