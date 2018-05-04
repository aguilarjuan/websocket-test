package ar.com.indra.beconnected.service.impl;


import ar.com.indra.beconnected.dao.RealTimeEventsDao;
import ar.com.indra.beconnected.model.*;
import ar.com.indra.beconnected.service.RealTimeEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class RealTimeEventsServiceImpl implements RealTimeEventsService {

    @Autowired
    RealTimeEventsDao dao;

    @Override
    public JsonClientesTienda getCountOfClientsOnSite() {
        JsonClientesTienda respCount = dao.getCountOfClientsOnSite();

        return respCount;
    }

    @Override
    public List<JsonClientsGroupedHours> clientesHora(){

        List<JsonClientsGroupedHours> respHora = dao.queryClientesHora();

        return respHora;
    }

    @Override
    public List<JsonClientesGroupWeek> clientesSemana() {
        List<JsonClientesGroupWeek> respSemana = dao.queryClientesSemana();
        return respSemana;
    }

    @Override
    public List<JsonClientsGroupMonth> clientesMes() {

        List<JsonClientsGroupMonth> respMes = dao.queryClientesMes();

        return respMes;
    }

    @Override
    public  List<JsonClientesHombreMujer> ClientesHombreMujer(){

        List<JsonClientesHombreMujer> respMenWomenCantidad = dao.queryClintesHombreMujer();

        return respMenWomenCantidad;
    }

    @Override
    public List<JsonClientesUnicos> clientesUnicos(){

        List<JsonClientesUnicos> respUnicos = dao.queryClientesUnicos();

    return respUnicos;
    }

    @Override
    public  List<JsonClientesRecurrentes> clientesRecurrentes(){

        List<JsonClientesRecurrentes> respConcurrentes = dao.queryClientesRecurrentes();

        return respConcurrentes;

    }

    @Override
    public JsonCantidadClintesUnicosConcurrentes CantidadClintesUnicosConcurrentes(){

        JsonCantidadClintesUnicosConcurrentes respCantClientUnicosConcurrentes = dao.queryCantClientUnicosConcurrentes();

        return respCantClientUnicosConcurrentes;
    }


}
