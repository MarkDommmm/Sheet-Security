package hh.service.mapper;



import hh.model.dto.request.RoleRequest;

import hh.model.dto.response.RoleResponse;

import hh.model.entity.Role;
import hh.service.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class RolesMapper implements IGenericMapper<Role, RoleRequest, RoleResponse> {


    @Override
    public Role toEntity(RoleRequest roleRequest) {
        return Role.builder()
                .roleName(roleRequest.getRoleName())
                .description(roleRequest.getDescription())
                .build();
    }

    @Override
    public RoleResponse toResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build();
    }
}
