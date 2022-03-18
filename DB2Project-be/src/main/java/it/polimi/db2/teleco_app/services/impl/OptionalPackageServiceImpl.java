package it.polimi.db2.teleco_app.services.impl;

import it.polimi.db2.teleco_app.dataaccess.repositories.OptionalPackageRepository;
import it.polimi.db2.teleco_app.dataaccess.repositories.OrderRepository;
import it.polimi.db2.teleco_app.services.OptionalPackageService;
import it.polimi.db2.teleco_app.services.mappers.OptionalPackageMapper;
import it.polimi.db2.teleco_app.services.mappers.OrderMapper;
import it.polimi.db2.teleco_app.services.models.OptionalPackage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionalPackageServiceImpl implements OptionalPackageService {

    private final OptionalPackageRepository optionalPackageRepository;
    private final OptionalPackageMapper optionalPackageMapper;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OptionalPackageServiceImpl(OptionalPackageRepository optionalPackageRepository, OptionalPackageMapper optionalPackageMapper, OrderRepository orderRepository, OrderMapper orderMapper) {
        this.optionalPackageRepository = optionalPackageRepository;
        this.optionalPackageMapper = optionalPackageMapper;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OptionalPackage> findAll() {
        return optionalPackageRepository.findAll()
                .stream().map(optionalPackageMapper::toTarget)
                .toList();
    }

    @Override
    public List<OptionalPackage> findAllByPackageId(Long id) {
        var res =
                optionalPackageRepository.findAllByPackageId(id);
        return res.stream().map(optionalPackageMapper::toTarget)
                .toList();
    }

    @Override
    public OptionalPackage save(OptionalPackage optionalPackage) {
        return optionalPackageMapper.toTarget(
                optionalPackageRepository
                        .save(optionalPackageMapper.toSource(optionalPackage)));

    }

    @Override
    public void delete(Long optionalPackageId) {
        optionalPackageRepository.deleteById(optionalPackageId);
    }


}
