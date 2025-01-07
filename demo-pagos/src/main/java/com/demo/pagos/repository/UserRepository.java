package com.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.pagos.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by name
     * @param name user name
     * @return user
     */
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByUsername(String name);

}
