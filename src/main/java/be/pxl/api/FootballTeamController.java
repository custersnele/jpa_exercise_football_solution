package be.pxl.api;

import be.pxl.api.dto.CreateTeamDto;
import be.pxl.api.dto.TeamDto;
import be.pxl.api.dto.TeamFullDto;
import be.pxl.domain.FootballTeam;
import be.pxl.service.FootballTeamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class FootballTeamController {

	private final FootballTeamService footballTeamService;

	public FootballTeamController(FootballTeamService footballTeamService) {
		this.footballTeamService = footballTeamService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TeamDto createTeam(@RequestBody @Valid CreateTeamDto team) {
		return footballTeamService.createTeam(team);
	}

	@GetMapping("/{teamId}")
	public TeamFullDto getTeam(@PathVariable Long teamId) {
		return footballTeamService.getTeamById(teamId);
	}
	// Update a team's coach
	@PutMapping("/{teamId}/coach")
	public FootballTeam updateCoach(@PathVariable Long teamId, @RequestParam String newCoach) {
		return footballTeamService.updateCoach(teamId, newCoach);
	}

	// Retrieve all teams
	@GetMapping
	public List<TeamDto> getAllTeams() {
		return footballTeamService.findAllTeams();
	}

	// Retrieve all teams for a given city
	@GetMapping("/city/{city}")
	public List<TeamDto> getTeamsByCity(@PathVariable String city) {
		return footballTeamService.getTeamsByCity(city);
	}

	// Upload the players for a team
	@PostMapping("/{teamId}/players")
	public ResponseEntity<String> uploadPlayers(@PathVariable Long teamId, @RequestParam("file") MultipartFile file) {
		footballTeamService.uploadPlayers(teamId, file);
		return ResponseEntity.ok("File is being processed.");
	}
}
