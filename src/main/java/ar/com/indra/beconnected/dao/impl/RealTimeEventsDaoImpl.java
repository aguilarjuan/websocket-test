package ar.com.indra.beconnected.dao.impl;


import ar.com.indra.beconnected.dao.RealTimeEventsDao;
import ar.com.indra.beconnected.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import static java.time.LocalDateTime.now;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.DateOperators.dateOf;

@Component
public class RealTimeEventsDaoImpl implements RealTimeEventsDao {

    final String TIME_ZONE = "+0000";

    Logger logger = Logger.getLogger(RealTimeEventsDaoImpl.class.getCanonicalName());

    @Autowired
    MongoOperations mongoOps;

    @Override
    public JsonClientesTienda getCountOfClientsOnSite() {

        long totalClientes;

        LocalDateTime fechaRetrasada = now().minusMinutes(1);

        String formatoFechaRetrasada = fechaRetrasada.toString() + TIME_ZONE;

        logger.info("[fechaRetrasada 1 min] = " + formatoFechaRetrasada);

        Date fechaDateRetrasada = obtenerFechaFormateada(formatoFechaRetrasada);

        String formatoFechaActual = obtenerStringFechaActualFormateado();

        logger.info("[FechaActual] = " + formatoFechaActual);

        Date fechaDateActual = obtenerFechaFormateada(formatoFechaActual);

        Criteria criterioEntra = new Criteria("eventDate");
        criterioEntra.gte(fechaDateRetrasada).lte(fechaDateActual).and("distanceFromBeacon").lt(20.0);

        Query queryEntra = new Query(criterioEntra);

        long countEntra = mongoOps.count(queryEntra, Event.class);

        logger.info("entran " + countEntra + " clientes" );

        Criteria criterioSale = new Criteria("eventDate");
        criterioSale.gte(fechaDateRetrasada).lte(fechaDateActual).and("distanceFromBeacon").gte(20.0);

        Query querySale = new Query(criterioSale);

        long countSale = mongoOps.count(querySale, Event.class);

        logger.info("salen  " + countSale + " clientes" );

        logger.info("total clientes en tienda = " + (countEntra - countSale) );

        totalClientes = (countEntra - countSale);

        JsonClientesTienda respTienda = new JsonClientesTienda(totalClientes);

        return respTienda;
    }

    @Override
    public List<JsonClientsGroupedHours> queryClientesHora(){

        LocalDateTime fechaRetrasada = now().minusHours(1);

        String formatoFechaRetrasada = fechaRetrasada.toString() + TIME_ZONE;

        logger.info("[FechaRetrasada 1 hs] = " + formatoFechaRetrasada);

        Date fechaDateRetrasada = obtenerFechaFormateada(formatoFechaRetrasada);

        String formatoFechaActual = obtenerStringFechaActualFormateado();

        logger.info("[FechaActual] = " + formatoFechaActual);

        Date fechaDateActual = obtenerFechaFormateada(formatoFechaActual);

        Criteria criterioMatch = new Criteria("eventDate");
        criterioMatch.gte(fechaDateRetrasada).lte(fechaDateActual).and("distanceFromBeacon").lt(20.0);

        MatchOperation operacionMatch = match(criterioMatch);

        GroupOperation operacionGrupo = group("eventDate").count().as("cantidad");

        ProjectionOperation operacionProjecto = project("eventDate").and(dateOf("eventDate").hour()).as("eventDate");

        ProjectionOperation operacionProjeccion = project("cantidad").andExclude("_id").and("_id").as("hora");

        SortOperation operacionSort = sort(Sort.Direction.ASC,"hora");

        Aggregation RespLista = newAggregation(operacionMatch,operacionProjecto,operacionGrupo,operacionProjeccion,operacionSort);

        AggregationResults<JsonClientsGroupedHours>  groupResults = mongoOps.aggregate(RespLista,Event.class,JsonClientsGroupedHours.class);

        List<JsonClientsGroupedHours> resultadoAgregacion = groupResults.getMappedResults();

        logger.info( "registro tamaño =" + resultadoAgregacion.size());

        return resultadoAgregacion;

    }
    @Override
    public List<JsonClientesGroupWeek> queryClientesSemana(){

        LocalDateTime fechaRetrasada = now().minusWeeks(1);

        String formatoFechaRetrasada = fechaRetrasada.toString() + TIME_ZONE;

        logger.info("[fechaRetrasada 7 dias] = " + formatoFechaRetrasada);

        Date fechaDateRetrasada = obtenerFechaFormateada(formatoFechaRetrasada);

        String formatoFechaActual = obtenerStringFechaActualFormateado();

        logger.info("[FechaActual] = " + formatoFechaActual);

        Date fechaDateActual = obtenerFechaFormateada(formatoFechaActual);

        Criteria criterioMatch = new Criteria("eventDate");
        criterioMatch.gte(fechaDateRetrasada).lte(fechaDateActual).and("distanceFromBeacon").lt(20.0);

        MatchOperation operacionMatch = match(criterioMatch);

        GroupOperation operacionGrupo = group("eventDate").count().as("cantidad");

        ProjectionOperation operacionProjecto = project("eventDate").and(dateOf("eventDate").dayOfWeek()).as("eventDate");

        ProjectionOperation operacionProjeccion = project("cantidad").andExclude("_id").and("_id").as("dia");

        SortOperation operacionSort = sort(Sort.Direction.ASC,"dia");

        Aggregation RespLista = newAggregation(operacionMatch,operacionProjecto,operacionGrupo,operacionProjeccion,operacionSort);

        AggregationResults<JsonClientesGroupWeek> groupResults = mongoOps.aggregate(RespLista,Event.class,JsonClientesGroupWeek.class);

        List<JsonClientesGroupWeek> resultadoAgregacion = groupResults.getMappedResults();

        logger.info( "registro tamaño = " + resultadoAgregacion.size());

        return resultadoAgregacion;
    }

