package com.mary.blog.securityex01.config.oauth.provider;

public interface OAuth2UserInfo {
     String getProviderId();
     String getName();
	 String getEmail();
	 String getProvider();
}
