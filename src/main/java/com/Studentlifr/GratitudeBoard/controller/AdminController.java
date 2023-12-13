package com.Studentlifr.GratitudeBoard.controller;

import com.Studentlifr.GratitudeBoard.dao.UserDAO;
import com.Studentlifr.GratitudeBoard.dto.response.AdminMessagesResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.GenericResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.ResponseDTO;
import com.Studentlifr.GratitudeBoard.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"admin"})
public class AdminController {

    private final AdminServices adminServices;
    @Autowired
    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }
    @GetMapping("messages")
    public GenericResDTO<List<AdminMessagesResDTO>> getAllMessages() {
        return adminServices.getAllMessages() ;
    }
    @DeleteMapping("messages")
    public ResponseDTO deleteMessageById(@RequestParam UUID id) {
        return adminServices.deleteMessageById(id);
    }
    @GetMapping("users")
    public GenericResDTO<List<UserDAO>> getAllUsers() {
        return adminServices.getAllUsers();
    }
    @DeleteMapping("users")
    public ResponseDTO deleteUserById(@RequestParam UUID id) {
        return adminServices.deleteUserById(id);
    }
    @PatchMapping("users/promote")
    public ResponseDTO promoteUserById(@RequestParam UUID id) {
        return adminServices.promoteUserById(id);
    }
    @PatchMapping("users/demote")
    public ResponseDTO demoteUserById(@RequestParam UUID id) {
        return adminServices.demoteUserById(id);
    }
}