    @Override
    public List<JsonClientsGroupMonth> queryClientesMes() {

        LocalDateTime fechaRetrasada = now().minusMonths(1);

        String formatoFechaRetrasada = fechaRetrasada.toString() + TIME_ZONE;

        logger.info("fechaRetrasada 1 mes = " + formatoFechaRetrasada);

        Date fechaDateRetrasada = obtenerFechaFormateada(formatoFechaRetrasada);

        String formatoFechaActual = obtenerStringFechaActualFormateado();

        logger.info("[FechaActual] = " + formatoFechaActual);

        Date fechaDateActual = obtenerFechaFormateada(formatoFechaActual);

        Criteria criterioMatch = new Criteria("eventDate");
        criterioMatch.gte(fechaDateRetrasada).lte(fechaDateActual).and("distanceFromBeacon").lt(20.0);

        MatchOperation operacionMatch = match(criterioMatch);

        GroupOperation operacionGrupo = group("eventDate").count().as("cantidad");

        ProjectionOperation operacionProjecto = project("eventDate").and(dateOf("eventDate").dayOfMonth()).as("eventDate");

        ProjectionOperation operacionProjeccion = project("cantidad").andExclude("_id").and("_id").as("mesDia");

        SortOperation operacionSort = sort(Sort.Direction.ASC,"mesDia");

        Aggregation RespLista = newAggregation(operacionMatch,operacionProjecto,operacionGrupo,operacionProjeccion,operacionSort);

        AggregationResults<JsonClientsGroupMonth> groupResults = mongoOps.aggregate(RespLista,Event.class,JsonClientsGroupMonth.class);

        List<JsonClientsGroupMonth> resultadoAgregacion = groupResults.getMappedResults();

        logger.info( "registro tamaño =" + resultadoAgregacion.size());

        return resultadoAgregacion;

    }

    @Override
    public List<JsonClientesHombreMujer> queryClintesHombreMujer(){

        LocalDateTime fechaRetrasada = now().minusMinutes(1);

        String formatoFechaRetrasada = fechaRetrasada.toString() + TIME_ZONE;

        logger.info("[fechaRetrasada 1 min] = " + formatoFechaRetrasada);

        Date fechaDateRetrasada = obtenerFechaFormateada(formatoFechaRetrasada);

        String formatoFechaActual = obtenerStringFechaActualFormateado();

        logger.info("[FechaActual] = " + formatoFechaActual);

        Date fechaDateActual = obtenerFechaFormateada(formatoFechaActual);

        Criteria criterioMatch = new Criteria("eventDate");
        criterioMatch.gte(fechaDateRetrasada).lte(fechaDateActual).and("distanceFromBeacon").lt(20.0);

        MatchOperation operacionMatch = match(criterioMatch);

        GroupOperation operacionGrupo = group("userData.gender").count().as("cantidad");

        ProjectionOperation operacionProjeccion = project("cantidad").andExclude("_id").and("_id").as("sexo");

        Aggregation RespLista = newAggregation(operacionMatch,operacionGrupo,operacionProjeccion);

        AggregationResults<JsonClientesHombreMujer> groupResults = mongoOps.aggregate(RespLista,Event.class,JsonClientesHombreMujer.class);

        List<JsonClientesHombreMujer> resultadoAgregacion = groupResults.getMappedResults();

        logger.info( "registro tamaño =" + resultadoAgregacion.size());

        return resultadoAgregacion;
    }

