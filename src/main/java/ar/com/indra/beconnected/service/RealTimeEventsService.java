package ar.com.indra.beconnected.service;


import ar.com.indra.beconnected.model.*;

import java.time.LocalDate;
import java.util.List;

public interface RealTimeEventsService {

    JsonClientesTienda getCountOfClientsOnSite();
    List<JsonClientsGroupedHours> clientesHora();
    List<JsonClientesGroupWeek> clientesSemana();
    List<JsonClientsGroupMonth> clientesMes();
    List<JsonClientesHombreMujer> ClientesHombreMujer();
    List<JsonClientesUnicos> clientesUnicos();
    List<JsonClientesRecurrentes> clientesRecurrentes();
    JsonCantidadClintesUnicosConcurrentes CantidadClintesUnicosConcurrentes();

}
