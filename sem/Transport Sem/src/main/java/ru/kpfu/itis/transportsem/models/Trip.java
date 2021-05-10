package ru.kpfu.itis.transportsem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    @Enumerated(value = EnumType.STRING)
    private City fromCity;
    @Enumerated(value = EnumType.STRING)
    private City toCity;

    private Date departureDate;
    private Date arrivalDate;

    @Enumerated(value = EnumType.STRING)
    private Transport transport;

    private Integer number;


    public enum City {
        KAZAN, MOSCOW, YEKATERINBURG, SAINT_PETERSBURG, NOVOSIBIRSK
    }

    public enum Transport {
        BUS, TRAIN, PLANE
    }

    @ManyToMany(mappedBy = "trips")
    private List<Reservation> reservations;

}
