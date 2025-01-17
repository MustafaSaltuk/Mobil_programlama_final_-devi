package com.toyotaproje.arizatakipsistemi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toyotaproje.arizatakipsistemi.model.Personel;
import com.toyotaproje.arizatakipsistemi.dao.PersonelRepository;

@RestController
@RequestMapping("/personel")
public class PersonelController {

    @Autowired
    private PersonelRepository personalRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonelController(PersonelRepository developerRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personalRepository = developerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody Personel personel) {
    	if(!personalRepository.existsByUsername(personel.getUsername())) {
    		personel.setPassword(bCryptPasswordEncoder.encode(personel.getPassword()));
            personalRepository.save(personel);
            return ResponseEntity.ok().build();
    	}else {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
}