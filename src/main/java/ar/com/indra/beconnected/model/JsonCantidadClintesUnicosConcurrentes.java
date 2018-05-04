package ar.com.indra.beconnected.model;

public class JsonCantidadClintesUnicosConcurrentes {

    private int clientesUnicos;
    private int clientesRecurrentes;

    public JsonCantidadClintesUnicosConcurrentes(){

    }

    public JsonCantidadClintesUnicosConcurrentes(int unicos,int recurrentes){

        this.clientesRecurrentes = recurrentes;
        this.clientesUnicos = unicos;

    }

    public int cantidadTotalCleintes(){

     return (clientesUnicos + clientesRecurrentes );

    }

    public int getClientesRecurrentes() {
        return clientesRecurrentes;
    }

    public void setClientesRecurrentes(int clientesRecurrentes) {
        this.clientesRecurrentes = clientesRecurrentes;
    }

    public int getClientesUnicos() {
        return clientesUnicos;
    }

    public void setClientesUnicos(int clientesUnicos) {
        this.clientesUnicos = clientesUnicos;
    }


    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[clientesUnicos = ");
        builder.append(clientesUnicos);
        builder.append("][clientesRecurrentes = ");
        builder.append(clientesRecurrentes);
        builder.append("]");

        return builder.toString();

    }



}
