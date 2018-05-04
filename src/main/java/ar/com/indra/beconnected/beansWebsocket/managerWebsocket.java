package ar.com.indra.beconnected.beansWebsocket;

import ar.com.indra.beconnected.service.RealTimeEventsService;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import ar.com.indra.beconnected.model.*;

import java.util.List;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class managerWebsocket {

    String sessionId;
    String subscription;
    long schedule;

    RealTimeEventsService service;
    SimpMessageSendingOperations messagingTemplate;

    Logger logger = Logger.getLogger(managerWebsocket.class.getCanonicalName());

    public managerWebsocket(RealTimeEventsService service,SimpMessageSendingOperations messagingTemplate,String sessionId,String subscription,long schedule) {

        this.schedule = schedule;
        this.service = service;
        this.messagingTemplate = messagingTemplate;
        this.sessionId = sessionId;
        this.subscription = subscription;

    }

    public SimpMessageSendingOperations getMessagingTemplate() {
        return messagingTemplate;
    }

    public RealTimeEventsService getService() {
        return service;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getSubscription() {
        return subscription;
    }

    public long getSchedule() {
        return schedule;
    }

    public void startSession(){

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

        RealTimeEventsResp resp = new RealTimeEventsResp();

        logger.info("Executing QuerySubscription sessionId/subscription/time: " + sessionId + "/" + subscription + "/" + schedule);

        if(subscription.equalsIgnoreCase("queryClienteTienda")) {
            logger.info("identificador ingresado N° = " + subscription );
            JsonClientesTienda respClienteTienda = service.getCountOfClientsOnSite();
            logger.info("cantidad de clientes en tienda = " + respClienteTienda.getClientesTienda());
            String respJson = prettyGson.toJson(respClienteTienda);
            resp.setContent(respJson);
            logger.info("json = " + respJson);
            messagingTemplate.convertAndSend("/topic/subscriptions/" + sessionId, resp);

        } else if (subscription.equalsIgnoreCase("queryClienteHora")){
            logger.info("identificador ingresado N° = " + subscription );
            List<JsonClientsGroupedHours> respClientesHora = service.clientesHora();
            logger.info("enviando la Lista de respuestas Json, cantidad de registros = " + respClientesHora.size() );
            String respJson = prettyGson.toJson(respClientesHora);
            resp.setContent(respJson);
            logger.info("json = " + respJson);
            messagingTemplate.convertAndSend("/topic/subscriptions/" + sessionId, resp);

        } else if(subscription.equalsIgnoreCase("queryClienteSemana")){
            logger.info("identificador ingresado N° = " + subscription );
            List<JsonClientesGroupWeek> respClientesSemana = service.clientesSemana();
            logger.info("enviando la Lista de respuestas Json, cantidad de registros = " + respClientesSemana.size());
            String respJson = prettyGson.toJson(respClientesSemana);
            resp.setContent(respJson);
            logger.info("json = " + respJson);
            messagingTemplate.convertAndSend("/topic/subscriptions/" + sessionId, resp);

        } else if(subscription.equalsIgnoreCase("queryClienteMes")){
            logger.info("identificador ingresado N° = " + subscription );
            List<JsonClientsGroupMonth> respClientesMes = service.clientesMes();
            logger.info("enviando la Lista de respuestas Json, cantidad de registros = " + respClientesMes.size());
            String respJson = prettyGson.toJson(respClientesMes);
            resp.setContent(respJson);
            logger.info("json = " + respJson);
            messagingTemplate.convertAndSend("/topic/subscriptions/" + sessionId,resp);

        } else if (subscription.equalsIgnoreCase("queryClienteGenero")){
            logger.info("identificador ingresado N° = " + subscription );
            List<JsonClientesHombreMujer> respHombreMujer = service.ClientesHombreMujer();
            logger.info("enviando la Lista de respuestas Json, cantidad de registros = " + respHombreMujer.size());
            String respJson = prettyGson.toJson(respHombreMujer);
            resp.setContent(respJson);
            logger.info("json = " + respJson);
            messagingTemplate.convertAndSend("/topic/subscriptions/" + sessionId, resp);

        } else if (subscription.equalsIgnoreCase("queryListaClientesUnicos")){
            logger.info("identificador ingresado N° = " + subscription );
            List<JsonClientesUnicos> respClientesUnicos = service.clientesUnicos();
            logger.info("enviando la Lista de respuestas Json, cantidad de registros = " + respClientesUnicos.size());
            String respJson = prettyGson.toJson(respClientesUnicos);
            resp.setContent(respJson);
            logger.info("json = " + respJson);
            messagingTemplate.convertAndSend("/topic/subscriptions/" + sessionId, resp);

        } else if (subscription.equalsIgnoreCase("queryListaClientesHabituales")){
            logger.info("identificador ingresado N° = " + subscription );
            List<JsonClientesRecurrentes> respClientesRecurrentes = service.clientesRecurrentes();
            logger.info("enviando la Lista de respuestas Json, cantidad de registros = " + respClientesRecurrentes.size());
            String respJson = prettyGson.toJson(respClientesRecurrentes);
            resp.setContent(respJson);
            logger.info("json = " + respJson);
            messagingTemplate.convertAndSend("/topic/subscriptions/" + sessionId, resp);

        } else if (subscription.equalsIgnoreCase("queryClientesUnicosHabituales")){
            logger.info("identificador ingresado N° = " + subscription );
            JsonCantidadClintesUnicosConcurrentes respCantidadClientesUnicosConcurrentes = service.CantidadClintesUnicosConcurrentes();
            logger.info("enviando la Lista de respuestas Json= " + respCantidadClientesUnicosConcurrentes.toString());
            String respJson = prettyGson.toJson(respCantidadClientesUnicosConcurrentes);
            resp.setContent(respJson);
            logger.info("json = " + respJson);
            messagingTemplate.convertAndSend("/topic/subscriptions/" + sessionId, resp);

        }


    }

}
