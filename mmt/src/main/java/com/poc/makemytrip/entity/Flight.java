package com.poc.makemytrip.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.thymeleaf.expression.Dates;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;


@Entity
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String source;
    private String destination;
    private Integer capacity;
    private Integer availability;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String date;  //Date type
    private String arrival; //Time type
    private String departure; //Time type
    private Integer price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "aircraftID",
            referencedColumnName = "aircraftID"
    )
    private Aircraft aircraft; // Foreign key referencing the Flight table
}
