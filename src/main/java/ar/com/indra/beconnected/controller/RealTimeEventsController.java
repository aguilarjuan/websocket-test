package ar.com.indra.beconnected.controller;

import ar.com.indra.beconnected.model.RealTimeEventsReq;
import ar.com.indra.beconnected.model.RealTimeEventsResp;
import ar.com.indra.beconnected.service.RealTimeEventsService;
import ar.com.indra.beconnected.subscriptions.SubscriptionManager;
import ar.com.indra.beconnected.subscriptions.impl.QuerySubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class RealTimeEventsController {

    Logger logger = Logger.getLogger(RealTimeEventsController.class.getCanonicalName());

    @Autowired
    RealTimeEventsService rts;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private RealTimeEventsService service;

    @MessageMapping("/subscribe")
    public void subscribe(@Payload RealTimeEventsReq req, SimpMessageHeaderAccessor headerAccessor) throws Exception {

        logger.log(Level.INFO, "Receiving request: " + req.getIdentifier());

        String sessionId = headerAccessor.getSessionId();
        logger.log(Level.FINE ,"SessionID: " + sessionId);

        Timer timer = new Timer();
        QuerySubscription querySubscription = new QuerySubscription(template, service);
        querySubscription.setSessionId(sessionId);
        querySubscription.setScheduleTime(req.getRefresh());
        querySubscription.setSubscription(req.getIdentifier());

        timer.schedule(querySubscription, 0, querySubscription.getScheduleTime());

        SubscriptionManager.instance.subscribe(sessionId, querySubscription);

    }
}