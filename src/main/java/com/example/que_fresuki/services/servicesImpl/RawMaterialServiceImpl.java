package com.example.que_fresuki.services.servicesImpl;

import com.example.que_fresuki.entitys.models.RawMaterial;
import com.example.que_fresuki.exceptions.rawMaterialExceptions.InvalidRawMaterialExceptions;
import com.example.que_fresuki.exceptions.rawMaterialExceptions.NotFoundRawMaterialExceptions;
import com.example.que_fresuki.repositorys.IRawMaterialRepository;
import com.example.que_fresuki.services.RawMaterialService;

import com.example.que_fresuki.utils.Message;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class RawMaterialServiceImpl implements RawMaterialService {
    private final IRawMaterialRepository rawMaterialRepository;

    @Autowired
    public RawMaterialServiceImpl(IRawMaterialRepository iRawMaterialRepository) {
        this.rawMaterialRepository = iRawMaterialRepository;
    }

    @Override
    public List<RawMaterial> getRawMaterials() {
        log.info("LISTADO DE MATERIAS PRIMAS");
        return rawMaterialRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public RawMaterial getRawMaterial(@NonNull Long id) {
        return rawMaterialRepository.findById(id)
                .orElseThrow(() -> new NotFoundRawMaterialExceptions(Message.NOT_FOUND_RAW_MATERIAL, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
    }

    @Override
    public RawMaterial saveRawMaterial(RawMaterial rawMaterial) {
        Optional<RawMaterial> rawMaterialFound = rawMaterialRepository.findByNameRawMaterial(rawMaterial.getNameRawMaterial());
        if (rawMaterialFound.isPresent()) {
            throw new InvalidRawMaterialExceptions(Message.RAW_MATERIAL_ALREADY_EXISTS, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT);
        }
        log.info("SAVE RawMaterial");
        return rawMaterialRepository.save(rawMaterial);
    }

    @Override
    public RawMaterial updateRawMaterial(@NonNull Long id, @NonNull RawMaterial rawMaterial) {
        Optional<RawMaterial> rawMaterialFound = rawMaterialRepository.findByNameRawMaterial(rawMaterial.getNameRawMaterial());
        if (rawMaterialFound.isEmpty()) {
            throw new NotFoundRawMaterialExceptions(Message.NOT_FOUND_RAW_MATERIAL, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(rawMaterial, rawMaterialFound.get(), "id", "createAt");
        RawMaterial updatedRawMaterial = rawMaterialFound.get();
        return rawMaterialRepository.save(updatedRawMaterial);
    }

    @Override
    public void deleteRawMaterial(@NonNull Long id) {
        Objects.requireNonNull(id, "RawMaterial body must not be null");
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new NotFoundRawMaterialExceptions(Message.NOT_FOUND_RAW_MATERIAL, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
        rawMaterialRepository.delete(rawMaterial);
    }
}
