package com.zakbain.vacationer.controller;

import com.zakbain.vacationer.model.TaskLog;
import com.zakbain.vacationer.repository.TaskLogRepository;
import com.zakbain.vacationer.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class TaskLogController {
    private TaskLogRepository taskLogRepository;
    private TaskRepository taskRepository;

    @Autowired
    public TaskLogController(TaskLogRepository taskLogRepository, TaskRepository taskRepository) {
        this.taskLogRepository = taskLogRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/createTaskLog")
    public String createEvent(TaskLog taskLog, Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "createTaskLog";
    }

    @PostMapping("/addTaskLog")
    public String addEvent(@Valid TaskLog taskLog, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "createTaskLog";
        }

        taskLogRepository.save(taskLog);
        model.addAttribute("taskLogs", taskLogRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateEvent(@PathVariable("id") Integer id, Model model) {
        TaskLog taskLog = taskLogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid TaskLog ID:" + id));
        model.addAttribute("taskLog", taskLog);
        return "updateTaskLog";
    }

    @PostMapping("/update/{id}")
    public String updateEvent(@PathVariable("id") Integer id, @Valid TaskLog taskLog, BindingResult result, Model model) {
        if (result.hasErrors()) {
            taskLog.setId(id);
            return "updateTaskLog";
        }

        taskLogRepository.save(taskLog);
        model.addAttribute("taskLogs", taskLogRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") Integer id, Model model) {
        TaskLog taskLog = taskLogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid TaskLog Id:" + id));
        taskLogRepository.delete(taskLog);
        model.addAttribute("taskLogs", taskLogRepository.findAll());
        return "index";
    }

    @GetMapping("/startTimer/{id}")
    public String startTimer(@PathVariable("id") Integer id, Model model) {
        TaskLog taskLog = taskLogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid TaskLog Id:" + id));

        // Set started time to now
        if (taskLog.getStartedOn() == null) {
            taskLog.setStartedOn(new Date());
            taskLogRepository.save(taskLog);
        }
        model.addAttribute("taskLogs", taskLogRepository.findAll());
        return "index";
    }

    @GetMapping("/stopTimer/{id}")
    public String stopTimer(@PathVariable("id") Integer id, Model model) {
        TaskLog taskLog = taskLogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid TaskLog Id:" + id));

        // Set started time to now
        if (taskLog.getTimeSpent() == null) {
            Date currentDate = new Date();
            Date dateStarted = taskLog.getStartedOn();
            int timeElapsed = (int) ((currentDate.getTime() - dateStarted.getTime()) / 1000);
            taskLog.setTimeSpent(timeElapsed);
            taskLogRepository.save(taskLog);
        }
        model.addAttribute("taskLogs", taskLogRepository.findAll());
        return "index";
    }
}
