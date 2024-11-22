package com.example.evaluacion3.Controllers;

import com.example.evaluacion3.models.BEmpleado;
import com.example.evaluacion3.services.EmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmpleadoControllerTest {

    @Mock
    private EmpleadoService empleadoService;

    @Mock
    private Model model;

    @InjectMocks
    private EmpleadoController empleadoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMostrarEmpleados() {
        // Arrange
        List<BEmpleado> empleados = Arrays.asList(
                new BEmpleado(1L, "Juan", "Ingeniero", 40, 50.0),
                new BEmpleado(2L, "Ana", "Doctora", 35, 60.0)
        );
        when(empleadoService.findAll()).thenReturn(empleados);

        // Act
        String viewName = empleadoController.mostrarEmpleados(model);

        // Assert
        assertEquals("empleados", viewName);
        verify(model).addAttribute("empleados", empleados);
        verify(empleadoService).findAll();
    }

    @Test
    void testMostrarFormularioCrearEmpleado() {
        // Act
        String viewName = empleadoController.mostrarFormularioCrearEmpleado(model);

        // Assert
        assertEquals("crearEmpleado", viewName);
        verify(model).addAttribute(eq("empleado"), any(BEmpleado.class));
    }

    @Test
    void testGuardarEmpleado() {
        // Arrange
        BEmpleado empleado = new BEmpleado(1L, "Pedro", "Abogado", 20, 40.0);

        // Act
        String viewName = empleadoController.guardarEmpleado(empleado);

        // Assert
        assertEquals("redirect:/empleados", viewName);
        verify(empleadoService).saveEmpleado(empleado);
    }

    @Test
    void testVerEmpleado() {
        // Arrange
        BEmpleado empleado = new BEmpleado(1L, "Luis", "Contador", 30, 50.0);
        when(empleadoService.findById(1L)).thenReturn(empleado);

        // Act
        String viewName = empleadoController.verEmpleado(1L, model);

        // Assert
        assertEquals("verEmpleado", viewName);
        verify(model).addAttribute("empleado", empleado);
        verify(empleadoService).findById(1L);
    }

    @Test
    void testMostrarFormularioEditarEmpleado() {
        // Arrange
        BEmpleado empleado = new BEmpleado(2L, "Laura", "Analista", 40, 45.0);
        when(empleadoService.findById(2L)).thenReturn(empleado);

        // Act
        String viewName = empleadoController.mostrarFormularioEditarEmpleado(2L, model);

        // Assert
        assertEquals("editarEmpleado", viewName);
        verify(model).addAttribute("empleado", empleado);
        verify(empleadoService).findById(2L);
    }

    @Test
    void testActualizarEmpleado() {
        // Arrange
        BEmpleado empleado = new BEmpleado(3L, "María", "Diseñadora", 25, 30.0);

        // Act
        String viewName = empleadoController.actualizarEmpleado(3L, empleado);

        // Assert
        assertEquals("redirect:/empleados", viewName);
        verify(empleadoService).saveEmpleado(empleado);
        assertEquals(3L, empleado.getId());
    }

    @Test
    void testEliminarEmpleado() {
        // Act
        String viewName = empleadoController.eliminarEmpleado(4L);

        // Assert
        assertEquals("redirect:/empleados", viewName);
        verify(empleadoService).deleteEmpleado(4L);
    }
}
