package ar.com.indra.beconnected.model;

public class RealTimeEventsResp {

    private String content;

    public RealTimeEventsResp() {

    }

    public RealTimeEventsResp(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[content = ");
        builder.append(content);
        builder.append("]");

        return builder.toString();

    }


}
