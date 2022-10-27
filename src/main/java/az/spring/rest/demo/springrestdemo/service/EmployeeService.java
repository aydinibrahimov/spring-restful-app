package az.spring.rest.demo.springrestdemo.service;

import az.spring.rest.demo.springrestdemo.model.Employee;
import az.spring.rest.demo.springrestdemo.rest.model.dto.EmployeeDto;
import az.spring.rest.demo.springrestdemo.rest.model.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse getAllEmployees();
    EmployeeDto getEmployeeById(Long id);
    EmployeeResponse getEmployeeByNameAndSurname(String name,String surname);
    void insert(EmployeeDto employeeDto);

    void update(EmployeeDto employeeDto,Long id);
    void updateSpecific(EmployeeDto employeeDto,Long id);

    void delete(Long id);
}
