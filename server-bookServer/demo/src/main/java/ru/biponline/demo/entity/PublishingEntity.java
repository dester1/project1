package ru.biponline.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name = "publishers")
public class PublishingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String publisher;
    private String city;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publishing")
    private List<BookEntity> book;
}
