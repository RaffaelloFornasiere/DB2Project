package it.polimi.db2.telecoApp.services.impl;

import it.polimi.db2.telecoApp.dataaccess.repositories.ServiceRepository;
import it.polimi.db2.telecoApp.services.ServiceService;
import it.polimi.db2.telecoApp.services.mappers.ServiceMapper;
import it.polimi.db2.telecoApp.services.models.TelecomService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public List<TelecomService> findAll() {
        return serviceRepository.findAll()
                .stream().map(serviceMapper::toTarget)
                .toList();
            }

    @Override
    public TelecomService save(TelecomService telecomService){
        return serviceMapper.toTarget(
                serviceRepository
                        .save(serviceMapper.toSource(telecomService)));
    }

    @Override
    public TelecomService findById(Long serviceId) {
        return serviceRepository.findById(serviceId)
                .map(serviceMapper::toTarget).orElseThrow();

    }
}
