package it.polimi.db2.telecoApp.services.impl;

import it.polimi.db2.telecoApp.dataaccess.repositories.ValidityPeriodRepository;
import it.polimi.db2.telecoApp.services.ValidityPeriodService;
import it.polimi.db2.telecoApp.services.mappers.ValidityPeriodMapper;
import it.polimi.db2.telecoApp.services.models.ValidityPeriod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidityPeriodServiceImpl implements ValidityPeriodService {

    private final ValidityPeriodRepository validityPeriodRepository;
    private final ValidityPeriodMapper validityPeriodMapper;

    public ValidityPeriodServiceImpl(ValidityPeriodRepository validityPeriodRepository, ValidityPeriodMapper validityPeriodMapper) {
        this.validityPeriodRepository = validityPeriodRepository;
        this.validityPeriodMapper = validityPeriodMapper;
    }

    @Override
    public List<ValidityPeriod> findAllByPackageId(Long packageId) {
        return validityPeriodRepository.findAllByPackageId(packageId)
                .stream().map(validityPeriodMapper::toTarget)
                .toList();
    }

    @Override
    public List<ValidityPeriod> findAll() {
        return validityPeriodRepository.findAll()
                .stream().map(validityPeriodMapper::toTarget)
                .toList();
    }

    @Override
    public ValidityPeriod save(ValidityPeriod validityPeriod) {
        return validityPeriodMapper.toTarget(validityPeriodRepository.save(
                validityPeriodMapper.toSource(validityPeriod)
        ));
    }
}
