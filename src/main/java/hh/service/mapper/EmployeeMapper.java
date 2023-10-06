package hh.service.mapper;

import hh.Repository.IDepartmentRepo;
import hh.Repository.IRoleRepo;
import hh.exception.CustomsException;
import hh.model.dto.request.EmployeeRequest;
import hh.model.dto.response.EmployeeResponse;
import hh.model.entity.Department;
import hh.model.entity.Employee;
import hh.model.entity.Role;
import hh.service.IGenericMapper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
public class EmployeeMapper implements IGenericMapper<Employee, EmployeeRequest, EmployeeResponse> {

    private final IDepartmentRepo department;

    private final IRoleRepo roleRepo;

    @Override
    public Employee toEntity(EmployeeRequest employeeRequest) throws CustomsException {
//        Optional<Department> d = department.findById(employeeRequest.getDepartment());
//        if (!d.isPresent()) {
//            throw  new CustomsException("Couldn't find department'");
//        }
        Set<Role> roleList = roleRepo.findAllByIdIn(employeeRequest.getRole());
        if (roleList.isEmpty()) {
            throw  new CustomsException("Couldn't find role list");
        }
        return Employee.builder()
//                .id(employeeRequest.getId())
                .name(employeeRequest.getName())
                .email(employeeRequest.getEmail())
//                .department(d.get())
                .username(employeeRequest.getUsername())
                .password(employeeRequest.getPassword())
                .role(roleList)
                .status(employeeRequest.isStatus())
                .build();
    }

    @Override
    public EmployeeResponse toResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .username(employee.getUsername())
                .department(employee.getDepartment())
                .password(employee.getPassword())
                .role(employee.getRole())
                .status(employee.isStatus())
                .build();
    }
}
