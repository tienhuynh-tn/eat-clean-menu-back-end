//package com.happy3friends.eatcleanmenubackend.security;
//
//import com.happy3friends.eatcleanmenubackend.entity.UsersEntity;
//import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;
//import com.happy3friends.eatcleanmenubackend.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * CustomUserDetailsService: To authenticate a User or perform various role-based checks,
// * Spring security needs to load users details somehow.
// *
// * For this purpose, It consists of an interface called UserDetailsService
// * which has a single method that loads a user based on username-loadUserByUsername
// */
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String email)
//            throws UsernameNotFoundException {
//        UsersEntity user = userRepository.findByGmail(email)
//                .orElseThrow(() ->
//                    new UsernameNotFoundException("User not found with email : " + email)
//                );
//
//        return UserPrincipal.create(user);
//    }
//
//    @Transactional
//    public UserDetails loadUserById(int id) {
//        UsersEntity user = userRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("User", "id", id)
//        );
//
//        return UserPrincipal.create(user);
//    }
//}