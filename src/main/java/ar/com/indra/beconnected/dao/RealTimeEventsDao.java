package ar.com.indra.beconnected.dao;


import ar.com.indra.beconnected.model.*;

import java.util.List;

public interface RealTimeEventsDao {


    JsonClientesTienda getCountOfClientsOnSite();

    List<JsonClientsGroupedHours> queryClientesHora();

    List<JsonClientesGroupWeek> queryClientesSemana();

    List<JsonClientsGroupMonth> queryClientesMes();

    List<JsonClientesHombreMujer> queryClintesHombreMujer();

    List<JsonClientesUnicos> queryClientesUnicos();

    List<JsonClientesRecurrentes> queryClientesRecurrentes();

    JsonCantidadClintesUnicosConcurrentes queryCantClientUnicosConcurrentes();

}
