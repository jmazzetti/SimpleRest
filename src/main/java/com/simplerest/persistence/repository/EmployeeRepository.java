package com.simplerest.persistence.repository;

import com.simplerest.persistence.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{

    Employee findByName(String name);

    @Transactional
    Long deleteByName(String name);

}
