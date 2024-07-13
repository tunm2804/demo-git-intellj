package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="Accounts")
public class Account implements Serializable {
    @Id
    @NotBlank(message = "*Username Accounts là trường hợp bắt buộc!")
    String username;
    @NotBlank(message = "*Password Accounts là trường hợp bắt buộc!")
    String password;
    @NotBlank(message = "*Fullname Accounts là trường hợp bắt buộc!")
    String fullname;
    @NotBlank(message = "*Email Accounts là trường hợp bắt buộc!")
    String email;
}
