package be.pxl.domain;

import be.pxl.Position;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class FootballPlayer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	@Transient
	private int shirtNumber;
	@Enumerated(value = EnumType.STRING)
	private Position position;
	@ManyToOne
	private FootballTeam team;

	public FootballPlayer() {
	}

	public FootballPlayer(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(int shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public FootballTeam getTeam() {
		return team;
	}

	public void setTeam(FootballTeam team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "FootballPlayer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", shirtNumber=" + shirtNumber +
				'}';
	}
}
