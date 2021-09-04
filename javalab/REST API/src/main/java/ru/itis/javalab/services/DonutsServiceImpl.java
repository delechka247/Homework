package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.DonutDto;
import ru.itis.javalab.dto.DonutForm;
import ru.itis.javalab.models.Donut;
import ru.itis.javalab.repositories.DonutsRepository;

import java.util.List;

import static ru.itis.javalab.dto.DonutDto.from;

@Service
public class DonutsServiceImpl implements DonutsService {

    @Autowired
    private DonutsRepository donutsRepository;

    @Override
    public List<DonutDto> getAllDonuts() {
        return from(donutsRepository.findAllByIsDeletedIsFalse());
    }

    @Override
    public DonutDto addDonut(DonutForm donutForm) {
            Donut newDonut = Donut.builder()
                    .name(donutForm.getName())
                    .description(donutForm.getDescription())
                    .flavor(donutForm.getFlavor())
                    .number(donutForm.getNumber())
                    .price(donutForm.getPrice())
                    .build();

            donutsRepository.save(newDonut);
            return from(newDonut);
    }

    @Override
    public DonutDto updateDonut(Long donutId, DonutForm donutForm) {
        Donut donutForUpdate = donutsRepository.findById(donutId)
                .orElseThrow(IllegalArgumentException::new);
        donutForUpdate.setNumber(donutForm.getNumber());
        donutForUpdate.setPrice(donutForm.getPrice());
        donutForUpdate.setName(donutForm.getName());
        donutForUpdate.setDescription(donutForm.getDescription());
        donutForUpdate.setFlavor(donutForm.getFlavor());
        donutsRepository.save(donutForUpdate);
        return from(donutForUpdate);
    }

    @Override
    public void deleteDonut(Long donutId) {
        Donut donutForDelete = donutsRepository.findById(donutId)
                .orElseThrow(IllegalArgumentException::new);
        donutForDelete.setDeleted(true);
        donutsRepository.save(donutForDelete);

    }
}
