package hh.service.iml;

import hh.Repository.IDepartmentRepo;
import hh.exception.CustomsException;
import hh.model.dto.request.DepartmentRequest;
import hh.model.dto.response.DepartmentResponse;
import hh.model.entity.Department;
import hh.service.IGenericService;
import hh.service.mapper.DepartmentMapper;
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
public class DepartmentService implements IGenericService<DepartmentResponse, DepartmentRequest, Long> {

    private final IDepartmentRepo departmentRepo;

    private final DepartmentMapper departmentMapper;

    @Override
    public Page<DepartmentResponse> getAll(Pageable pageable, String search) {
        if (StringUtils.hasText(search)) {
            return departmentRepo.findAllByNameContaining(search, pageable)
                    .map(departmentMapper::toResponse);
        } else {
            return departmentRepo.findAll(pageable)
                    .map(departmentMapper::toResponse);
        }
    }

    @Override
    public List<DepartmentResponse> getAll() {
        return departmentRepo.findAll().stream()
                .map(departmentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void save(DepartmentRequest departmentRequest)  {
        departmentMapper.toResponse(departmentRepo.save(departmentMapper.toEntity(departmentRequest)));

    }

    @Override
    public DepartmentResponse update(DepartmentRequest departmentRequest, Long aLong) throws CustomsException {
        Optional<Department> check = departmentRepo.findById(aLong);
        if (check.isPresent()) {
            Department brand = departmentMapper.toEntity(departmentRequest);
            brand.setId(aLong);
            return departmentMapper.toResponse(departmentRepo.save(brand));
        }
        throw new CustomsException("Department not found");
    }

    @Override
    public DepartmentResponse findById(Long aLong) throws CustomsException {
        Optional<Department> b = departmentRepo.findById(aLong);
        if (b.isPresent()) {
            return departmentMapper.toResponse(b.get());
        }
        throw new CustomsException("Department not found");

    }

    @Override
    public DepartmentResponse remove(Long aLong) throws CustomsException {
        Optional<Department> brand = departmentRepo.findById(aLong);
        if (brand.isPresent()) {
            departmentRepo.deleteById(aLong);
            return departmentMapper.toResponse(brand.get());
        }
        throw new CustomsException("Department not found");
    }
}
