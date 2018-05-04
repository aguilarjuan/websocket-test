package ar.com.indra.beconnected.model;

public class JsonClientesUnicos {

    private String clienteUnico;

    public JsonClientesUnicos(){


    }


    public void setCliente(String clienteUnico) {
        this.clienteUnico = clienteUnico;
    }

    public String getCliente() {
        return clienteUnico;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[clienteUnico = ");
        builder.append(clienteUnico);
        builder.append("]");

        return builder.toString();

    }
}
