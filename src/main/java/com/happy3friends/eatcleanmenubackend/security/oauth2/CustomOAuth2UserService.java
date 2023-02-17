//package com.happy3friends.eatcleanmenubackend.security.oauth2;
//
//import com.happy3friends.eatcleanmenubackend.constants.AuthProvider;
//import com.happy3friends.eatcleanmenubackend.entity.UsersEntity;
//import com.happy3friends.eatcleanmenubackend.exception.OAuth2AuthenticationProcessingException;
//import com.happy3friends.eatcleanmenubackend.repository.UserRepository;
//import com.happy3friends.eatcleanmenubackend.security.UserPrincipal;
//import com.happy3friends.eatcleanmenubackend.security.oauth2.user.OAuth2UserInfo;
//import com.happy3friends.eatcleanmenubackend.security.oauth2.user.OAuth2UserInfoFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.util.Optional;
//
//@Service
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
//
//        try {
//            return processOAuth2User(oAuth2UserRequest, oAuth2User);
//        } catch (AuthenticationException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
//            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
//        }
//    }
//
//    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
//        OAuth2UserInfo oAuth2UserInfo =
//                OAuth2UserInfoFactory.getOAuth2UserInfo(
//                        oAuth2UserRequest
//                                .getClientRegistration()
//                                .getRegistrationId(),
//                        oAuth2User.getAttributes());
//
//        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
//            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
//        }
//
//        Optional<UsersEntity> userOptional = userRepository.findByGmail(oAuth2UserInfo.getEmail());
//        UsersEntity user;
//        if(userOptional.isPresent()) {
//            user = userOptional.get();
//            if (null != user.getProvider()) {
//                if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
//                    throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
//                            user.getProvider() + " account. Please use your " + user.getProvider() +
//                            " account to login.");
//                }
//            }
//            user = updateExistingUser(user, oAuth2UserInfo);
//        } else {
//            user = registerNewUser(oAuth2UserInfo);
//        }
//
//        return UserPrincipal.create(user, oAuth2User.getAttributes());
//    }
//
//    private UsersEntity registerNewUser(OAuth2UserInfo oAuth2UserInfo) {
//        UsersEntity user = new UsersEntity();
//
//        user.setFullname(oAuth2UserInfo.getName());
//        user.setGmail(oAuth2UserInfo.getEmail());
//        user.setAvatar(oAuth2UserInfo.getImageUrl());
//        return userRepository.save(user);
//    }
//
//    private UsersEntity updateExistingUser(UsersEntity existingUser, OAuth2UserInfo oAuth2UserInfo) {
//        existingUser.setFullname(oAuth2UserInfo.getName());
//        existingUser.setAvatar(oAuth2UserInfo.getImageUrl());
//        return userRepository.save(existingUser);
//    }
//}