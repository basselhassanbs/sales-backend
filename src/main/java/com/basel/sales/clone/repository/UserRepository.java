package com.basel.sales.clone.repository;

import com.basel.sales.clone.model.Client;
import com.basel.sales.clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
