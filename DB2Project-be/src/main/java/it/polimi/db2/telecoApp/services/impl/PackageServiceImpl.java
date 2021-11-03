package it.polimi.db2.telecoApp.services.impl;

import it.polimi.db2.telecoApp.services.PackageService;
import it.polimi.db2.telecoApp.services.models.Package;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {
    @Override
    public List<Package> findAll() {
        return null;
    }
}
