package com.asutosh.prod_ready_features.prod_ready_features.clients.impl;

import com.asutosh.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.asutosh.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.asutosh.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.asutosh.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    Logger log = LoggerFactory.getLogger (EmployeeClientImpl.class);

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees () {
        log.trace ("trying to retrieve all employees in getAllEmployees");
        try {
            log.info ("attempting to call the restClient method in getAllEmployees");
            ApiResponse<List<EmployeeDTO>> employeeDtoList = restClient.get ()
                                                                       .uri ("employees")
                                                                       .retrieve ()
                                                                       .onStatus (HttpStatusCode::is4xxClientError, ((request, response) -> {
                                                                           log.error ("Error occured : " + new String (response.getBody ().readAllBytes ()));
                                                                           throw new ResourceNotFoundException ("Employee can't be created !");
                                                                       }))
                                                                       .body (new ParameterizedTypeReference<> () {
                                                                       });
            log.debug ("Successfully retrieved the employees in getAllEmployees");
            log.trace ("Retrieved employees list in getAllEmployees : {}, {},{}", employeeDtoList.getData (), "Hello", 5);
            return employeeDtoList.getData ();
        } catch (Exception e) {
            log.error ("Exception occured in getAllEmployees", e);
            throw new RuntimeException (e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById (Long employeeId) {
        log.trace ("Trying to get employee by id in getEmployeeById with id : {}", employeeId);
        try {
            ApiResponse<EmployeeDTO> employeeDto = restClient.get ()
                                                             .uri ("employees/{employeeId}", employeeId)
                                                             .retrieve ()
                                                             .onStatus (HttpStatusCode::is4xxClientError, ((request, response) -> {
                                                                 log.error ("Error occured : " + new String (response.getBody ().readAllBytes ()));
                                                                 throw new ResourceNotFoundException ("Employee can't be Found!");
                                                             }))
                                                             .body (new ParameterizedTypeReference<> () {
                    });
            return employeeDto.getData ();
        } catch (Exception e) {
            log.error ("Exception occured in getAllEmployees", e);
            throw new RuntimeException (e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee (EmployeeDTO employeeDTO) {
        log.trace ("Trying to create Employee with information {}",employeeDTO);
        try {
            ApiResponse<EmployeeDTO> newEmployee = restClient.post ()
                                                             .uri ("employees")
                                                             .body (employeeDTO)
                                                             .retrieve ()
                                                             .onStatus (HttpStatusCode::is4xxClientError, ((request, response) -> {
                                                                 log.debug ("4xxClient error occurred during create new Employee");
                                                                 log.error (new String (response.getBody ().readAllBytes ()));
                                                                 throw new ResourceNotFoundException ("Employee can't be created !");
                                                             }))
                                                             .body (new ParameterizedTypeReference<> () {
                                                             });
            log.trace ("Successfully created a new employee  :{}",newEmployee.getData ());
            return newEmployee.getData ();
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }
}
