package ru.booking.event_booking_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.booking.event_booking_service.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PatchMapping("/set/{id}")
    public ResponseEntity<String> setAdminById(@PathVariable("id") Long id) {
        adminService.setAdminRoleById(id);
        return new ResponseEntity<>("Admin was setted", HttpStatus.ACCEPTED);
    }

}
