package be.pxl.service;

import be.pxl.api.dto.FilterDto;
import be.pxl.api.dto.FootballPlayerDto;
import be.pxl.domain.FootballPlayer;
import be.pxl.repository.FootballPlayerRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FootballPlayerService {

	private final FootballPlayerRepository footballPlayerRepository;

	public FootballPlayerService(FootballPlayerRepository footballPlayerRepository) {
		this.footballPlayerRepository = footballPlayerRepository;
	}

	public Page<FootballPlayerDto> findAllFootballPlayers(Pageable pageable) {
		Page<FootballPlayer> allFootballPlayers = footballPlayerRepository.findAll(pageable);
		return allFootballPlayers.map(FootballPlayerDto::from);
	}

	public List<FootballPlayerDto> searchFootballPlayers(FilterDto filter) {
		return footballPlayerRepository.findAll(createFootballPlayerSpecification(filter))
				.stream()
				.map(FootballPlayerDto::from)
				.toList();
	}

	private Specification<FootballPlayer> createFootballPlayerSpecification(FilterDto filter) {
		return new Specification<FootballPlayer>() {
			@Override
			public Predicate toPredicate(Root<FootballPlayer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> allPredicates = new ArrayList<>();
				if (filter.getName() != null) {
					allPredicates.add(builder.like(builder.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
				}
				if (filter.getPosition() != null) {
					allPredicates.add(builder.equal(root.get("position"), filter.getPosition()));
				}
				if (filter.getEmail() != null) {
					allPredicates.add(builder.equal(root.get("email"), filter.getEmail()));
				}
				if (filter.getTeam() != null) {
					allPredicates.add(builder.equal(root.join("team").get("name"), filter.getTeam()));
				}
				if (filter.getCity() != null) {
					allPredicates.add(builder.equal(root.join("team").get("city"), filter.getCity()));
				}
				return builder.and(allPredicates.toArray(new Predicate[0]));
			}
		};
	}
}
