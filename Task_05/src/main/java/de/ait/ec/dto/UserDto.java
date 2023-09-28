package de.ait.ec.dto;

import de.ait.ec.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "User", description = "User data")
public class UserDto {

    @Schema(description = "User's ID", example = "1")
    private Long id;

    @Schema(description = "User's name", example = "Natalia")
    private String firstName;

    @Schema(description = "User's last name", example = "Bodnaryuk")
    private String lastName;

    @Schema(description = "User's email", example = "user2mail.com")
    private String email;

    @Schema(description = "User Role", example = "USER")
    private String role;

    public  static  UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }
}
