package com.revature.rideshare.adminservice.services;

import com.revature.rideshare.adminservice.models.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
	List<Admin> getAdmins();

	Optional<Admin> getAdminById(int id);

	Admin createAdmin(Admin admin);

	Admin updateAdmin(Admin admin);

	String deleteAdminById(int id);
}
