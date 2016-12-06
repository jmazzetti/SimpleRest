package com.simplerest.controller;

import com.simplerest.exception.SimpleRestException;
import com.simplerest.persistence.entity.Employee;
import com.simplerest.persistence.repository.EmployeeRepository;
import com.simplerest.protocol.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is a simple implementation of RESTful Methods
 * @link http://www.restapitutorial.com/lessons/httpmethods.html
 * <p>The methods of this class all throw a <tt>Exception</tt>
 *
 * @author  Jose Mazzetti
 * @version   2.0
 */

@RestController
public class SimpleRestController {

    private static Logger log = LogManager.getLogger(SimpleRestController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    private static final String CREATE_EMPLOYEE = "/employee";
    private static final String EMPLOYEE = "/employee/{name}";
    private static final String DELETE_EMPLOYEE = "/employee/{name}";
    private static final String PUT_EMPLOYEE = "/employee";
    private static final String PATCH_EMPLOYEE = "/employee/{id}";



    @PostMapping(value = CREATE_EMPLOYEE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseObject createEmployee(@Valid @RequestBody final CreateRequest request) throws SimpleRestException{
        ResponseObject response = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        Employee employee = new Employee(request.getName(), request.getSurname(),request.getAge(), request.getStartdate());
        employeeRepository.save(employee);
        parameters.put("response", "SUCCESS");
        parameters.put("employee", employee);
        response = ResponseObject.responseSuccess("1", parameters);
        return response;
    }

    @GetMapping(value = EMPLOYEE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseObject getEmployee(@PathVariable("name") final String name) throws SimpleRestException{
        log.info("getting Employee " + name);
        ResponseObject response = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        Employee employee =  employeeRepository.findByName(name);
        parameters.put("response", "SUCCESS");

        if(employee!=null)
            parameters.put("employee", employee);
        else
            parameters.put("description", "Employee not found");

        response = ResponseObject.responseSuccess("1", parameters);

        return response;
    }


    @DeleteMapping(value = DELETE_EMPLOYEE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseObject deleteEmployee(@PathVariable("name") final String name) throws SimpleRestException{
        Map<String, Object> parameters = new HashMap<String, Object>();
        ResponseObject response = null;
        employeeRepository.deleteByName(name);
        parameters.put("response", "SUCCESS");
        parameters.put("description", "Employee deleted.");
        response = ResponseObject.responseSuccess("1", parameters);
        return response;
    }

    //TODO: review me please.
    @PutMapping(value = PUT_EMPLOYEE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseObject putEmployee(@Valid @RequestBody final PutRequest request) throws SimpleRestException{
        Map<String, Object> parameters = new HashMap<String, Object>();
        ResponseObject response = null;
        Employee employee = new Employee(request.getName(), request.getSurname(),request.getAge(), request.getStartdate());
        Employee updateEmployee = employeeRepository.findByName(employee.getName());
        if(updateEmployee!=null){
            employee.setId(updateEmployee.getId());
            employeeRepository.save(employee);
            parameters.put("employee", employee);
        }else{
            parameters.put("description", "Employee not found.");
        }
        parameters.put("response", "SUCCESS");
        response = ResponseObject.responseSuccess("1", parameters);
        return response;
    }

    @PatchMapping(value = PATCH_EMPLOYEE, produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseObject patchEmployee(@Valid @RequestBody final PatchRequest request, @PathVariable("id") final Long id) throws SimpleRestException{
        Map<String, Object> parameters = new HashMap<String, Object>();
        ResponseObject response = null;
        if(employeeRepository.exists(id)){
            Employee pathEmployee = employeeRepository.findByName(request.getName());
            Employee employee = new Employee(request.getName(), request.getSurname(), request.getAge(), request.getStartdate());
            employee.setId(pathEmployee.getId());
            employeeRepository.save(employee);
            parameters.put("employee", employee);
        }else{
            parameters.put("description", "Employee not found");
        }
        parameters.put("response", "SUCCESS");
        response = ResponseObject.responseSuccess("1", parameters);
        return response;
    }

}
