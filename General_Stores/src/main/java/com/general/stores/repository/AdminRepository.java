package com.general.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.general.stores.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	@Query(value = "select a from Admin a where a.email=?1 and a.password=?2")
	Admin checkAdminLogin(String email, String password);

	@Query(value = "select a from Admin a where a.email=?1")
	Admin findAdminByEmail(String email);
	
	@Modifying
    @Query("UPDATE Admin c SET c.password = :password WHERE c.email = :email")
    int changePassword(@Param("password") String password, @Param("email") String email);
	
	@Modifying
    @Query("UPDATE Admin a SET a.name = :name,a.email =:email WHERE a.id = :id")
	void updateAdmin(@Param("name") String name, @Param("email") String email, @Param("id") Long id);
}
