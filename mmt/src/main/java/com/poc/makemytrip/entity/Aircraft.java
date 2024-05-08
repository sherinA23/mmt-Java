package com.poc.makemytrip.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@Data
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @NaturalId
    private String aircraftID;
    private Integer capacity;

}
