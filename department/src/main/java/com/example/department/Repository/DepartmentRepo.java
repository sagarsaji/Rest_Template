package com.example.department.Repository;

import com.example.department.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {

    Department findByDid(int did);

    void deleteByDid(int did);

    Optional<Department> getByDid(int did);
}
