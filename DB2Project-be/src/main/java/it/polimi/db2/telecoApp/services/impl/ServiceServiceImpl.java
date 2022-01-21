package it.polimi.db2.telecoApp.services.impl;

import it.polimi.db2.telecoApp.dataaccess.repositories.ServiceRepository;
import it.polimi.db2.telecoApp.services.ServiceService;
import it.polimi.db2.telecoApp.services.mappers.ServiceMapper;
import it.polimi.db2.telecoApp.services.models.TelecoService;

import java.util.List;
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public List<TelecoService> findAll() {
        return serviceRepository.findAll()
                .stream().map(serviceMapper::toTarget)
                .toList();
            }

    @Override
    public TelecoService save(TelecoService telecoService){
        return serviceMapper.toTarget(
                serviceRepository
                        .save(serviceMapper.toSource(telecoService)));
    }
}
