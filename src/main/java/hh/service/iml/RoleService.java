package hh.service.iml;

import hh.Repository.IRoleRepo;

import hh.exception.CustomsException;
import hh.model.dto.request.RoleRequest;
import hh.model.dto.response.RoleResponse;

import hh.model.entity.Role;
import hh.service.IGenericService;

import hh.service.mapper.RolesMapper;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleService implements IGenericService<RoleResponse, RoleRequest, Long> {

    private final IRoleRepo roleRepo;

    private final RolesMapper rolesMapper;



    @Override
    public Page<RoleResponse> getAll(Pageable pageable, String search) {
        if (StringUtils.hasText(search)) {
            return roleRepo.findAllByroleNameContaining(search, pageable)
                    .map(rolesMapper::toResponse);
        } else {
            return roleRepo.findAll(pageable)
                    .map(rolesMapper::toResponse);
        }
    }

    @Override
    public List<RoleResponse> getAll() {
        return roleRepo.findAll().stream()
                .map(rolesMapper::toResponse).collect(Collectors.toList());
    }


    @Override
    public void save(RoleRequest roleRequest)  {
        rolesMapper.toResponse(roleRepo.save(rolesMapper.toEntity(roleRequest)));

    }

    @Override
    public RoleResponse update(RoleRequest roleRequest, Long aLong) throws CustomsException {
        Optional<Role> check = roleRepo.findById(aLong);
        if (check.isPresent()) {
            Role brand = rolesMapper.toEntity(roleRequest);
            brand.setId(aLong);
            return rolesMapper.toResponse(roleRepo.save(brand));
        }
        throw new CustomsException("Role not found");
    }

    @Override
    public RoleResponse findById(Long aLong) throws CustomsException {
        Optional<Role> b = roleRepo.findById(aLong);
        if (b.isPresent()) {
            return rolesMapper.toResponse(b.get());
        }
        throw new CustomsException("Role not found");

    }

    @Override
    public RoleResponse remove(Long aLong) throws CustomsException {
        Optional<Role> brand = roleRepo.findById(aLong);
        if (brand.isPresent()) {
            roleRepo.deleteById(aLong);
            return rolesMapper.toResponse(brand.get());
        }
        throw new CustomsException("Role not found");
    }
}
