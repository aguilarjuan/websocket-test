package ar.com.indra.beconnected.model;

public class JsonClientesHombreMujer {

    private String sexo;

    private Double cantidad;

    public  JsonClientesHombreMujer(){

    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[sexo = ");
        builder.append(sexo);
        builder.append("[cantidad = ");
        builder.append(cantidad);
        builder.append("]");

        return builder.toString();

    }
}
