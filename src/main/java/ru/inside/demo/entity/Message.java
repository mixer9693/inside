package ru.inside.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String message;
}
