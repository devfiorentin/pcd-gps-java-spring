package fiorentin.dev.pcd_gps.controller;

import fiorentin.dev.pcd_gps.controller.dto.LocationDTO;
import fiorentin.dev.pcd_gps.controller.dto.UserDTO;
import fiorentin.dev.pcd_gps.entity.User;
import fiorentin.dev.pcd_gps.repository.UserClassRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173/  ")
@RestController
@RequestMapping("/")

public class UserController {

    private final UserClassRepository repository;

    public UserController(UserClassRepository repository) {
        this.repository = repository;
    }

    // Rota de listar ususarios
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = repository.findAll();
        return ResponseEntity.ok(users);
    }

    // Rota de criar usuario
    @PostMapping("/new-user")
    public ResponseEntity<User> newUser(@RequestBody UserDTO body) {
        repository.save(new User(body.firstName(), body.lastName(), body.age(), body.pcd(), body.carModel(), body.plate(), body.latitude(), body.longitude()));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editUser(@PathVariable Long id, @RequestBody UserDTO body) {
        User user = new User(body.firstName(), body.lastName(), body.age(), body.pcd(), body.carModel(), body.plate(), body.latitude(), body.longitude());
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


    @PutMapping("/location/{id}")
    public ResponseEntity<User> updateLocation(@PathVariable Long id, @RequestBody LocationDTO body) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setLatitude(body.latitude());
            user.setLongitude(body.longitude());
            repository.save(user);
            return ResponseEntity.ok(user); // <-- agora retorna o objeto atualizado
        }
        return ResponseEntity.notFound().build(); // <-- melhor retornar 404 se não encontrar
    }







}