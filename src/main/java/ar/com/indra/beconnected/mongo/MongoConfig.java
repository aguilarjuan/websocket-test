package ar.com.indra.beconnected.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@Profile("!unit-test")
public class MongoConfig {

    Logger logger = Logger.getLogger(MongoConfig.class.getCanonicalName());

    @Value("${spring.data.mongodb.user}")
    private String userName;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.password}")
    private String password;

    public MongoConfig() {
    }

    /**
     * Permite obtener una instancia de <code>SimpleMongoDbFactory</code> para poder instanciar una conexion a una instancia de DB.
     *
     * @return una instancia de <code>SimpleMongoDbFactory</code>.
     *
     */
    public MongoDbFactory getMongoDbFactory() {

        logger.log(Level.INFO, String.format("{host: %s, port: %d, database: %s, username: %s}", host, port, database, userName));

        ServerAddress serverAddres = new ServerAddress(host, port);
        List<MongoCredential> credentialsList = new ArrayList<>();

        credentialsList.add(MongoCredential.createCredential(userName, database, password.toCharArray()));

        return new SimpleMongoDbFactory(new MongoClient(serverAddres, credentialsList), database);
    }

    /**
     * Obtiene una implementacion de la interfaz <code>MongoOperations</code> para poder realizar operaciones sobre la base de datos MongoDB.
     *
     * @return una instancia de <code>MongoTemplate</code>.
     */
    public @Bean
    MongoOperations getMongoTemplate() {

        MongoDbFactory mf = getMongoDbFactory();
        MongoTemplate mt = new MongoTemplate(mf);
        return mt;
    }
}
