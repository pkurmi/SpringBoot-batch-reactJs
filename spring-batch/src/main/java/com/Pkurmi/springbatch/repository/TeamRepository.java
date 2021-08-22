package com.Pkurmi.springbatch.repository;

import org.springframework.data.repository.CrudRepository;

import com.Pkurmi.springbatch.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	Team findByTeamName(String teamName);
}
