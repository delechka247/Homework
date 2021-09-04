package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonutForm {

    private String name;
    private String description;
    private ru.itis.javalab.models.Donut.Flavor flavor;
    private Integer price;
    private Integer number;

}