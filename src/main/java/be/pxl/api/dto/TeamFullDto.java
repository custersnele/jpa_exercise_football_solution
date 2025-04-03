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
		TeamFullDto result = new TeamFullDto();
		result.setName(footballTeam.getName());
		result.setCity(footballTeam.getCity());
		result.setCoach(footballTeam.getCoach());
		List<FootballPlayerBasicDto> players = footballTeam.getFootballPlayers().stream().map(FootballPlayerBasicDto::from).toList();
		result.setPlayers(players);
		return result;
	}
}
