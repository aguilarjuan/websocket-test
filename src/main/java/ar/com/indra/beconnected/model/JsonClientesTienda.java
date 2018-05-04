package ar.com.indra.beconnected.model;

public class JsonClientesTienda {

private long clientesTienda;

public JsonClientesTienda(long clientesTienda){
    this.clientesTienda = clientesTienda;
}

public JsonClientesTienda(){

}

    public long getClientesTienda() {
        return clientesTienda;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[clientesTienda = ");
        builder.append(clientesTienda);
        builder.append("]");

        return builder.toString();

    }

}
