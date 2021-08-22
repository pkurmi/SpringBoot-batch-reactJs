package com.Pkurmi.springbatch.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Pkurmi.springbatch.model.Match;
import com.Pkurmi.springbatch.model.Team;
import com.Pkurmi.springbatch.repository.MatchRepository;
import com.Pkurmi.springbatch.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {
	private TeamRepository teamRepository;
	private MatchRepository matchRepository;

	public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {		
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}
	
	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = this.teamRepository.findByTeamName(teamName);
		// Calls the method in MatchRepository and sets matches in Team.Match list. 
		team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName, 4));
				
		return team;		 	
	}
	
	
	@GetMapping("/team/{teamName}/matches")
	public List<Match> getmatchesForTeam(@PathVariable String teamName, @RequestParam int year){
		LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        
        // Calls method in Match.Repository and returns it. 
        return this.matchRepository.getMatchesByTeamBetweenDates(
            teamName,
            startDate,
            endDate
            );
	}
	
	

}
