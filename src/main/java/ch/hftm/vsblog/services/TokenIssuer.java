package ch.hftm.vsblog.services;

import java.util.Date;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

public class TokenIssuer {

    public static String generateToken(String username) {
        //Valid for 24h!
        JwtClaimsBuilder builder1 = Jwt.claims().issuer("hftm").groups("admin").upn(username).expiresAt((new Date().getTime()/1000) + 86400);
        return builder1.sign();
    }
}