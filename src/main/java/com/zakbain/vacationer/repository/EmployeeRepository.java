package com.zakbain.vacationer.repository;

import com.zakbain.vacationer.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    public Iterable<Employee> findByUsername(String username);
}
