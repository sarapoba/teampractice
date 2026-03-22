package com.facet.api.config.oauth2;

import com.facet.api.utils.Aes256;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
public class OAuth2AuthorizationRequestRepository
        implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("OAUTH2_REQUEST")) {
                OAuth2AuthorizationRequest oAuth2AuthorizationRequest =
                        (OAuth2AuthorizationRequest) SerializationUtils.deserialize(cookie.getValue().getBytes());

                return oAuth2AuthorizationRequest;
            }
        }

        return null;
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {

//        response.addHeader("Set-Cookie", "");
        Cookie cookie = new Cookie(
                "OAUTH2_REQUEST",
                Aes256.encrypt(SerializationUtils.serialize(authorizationRequest))
        );
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(((int) Duration.ofSeconds(300L).toSeconds()));

        response.addCookie(cookie);

        // 프론트에서 보낸 경로 추출
        String redirectUriAfterLogin = request.getParameter("redirect_uri");
        if (redirectUriAfterLogin != null && !redirectUriAfterLogin.isBlank()) {
            Cookie redirectCookie = new Cookie("REDIRECT_URI", redirectUriAfterLogin);
            redirectCookie.setPath("/");
            redirectCookie.setHttpOnly(true);
            redirectCookie.setSecure(true); // HTTPS 사용 시
            cookie.setMaxAge(((int) Duration.ofSeconds(300L).toSeconds()));  // 5분 유효
            response.addCookie(redirectCookie);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("OAUTH2_REQUEST")) {
                OAuth2AuthorizationRequest oAuth2AuthorizationRequest =
                        (OAuth2AuthorizationRequest) SerializationUtils.deserialize(
                                Aes256.decrypt(cookie.getValue().getBytes(StandardCharsets.UTF_8))
                        );

                cookie.setValue("");
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setMaxAge(((int) Duration.ofSeconds(0L).toSeconds()));
                response.addCookie(cookie);

                return oAuth2AuthorizationRequest;
            }
        }
        return null;
    }
}