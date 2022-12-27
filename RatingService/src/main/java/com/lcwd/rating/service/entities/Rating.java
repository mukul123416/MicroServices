package com.lcwd.rating.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "user_ratings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Rating {

    @Id
    @Column(name = "ID")
    private String ratingId;

    private String userId;

    private String hotelId;

    private int rating;

    private String feedback;
}
