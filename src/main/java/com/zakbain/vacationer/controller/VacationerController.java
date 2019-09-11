package com.zakbain.vacationer.controller;

import com.zakbain.vacationer.model.Employee;
import com.zakbain.vacationer.repository.EmployeeRepository;
import com.zakbain.vacationer.repository.TaskLogRepository;
import com.zakbain.vacationer.util.TaskLogFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VacationerController {
    private final TaskLogRepository taskLogRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public VacationerController(TaskLogRepository taskLogRepository, EmployeeRepository employeeRepository) {
        this.taskLogRepository = taskLogRepository;
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping({"/index", "/"})
    public String index(Model model) {

        model.addAttribute("taskLogs", new TaskLogFilter().getTasksForUser(employeeRepository, taskLogRepository));
        return "index";


    }

//    @RequestMapping("/indexCard")
//    public String indexCard(Model model) {
//        model.addAttribute("taskLog", eventRepository.findAll());
//        return "indexCard";
//    }
}

