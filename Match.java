import java.io.*;

public class Match implements Serializable{		/* class that creates a match object with two teams and the score of the match*/

	Team team1, team2;							/* teams in match */
	float team1Score, team2Score;				/* the score of the teams */
	int team1rank, team2rank;					/* the ranks of the teams (used for certain formats) */

	public Team getTeam1(){						/* get first team */
		return team1;
	}

	public Team getTeam2(){						/* get second team */
		return team2;
	}

	public float getTeam1Score(){				/* get score of first team */
		return team1Score;
	}

	public float getTeam2Score(){				/* get score of second team */
		return team2Score;
	}

	public Team getWinner(){					/* get winner of match */
		if(team1Score > team2Score){			/* team1 is the winner */
			return team1;
		}
		if(team2Score > team1Score){			/* team2 is the winner */
			return team2;
		}
		else{
			return null;						/* the match was a tie */
		}
	}

	public void setTeam1(Team firstTeam){		/* set first team */
		team1 = firstTeam;
	}

	public void setTeam2(Team secondTeam){		/* set second team */
		team2 = secondTeam;
	}

	public void setTeam1Score(float score){		/* set first team score */
		team1Score = score;
	}

	public void setTeam2Score(float score){		/* set second team score */
		team2Score = score;
	}
	
	public void setTeam1Rank(int rank){			/* set the rank of the first team */			
		team1rank = rank;
	}
	
	public void setTeam2Rank(int rank){			/* set the rank of the second team */
		team2rank = rank;
	}
}
