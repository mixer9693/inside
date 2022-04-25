package ru.inside.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @SequenceGenerator(name = "message_seq", sequenceName = "message_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
    private Integer id;
    private String username;
    private String message;
}
