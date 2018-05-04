package ar.com.indra.beconnected.model;

public class JsonClientsGroupedHours {

    private Integer cantidad;
    private Double hora;


    public JsonClientsGroupedHours(){

    }

    public Integer getcantidad(){

        return this.cantidad;

    }

    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;

    }

    public Double getHora(){

        return this.hora;

    }

    public void setHora(Double hora){

        this.hora = hora;

    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[cantidad = ");
        builder.append(cantidad);
        builder.append("],[hora = ");
        builder.append(hora);
        builder.append("]");

        return builder.toString();

    }
}

