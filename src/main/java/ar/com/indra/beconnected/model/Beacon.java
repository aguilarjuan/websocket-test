package ar.com.indra.beconnected.model;

public class Beacon {

    private int major;

    private int minor;

    private String uuid;

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("{ [major = ");
        builder.append(major);
        builder.append("],[minor = ");
        builder.append(minor);
        builder.append("],[uuid = ");
        builder.append(uuid);

        builder.append("] }");

        return builder.toString();

    }
}
