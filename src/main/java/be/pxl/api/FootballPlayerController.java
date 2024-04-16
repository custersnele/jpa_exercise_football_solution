package be.pxl.api;

import be.pxl.api.dto.FilterDto;
import be.pxl.api.dto.FootballPlayerDto;
import be.pxl.service.FootballPlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class FootballPlayerController {

	private final FootballPlayerService footballPlayerService;

	public FootballPlayerController(FootballPlayerService footballPlayerService) {
		this.footballPlayerService = footballPlayerService;
	}

	@GetMapping
	public Page<FootballPlayerDto> findAll(Pageable pageable) {
		return footballPlayerService.findAllFootballPlayers(pageable);
	}

	@GetMapping("search")
	public List<FootballPlayerDto> search(@RequestBody FilterDto filter) {
		return footballPlayerService.searchFootballPlayers(filter);
	}


}
