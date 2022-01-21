package it.polimi.db2.telecoApp.services.impl;

import it.polimi.db2.telecoApp.dataaccess.repositories.OptionalPackageRepository;
import it.polimi.db2.telecoApp.services.OptionalPackageService;
import it.polimi.db2.telecoApp.services.mappers.OptionalPackageMapper;
import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionalPackageServiceImpl implements OptionalPackageService {

    private final OptionalPackageRepository optionalPackageRepository;
    private final OptionalPackageMapper optionalPackageMapper;

    public OptionalPackageServiceImpl(OptionalPackageRepository optionalPackageRepository, OptionalPackageMapper optionalPackageMapper) {
        this.optionalPackageRepository = optionalPackageRepository;
        this.optionalPackageMapper = optionalPackageMapper;
    }

    @Override
    public List<OptionalPackage> findAll() {
        return optionalPackageRepository.findAll()
                .stream().map(optionalPackageMapper::toTarget)
                .toList();
    }

    @Override
    public OptionalPackage save(OptionalPackage optionalPackage) {
        return optionalPackageMapper.toTarget(
                optionalPackageRepository
                .save(optionalPackageMapper.toSource(optionalPackage)));

    }
}
