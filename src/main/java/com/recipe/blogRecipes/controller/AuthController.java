package com.recipe.blogRecipes.controller;

import com.recipe.blogRecipes.entity.Authority;
import com.recipe.blogRecipes.entity.User;
import com.recipe.blogRecipes.payload.request.SigninRequest;
import com.recipe.blogRecipes.payload.request.SignupRequest;
import com.recipe.blogRecipes.payload.response.JwtAuthenticationResponse;
import com.recipe.blogRecipes.security.JwtTokenProvider;
import com.recipe.blogRecipes.security.UserPrincipal;
import com.recipe.blogRecipes.service.AuthorityService;
import com.recipe.blogRecipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;


@RestController
@RequestMapping("auth") // localhost:{port}/auth/....  Alivello di classe questa parte di path subito dopo il nome dominio e prima della URL nella chiamata
public class AuthController {

    @Autowired AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    AuthorityService authorityService;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider tokenProvider;


    //Sempre usiamo ResponseEntity perchè non sappiamo esito del nostro metodo. Gli passiamo un oggetto di tipo SigninRequest


    @Transactional //creiamo un nuovo oggetto
    @PutMapping("/signup") //È PutMapping. Memorizza una risorsa sul server. Persisto sul server qualcosa.
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    	if(userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Username already in use", HttpStatus.BAD_REQUEST);
        }


        // Find default valid authority. Mi stanzio un oggetto di tipo authority
        Optional<Authority> authority = authorityService.findByAuthorityName("ROLE_GUEST");

        // Creating user's account
        User user = new User(
    		signUpRequest.getUsername(),
            passwordEncoder.encode(signUpRequest.getPassword()),
            Collections.singleton(authority.get()) // transform object Authority into Set<Authority> trasforma un singolo oggetto in un set di authority
        );

       userService.save(user); //metodo save fa insert sul dB
        //user service si traduce in una INSERT
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PostMapping("signin") //Invio una risorsa al server. Utilizzo la risorsa per fare verifiche. LA risorsa è Email e Password
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest request) {  //ResponseEntity nel body risultato che ci aspettiamo che il metodo ci restituisca e HTTP status
        Optional<User> u = userService.findByUsername(request.getUsername()); //Controller chiama metodo dal Service che a sua volta ritorna dal Repository.
        if (!u.isPresent())
            return new ResponseEntity<String>("Username or password wrong", HttpStatus.FORBIDDEN);
        if (!passwordEncoder.matches(request.getPassword(), u.get().getPassword())) //matches richiede 2 parametri pasword in chiaro. metodo get cerca la pssword all'interno dell'oggetto user passato
            return new ResponseEntity<String>("Username or password wrong", HttpStatus.FORBIDDEN);
        //Comincia Spring Security. AuthenticationManager mette a disposizione metodo authenticate.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( //recupera ruoli utente e questo oggetto lo setta nel SecurityContextHolder
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //settato oggetto authentication nel SecurityContextHolder
        String jwt = JwtTokenProvider.generateToken(authentication);
        // genero Jason Web Token:1)Header regole criptazione, 3) signature, 2)parti che ci servono come payload
        JwtAuthenticationResponse currentUser = UserPrincipal.createJwtAuthenticationResponseFromUserPrincipal((UserPrincipal) authentication.getPrincipal(), jwt);

        return ResponseEntity.ok(currentUser); //ResponseEntity è quello che mandiamo di risposta al Client
    }
}

