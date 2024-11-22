package com.example.evaluacion3.Controllers;

import com.example.evaluacion3.models.BEmpleado;
import com.example.evaluacion3.services.EmpleadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    // Este método obtiene los empleados y los pasa al modelo para ser mostrados en la vista 'empleados.html'
    @GetMapping
    public String mostrarEmpleados(Model model) {
        List<BEmpleado> empleados = empleadoService.findAll();
        model.addAttribute("empleados", empleados);
        System.out.println("Mostrando empleados");
        return "empleados";
    }

    // Este método muestra el formulario para agregar un nuevo empleado
    @GetMapping("/crear")
    public String mostrarFormularioCrearEmpleado(Model model) {
        model.addAttribute("empleado", new BEmpleado());
        return "crearEmpleado";
    }

    // Este método maneja el formulario para guardar un nuevo empleado
    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute BEmpleado empleado) {
        empleadoService.saveEmpleado(empleado);
        return "redirect:/empleados";
    }

    // Este método muestra los detalles de un empleado específico
    @GetMapping("/detalles/{id}") // Cambié la URL a /detalles/{id}
    public String verEmpleado(@PathVariable Long id, Model model) {
        BEmpleado empleado = empleadoService.findById(id);
        model.addAttribute("empleado", empleado);
        return "verEmpleado";
    }

    // Este método muestra el formulario para editar un empleado
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarEmpleado(@PathVariable Long id, Model model) {
        BEmpleado empleado = empleadoService.findById(id);
        model.addAttribute("empleado", empleado);
        return "editarEmpleado";
    }

    // Este método maneja el formulario para actualizar un empleado
    @PostMapping("/editar/{id}")
    public String actualizarEmpleado(@PathVariable Long id, @ModelAttribute BEmpleado empleado) {
        empleado.setId(id);
        empleadoService.saveEmpleado(empleado);
        return "redirect:/empleados";
    }

    // Este método maneja la eliminación de un empleado
    @PostMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return "redirect:/empleados";
    }
}
