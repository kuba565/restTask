package pl.kuba565.resttask.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kuba565.resttask.service.hibernate.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/authenticate")
public class AuthController {
    private UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseBody
    public void authenticate(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("name");
        byte[] password = request.getParameter("password").getBytes();

//        if (userService.logIn(username, password)) {
//            Algorithm algorithm = Algorithm.HMAC256("secret");
//            String name = request.getParameter("name");
//            String token = JWT.create()
//                    .withIssuer(name)
//                    .sign(algorithm);
//            response.addHeader("token", token);
//        }

        //TODO: 1. add a user to db
        // 2. encrypt a password with salt - jbcrypt?
        // 3. verify the user - in a separate service layer
        // 4. use a filter to authenticate all requests!
    }

    @GetMapping()
    @ResponseBody
    public String checkUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = request.getParameter("token");
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("dupa")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return "user authenticated";
        } catch (JWTVerificationException exception) {
            return "user not authenticated!";
        }
    }
}
