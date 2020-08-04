package com.mary.blog.securityex01.config.oauth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.mary.blog.securityex01.config.oauth.provider.FacebookUserInfo;
import com.mary.blog.securityex01.config.oauth.provider.GoogleUserInfo;
import com.mary.blog.securityex01.config.oauth.provider.OAuth2UserInfo;
import com.mary.blog.securityex01.model.User;
import com.mary.blog.securityex01.repository.UserRepository;


@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User=super.loadUser(userRequest); //회원 프로필 조회
		//OAuth2User 정보를 어디에 담아서 무엇을 리턴하면 될까?
		//1. PrincipalDetails에 OAuth2User 정보 넣기
		//2. PrincipalDetails리턴
		System.out.println("oAuth2User : "+oAuth2User); //토큰을 통해 응답 받은 회원 정보
		System.out.println("userRequest : "+userRequest.getAccessToken().getTokenValue());
		System.out.println(userRequest.getClientRegistration());
		try {
			
		} catch (Exception e) {
			
		}
		
		return super.loadUser(userRequest);
	}
	
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		//일반적으로 로그인 할 때 유저정보 User
		//1. OAuth2로 로그인 할 때 유저정보 attributes <- 이거 구성
		//2. DB에 존재여부 확인
		//3. 있으면? update, 없으면? Insert
		//리턴을 PrincipalDetails();
		OAuth2UserInfo oAuth2UserInfo=null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			oAuth2UserInfo=new GoogleUserInfo(oAuth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			oAuth2UserInfo=new FacebookUserInfo(oAuth2User.getAttributes());
		}else {
			System.out.println("우리는 구글과 페이스북만 지원해요.");
		}
		
		System.out.println("oAuth2UserInfo.getProvider"+oAuth2UserInfo);
		
		Optional<User> userOptional=userRepository.findByEmail(oAuth2UserInfo.getEmail());
		
		return oAuth2User;
	}
}
