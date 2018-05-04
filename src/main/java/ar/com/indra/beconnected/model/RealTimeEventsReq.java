package ar.com.indra.beconnected.model;


public class RealTimeEventsReq {

    private String identifier;

    private long refresh;


    public RealTimeEventsReq() {

    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public long getRefresh() {
        return refresh;
    }

    public void setRefresh(long refresh) {
        this.refresh = refresh;
    }
}
