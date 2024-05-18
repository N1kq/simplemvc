package ru.selever.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.selever.models.FormData;

public interface FormDataRepository extends JpaRepository<FormData, Long> {
}
