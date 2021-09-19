package rs.ac.uns.ftn.clinic.controller;

import rs.ac.uns.ftn.clinic.model.User;
import rs.ac.uns.ftn.clinic.payload.*;
import rs.ac.uns.ftn.clinic.security.UserPrincipal;
import rs.ac.uns.ftn.clinic.service.UserService;
import rs.ac.uns.ftn.clinic.security.CurrentUser;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users/me")
    @PreAuthorize("isAuthenticated()")
    public User getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return userService.getUserById(currentUser.getId());
    }

    @PutMapping("/users/me")
    public User updateProfile(@Valid @RequestBody ProfileUpdateRequest profileUpdateRequest,
            @CurrentUser UserPrincipal currentUser) {
        User user = userService.getUserById(currentUser.getId());

        modelMapper.map(profileUpdateRequest, user);

        return userService.save(user);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ALL_USER_READ_PRIVILEGE')")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/users/doctors")
    @PreAuthorize("isAuthenticated()")
    public Page<User> getDoctors(Pageable pageable) {
        return userService.getByRoleName("ROLE_DOCTOR", pageable);
    }

    @GetMapping("/users/doctors/{doctorId}")
    @PreAuthorize("isAuthenticated()")
    public User getDoctors(@PathVariable(value = "doctorId") Long doctorId) {
        return userService.getUserById(doctorId);
    }

    @PutMapping("/users")
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public User updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        User user = userService.getUserById(userUpdateRequest.getId());

        modelMapper.map(userUpdateRequest, user);

        return userService.save(user);
    }

    @GetMapping("/users/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return userService.checkUsernameAvailability(username);
    }

    @GetMapping("/users/{username}")
    public User getUserProfile(@PathVariable(value = "username") String username) {
        return userService.getUserByUsername(username);
    }
}
