package be.pxl.repository;

import be.pxl.demo.domain.football.FootballPlayer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class FootballPlayerRepositoryTest {

	private static final String SUPERHERO_NAME = "Superman";

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private FootballPlayerRepository footballPlayerRepository;

	private final FootballPlayer footballPlayer1 = FootballPlayerBuilder.aFootballPlayer()
			.withEmail("test1@krc-genk.be")
			.withName("Test1")
			.withShirtNumber(15)
			.build();

	private final FootballPlayer footballPlayer2 = FootballPlayerBuilder.aFootballPlayer()
			.withEmail("test2@krc-genk.be")
			.withName("Test2")
			.withShirtNumber(22)
			.build();

	@BeforeEach
	public void init() {
		footballPlayerRepository.saveAll(Arrays.asList(footballPlayer1, footballPlayer2));
		entityManager.flush();
		entityManager.clear();
	}

	@Test
	public void returnsFootballPlayersWithEmailPattern() {
		List<FootballPlayer> footballPlayers = footballPlayerRepository.findFootballPlayerByEmailContainingIgnoreCase("test1");

		assertEquals(1, footballPlayers.size());
	}
}
