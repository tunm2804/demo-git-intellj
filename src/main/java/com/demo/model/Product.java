package com.demo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name="Products")
public class Product implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank(message = "* Name Product là trường hợp bắt buộc!")
    String name;
    @NotNull(message = "* Price Product là trường hợp bắt buộc!")
    @Min(value = 0,message = "* Price bắt buộc phải lớn hơn 0!")
    Integer price;
    @NotNull(message = "* Category Product là trường hợp bắt buộc!")
    @ManyToOne @JoinColumn(name="Categoryid")
    Category category;
    String image;
}
