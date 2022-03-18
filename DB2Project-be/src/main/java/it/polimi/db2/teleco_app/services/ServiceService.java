package it.polimi.db2.teleco_app.services;

import it.polimi.db2.teleco_app.services.models.TelecomService;

import java.util.List;

public interface ServiceService {
    void delete(Long serviceId);

    List<TelecomService> findAll();

    TelecomService save(TelecomService s);

    TelecomService findById(Long serviceId);
}
