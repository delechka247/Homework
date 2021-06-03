package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.UsedMethod;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsedMethodDto {
    private Long id;
    private String name;
    private Long time;

    public static UsedMethodDto from(UsedMethod usedMethod) {
        if (usedMethod == null) {
            return null;
        }
        return UsedMethodDto.builder()
                .name(usedMethod.getName())
                .time(usedMethod.getTime())
                .build();
    }

    public static List<UsedMethodDto> from(List<UsedMethod> usedMethods) {
        return usedMethods.stream()
                .map(UsedMethodDto::from)
                .collect(Collectors.toList());
    }

}
