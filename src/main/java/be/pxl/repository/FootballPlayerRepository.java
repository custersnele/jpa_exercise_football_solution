package be.pxl.repository;

import be.pxl.domain.FootballPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FootballPlayerRepository extends JpaRepository<FootballPlayer, Long>, JpaSpecificationExecutor<FootballPlayer> {

}
