package be.pxl.repository;

import be.pxl.domain.FootballTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FootballTeamRepository extends JpaRepository<FootballTeam, Long> {

	@Query("SELECT t FROM FootballTeam t WHERE t.city = :city")
	List<FootballTeam> findByCityJpql(@Param("city") String city);

	@Query(value = "SELECT * FROM FootballTeam t WHERE t.city = :city", nativeQuery = true)
	List<FootballTeam> findByCityNative(@Param("city") String city);

	List<FootballTeam> findFootballTeamByCity(String city);
}
