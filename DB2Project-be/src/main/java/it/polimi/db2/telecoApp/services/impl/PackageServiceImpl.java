package it.polimi.db2.telecoApp.services.impl;

import it.polimi.db2.telecoApp.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.telecoApp.dataaccess.repositories.PackageRepository;
import it.polimi.db2.telecoApp.services.PackageService;
import it.polimi.db2.telecoApp.services.mappers.PackageMapper;
import it.polimi.db2.telecoApp.services.models.Package;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;
    private final PackageMapper packageMapper;

    public PackageServiceImpl(PackageRepository packageRepository, PackageMapper packageMapper) {
        this.packageRepository = packageRepository;
        this.packageMapper = packageMapper;
    }

    @Override
    public List<Package> findAll() {
        return  packageRepository.findAll()
                .stream()
                .map(packageMapper::toTarget)
                .toList();
    }

    @Override
    public Package findById(Long packageId) {
        return  packageRepository.findById(packageId)
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
    public Package save(Package p){
        ServicePackageEntity toBeSaved = packageMapper.toSource(p);
        ServicePackageEntity saved = packageRepository.save(toBeSaved);
        return packageMapper.toTarget(saved);
    }


    ServicePackageEntity save(ServicePackageEntity entity){
        if(entity.getId() == null)
        {
            //create new entry in the packages table
            //insert into packages(.....) values (....)
        }
        else{
            //update the row with id = entity.getId()
            //update packges set .... where id = entity.id
        }
        return  entity;
    }
}