    @Override
    public  List<JsonClientesUnicos> queryClientesUnicos(){

        LocalDateTime fechaRetrasada = now().minusHours(1);

        String formatoFechaRetrasada = fechaRetrasada.toString() + TIME_ZONE;

        logger.info("[fechaRetrasada 1 hs] = " + formatoFechaRetrasada);

        Date fechaDateRetrasada = obtenerFechaFormateada(formatoFechaRetrasada);

        String formatoFechaActual = obtenerStringFechaActualFormateado();

        logger.info("[FechaActual] = " + formatoFechaActual);

        Date fechaDateActual = obtenerFechaFormateada(formatoFechaActual);

        Criteria criterioMatch = new Criteria("eventDate");
        criterioMatch.gte(fechaDateRetrasada).lte(fechaDateActual).and("distanceFromBeacon").lt(20.0);

        MatchOperation operacionMatch = match(criterioMatch);
        GroupOperation operacionGrupo = group("userData.email").count().as("cantidad");
        ProjectionOperation operacionProjecto1 = project("cantidad").andExclude("_id").and("_id").as("clienteUnico");

        Criteria criterioMatch1 = new Criteria("cantidad");
        criterioMatch1.lt(2.0);
        MatchOperation operacionMatch1 = match(criterioMatch1);

        ProjectionOperation operacionProjecto2 = project("clienteUnico");

        Aggregation RespLista = newAggregation(operacionMatch,operacionGrupo,operacionProjecto1,operacionMatch1,operacionProjecto2);

        AggregationResults<JsonClientesUnicos> groupResults = mongoOps.aggregate(RespLista,Event.class,JsonClientesUnicos.class);

        List<JsonClientesUnicos> resultadoAgregacion = groupResults.getMappedResults();

        logger.info( "registro tamaño =" + resultadoAgregacion.size());

        return resultadoAgregacion;

    }

    @Override
    public List<JsonClientesRecurrentes> queryClientesRecurrentes(){

        LocalDateTime fechaRetrasada = now().minusHours(1);

        String formatoFechaRetrasada = fechaRetrasada.toString() + TIME_ZONE;

        logger.info("[fechaRetrasada 1 hs] = " + formatoFechaRetrasada);

        Date fechaDateRetrasada = obtenerFechaFormateada(formatoFechaRetrasada);

        String formatoFechaActual = obtenerStringFechaActualFormateado();

        logger.info("[FechaActual] = " + formatoFechaActual);

        Date fechaDateActual = obtenerFechaFormateada(formatoFechaActual);

        Criteria criterioMatch = new Criteria("eventDate");
        criterioMatch.gte(fechaDateRetrasada).lte(fechaDateActual).and("distanceFromBeacon").lt(20.0);

        MatchOperation operacionMatch = match(criterioMatch);
        GroupOperation operacionGrupo = group("userData.email").count().as("cantidad");
        ProjectionOperation operacionProjecto1 = project("cantidad").andExclude("_id").and("_id").as("clienteRecurrente");

        Criteria criterioMatch1 = new Criteria("cantidad");
        criterioMatch1.gte(2.0);
        MatchOperation operacionMatch1 = match(criterioMatch1);

        ProjectionOperation operacionProjecto2 = project("clienteRecurrente");

        Aggregation RespLista = newAggregation(operacionMatch,operacionGrupo,operacionProjecto1,operacionMatch1,operacionProjecto2);

        AggregationResults<JsonClientesRecurrentes> groupResults = mongoOps.aggregate(RespLista,Event.class,JsonClientesRecurrentes.class);

        List<JsonClientesRecurrentes> resultadoAgregacion = groupResults.getMappedResults();

        logger.info( "registro tamaño =" + resultadoAgregacion.size());

        return resultadoAgregacion;
    }

    @Override
    public  JsonCantidadClintesUnicosConcurrentes queryCantClientUnicosConcurrentes(){

        List<JsonClientesUnicos> ListUnicos = this.queryClientesUnicos();
        List<JsonClientesRecurrentes> ListRecurrentes = this.queryClientesRecurrentes();

        int cantidadUnicos = ListUnicos.size();
        int cantidadRecurrentes = ListRecurrentes.size();

        JsonCantidadClintesUnicosConcurrentes Json = new JsonCantidadClintesUnicosConcurrentes(cantidadUnicos,cantidadRecurrentes);

        logger.info("cantidad total de clientes = " + Json.cantidadTotalCleintes());

        logger.info("clientes unicos = " + Json.getClientesUnicos());

        logger.info("clientes concurrentes = " + Json.getClientesRecurrentes());

        return Json;
    }


    private Date obtenerFechaFormateada(String fechaFormato) {
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date fecha = null;
        try{
            fecha = parseador.parse(fechaFormato);
        }catch (ParseException e){
            logger.warning("no se parseo la fecha");
        }
        return fecha;
    }

    private String obtenerStringFechaActualFormateado(){
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String fecha = parseador.format(new Date());

        String[] fechasTiempo = fecha.split("T");

        String[] tiempo = fechasTiempo[1].split(":");

        String[] timeZone = tiempo[2].split("-");

        String fechaToday = fechasTiempo[0] + "T" + tiempo[0] + ":" + tiempo[1] + ":" + timeZone[0] + "+0000";


        return fechaToday;
    }

}
