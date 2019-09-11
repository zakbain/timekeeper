package com.zakbain.vacationer.util;

import com.zakbain.vacationer.model.Employee;
import com.zakbain.vacationer.model.TaskLog;
import com.zakbain.vacationer.repository.EmployeeRepository;
import com.zakbain.vacationer.repository.TaskLogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class TaskLogFilter {
    public Iterable<TaskLog> getTasksForUser(EmployeeRepository employeeRepository, TaskLogRepository taskLogRepository) {
        Integer employeeId = getLoggedInEmployeeId(employeeRepository);
        if (employeeId.intValue() > 0) {
            return taskLogRepository.findByEmployeeId(employeeId);
        } else {
            return taskLogRepository.findAll();
        }
    }

    public Integer getLoggedInEmployeeId(EmployeeRepository employeeRepository) {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        if (employeeRepository.findByUsername(username).iterator().hasNext()) {
            Employee employee = employeeRepository.findByUsername(username).iterator().next();
            return employee.getId();
        } else {
            return -1;
        }

    }

}
