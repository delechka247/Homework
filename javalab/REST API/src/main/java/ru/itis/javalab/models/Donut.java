package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Donut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private Flavor flavor;

    private Integer price;

    private Integer number;

    private boolean isDeleted;

    public enum Flavor {
        STRAWBERRY, RASPBERRY, CHOCOLATE, BANANA, VANILLA
    }

    @ManyToMany(mappedBy = "donuts")
    private List<Order> orders;


}