package com.example.department.Controller;

import com.example.department.Entity.Department;
import com.example.department.Repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepo repo;

    @PostMapping("/submit")
    public ResponseEntity<Department>submit(@RequestBody Department department){
        return ResponseEntity.ok().body(repo.save(department));
    }
    @GetMapping("/dept")
    public ResponseEntity<List<Department>>getalldept()
    {
        return ResponseEntity.ok().body(repo.findAll());
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Department>> getbyid(@PathVariable int id)
//    {
//
//        return ResponseEntity.ok().body(repo.findById(id));
//
//    }
//
    @GetMapping("/{did}")
    public ResponseEntity<Department>getbyid(@PathVariable int did)
    {
        Department department=repo.findByDid(did);
        if(department==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(department);

    }

    @PutMapping("/{did}")
    public ResponseEntity<Department> updateById(@PathVariable int did,@RequestBody Department dep){
        Department dept = null;
        Optional<Department> departmen = repo.getByDid(did);
        if(departmen.isPresent()){
            dept = departmen.get();
            dept.setDname(dep.getDname());
        }
        return ResponseEntity.ok(repo.save(dept));
    }

    @DeleteMapping("/{did}")
    @Transactional
    public String deleteByDid(@PathVariable int did){
        Department dept = repo.findByDid(did);
        if(dept!=null){
            repo.deleteByDid(did);
        }
        return "deleted";
    }

}
