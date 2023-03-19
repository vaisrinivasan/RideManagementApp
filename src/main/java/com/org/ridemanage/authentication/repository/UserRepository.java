package com.org.ridemanage.authentication.repository;

import com.org.ridemanage.authentication.entity.UserEntity;
import com.org.ridemanage.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into users(id, username, email, password, created_date) values(:id, :username, :email, crypt(:password, gen_salt('bf',8)), :created_date)", nativeQuery = true)
    int createUser(@Param("id") String id,
                   @Param("username") String username,
                   @Param("email") String email,
                   @Param("password") String password,
                   @Param("created_date") Date createdDate);

    @Query(value = "select u.id from users u where u.username = :username and u.password = crypt(:password, u.password)", nativeQuery = true)
    String getUser(@Param("username") String username,
                             @Param("password") String password);

}
