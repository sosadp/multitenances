package com.djsmdev.multitenant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "Tenants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Tenant implements Serializable {

    @Serial
    private static final long serialVersionUID =12235434556535L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean active;
}
