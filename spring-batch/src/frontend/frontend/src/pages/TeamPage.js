
import './TeamPage.scss';
import {React, useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {MatchDetailCard} from '../components/MatchDetailCard';
import {MatchSmallCard} from '../components/MatchSmallCard';


	export const TeamPage = () =>  {
		
		const [team, setTeam] = useState({matches: []});
		const { teamName } = useParams();
		useEffect(
		() => {
		
		const fetchMatches = async () =>{
			const response = await fetch(`http://localhost:8080/team/${teamName}`);
			const data = await response.json();
			setTeam(data);
			
			};
			fetchMatches();
		}, [teamName]
		
		);	
					
		
		if(!team || !team.teamName){
			return <h1>Team not found!</h1>
		}
		
		  return (
		    <div className="TeamPage">
		    	 <div className="team-name-section">
			     	<h1> {team.teamName}</h1>
			      </div>
			      
			      <div className="win-loss-section">
			      	Win / Losses
			      </div>
			      
			      <div className="match-detail-section">
			     	<MatchDetailCard teamName={team.teamName} match={team.matches[0]} />
			     		<h3> Latest Matches </h3>
			      </div>
			      
			     {team.matches.slice(1).map(match => <MatchSmallCard teamName={team.teamName} match ={match} />)}
			     <div>
			     	<a href="#"> More </a>
			     </div>		   
		     </div>
		      
		  );
	}

