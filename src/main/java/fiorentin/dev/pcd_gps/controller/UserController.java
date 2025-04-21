package fiorentin.dev.pcd_gps.controller;

import fiorentin.dev.pcd_gps.controller.dto.LocationDTO;
import fiorentin.dev.pcd_gps.controller.dto.UserDTO;
import fiorentin.dev.pcd_gps.entity.User;
import fiorentin.dev.pcd_gps.repository.UserClassRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Getter
@Setter

@CrossOrigin(origins = "http://localhost:5173/  ")
@RestController
@RequestMapping("/")

public class UserController {


    private final UserClassRepository repository;



    // Rota de listar ususarios
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = repository.findAll();
        return ResponseEntity.ok(users);
    }

    // Rota de criar usuario
    @PostMapping("/new-user")
    public ResponseEntity<User> newUser(@RequestBody UserDTO body) {
        repository.save(new User(body.firstName(), body.lastName(), body.age(), body.pcd()));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editUser(@PathVariable Long id, @RequestBody UserDTO body) {
        User user = new User(body.firstName(), body.lastName(), body.age(), body.pcd());
        user.setId(id);
        repository.save(user);
        return ResponseEntity.ok().build();
    }

    // Rota de deletar usuario por ID
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Rota para listar apenas usuarios PCD
    @GetMapping("/pcd")
    public ResponseEntity<List<User>> getPcdUsers() {
        List<User> pcdUsers = repository.findByPcdTrue();
        return ResponseEntity.ok(pcdUsers);
    }










}