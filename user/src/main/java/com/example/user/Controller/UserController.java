package com.example.user.Controller;

import com.example.user.Model.User;
import com.example.user.Repository.UserRepo;
import com.example.user.VO.Department;
import com.example.user.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo repo;

    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("/save")
    public ResponseEntity<User> submitu(@RequestBody User user)
    {
        return ResponseEntity.ok().body(repo.save(user));
    }
    @GetMapping("/usr")
    public ResponseEntity<List<User>> getall()
    {
        return ResponseEntity.ok().body(repo.findAll());
    }
    @GetMapping("/{uid}")
    public ResponseEntity<ResponseTemplateVO> findUserWithDept(@PathVariable int uid)
    {
        ResponseTemplateVO op=new ResponseTemplateVO();
        User user=repo.findByUid(uid);
        if(user==null)
        {
            return ResponseEntity.notFound().build();
        }
        Department department=restTemplate.getForObject("http://localhost:8081/department/"+user.getDid(), Department.class);
        op.setUser(user);
        op.setDepartment(department);
        return ResponseEntity.ok().body(op);
    }

    @PutMapping("/{did}")
    public ResponseEntity<Department> updateByDid(@PathVariable int did,@RequestBody Department dep){
        restTemplate.put("http://localhost:8081/department/{did}",dep,did,Department.class);
        return ResponseEntity.ok(dep);
    }

    @DeleteMapping("/{did}")
    public ResponseEntity<String> deleteByDid(@PathVariable int did){
        restTemplate.delete("http://localhost:8081/department/{did}",did);
        return ResponseEntity.ok("deleted");
    }
}
