package com.example.oneinkedoneproject.dto.auth;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    //
    private String token;
}
