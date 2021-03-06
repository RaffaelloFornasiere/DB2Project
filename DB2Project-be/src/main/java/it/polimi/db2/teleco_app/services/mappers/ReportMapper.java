package it.polimi.db2.teleco_app.services.mappers;

import it.polimi.db2.teleco_app.dataaccess.entities.SalesValidityReportEntity;
import it.polimi.db2.teleco_app.services.models.Report;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    Report toTarget(SalesValidityReportEntity salesValidityReportEntity);

    SalesValidityReportEntity toSource(Report report);
}
