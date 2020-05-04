package com.revature.rideshare.adminservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rideshare.adminservice.models.Admin;
import com.revature.rideshare.adminservice.services.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdminService adminService;

    @Test
    public void testGettingAdmins() throws Exception {
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin());
        admins.add(new Admin());

        when(adminService.getAdmins()).thenReturn(admins);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGettingAdminById() throws Exception {
        Admin admin = new Admin(1, "UserName");

        when(adminService.getAdminById(1)).thenReturn(java.util.Optional.of(admin));

        mockMvc.perform(get("/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adminId").value(1));
    }

    @Test
    public void testCreatingAdmin() throws Exception {
        Admin admin = new Admin(1, "userName");

        when(adminService.createAdmin(new Admin(1, "userName"))).thenReturn(admin);

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(admin));
    }

    @Test
    public void testUpdatingAdmin() throws Exception {
        Admin admin = new Admin(1, "userName");

        when(adminService.updateAdmin(new Admin(1, "userName"))).thenReturn(admin);

        mockMvc.perform(put("/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(admin));
    }

    @Test
    public void testDeletingAdmin() throws Exception {
        Admin admin = new Admin(1, "userName");
        String returnedStr = "Admin with id " + admin.getAdminId() + " was deleted";

        when(adminService.deleteAdminById(1)).thenReturn(returnedStr);

        mockMvc.perform(delete("/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(returnedStr));
    }
}
