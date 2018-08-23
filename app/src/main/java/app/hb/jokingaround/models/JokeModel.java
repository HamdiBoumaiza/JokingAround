package app.hb.jokingaround.models;

import com.google.gson.annotations.Expose;

public class JokeModel {

    @Expose
    private int id;

    @Expose
    private String type;

    @Expose
    private String setup;

    @Expose
    private String punchline;


    public JokeModel() {
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }
}
