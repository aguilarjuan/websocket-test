package ar.com.indra.beconnected.subscriptions;

import ar.com.indra.beconnected.service.RealTimeEventsService;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Component
public abstract class Subscription extends TimerTask {

    @Expose(serialize = false)
    protected RealTimeEventsService service;

    @Expose
    protected String sessionId;

    @Expose
    protected String subscription;

    protected long schedule;

    public long getScheduleTime() {
        return schedule;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create().toJson(this);
    }
}
