package com.api.show_finder.api.request;

import feign.form.FormProperty;

public class LoginRequest {
        @FormProperty("grant_type")
        private String grantType;
        @FormProperty("client_id")
        private String clientId;
        @FormProperty("client_secret")
        private String clientSecret;

        public LoginRequest(String grandType, String clientId, String clientSecret) {
                this.grantType = grandType;
                this.clientId = clientId;
                this.clientSecret = clientSecret;
        }

}
