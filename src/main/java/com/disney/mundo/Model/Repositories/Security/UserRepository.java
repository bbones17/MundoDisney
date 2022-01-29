package com.disney.mundo.Model.Repositories.Security;


import com.disney.mundo.Model.Entities.Security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIdIgnoreCase(String emailId);
}