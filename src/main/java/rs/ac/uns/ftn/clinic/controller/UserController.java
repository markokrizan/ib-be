package rs.ac.uns.ftn.clinic.controller;

import rs.ac.uns.ftn.clinic.model.User;
import rs.ac.uns.ftn.clinic.payload.*;
import rs.ac.uns.ftn.clinic.security.UserPrincipal;
import rs.ac.uns.ftn.clinic.service.UserService;
import rs.ac.uns.ftn.clinic.security.CurrentUser;
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

    @GetMapping("/users/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return userService.getUserById(currentUser.getId());
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ALL_USER_READ_PRIVILEGE')")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
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
