package com.lcwd.hotel.service.entities;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
