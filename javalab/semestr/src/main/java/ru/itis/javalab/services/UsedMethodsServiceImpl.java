package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.StatisticDto;
import ru.itis.javalab.dto.UsedMethodDto;
import ru.itis.javalab.models.UsedMethod;
import ru.itis.javalab.repositories.UsedMethodsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsedMethodsServiceImpl implements UsedMethodsService {

    UsedMethodsRepository usedMethodsRepository;

    public UsedMethodsServiceImpl(UsedMethodsRepository usedMethodsRepository) {
        this.usedMethodsRepository = usedMethodsRepository;
    }

    @Override
    public void addUsedMethod(UsedMethodDto usedMethodDto) {
        UsedMethod usedMethod = UsedMethod.builder()
                .name(usedMethodDto.getName())
                .time(usedMethodDto.getTime())
                .build();
        usedMethodsRepository.save(usedMethod);
    }

    @Override
    public List<UsedMethodDto> getAllUsedMethods() {
        return UsedMethodDto.from(usedMethodsRepository.findAll());
    }

    @Override
    public List<StatisticDto> countMethods() {
        List<String> methods = usedMethodsRepository.countMethods();
        Map<String, Integer> methodsMap = new HashMap<>();
        for (String method : methods) {
            if(methodsMap.containsKey(method)) {
                methodsMap.put(method, methodsMap.get(method) + 1);
            }
            else {
                methodsMap.put(method, 1);
            }
        }
        return StatisticDto.from(methodsMap);
    }
}
