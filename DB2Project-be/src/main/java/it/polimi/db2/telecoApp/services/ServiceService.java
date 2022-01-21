package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.services.models.TelecoService;

import java.util.List;

public interface ServiceService {
    List<TelecoService> findAll();
    TelecoService save (TelecoService s);

}
