package com.example.learn.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

//import com.example.learn.entity.LearningGoal;
import com.example.learn.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
   @Query("SELECT u FROM User u")
    public List<User> getAllUsers();

   
    
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (name, email, password, preferredlanguage, profilePicture) VALUES (:name, :email, :password, :preferredlanguage, :profilePicture)", nativeQuery = true)
    void insertUserNative(@Param("name")String name,
      @Param("email")String email,
      @Param("password")String password,
      @Param("preferredlanguage") String preferredlanguage,
      @Param("profilePicture") String profilePicture);
    

}
