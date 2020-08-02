package com.general.stores.service;

import java.util.Optional;
import com.general.stores.entity.Admin;

public interface AdminService {

	Admin validateAdmin(String email, String password);
	Admin getAdminByEmail(String email);
	public Optional<Admin> getByAdminId(Long id);
	int changePassword(String password, String email);
	void updateAdminByEmail(String name, String email, Long id);
}
