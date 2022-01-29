package com.disney.mundo.Controllers;

import com.disney.mundo.Exceptions.ValidationException;
import com.disney.mundo.Model.DTO.UserLoginDTO;
import com.disney.mundo.Model.Entities.Security.ConfirmationToken;
import com.disney.mundo.Model.Entities.Security.Role;
import com.disney.mundo.Model.Entities.Security.User;
import com.disney.mundo.Model.Repositories.Security.ConfirmationTokenRepository;
import com.disney.mundo.Model.Repositories.Security.RoleRepository;
import com.disney.mundo.Model.Repositories.Security.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Objects;


@RestController
@RequestMapping("/auth")
public class UserAccountController {

    @Autowired
     AuthenticationManager authenticationManager;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
     UserRepository userRepository;
    @Autowired
     ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserLoginDTO userLoginDTO){

        User user= userRepository.findByEmailIdIgnoreCase(userLoginDTO.getEmailId());
        //Si el usuario no existe
        if(Objects.isNull(user))
        {
            //creo uno nuevo
            String toStorePassword=passwordEncoder.encode(userLoginDTO.getPassword());
            user = new User(userLoginDTO.getEmailId(),toStorePassword);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            Role roles = roleRepository.findByName("ROLE_USER").get();
            user.setRoles(Collections.singleton(roles));

            userRepository.save(user);
            confirmationTokenRepository.save(confirmationToken);

              return ResponseEntity.ok("REGISTRATION SUCCED" +
                      " TOKEN: "+confirmationToken.getConfirmationToken());
        }else
        {
            return ResponseEntity.badRequest().body("REGISTRATION FAILED. THE USER ALREADY EXIST");
        }
    }


    @PostMapping("/confirm-account")
    public String confirmUserAccount(
            @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null) {
            User user = userRepository.findByEmailIdIgnoreCase(token.getUser().getEmailId());
            if(user.getIsEnabled()==true) {
                return "ACCOUNT IS ALREADY CONFIRMED";
            }else{
                user.setEnabled(true);
                userRepository.save(user);
                return "ACCOUNT CONFIRMED";
            }
        }else{
            return "THE LINK IS INVALID OR BROKEN";
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(
           @NotNull @RequestBody UserLoginDTO userLoginDTO)
    {
//        if((Objects.isNull(userLoginDTO.getEmailId()))||(Objects.isNull(userLoginDTO.getPassword()))){
//        throw new ValidationException("INVALID PARAMETERS");
//         }


        User user= userRepository.findByEmailIdIgnoreCase(userLoginDTO.getEmailId());
       Boolean areTheSamePassword = BCrypt.checkpw(userLoginDTO.getPassword(), user.getPassword());

       //Si el usuario no existe o no coinciden las contraseñas
        if((Objects.isNull(user)||(!areTheSamePassword))) {
            return ResponseEntity.badRequest().body("INCORRECT EMAIL OR PASSWORD.  DID YOU COMFIRMED YOUR ACCOUNT?");
        } else
        {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getEmailId(),userLoginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok( "LOGIN SUCCESSFUL");
        }
    }

}
