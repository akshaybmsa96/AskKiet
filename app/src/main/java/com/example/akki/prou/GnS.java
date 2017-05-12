package com.example.akki.prou;

/**
 * Created by Akki on 05-03-2017.
 */

public class GnS {

    private String heading;
    private String name;

    public GnS(String heading , String name)
    {
        this.setHeading(heading);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
