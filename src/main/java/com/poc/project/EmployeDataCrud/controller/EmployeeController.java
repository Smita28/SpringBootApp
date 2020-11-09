package com.poc.project.EmployeDataCrud.controller;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.project.EmployeDataCrud.Exception.ErrorResponse;
import com.poc.project.EmployeDataCrud.Exception.InvalidInputException;
import com.poc.project.EmployeDataCrud.model.Employee;
import com.poc.project.EmployeDataCrud.service.EmployeeService;
import com.poc.project.EmployeDataCrud.util.Util;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	private static final Logger logger    = Logger.getLogger(EmployeeController.class.getName());
	
	@GetMapping("/employee")
	private ResponseEntity<List<Employee>> getAllEmployee() {
		ResponseEntity<List<Employee>> response = null;
		logger.info("Inside getAllEmployee method ");
		try {
			response = new ResponseEntity<List<Employee>>(employeeService.getAllEmployee(),HttpStatus.OK);
		}catch(Exception e){
			logger.error("System Error:",e);
			response = new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping("/employee/{employeeId}")
	private Employee getEmployee(@PathVariable("employeeId") int employeeId) {
		logger.info("Inside getEmployee method ");
		return employeeService.getEmployeeById(employeeId);
	}

	@PostMapping("/employee")
	private ResponseEntity<Object> saveEmployee(@RequestBody Employee employee) {
		ResponseEntity<Object> response = null;
		logger.info("Inside saveEmployee method ");
		try{
			if(Util.isValidEmail(employee.getEmailId())){
				employeeService.saveOrUpdate(employee);
				response = new ResponseEntity<>(employee.getId(),HttpStatus.OK);
			}else{
				 throw new InvalidInputException("Email Id is not valid");
			}
			
		}catch(InvalidInputException ie){
			logger.error("Invalid Input:",ie);
			List<String> details = new ArrayList<>();
	        details.add(ie.getLocalizedMessage());
			ErrorResponse error = new ErrorResponse("Invalid Input", details);
			response = new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
			
		}catch(Exception e){
			logger.error("System Error:",e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@PutMapping("/employee")
	private ResponseEntity<Employee> update(@RequestBody Employee employee) {
		ResponseEntity<Employee> response = null;
		logger.info("Inside update method ");
		if(Util.isValidEmail(employee.getEmailId())){
			employeeService.saveOrUpdate(employee);
			response = new ResponseEntity<>(employee,HttpStatus.OK);
		}else{
			 throw new InvalidInputException("Email Id is not valid");
		}
		return response;
	}

	@DeleteMapping("/employee/{employeeId}")
	private void deleteEmployee(@PathVariable("employeeId") int employeeId) {
		logger.info("Inside deleteEmployee method ");
		employeeService.delete(employeeId);
	}
}
