package com.revature.rideshare.adminservice.repositories;

import com.revature.rideshare.adminservice.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> { }
