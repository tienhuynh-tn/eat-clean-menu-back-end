package com.happy3friends.eatcleanmenubackend.sercurity;

import com.happy3friends.eatcleanmenubackend.entity.UsersEntity;
import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;
import com.happy3friends.eatcleanmenubackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String gmail) {
        Optional<UsersEntity> usersEntity = userRepository.findByGmail(gmail);

        if (!usersEntity.isPresent()) throw new NotFoundException("Users", "Gmail", gmail);

        return CustomUserDetails.create(usersEntity.get());
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        Optional<UsersEntity> usersEntity = userRepository.findById(id);

        if (!usersEntity.isPresent()) throw new NotFoundException("Users", "id", id);

        return CustomUserDetails.create(usersEntity.get());
    }
}
