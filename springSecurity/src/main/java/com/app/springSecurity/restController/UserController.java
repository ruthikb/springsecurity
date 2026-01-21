package com.app.springSecurity.restController;

import com.app.springSecurity.dto.UserDto;
import com.app.springSecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserController {

    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        if ( userDto == null) {
            return ResponseEntity.badRequest().body("Invalid user data");
        }
        else {
            service.saveUser(userDto);
        }
        return ResponseEntity.ok("User saved successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        UserDto user = service.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return  ResponseEntity.ok(service.updateUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(service.deleteUser(id));
    }
}
