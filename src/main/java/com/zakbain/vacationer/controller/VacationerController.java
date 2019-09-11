package com.zakbain.vacationer.controller;

import com.zakbain.vacationer.repository.TaskLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VacationerController {
    private final TaskLogRepository taskLogRepository;

    @Autowired
    public VacationerController(TaskLogRepository taskLogRepository) {
        this.taskLogRepository = taskLogRepository;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping({"/index", "/"})
    public String index(Model model) {
        model.addAttribute("taskLogs", taskLogRepository.findAll());
        return "index";
    }

//    @RequestMapping("/indexCard")
//    public String indexCard(Model model) {
//        model.addAttribute("taskLog", eventRepository.findAll());
//        return "indexCard";
//    }
}

