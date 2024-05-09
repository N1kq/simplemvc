package ru.selever.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.selever.models.Data;

public interface DataRepository extends JpaRepository<Data, Long> {
}
