package com.ada.sql.sql.controller.authentication;

import com.ada.sql.sql.dto.authentication.LoginDto;
import com.ada.sql.sql.dto.authentication.TokeResponseDto;
import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.model.sql.User;
import com.ada.sql.sql.security.encrypt.PasswordEncryptionService;
import com.ada.sql.sql.security.jwt.OperationJwt;
import com.ada.sql.sql.service.user.UserService;
import jakarta.servlet.http.HttpServlet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthorizationController {

    private final UserService userService;
    private final PasswordEncryptionService passwordEncryptionService;
    private final OperationJwt operationJwt;

    public AuthorizationController(UserService userService, PasswordEncryptionService passwordEncryptionService, OperationJwt operationJwt) {
        this.userService = userService;
        this.passwordEncryptionService = passwordEncryptionService;
        this.operationJwt = operationJwt;
    }

    @PostMapping
    public ResponseEntity<TokeResponseDto> generationJwt(@RequestBody LoginDto dto){
        Optional<UserGenericDto> userFound = userService.findByEmail(dto.getEmail());

        if (userFound.isPresent()){
            UserGenericDto userGenericFound = userFound.get();
            TokeResponseDto tokeResponseDto = new TokeResponseDto();
            Calendar durationToken = Calendar.getInstance();
            durationToken.add(Calendar.MINUTE, 30);
            String jwt = "JWT no generado";
            if (passwordEncryptionService.isPasswordMatch(dto.getPassword(), userGenericFound.getPassword())){
                jwt = operationJwt.generateToken(userGenericFound, durationToken);
            }else {
                return new ResponseEntity("The password " + dto.getPassword() + " is not correct.", HttpStatus.NOT_FOUND);
            }

            tokeResponseDto.setJwt(jwt);
            tokeResponseDto.setExpirationDateJwt(durationToken.getTime());

            return new ResponseEntity<>(tokeResponseDto, HttpStatus.OK);

        }

        return new ResponseEntity("The email " + dto.getEmail() + " not found.", HttpStatus.NOT_FOUND);
    }
}
