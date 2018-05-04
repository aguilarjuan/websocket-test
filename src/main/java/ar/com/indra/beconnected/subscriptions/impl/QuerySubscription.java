package ar.com.indra.beconnected.subscriptions.impl;

import ar.com.indra.beconnected.beansWebsocket.managerWebsocket;
import ar.com.indra.beconnected.service.RealTimeEventsService;
import ar.com.indra.beconnected.subscriptions.Subscription;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class QuerySubscription extends Subscription {

    public SimpMessageSendingOperations messagingTemplate;

    Logger logger = Logger.getLogger(QuerySubscription.class.getCanonicalName());

    public QuerySubscription(SimpMessagingTemplate template, RealTimeEventsService service) {
        messagingTemplate = template;
        super.service = service;
    }

    public void setSessionId(String session) {
        this.sessionId = session;
    }

    public void setScheduleTime(long time) {
        this.schedule = time;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    @Override
    public void run() {
        logger.info("Llamando a la logica general de servicios Beacons WebSocket");

        managerWebsocket WebsockeManager = new managerWebsocket(service,messagingTemplate,sessionId,subscription,schedule);
        WebsockeManager.startSession();
    }
}