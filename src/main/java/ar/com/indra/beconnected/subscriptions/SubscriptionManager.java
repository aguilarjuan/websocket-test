package ar.com.indra.beconnected.subscriptions;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Service
public class SubscriptionManager {

    public static SubscriptionManager instance = new SubscriptionManager();

    private Logger logger = Logger.getLogger(SubscriptionManager.class.getCanonicalName());

    private Map<String, Collection<Subscription>> subscriptions = new ConcurrentHashMap<String, Collection<Subscription>>();

    private SubscriptionManager(){

    }

    public void subscribe(String session, Subscription subscription) {
        Collection<Subscription> list = this.subscriptions.get(session);

        if(list != null) {
            list.add(subscription);
        } else {
            list = new ArrayList<>();
            list.add(subscription);
            this.subscriptions.put(session, list);
        }
    }

    public void unsubscribe(String session) {

        Collection<Subscription> subscriptions = this.subscriptions.get(session);

        if(subscriptions != null && subscriptions.size() > 0) {
            logger.info("Cancelling suscription for session " + session);

            Iterator<Subscription> itera = subscriptions.iterator();
            while(itera.hasNext()) {
                Subscription s = itera.next();
                s.cancel();
                logger.info("Subscription cancelled: " + s.subscription);
            }
        }
    }
}
