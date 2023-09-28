package de.ait.ec.services.impl;

import de.ait.ec.dto.NewUserDto;
import de.ait.ec.dto.UserDto;
import de.ait.ec.exeptions.RestException;
import de.ait.ec.models.User;
import de.ait.ec.repositories.UsersRepository;
import de.ait.ec.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static de.ait.ec.dto.UserDto.from;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UserService {

   private final UsersRepository usersRepository;

   private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDto register(NewUserDto newUser) {

        if (usersRepository.existsByEmail(newUser.getEmail())){
           throw new RestException(HttpStatus.CONFLICT,"User with email <" + newUser.getEmail() + "> already exist");
       }

        User user = User.builder()
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(User.Role.USER)
                .build();
        usersRepository.save(user);
        return from(user);
    }


}
