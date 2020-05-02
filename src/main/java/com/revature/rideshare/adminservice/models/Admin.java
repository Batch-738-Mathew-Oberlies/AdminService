package com.revature.rideshare.adminservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Component
@Entity
@Table(name = "admins")
@NoArgsConstructor
@Data
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private int adminId;

	@NotBlank(message = "Username cannot be blank.")
	@Column(name = "user_name")
	@Size(min = 3, max = 12, message = "Number of characters must be between 3 and 12.")
	@Pattern(regexp = "^\\w+\\.?\\w+$", message = "Username format is incorrect.")
	private String userName;

	public Admin(int adminId, String userName) {
		super();
		this.adminId = adminId;
		this.userName = userName;
	}

	public Admin(AdminDTO adminDTO) {
		super();
		if (adminDTO != null) {
			this.adminId = adminDTO.getAdminId();
			this.userName = adminDTO.getUserName();
		}
	}
}