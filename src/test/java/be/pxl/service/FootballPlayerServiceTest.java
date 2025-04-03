package be.pxl.service;

import be.pxl.api.dto.FilterDto;
import be.pxl.api.dto.FootballPlayerDto;
import be.pxl.builder.FootballPlayerBuilder;
import be.pxl.builder.FootballTeamBuilder;
import be.pxl.domain.FootballPlayer;
import be.pxl.domain.FootballTeam;
import be.pxl.domain.Position;
import be.pxl.repository.FootballPlayerRepository;
import be.pxl.repository.FootballTeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(FootballPlayerService.class)
public class FootballPlayerServiceTest {

	@Autowired
	private FootballPlayerRepository footballPlayerRepository;

	@Autowired
	private FootballTeamRepository teamRepository;

	@Autowired
	private FootballPlayerService footballPlayerService;

	@BeforeEach
	void setup() {
		FootballTeam team1 = FootballTeamBuilder.aFootballTeam()
				.withName("FC Test")
				.withCity("Brussels")
				.build();
		teamRepository.save(team1);

		FootballTeam team2 = FootballTeamBuilder.aFootballTeam()
				.withName("United")
				.withCity("Antwerp")
				.build();
		teamRepository.save(team2);

		FootballPlayer player1 = FootballPlayerBuilder.aFootballPlayer()
				.withName("John Doe")
				.withEmail("john@example.com")
				.withPosition(Position.GOALKEEPER)
				.withTeam(team1)
				.build();
		footballPlayerRepository.save(player1);

		FootballPlayer player2 = FootballPlayerBuilder.aFootballPlayer()
				.withName("Jane Smith")
				.withEmail("jane@example.com")
				.withPosition(Position.DEFENDER)
				.withTeam(team2)
				.build();
		footballPlayerRepository.save(player2);
	}

	@Test
	void testSearchFootballPlayers_ByName() {
		FilterDto filter = new FilterDto();
		filter.setName("john");

		List<FootballPlayerDto> results = footballPlayerService.searchFootballPlayers(filter);

		assertThat(results).hasSize(1);
		assertThat(results.get(0).getName()).contains("John Doe");
	}

	@Test
	void testSearchFootballPlayers_ByPosition() {
		FilterDto filter = new FilterDto();
		filter.setPosition(Position.DEFENDER);

		List<FootballPlayerDto> results = footballPlayerService.searchFootballPlayers(filter);

		assertThat(results).hasSize(1);
		assertThat(results.get(0).getName()).contains("Jane Smith");
	}

	@Test
	void testSearchFootballPlayers_ByTeamCity() {
		FilterDto filter = new FilterDto();
		filter.setCity("Brussels");

		List<FootballPlayerDto> results = footballPlayerService.searchFootballPlayers(filter);

		assertThat(results).hasSize(1);
		assertThat(results.get(0).getName()).contains("John Doe");
	}
}
