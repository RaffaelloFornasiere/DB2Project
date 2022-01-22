package it.polimi.db2.telecoApp.services.impl;

import it.polimi.db2.telecoApp.dataaccess.repositories.OptionalPackageRepository;
import it.polimi.db2.telecoApp.dataaccess.repositories.OrderRepository;
import it.polimi.db2.telecoApp.services.OptionalPackageService;
import it.polimi.db2.telecoApp.services.mappers.OptionalPackageMapper;
import it.polimi.db2.telecoApp.services.mappers.OrderMapper;
import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import it.polimi.db2.telecoApp.services.models.Order;
import it.polimi.db2.telecoApp.services.models.Package;
import it.polimi.db2.telecoApp.services.models.Pair;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return optionalPackageRepository.findAllByPackageId(id)
                .stream().map(optionalPackageMapper::toTarget)
                .toList();
    }

    @Override
    public OptionalPackage save(OptionalPackage optionalPackage) {
        return optionalPackageMapper.toTarget(
                optionalPackageRepository
                .save(optionalPackageMapper.toSource(optionalPackage)));

    }

    @Override
    public List<Pair<Package, Double>> findAverageOptionalPackages() {
        List<Order> orders = orderRepository.findAll().stream().map(orderMapper::toTarget).toList();

        List<Pair<Package, Double>> res = new ArrayList<>();

        List<Package> packages = orders.stream().map(Order::getServicePackage).distinct().toList();

        for (int i = 0; i < packages.size(); i++) {
            int finalI = i;
            var sum = orders.stream()
                    .filter(order -> order.getServicePackage().equals(packages.get(finalI)))
                    .map(order-> order.getOptionalPackages().size()).toList();
            Double average =  sum.stream().reduce(Integer::sum).get().doubleValue()/sum.size();
            res.add(new Pair<>(packages.get(i), average));
        }

        return res;
    }
}
