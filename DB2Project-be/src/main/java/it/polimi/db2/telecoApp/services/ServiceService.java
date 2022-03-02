package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.services.models.TelecomService;

import java.util.List;

public interface ServiceService {
    List<TelecomService> findAll();
    TelecomService save (TelecomService s);

    TelecomService findById(Long serviceId);
}
