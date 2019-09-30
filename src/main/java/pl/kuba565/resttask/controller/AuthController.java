package pl.kuba565.resttask.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kuba565.resttask.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RequestMapping("/authenticate")
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseBody
    public String authenticate(HttpServletRequest request, HttpServletResponse response) {
        String givenToken = request.getParameter("token");

        Algorithm algorithm = Algorithm.HMAC256("secret");
        String name = request.getParameter("name");
        String token = JWT.create()
                .withIssuer(name)
                .sign(algorithm);

        return givenToken.equals(token) ? "user authenticated!" : "user not authenticated!";
    }


    //TODO: 1. add a user to db
    // 2. encrypt a password with salt - jbcrypt?
    // 3. verify the user - in a separate service layer
    // 4. use a filter to authenticate all requests!


    @GetMapping()
    @ResponseBody
    public String signIn(HttpServletRequest request, HttpServletResponse response) {
        String databasePassword = String.valueOf(userService.findByName(request.getParameter("name")).getPassword());
        String givenPassword = request.getParameter("password");
        return compareHashedPasswords(request, response, databasePassword, givenPassword);
    }

    @NotNull
    private String compareHashedPasswords(HttpServletRequest request, HttpServletResponse response, String databasePassword, String givenPassword) {
        System.out.println(String.format("databasePassword: %s, hashedPassword: %s", databasePassword, givenPassword));
        if (BCrypt.checkpw(givenPassword, databasePassword)) {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String name = request.getParameter("name");
            String token = JWT.create()
                    .withIssuer(name)
                    .sign(algorithm);
            response.addHeader("token", token);

            return "user logged in!";
        } else {
            return "invalid name or password!";
        }
    }
}
