package ar.com.indra.beconnected.model;

public class JsonClientesRecurrentes {

    private String clienteRecurrente;

    public JsonClientesRecurrentes(){

    }

    public String getCliente() {
        return clienteRecurrente;
    }

    public void setCliente(String clienteRecurrente) {
        this.clienteRecurrente = clienteRecurrente;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[clienteRecurrente = ");
        builder.append(clienteRecurrente);
        builder.append("]");

        return builder.toString();

    }
}
