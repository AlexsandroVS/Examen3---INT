package com.example.evaluacion3.services;

import com.example.evaluacion3.models.BEmpleado;
import com.example.evaluacion3.repositories.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEmpleado() {
        // Arrange
        BEmpleado empleado = new BEmpleado(1L, "Juan", "IT", 40, 5000.0); // Cambié horasTrabajadas a 40 y ocupación a "IT"
        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        // Act
        BEmpleado result = empleadoService.saveEmpleado(empleado);

        // Assert
        assertNotNull(result);
        assertEquals(empleado.getId(), result.getId());
        verify(empleadoRepository, times(1)).save(empleado);
    }

    @Test
    void findAll() {
        // Arrange
        BEmpleado empleado1 = new BEmpleado(1L, "Juan", "IT", 40, 5000.0);
        BEmpleado empleado2 = new BEmpleado(2L, "Ana", "HR", 40, 6000.0);
        List<BEmpleado> empleados = Arrays.asList(empleado1, empleado2);
        when(empleadoRepository.findAll()).thenReturn(empleados);

        // Act
        List<BEmpleado> result = empleadoService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void findById_found() {
        // Arrange
        BEmpleado empleado = new BEmpleado(1L, "Juan", "IT", 40, 5000.0);
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        // Act
        BEmpleado result = empleadoService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(empleado.getId(), result.getId());
        verify(empleadoRepository, times(1)).findById(1L);
    }

    @Test
    void findById_notFound() {
        // Arrange
        when(empleadoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        BEmpleado result = empleadoService.findById(1L);

        // Assert
        assertNull(result);
        verify(empleadoRepository, times(1)).findById(1L);
    }

    @Test
    void deleteEmpleado() {
        // Arrange
        Long id = 1L;

        // Act
        empleadoService.deleteEmpleado(id);

        // Assert
        verify(empleadoRepository, times(1)).deleteById(id);
    }
}
