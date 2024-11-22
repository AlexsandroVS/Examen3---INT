package com.example.evaluacion3.services;

import com.example.evaluacion3.models.BEmpleado;
import com.example.evaluacion3.repositories.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository){
        this.empleadoRepository = empleadoRepository;
    }

    // Guardar un nuevo empleado
    public BEmpleado saveEmpleado(BEmpleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Obtener todos los empleados
    public List<BEmpleado> findAll() {
        return empleadoRepository.findAll();
    }

    // Obtener un empleado por su ID
    public BEmpleado findById(Long id) {
        Optional<BEmpleado> empleado = empleadoRepository.findById(id);
        return empleado.orElse(null); // Devuelve null si no se encuentra el empleado
    }

    // Eliminar un empleado por su ID
    public void deleteEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}
