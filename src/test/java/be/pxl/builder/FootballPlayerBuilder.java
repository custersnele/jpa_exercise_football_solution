package be.pxl.builder;


import be.pxl.domain.FootballPlayer;

public final class FootballPlayerBuilder {
    private String name;
    private String email;
    private int shirtNumber;

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

    public FootballPlayerBuilder withShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
        return this;
    }

    public FootballPlayer build() {
        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setName(name);
        footballPlayer.setEmail(email);
        footballPlayer.setShirtNumber(shirtNumber);
        return footballPlayer;
    }
}
