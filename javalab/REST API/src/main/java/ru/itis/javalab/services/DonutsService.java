package ru.itis.javalab.services;

import ru.itis.javalab.dto.DonutDto;
import ru.itis.javalab.dto.DonutForm;
import ru.itis.javalab.models.Donut;

import java.util.List;

public interface DonutsService {
    List<DonutDto> getAllDonuts();

    DonutDto addDonut(DonutForm donutForm);

    DonutDto updateDonut(Long donutId, DonutForm donutForm);

    void deleteDonut(Long donutId);
}

