package hh.service.iml;

import hh.Repository.IEmployeeRepo;

import hh.model.dto.request.EmployeeRequest;
import hh.model.dto.response.EmployeeResponse;
import hh.model.entity.Employee;
import hh.exception.CustomsException;

import hh.service.IGenericService;
import hh.service.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IEmployeeServiceIml implements IGenericService<EmployeeResponse, EmployeeRequest, Long> {

    private final IEmployeeRepo iEmployeeRepo;

    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    public Page<EmployeeResponse> findAll(int page, int size) {
        Page<Employee> employeeResponses = iEmployeeRepo.findAll(PageRequest.of(page, size));
        return employeeResponses.map(employeeMapper::toResponse);
    }

    @Override
    public Page<EmployeeResponse> getAll(Pageable pageable, String employeeName) {
        if (StringUtils.hasText(employeeName)) {
            return iEmployeeRepo.findAllByNameContaining(employeeName, pageable)
                    .map(employeeMapper::toResponse);
        } else {
            return iEmployeeRepo.findAll(pageable)
                    .map(employeeMapper::toResponse);
        }
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return iEmployeeRepo.findAll().stream()
                .map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void save(EmployeeRequest employeeRequest) throws CustomsException {
        employeeRequest.setRole(Collections.singleton(2L));
        employeeRequest.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        employeeMapper.toResponse(iEmployeeRepo.save(employeeMapper.toEntity(employeeRequest)));
    }

    @Override
    public EmployeeResponse update(EmployeeRequest employeeRequest, Long aLong) throws CustomsException {
        Optional<Employee> check = iEmployeeRepo.findById(aLong);
        if (check.isPresent()) {
            Employee em = employeeMapper.toEntity(employeeRequest);
            em.setId(aLong);
            return employeeMapper.toResponse(iEmployeeRepo.save(em));
        }
        throw new CustomsException("Employee not found");
    }

    @Override
    public EmployeeResponse findById(Long aLong) throws CustomsException {
        Optional<Employee> b = iEmployeeRepo.findById(aLong);
        if (b.isPresent()) {
            return employeeMapper.toResponse(b.get());
        }
        throw new CustomsException("Employee not found");

    }

    @Override
    public EmployeeResponse remove(Long aLong) throws CustomsException {
        Optional<Employee> brand = iEmployeeRepo.findById(aLong);
        if (brand.isPresent()) {
            iEmployeeRepo.deleteById(aLong);
            return employeeMapper.toResponse(brand.get());
        }
        throw new CustomsException("Employee not found");
    }

    public Optional<Employee> findByUsername(String username) {
        return iEmployeeRepo.findByUsername(username);
    }


}
