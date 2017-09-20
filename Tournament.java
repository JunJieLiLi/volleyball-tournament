import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class Tournament implements Serializable{					/* class to create a tournament object with a list of teams and a list of matches, as well as other details */

	List<Team> teams = new ArrayList<Team>();						/* the teams in the tournament */
	List<Match> matches = new ArrayList<Match>();					/* the matches in the tournament */
	boolean matched,editable;
	Format format;													/* the format of the tournament */
	String deadline, name, maxTeams, location;						/* the deadline to sign up for the tournament and the name of the tournament */
	int numDivs;

	public Tournament(String deadline, String name, String maxTeams, String location, int numDivs,Format f){		/* constructor for tournament */
		matched = false;
		editable = true;
		this.deadline = deadline;
		this.name = name;
		this.maxTeams = maxTeams;
		this.location = location;
		this.numDivs = numDivs;
		format = f;
	}

	public List<Team> getTeams(){									/* get the teams in the tournament */
		return teams;
	}

	public int getNumDivs() {										/* get the number of divisions in the tournment */
		return numDivs;
	}

	public List<Match> getMatches(){								/* get the matches of the tournament */
		return matches;
	}
	public boolean getMatched() {									/*method that returns true if the matches have been created*/
		return matched;
	}
	public void setMatched(boolean m) {								/*set the matched variable*/
		matched = m;
	}
	public boolean getEditable() {									/*method that returns true if the tournament is editablee*/
		return editable;
	}
	public void setEditable(boolean e) {							/*set the editable variable*/
		editable = e;
	}
	public Format getFormat(){										/* get the format of the tournament */
		return format;
	}

	public String getDeadline(){									/* get the deadline of the tournament */
		return deadline;
	}

	public String getName(){										/* get the tournament name */
		return name;
	}

	public String getMaxTeams() {									/* get the max teams for the tournament */			
		return maxTeams;
	}

	public String getLocation() {									/* get the location of the tournament */
		return location;
	}

	public void setMaxTeams(String maxTeams) {						/* set the max number of teams for the tournament */
		this.maxTeams = maxTeams;
	}

	public void setLocation(String location) {						/* set the location of the tournament */
		this.location = location;
	}

	public void setFormat(Format tournamentFormat){					/* set the format of the tournament */
		format = tournamentFormat;
	}

	public void setSchedule(Match[] matches){						/* set the schedule of the tournament */
	}

	public void setName(String name){								/* set the tournament name */
		this.name = name;
	}

	public void setNumDivs(int numDivs) {							/* set the number of divisions in the tournment */
		this.numDivs = numDivs;
	}

	public void addTeam(Team newTeam){								/* add a team to the tournament */
		teams.add(newTeam);
	}

	public void delTeam(Team delTeam){								/* remove a player from the team	*/	
		for(int i = 0; i <= teams.size(); i++){
			Team temp = teams.get(i);
			String tempName = temp.name;
			if((tempName.compareTo(delTeam.name)) == 0){
				teams.remove(i);
				return;
			}
		}
	}

	public Team getTeams(Team teams){								/* get the teams in the tournament */
		return teams;
	}

	public boolean isDeadline(){									/* return true if the deadline has passed */
		Date startDate;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();		
		try{
			startDate = df.parse(deadline);
			Calendar cal = Calendar.getInstance();
			currentDate = df.parse(df.format(cal.getTime()));
			if(startDate.after(currentDate)){
				return true;
			}
			else{
				return false;
			}
		}
		catch (ParseException e){
			e.printStackTrace();
			return false;
		}
	}

	public void setMatches(List<Match> m){									/* creates the matches of the tournament */
		matches = m;
	}

	public void editMatches(){										/* edits the matches of the tournament */
	}

	public void editSchedule(){										/* edits the schedule of the tournament */
	}
}
