package com.zakbain.vacationer.controller;

import com.zakbain.vacationer.repository.EmployeeRepository;;
import com.zakbain.vacationer.repository.TaskLogRepository;
import com.zakbain.vacationer.util.TaskLogFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.mockito.Mockito.*;

public class VacationerControllerTest {
    @Mock
    private TaskLogRepository mockTaskLogRepository;
    @Mock
    private EmployeeRepository mockEmployeeRepository;
    @Mock
    private Iterable mockTaskLogs;
    @Mock
    private Model mockModel;
    @Mock
    private TaskLogFilter mockTaskLogFilter;
    private VacationerController vacationerController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vacationerController = new VacationerController(mockTaskLogRepository, mockEmployeeRepository);
        vacationerController.setTaskLogFilter(mockTaskLogFilter);
    }

    @Test
    public void getLoginPage() {
        String login = vacationerController.login();
        Assert.assertEquals("login endpoint should return login page name", "login", login);
    }

    @Test
    public void getIndexPageWithTasks() {
        when(mockTaskLogFilter.getTasksForUser(mockEmployeeRepository, mockTaskLogRepository)).thenReturn(mockTaskLogs);
        when(mockTaskLogRepository.findAll()).thenReturn(mockTaskLogs);
        vacationerController.index(mockModel);
        verify(mockModel, times(1)).addAttribute("taskLogs", mockTaskLogs);
    }
}