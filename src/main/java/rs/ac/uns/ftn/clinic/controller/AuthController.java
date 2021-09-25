package rs.ac.uns.ftn.clinic.controller;

import java.util.List;

import rs.ac.uns.ftn.clinic.model.User;
import rs.ac.uns.ftn.clinic.model.Role;
import rs.ac.uns.ftn.clinic.payload.ApiResponse;
import rs.ac.uns.ftn.clinic.payload.JwtAuthenticationResponse;
import rs.ac.uns.ftn.clinic.payload.LoginRequest;
import rs.ac.uns.ftn.clinic.payload.SignUpRequest;
import rs.ac.uns.ftn.clinic.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
        @Autowired
        ModelMapper modelMapper;

        @Autowired
        UserService userService;

        @GetMapping("/roles")
        @PreAuthorize("hasRole('ADMIN')")
        public List<Role> getAllRoles() {
                return userService.getAllRoles();
        }

        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
                String jwt = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        }

        @PostMapping("/signup")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
                User user = modelMapper.map(signUpRequest, User.class);

                URI location = userService.registerUser(user);

                return ResponseEntity.created(location).body(new ApiResponse(true, "User created successfully"));
        }
}
