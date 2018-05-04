package ar.com.indra.beconnected.model;

public class JsonClientsGroupMonth {

    private Double cantidad;
    private Integer mesDia;

    public JsonClientsGroupMonth(){

    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getMesDia() {
        return mesDia;
    }

    public void setMesDia(Integer mesDia) {
        this.mesDia = mesDia;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[cantidad = ");
        builder.append(cantidad);
        builder.append("],[mesDia = ");
        builder.append(mesDia);
        builder.append("]");

        return builder.toString();

    }
}
