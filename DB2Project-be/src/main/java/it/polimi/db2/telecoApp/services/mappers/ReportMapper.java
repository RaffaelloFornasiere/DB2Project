package it.polimi.db2.telecoApp.services.mappers;

import it.polimi.db2.telecoApp.dataaccess.entities.SalesValidityReportEntity;
import it.polimi.db2.telecoApp.services.models.Report;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    Report toTarget(SalesValidityReportEntity salesValidityReportEntity);
    SalesValidityReportEntity toSource(Report report);
}
