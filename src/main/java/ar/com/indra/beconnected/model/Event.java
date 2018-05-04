package ar.com.indra.beconnected.model;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Document(collection = "indraBeconnected")
public class Event {

    @Id
    private String _id;

    private Beacon beacon;

    private Number distanceFromBeacon;

    private UserData userData;

    private Date eventDate;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = _id;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public Number getDistanceFromBeacon(){return distanceFromBeacon;}

    public void setDistanceFromBeacon(Number distanceFromBeacon ){this.distanceFromBeacon = distanceFromBeacon;}

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[_id = ");
        builder.append(_id);
        builder.append("],[beacon = ");
        builder.append(beacon);
        builder.append("],[distanceFromBeacon = ");
        builder.append(distanceFromBeacon);
        builder.append("],[userData = ");
        builder.append(userData);
        builder.append("],[eventDate = ");
        builder.append(eventDate);
        builder.append("]");

        return builder.toString();

    }
}
