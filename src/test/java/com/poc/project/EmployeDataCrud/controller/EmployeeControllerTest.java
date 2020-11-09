package com.poc.project.EmployeDataCrud.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import com.poc.project.EmployeDataCrud.model.Employee;
import com.poc.project.EmployeDataCrud.repository.EmployeeRepository;
import com.poc.project.EmployeDataCrud.service.EmployeeService;
import com.poc.project.EmployeDataCrud.util.Util;

public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@MockBean
	Employee employee;
	@Mock
	EmployeeService employeeService;
	@Mock
	EmployeeRepository employeeRepository;

	@Test
	public void getAllEmployeeTest() {

		List<Employee> employee = new ArrayList<Employee>();
		employee.add(new Employee("smita", "devi", "s.d@gmail.com"));
		Mockito.when(employeeService.getAllEmployee()).thenReturn(employee);
		Mockito.when(employeeRepository.findAll()).thenReturn(employee);
		ReflectionTestUtils.invokeMethod(employeeController, EmployeeController.class, "getAllEmployee");
	}

	@Test
	public void saveEmployeeTest() {

		int id=0;
		employeeService.saveOrUpdate(employee);
		employee.setEmailId("s.d@gmail,com");
		employee.setFirstName("Smita");
		employee.setLastName("Devi");
		employee.setId(2);
		ReflectionTestUtils.invokeMethod(EmployeeController.class, "saveEmployee", id);
	}
	@Test
	public void getEmployeeTest() {
		int employeeId = 1;
		employeeService.getEmployeeById(employeeId);
		ReflectionTestUtils.invokeMethod(EmployeeController.class, "getEmployee", employeeId);
	}

	@Test
	public void updateTest() {

		String email = "smita.s@gmail.com";
		Util.isValidEmail(email);	
		employeeService.saveOrUpdate(employee);
		employeeRepository.save(employee);
		employee.setEmailId("s.d@gmail,com");
		employee.setFirstName("Smita");
		employee.setLastName("Devi");
		employee.setId(2);
		ReflectionTestUtils.invokeMethod(EmployeeController.class, "update", employee);
	}
	
	@Test
	public void deleteEmployeeTest() {

		int employeeId = 1;
		employeeService.delete(employeeId);
		employeeRepository.deleteById(employeeId);
		ReflectionTestUtils.invokeMethod(EmployeeController.class, "deleteEmployee", employeeId);
	}
	

}
