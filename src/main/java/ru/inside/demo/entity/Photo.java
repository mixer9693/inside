package ru.inside.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "photos")
@Data
public class Photo {
    @Id
    @SequenceGenerator(name = "photo_seq", sequenceName = "photo_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_seq")
    private Integer id;
    private String name;
    private String path;
    private LocalDateTime creationDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
