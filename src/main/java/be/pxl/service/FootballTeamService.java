package be.pxl.service;

import be.pxl.api.dto.CreateTeamDto;
import be.pxl.api.dto.TeamDto;
import be.pxl.domain.FootballTeam;
import be.pxl.repository.FootballTeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FootballTeamService {

	private final FootballTeamRepository footballTeamRepository;

	public FootballTeamService(FootballTeamRepository footballTeamRepository) {
		this.footballTeamRepository = footballTeamRepository;
	}

	@Transactional
	public FootballTeam createTeam(CreateTeamDto team) {
		// There can be maximum two teams in one city.
		// Stadium and name must be unique.
		FootballTeam footballTeam = new FootballTeam();
		footballTeam.setName(team.getName());
		footballTeam.setCity(team.getCity());
		footballTeam.setCoach(team.getCoach());
		return footballTeamRepository.save(footballTeam);
	}

	public FootballTeam updateCoach(Long teamId, String newCoach) {
		// TODO
		return null;
	}

	public List<TeamDto> findAllTeams() {
		return footballTeamRepository.findAll().stream().map(TeamDto::from).toList();
	}

	public List<TeamDto> getTeamsByCity(String city) {
		return footballTeamRepository.findByCityJpql(city).stream().map(TeamDto::from).toList();
	}
}
