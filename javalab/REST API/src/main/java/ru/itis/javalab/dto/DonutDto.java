package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.Donut;
import ru.itis.javalab.models.Order;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonutDto {

    private Long id;
    private String name;
    private String description;
    private ru.itis.javalab.models.Donut.Flavor flavor;
    private Integer price;

    public static DonutDto from(Donut donut) {
        DonutDto result = DonutDto.builder()
                .id(donut.getId())
                .name(donut.getName())
                .description(donut.getDescription())
                .flavor(donut.getFlavor())
                .price(donut.getPrice())
                .build();
        return result;
    }

    public static List<DonutDto> from(List<Donut> donuts) {
        return donuts.stream().map(DonutDto::from).collect(Collectors.toList());
    }


}