package com.example.user.Repository;

import com.example.user.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {


    User findByUid(int uid);

    User findByDid(int did);

    Optional<User> getByUid(int uid);
}
