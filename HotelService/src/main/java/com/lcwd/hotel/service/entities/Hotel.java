package com.lcwd.hotel.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "hotels")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Hotel {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME",length = 20)
    private String name;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "ABOUT")
    private String about;
}
