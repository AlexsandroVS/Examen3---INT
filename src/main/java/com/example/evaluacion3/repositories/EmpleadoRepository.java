package com.example.evaluacion3.repositories;

import com.example.evaluacion3.models.BEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<BEmpleado, Long> {

}