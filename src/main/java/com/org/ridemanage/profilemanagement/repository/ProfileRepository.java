package com.org.ridemanage.profilemanagement.repository;

import com.org.ridemanage.profilemanagement.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into profile(id, user_id, first_name, last_name, address, date_of_birth, country) values(:id, :user_id, :first_name, :last_name, :address, :date_of_birth, :country)", nativeQuery = true)
    int createProfile(@Param("id") UUID id,
                      @Param("user_id") String userId,
                      @Param("first_name") String firstName,
                      @Param("last_name") String lastName,
                      @Param("address") String address,
                      @Param("date_of_birth") Date dateOfBirth,
                      @Param("country") String country);
}
