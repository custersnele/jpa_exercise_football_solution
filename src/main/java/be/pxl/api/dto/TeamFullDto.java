package be.pxl.api.dto;

import be.pxl.domain.FootballTeam;

import java.util.List;

public class TeamFullDto extends TeamDto {
	private List<FootballPlayerBasicDto> players;

	public List<FootballPlayerBasicDto> getPlayers() {
		return players;
	}

	public void setPlayers(List<FootballPlayerBasicDto> players) {
		this.players = players;
	}

	public static TeamFullDto from(FootballTeam footballTeam) {


	}
}
