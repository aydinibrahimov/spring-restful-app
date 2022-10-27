package az.spring.rest.demo.springrestdemo.service.impl;

import az.spring.rest.demo.springrestdemo.enums.ErrorCodeEnum;
import az.spring.rest.demo.springrestdemo.exception.CustomNotFoundException;
import az.spring.rest.demo.springrestdemo.model.Employee;
import az.spring.rest.demo.springrestdemo.repository.EmployeeRepository;
import az.spring.rest.demo.springrestdemo.rest.model.dto.EmployeeDto;
import az.spring.rest.demo.springrestdemo.rest.model.response.EmployeeResponse;
import az.spring.rest.demo.springrestdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Override
    public EmployeeResponse getAllEmployees() {
        List<EmployeeDto> employeeDtoList= employeeRepository.findAll()
                .stream()
                .map(employee ->convertToDto(employee))
                .collect(Collectors.toList());
        return makeEmployeeResponse(employeeDtoList);    }
    public  EmployeeDto getEmployeeById(Long employeeId){
        Employee employee=  employeeRepository.findById(employeeId)
                .orElseThrow(()->new CustomNotFoundException(ErrorCodeEnum.EMPLOYEE_NOT_FIND));
        return convertToDto(employee);   }
    @Override
    public EmployeeResponse getEmployeeByNameAndSurname(String name,String surname) {
        List<EmployeeDto> employeeDto= employeeRepository.findByNameAndSurname(name,surname)
                .stream()
                .map(employee -> convertToDto(employee))
                .collect(Collectors.toList());
        return makeEmployeeResponse(employeeDto);  }
    @Override
    public void insert(EmployeeDto employeeDto) {
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        employeeRepository.save(employee);  }
    @Override
    public void update(EmployeeDto employeeDto,Long id) {
        Employee employee =  getEmployee(id);
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setAge(employeeDto.getAge());
        employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);    }
    private Employee getEmployee(Long employeeId){
        return   employeeRepository.findById(employeeId)
                .orElseThrow(()->new CustomNotFoundException(ErrorCodeEnum.EMPLOYEE_NOT_FIND));     }
    @Override
    public void updateSpecific(EmployeeDto employeeDto, Long id) {
        Employee employee=getEmployee(id);
        if(employeeDto.getName()!=null)
            employee.setName(employeeDto.getName());
        if(employeeDto.getSurname()!=null)
            employee.setSurname(employeeDto.getSurname());
        if(employeeDto.getAge()>0)
            employee.setAge(employeeDto.getAge());
        if(employeeDto.getSalary()>0)
            employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);    }
    @Override
    public void delete(Long id) {
        employeeRepository.delete(getEmployee(id)); }
    private EmployeeResponse makeEmployeeResponse(List<EmployeeDto> employeesDto){
        return EmployeeResponse.builder()
                .employees(employeesDto)
                .build();
    }

    private EmployeeDto convertToDto(Employee employee){
//        return  EmployeeDto.builder()
//                .id(employee.getId())
//                .name(employee.getName())
//                .surname(employee.getSurname())
//                .age(employee.getAge())
//                .salary(employee.getSalary())
//                .build();
        EmployeeDto employeeDto=new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        return employeeDto;
    }

}
