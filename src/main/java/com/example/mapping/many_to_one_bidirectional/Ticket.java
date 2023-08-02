package com.example.mapping.many_to_one_bidirectional;

import com.example.mapping.one_to_one_bidirectional.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="seat_number")
    private String seatNumber;

    @Column(name="local_date_time")
    private LocalDateTime localDateTime;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "movie_id", foreignKey = @ForeignKey(name = "fk_ticket_movie"),nullable = false)
    private Movie movie;

}
