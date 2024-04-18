package be.pxl.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class FootballTeam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String coach;
	private String city;
	@OneToMany(mappedBy = "team")
	private List<FootballPlayer> footballPlayers = new ArrayList<>();


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<FootballPlayer> getFootballPlayers() {
		return footballPlayers;
	}

	public void addFootballPlayer(FootballPlayer footballPlayer) {
		this.footballPlayers.add(footballPlayer);
	}
}
