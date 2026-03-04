package medi.vol.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import medi.vol.api.domain.user.AuthData;
import medi.vol.api.domain.user.User;
import medi.vol.api.infra.security.JwtTokenData;
import medi.vol.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity makeLogin(@RequestBody @Valid AuthData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var althentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) althentication.getPrincipal());

        return ResponseEntity.ok(new JwtTokenData(tokenJWT));
    }

}
