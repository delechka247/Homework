package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.UsedMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticDto {
    private String methodName;
    private Integer number;

    public static List<StatisticDto> from(Map<String, Integer> methodsMap) {
        List<StatisticDto> methodDtoList = new ArrayList<>();
        for (Map.Entry<String, Integer> pair: methodsMap.entrySet()) {
            methodDtoList.add(StatisticDto.builder()
                    .methodName(pair.getKey())
                    .number(pair.getValue())
                    .build()
            );
        }
        return methodDtoList;
    }
}
