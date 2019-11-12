package com.coursera.admin.web.service;

public class SessionInfo {
    final private String session;
    final private String accessToken;
    final private String challengeResult;
    final private String idnetityToken;
    
    public SessionInfo(final String session, final String accessToken, final String challengeResult, final String idnetityToken ) {
        this.session = session;
        this.accessToken = accessToken;
        this.challengeResult = challengeResult;
        this.idnetityToken = idnetityToken;
    }

    public String getSession() {
        return session;
    }

    public String getChallengeResult() {
        return challengeResult;
    }

    public String getAccessToken() {
        return accessToken;
    }

	public String getIdnetityToken() {
		return idnetityToken;
	}
    
   
    
}
