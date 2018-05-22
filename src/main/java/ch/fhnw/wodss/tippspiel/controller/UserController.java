package ch.fhnw.wodss.tippspiel.controller;

import ch.fhnw.wodss.tippspiel.domain.User;
import ch.fhnw.wodss.tippspiel.dto.BetDTO;
import ch.fhnw.wodss.tippspiel.dto.RestUserDTO;
import ch.fhnw.wodss.tippspiel.dto.UserDTO;
import ch.fhnw.wodss.tippspiel.dto.UserRankingDTO;
import ch.fhnw.wodss.tippspiel.service.BetService;
import ch.fhnw.wodss.tippspiel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final BetService betService;

    @Autowired
    public UserController(UserService service, BetService betService) {
        this.service = service;
        this.betService = betService;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/self", method = RequestMethod.GET)
    public ResponseEntity<User> getLoggedInUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/ranking", produces = "application/json")
    public ResponseEntity<List<UserRankingDTO>> getAllUsersForRanking() {
        return new ResponseEntity<>(service.getAllUsersForRanking(), HttpStatus.OK);
    }

    @Cacheable(value = "users", key = "#id", unless = "#result.statusCode != 200")
    @GetMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = service.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}", produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String name) {
        UserDTO user = service.getUserByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody RestUserDTO restUserDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO newUser = service.addUser(restUserDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/email", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDTO> updateUserEmail(@Valid @RequestBody RestUserDTO restUserDTO, @AuthenticationPrincipal User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO newUser = service.changeEmail(user, restUserDTO);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PutMapping(value = "/passwordReset")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> resetUserPassword(@AuthenticationPrincipal User user) {
        service.resetPassword(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/passwordChange", consumes = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> changeUserPassword(@RequestParam("old") String oldPassword, @RequestParam("new") String newPassword, @AuthenticationPrincipal User user, BindingResult result) {
        service.changePassword(user, oldPassword, newPassword);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @CacheEvict(value = "users", key = "#id")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id, @AuthenticationPrincipal User user) {
        service.deleteUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/bets", produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BetDTO>> getBetsForUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(betService.getBetsForUser(user), HttpStatus.OK);
    }

}
