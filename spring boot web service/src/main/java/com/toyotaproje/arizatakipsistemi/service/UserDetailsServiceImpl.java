package com.toyotaproje.arizatakipsistemi.service;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toyotaproje.arizatakipsistemi.model.Personel;
import com.toyotaproje.arizatakipsistemi.dao.PersonelRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private PersonelRepository personelRepository;

    public UserDetailsServiceImpl(PersonelRepository personelRepository) {
        this.personelRepository = personelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Personel personel = personelRepository.findByUsername(username);
        if (personel == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(personel.getUsername(), personel.getPassword(), emptyList());
    }
}
//Bu layer mantık olarak Read/Write işlemlerini yapan DeveloperRepository sınıfıyla arasında kalan bir Data Access Object (DAO)
//Bu layer ile dataya ulaşabiliyorum