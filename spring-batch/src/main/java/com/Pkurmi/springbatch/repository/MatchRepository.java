package com.Pkurmi.springbatch.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Pkurmi.springbatch.model.Match;


public interface MatchRepository extends CrudRepository<Match, Long> {
	//Jpa queries teamName in both Team1 and Team2 then lists in Match list. 
	List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);
	
	@Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :dateStart and :dateEnd order by date desc")
    List<Match> getMatchesByTeamBetweenDates(
        @Param("teamName") String teamName, 
        @Param("dateStart") LocalDate dateStart, 
        @Param("dateEnd") LocalDate dateEnd
    );
    
    // List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
    //     String teamName1, LocalDate date1, LocalDate date2,
    //     String teamName2, LocalDate date3, LocalDate date4
    //     );
	
	// Takes teamName and number of elements to be shown and returns TeamName in both team1 & team2.		
	default List<Match> findLatestMatchesbyTeam(String teamName, int count){
		return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
	}
				 		
}
