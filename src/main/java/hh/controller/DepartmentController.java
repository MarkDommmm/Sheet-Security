package hh.controller;

import hh.exception.CustomsException;
import hh.model.dto.request.DepartmentRequest;
import hh.model.entity.Department;
import hh.service.iml.DepartmentService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;
    @GetMapping("/get-all")
    public String getHome(Model model) {
        model.addAttribute("department", departmentService.getAll());
        return "/department/ListDepartment";
    }

    @GetMapping("/add-department")
    public ModelAndView addEmployee() {
        return new ModelAndView("/department/AddDepartment", "department", new Department());
    }

    @PostMapping("/add-department")
    public String saveEmployee(@ModelAttribute("department") DepartmentRequest departmentRequest)  {
        departmentService.save(departmentRequest);
        return "redirect:/department/get-all";
    }

    @GetMapping("/delete-department/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) throws CustomsException {
        departmentService.remove(id);
        return "redirect:/department/get-all";
    }
    @GetMapping("/update-department/{id}")
    public ModelAndView updateUser(@PathVariable("id") Long id, Model model) throws CustomsException {
        model.addAttribute("department", departmentService.getAll());
        return new ModelAndView("/department/UpdateDepartment", "DepartmentUpdate", departmentService.findById(id));
    }

    @PostMapping("/update-department{id}")
    public String updateUser(@ModelAttribute("UserUpdate") DepartmentRequest departmentRequest) throws CustomsException {
        departmentService.update(departmentRequest,departmentRequest.getId());
        return "redirect:/department/get-all";
    }
}
