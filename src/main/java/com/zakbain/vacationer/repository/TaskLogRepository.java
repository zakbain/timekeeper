package com.zakbain.vacationer.repository;

import com.zakbain.vacationer.model.TaskLog;
import org.springframework.data.repository.CrudRepository;

public interface TaskLogRepository extends CrudRepository<TaskLog, Integer> {
    public Iterable<TaskLog> findByEmployeeId(Integer employeeId);
}
