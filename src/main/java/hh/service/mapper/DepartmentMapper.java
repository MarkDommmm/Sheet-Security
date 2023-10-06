package hh.service.mapper;

import hh.model.dto.request.DepartmentRequest;
import hh.model.dto.response.DepartmentResponse;
import hh.model.entity.Department;
import hh.service.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper implements IGenericMapper<Department, DepartmentRequest, DepartmentResponse> {
    @Override
    public Department toEntity(DepartmentRequest departmentRequest) {
        return Department.builder()
                .name(departmentRequest.getName())
                .description(departmentRequest.getDescription())
                .build();
    }

    @Override
    public DepartmentResponse toResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }
}
