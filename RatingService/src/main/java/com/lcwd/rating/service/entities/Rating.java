package com.lcwd.rating.service.entities;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
