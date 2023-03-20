package com.org.ridemanage.profilemanagement.repository;

import com.org.ridemanage.profilemanagement.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into profile(id, user_id, updated_date, created_date) values(:id, :user_id, now(), now())", nativeQuery = true)
    int createProfile(@Param("id") String id,
                      @Param("user_id") String userId);

    @Modifying
    @Transactional
    @Query(value = "update profile set " +
            "first_name = coalesce(nullif(:first_name, ''), first_name), " +
            "last_name = coalesce(nullif(:last_name, ''), last_name), " +
            "address = coalesce(nullif(:address, ''), address)," +
            "date_of_birth = coalesce(:date_of_birth, date_of_birth)," +
            "country =  coalesce(nullif(:country, ''), country)," +
            "updated_date = now()," +
            "created_date = now()" +
            "where user_id = :user_id", nativeQuery = true)
    int updateProfile(@Param("user_id") String userId,
                      @Param("first_name") String firstName,
                      @Param("last_name") String lastName,
                      @Param("address") String address,
                      @Param("date_of_birth") Date dateOfBirth,
                      @Param("country") String country);

    ProfileEntity findByUserId(String userId);
}
