package com.asutosh.prod_ready_features.prod_ready_features;

import com.asutosh.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.asutosh.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProdReadyFeaturesApplicationTests {
    @Autowired
    private EmployeeClient employeeClient;

    @Test
    @Order (2)
    void getAllEmployees () {
        List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees ();
        for (EmployeeDTO employeeDTO : employeeDTOList) {
            System.out.println (employeeDTO);
        }
    }

    @Test
    @Order (3)
    void getEmployeeById () {
        EmployeeDTO employeeDTO = employeeClient.getEmployeeById (300L);
        System.out.println (employeeDTO);
    }

    @Test
    @Order (1)
    void createNewEmployee () {
        EmployeeDTO employeeDTO = new EmployeeDTO (null, "Asutosh",
                                                   "connect.asutoshsahoo@gmail.com",
                                                   2, "ADMIN",
                                                   100000D, LocalDate.of (2026,10,1), true);
        EmployeeDTO newEmployee = employeeClient.createNewEmployee (employeeDTO);
        System.out.println (newEmployee);
    }
}

