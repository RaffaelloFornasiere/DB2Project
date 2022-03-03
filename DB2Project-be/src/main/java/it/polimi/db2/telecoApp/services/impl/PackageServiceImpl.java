package it.polimi.db2.telecoApp.services.impl;

import it.polimi.db2.telecoApp.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.telecoApp.dataaccess.repositories.OptionalPackageRepository;
import it.polimi.db2.telecoApp.dataaccess.repositories.PackageRepository;
import it.polimi.db2.telecoApp.services.PackageService;
import it.polimi.db2.telecoApp.services.mappers.OptionalPackageMapper;
import it.polimi.db2.telecoApp.services.mappers.PackageMapper;
import it.polimi.db2.telecoApp.services.mappers.ValidityPeriodMapper;
import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import it.polimi.db2.telecoApp.services.models.Package;
import it.polimi.db2.telecoApp.services.models.ValidityPeriod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;
    private final PackageMapper packageMapper;
    private final OptionalPackageRepository optionalPackageRepository;
    private final OptionalPackageMapper optionalPackageMapper;
    private final ValidityPeriodMapper validityPeriodMapper;

    public PackageServiceImpl(PackageRepository packageRepository, PackageMapper packageMapper, OptionalPackageRepository optionalPackageRepository, OptionalPackageMapper optionalPackageMapper, ValidityPeriodMapper validityPeriodMapper) {
        this.packageRepository = packageRepository;
        this.packageMapper = packageMapper;
        this.optionalPackageRepository = optionalPackageRepository;
        this.optionalPackageMapper = optionalPackageMapper;
        this.validityPeriodMapper = validityPeriodMapper;
    }

    @Override
    public List<Package> findAll() {

        return packageRepository.findAll()
                .stream()
                .map(packageMapper::toTarget)
                .toList();
    }

    @Override
    public Package findById(Long packageId) {
        return packageRepository.findById(packageId)
                .map(packageMapper::toTarget)
                .orElseThrow();
    }

    @Override
    public Package getDetails(Long id) {
        return packageRepository.findById(id)
                .map(packageMapper::toTarget)
                .orElseThrow();
    }

    @Override
    public List<OptionalPackage> getOptionalPackage(Long id) {
        return optionalPackageRepository.findAllByPackageId(id).stream().map(
                optionalPackageMapper::toTarget).toList();
    }


    @Override
    public Package save(Package p) {
        ServicePackageEntity toBeSaved = packageMapper.toSource(p);
        ServicePackageEntity saved = packageRepository.save(toBeSaved);
        return packageMapper.toTarget(saved);
    }

    @Override
    public Package saveWithDetails(Package p, List<OptionalPackage> optionalPackages, List<ValidityPeriod> validityPeriods) {
        return packageMapper.toTarget(packageRepository.savePackageWithDetails(
                packageMapper.toSource(p),
                optionalPackages.stream().map(optionalPackageMapper::toSource).toList(),
                validityPeriods.stream().map(validityPeriodMapper::toSource).toList()
        ));
    }

}
