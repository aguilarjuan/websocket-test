package ar.com.indra.beconnected.model;

public class JsonClientesGroupWeek {

    private Double cantidad;
    private Integer dia;

    public JsonClientesGroupWeek(){

    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getDia() {
        return dia;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCantidad() {
        return cantidad;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[cantidad = ");
        builder.append(cantidad);
        builder.append("],[dia = ");
        builder.append(dia);
        builder.append("]");

        return builder.toString();

    }


}
