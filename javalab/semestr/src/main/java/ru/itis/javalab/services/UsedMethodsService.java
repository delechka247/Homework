package ru.itis.javalab.services;

import ru.itis.javalab.dto.StatisticDto;
import ru.itis.javalab.dto.UsedMethodDto;

import java.util.List;
import java.util.Map;

public interface UsedMethodsService {
    void addUsedMethod(UsedMethodDto usedMethodDto);
    List<UsedMethodDto> getAllUsedMethods();
    List<StatisticDto> countMethods();
}
