package be.pxl.service;

import be.pxl.api.dto.CreateTeamDto;
import be.pxl.api.dto.TeamDto;
import be.pxl.domain.FootballTeam;
import be.pxl.exception.ResourceNotFoundException;
import be.pxl.repository.FootballTeamRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

	public void uploadPlayers(Long teamId, MultipartFile file) {
		FootballTeam footballTeam = footballTeamRepository.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("No team with id [" + teamId + "]"));
		uploadService.createTeam(footballTeam, file);

	}
}
