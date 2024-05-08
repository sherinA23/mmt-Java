package com.poc.makemytrip.config;

import com.poc.makemytrip.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String password;
    private String emailID;
    @Enumerated(EnumType.STRING)
    private Role role;
}
