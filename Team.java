import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Team implements Serializable,Cloneable{				/* class to create a team object */

	String name;													/* name of team */
	List<Player> players = new ArrayList<Player>();					/* list of player names */
	List<Player> coaches = new ArrayList<Player>();
	int wins = 0, losses = 0;										/* total wins and losses for the team, each object starts with 0 */

	public Team(String name){										/* constructor for team */
		this.name = name;
	}

	public String getTeamName(){									/* get team name */
		return name;
	}

	public List<Player> getPlayers(){								/* get all players on team */
		return players;
	}

	
	public List<Player> getCoaches(){								/* get all coaches */
		return(coaches);		
	}	

	public int getWins(){											/* get the number of wins for the team */
		return wins;
	}

	public int getLosses(){											/* get the number of losses for the team */
		return losses;
	}

	public void setTeamName(String name){							/* set team name */
		this.name = name;
	}	

	public void addPlayer(Player newPlayer){						/* add a player to the team */
		players.add(newPlayer);
	}

	public void addCoach(Player newCoach){							/* add a coach to the team */
		coaches.add(newCoach);
	}
	
	public void addWin(){											/* add a win */
		wins++;
	}

	public void addLoss(){											/* add a loss */
		losses--;
	}

	public void delPlayer(Player delPlayer){						/* remove a player from the team */	
		for(int i = 0; i <= players.size(); i++){
			Player temp = players.get(i);
			if((temp.name.compareTo(delPlayer.name)) == 0){
				players.remove(i);
				return;
			}
		}
	}	

	public void delCoach(Player delCoach){							/* remove a coach from the team	*/
		for(int i = 0; i <= coaches.size(); i++){
			Player temp = coaches.get(i);
			if((temp.name.compareTo(delCoach.name)) == 0){
				coaches.remove(i);
				return;
			}
		}
	}

	public void removeWin(){										/* remove a win */
		wins--;
	}

	public void removeLoss(){										/* remove a loss */
		losses--;
	}
	protected Object clone() throws CloneNotSupportedException {	/*method to clone the object*/
		return super.clone();
	}
}