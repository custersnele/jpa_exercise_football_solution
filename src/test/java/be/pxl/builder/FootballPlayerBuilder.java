package be.pxl.builder;


import be.pxl.domain.FootballPlayer;
import be.pxl.domain.FootballTeam;
import be.pxl.domain.Position;

public final class FootballPlayerBuilder {
    private String name;
    private String email;
    private int shirtNumber;
    private Position position;
    private FootballTeam team;

    private FootballPlayerBuilder() {
    }

    public static FootballPlayerBuilder aFootballPlayer() {
        return new FootballPlayerBuilder();
    }

    public FootballPlayerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FootballPlayerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public FootballPlayerBuilder withPosition(Position position) {
        this.position = position;
        return this;
    }

    public FootballPlayerBuilder withShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
        return this;
    }

    public FootballPlayer build() {
        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setName(name);
        footballPlayer.setEmail(email);
        footballPlayer.setShirtNumber(shirtNumber);
        footballPlayer.setPosition(position);
        if (team != null) {
            footballPlayer.setTeam(team);
        }
        return footballPlayer;
    }

    public FootballPlayerBuilder withTeam(FootballTeam team) {
        this.team = team;
        return this;
    }
}
