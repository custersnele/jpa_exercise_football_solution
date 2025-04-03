package be.pxl.service;

import be.pxl.api.dto.CreateTeamDto;
import be.pxl.api.dto.TeamDto;
import be.pxl.api.dto.TeamFullDto;
import be.pxl.domain.FootballTeam;
import be.pxl.exception.FootballException;
import be.pxl.exception.ResourceNotFoundException;
import be.pxl.repository.FootballTeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FootballTeamService {

	private final FootballTeamRepository footballTeamRepository;
	private final UploadService uploadService;

	public FootballTeamService(FootballTeamRepository footballTeamRepository, UploadService uploadService) {
		this.footballTeamRepository = footballTeamRepository;
		this.uploadService = uploadService;
	}

	@Transactional
	public TeamDto createTeam(CreateTeamDto team) {
		// There can be maximum two teams in one city.
		// Coach and name must be unique.
		if (footballTeamRepository.countFootballTeamByCity(team.getCity()) >= 2) {
			throw new FootballException("Too many teams in city [" + team.getCity() + "]");
		}
		if (footballTeamRepository.countFootballTeamByName(team.getName()) >= 1) {
			throw new FootballException("Name [" + team.getName() + "] already taken.");
		}
		if (footballTeamRepository.countFootballTeamByCoach(team.getCoach()) >= 1) {
			throw new FootballException("Coach [" + team.getCoach() + "] already assigned.");
		}
		FootballTeam footballTeam = new FootballTeam();
		footballTeam.setName(team.getName());
		footballTeam.setCity(team.getCity());
		footballTeam.setCoach(team.getCoach());
		FootballTeam savedTeam = footballTeamRepository.save(footballTeam);

		return TeamDto.from(savedTeam);
	}

	@Transactional
	public FootballTeam updateCoach(Long teamId, String newCoach) {
		FootballTeam footballTeam = footballTeamRepository.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("No team with id [" + teamId + "]"));
		footballTeam.setCoach(newCoach);
		return footballTeam;
	}

	public List<TeamDto> findAllTeams() {
		return footballTeamRepository.findAll().stream().map(TeamDto::from).toList();
	}

	public List<TeamDto> getTeamsByCity(String city) {
		return footballTeamRepository.findByCityJpql(city).stream().map(TeamDto::from).toList();
	}

	// TODO will transaction that starts here, be propagated to the
	// async method?
	public void uploadPlayers(Long teamId, MultipartFile file) {
		//FootballTeam footballTeam = footballTeamRepository.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("No team with id [" + teamId + "]"));
		uploadService.createTeam(teamId, file);

	}

	public TeamFullDto getTeamById(Long teamId) {
		FootballTeam footballTeam = footballTeamRepository.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("No team with id"));
		return TeamFullDto.from(footballTeam);
	}
}
