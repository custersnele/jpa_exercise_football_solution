package be.pxl.builder;

import be.pxl.domain.FootballTeam;

public final class FootballTeamBuilder {
    private String name;
    private String coach;
    private String city;

    private FootballTeamBuilder() {
    }

    public static FootballTeamBuilder aFootballTeam() {
        return new FootballTeamBuilder();
    }

    public FootballTeamBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FootballTeamBuilder withCoach(String coach) {
        this.coach = coach;
        return this;
    }

    public FootballTeamBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public FootballTeam build() {
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setName(name);
        footballTeam.setCoach(coach);
        footballTeam.setCity(city);
        return footballTeam;
    }
}
