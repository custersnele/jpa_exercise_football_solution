package be.pxl.repository;

import be.pxl.builder.FootballPlayerBuilder;
import be.pxl.domain.FootballPlayer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class FootballPlayerRepositoryTest {

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
	public void returnsFootballPlayersWithEmail() {
		Optional<FootballPlayer> footballPlayer = footballPlayerRepository.findFootballPlayerByEmail("test2@krc-genk.be");

		assertTrue(footballPlayer.isPresent());
		assertEquals("test2@krc-genk.be", footballPlayer.get().getEmail());
	}

	@Test
	public void returnsEmptyOptionalWhenNoFootballPlayerWithEmail() {
		Optional<FootballPlayer> footballPlayer = footballPlayerRepository.findFootballPlayerByEmail("test3@krc-genk.be");

		assertTrue(footballPlayer.isEmpty());
	}
}
