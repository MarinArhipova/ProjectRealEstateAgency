package com.company.dto;

import com.company.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
        private String firstName;
        private String lastName;
        private String email;

        public static UserDto from(User user) {
            return UserDto.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .build();
        }

        public static List<UserDto> from(List<User> users) {
            return users.stream().map(UserDto::from).collect(Collectors.toList());
        }
}

