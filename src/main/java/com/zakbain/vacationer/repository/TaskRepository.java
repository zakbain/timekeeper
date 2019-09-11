package com.zakbain.vacationer.repository;

import com.zakbain.vacationer.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
