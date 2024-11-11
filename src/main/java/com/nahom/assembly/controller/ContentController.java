package com.nahom.assembly.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nahom.assembly.config.MyUserDetailService;
import com.nahom.assembly.model.Sherholderdetail;
import com.nahom.assembly.repo.ShareholderRepo;
import com.nahom.assembly.webtoken.JwtService;
import com.nahom.assembly.webtoken.LoginForm;




// @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class ContentController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private ShareholderRepo sharerepo;
       

    @GetMapping("/home")
    public String handleWelcome() {
        return "Welcome to home!";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return "Welcome to ADMIN home!";
    }

    @GetMapping("/admin/phone/{phone}")
    public List<Sherholderdetail> searchphone(@PathVariable String phone) {
        return sharerepo.findByPhoneStartsWith(phone);
    }

    @GetMapping("/admin/name/{name}")
    public List<Sherholderdetail> searchname(@PathVariable String name) {
        return sharerepo.findByNameengStartsWith(name);
    }
// @GetMapping("/admin/getperson/{id}")
// public String getuserdata(@PathVariable Long id) {
    
//     return sharerepo.findAllById(id);
// }

@GetMapping("/admin/person/{id}")
public Optional<Sherholderdetail> getSherholderdetail( @PathVariable Long id){

    
    return sharerepo.findById(id); 
}


    @PostMapping("/admin/attendance/{id}")
    public  ResponseEntity<Sherholderdetail> updateAttendance( @PathVariable Long id) {
        //TODO: process POST request
       Optional<Sherholderdetail> temp= sharerepo.findById(id);
        
           if (temp.isPresent()) {
        // Update the attendance value, for example, to mark attendance
        Sherholderdetail shareholder = temp.get();
        shareholder.setAttendance(1); // Set attendance to 1 or any desired value

        // Save the updated entity back to the repository
        sharerepo.save(shareholder);

        // Return the updated entity
        return ResponseEntity.ok(shareholder);
    } else {
        // If the entity is not found, return a 404 response
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    
    }

    @PostMapping("/admin/attendance0/{id}")
    public  ResponseEntity<Sherholderdetail> updateAttendance0( @PathVariable Long id) {
        //TODO: process POST request
       Optional<Sherholderdetail> temp= sharerepo.findById(id);
        
           if (temp.isPresent()) {
        // Update the attendance value, for example, to mark attendance
        Sherholderdetail shareholder = temp.get();
        shareholder.setAttendance(0); // Set attendance to 1 or any desired value

        // Save the updated entity back to the repository
        sharerepo.save(shareholder);

        // Return the updated entity
        return ResponseEntity.ok(shareholder);
    } else {
        // If the entity is not found, return a 404 response
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


        
    }
    







    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(), loginForm.password()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(myUserDetailService.loadUserByUsername(loginForm.username()));
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}

