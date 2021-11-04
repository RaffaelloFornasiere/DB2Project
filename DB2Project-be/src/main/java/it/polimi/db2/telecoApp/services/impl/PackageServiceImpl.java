package it.polimi.db2.telecoApp.services.impl;

import it.polimi.db2.telecoApp.dataaccess.repositories.PackageRepository;
import it.polimi.db2.telecoApp.services.PackageService;
import it.polimi.db2.telecoApp.services.mappers.PackageMapper;
import it.polimi.db2.telecoApp.services.models.Package;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;

    public PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public List<Package> findAll() {
        return  packageRepository.findAll()
                .stream()
                .map(PackageMapper.MAPPER::toTarget)
                .toList();
    }
}
