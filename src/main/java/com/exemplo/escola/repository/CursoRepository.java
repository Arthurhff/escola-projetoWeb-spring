package com.exemplo.escola.repository;

import com.exemplo.escola.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}