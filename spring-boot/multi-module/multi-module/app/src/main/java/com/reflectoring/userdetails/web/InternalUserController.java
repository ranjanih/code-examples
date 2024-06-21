package com.reflectoring.userdetails.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.reflectoring.userdetails.model.UserData;
import com.reflectoring.userdetails.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/internal")
public class InternalUserController {

    private static final Logger log = LoggerFactory.getLogger(InternalUserController.class);

    private final UserService userService;

    public InternalUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserData>> getAllUsers(@RequestParam(required = false) String loginId) {
        if (Objects.isNull(loginId)) {
            return ResponseEntity.ok().body(userService.getAllUsers(true));
        } else {
            return ResponseEntity.ok().body(List.of(userService.getUser(loginId)));
        }

    }

    @GetMapping("/userdetails/all")
    public ResponseEntity<UserData> getDetailUsers(@RequestParam String loginId) {
        return ResponseEntity.ok().body(userService.getUser(loginId));
    }

    @GetMapping("/userdetails")
    public ResponseEntity<UserData> getUserSummary(@RequestParam String loginId) {
        return ResponseEntity.ok().body(userService.getUser(loginId));
    }


    @PatchMapping("/users")
    public ResponseEntity<UserData> updateAddress(@RequestParam String loginId,
                                                  @RequestBody UserData addressData) {
        return ResponseEntity.ok().body(userService.updateAddress(loginId, addressData));
    }
}