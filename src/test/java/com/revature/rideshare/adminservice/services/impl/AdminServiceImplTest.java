package com.revature.rideshare.adminservice.services.impl;

import com.revature.rideshare.adminservice.models.Admin;
import com.revature.rideshare.adminservice.repositories.AdminRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AdminServiceImplTest {
    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminRepository adminRepository;

    @Test
    public void testGettingAdmins() {
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin());
        admins.add(new Admin());

        when(adminRepository.findAll()).thenReturn(admins);

        assertEquals(2, adminService.getAdmins().size());
    }

    @Test
    public void testGettingAdminById() {
        Admin expected = new Admin(1, "username");

        when(adminRepository.findById(1)).thenReturn(Optional.of(expected));

        Optional<Admin> actual = adminService.getAdminById(1);
        if (actual.isPresent()) assertEquals(expected, actual.get());
        else fail();
    }

    @Test
    public void testCreatingAdmin() {
        Admin expected = new Admin(1, "username");

        when(adminRepository.save(expected)).thenReturn(expected);

        Admin actual = adminService.createAdmin(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdatingAdmin() {
        Admin expected = new Admin(1, "username");

        when(adminRepository.save(expected)).thenReturn(expected);

        Admin actual = adminService.updateAdmin(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeletingAdmin() {
        String expected = "Admin with id: 1 was deleted.";

        when(adminRepository.existsById(1)).thenReturn(true);

        String actual = adminService.deleteAdminById(1);
        assertEquals(expected, actual);
    }
}
