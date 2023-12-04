//package com.project.softunisunset.controller;
//
//import com.project.softunisunset.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RoleController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/assign/{userId}/{role}")
//    public ResponseEntity<String> assignRole(@PathVariable Long userId, @PathVariable String role) {
//        try {
//            userService.assignRole(userId, role);
//            return ResponseEntity.ok("Role assigned successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error assigning role.");
//        }
//    }
//}
