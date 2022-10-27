package az.spring.rest.demo.springrestdemo.controller;

import az.spring.rest.demo.springrestdemo.rest.model.dto.EmployeeDto;
import az.spring.rest.demo.springrestdemo.rest.model.response.EmployeeResponse;
import az.spring.rest.demo.springrestdemo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Scanner;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "Employee services", description = "all employee services")

public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping
    public EmployeeResponse getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{employee-id}")
    @Operation(summary = "this gets employee by id")

    public EmployeeDto getEmployee(@PathVariable("employee-id") Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }
    @GetMapping("/search")
    public EmployeeResponse getEmployeeDtoByNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname){
        return employeeService.getEmployeeByNameAndSurname(name,surname);   }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid EmployeeDto employeeDto){
        employeeService.insert(employeeDto);    }
    @PutMapping("/  {id}")
    public  void update(@RequestBody EmployeeDto employeeDto, @PathVariable("id")Long id){
        employeeService.update(employeeDto,id);     }
    @PatchMapping("/{id}")
    public  void updatepSecific(@RequestBody EmployeeDto employeeDto, @PathVariable("id") Long id){
        employeeService.updateSpecific(employeeDto,id); }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void delete(@PathVariable("id") Long id){
        employeeService.delete(id);
    }
}
