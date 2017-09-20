import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;

public class SingleElimination implements Format,Serializable{						/*single elimination class, uses the format interface */

	@SuppressWarnings("null")
	@Override
	public void setSchedule(Tournament tournament){									/*set the schedule for the tournament*/
		ArrayList<Team> teamList = new ArrayList<Team>();
		try {																		/*creates a copy of the list of teams*/
			for (int i=0; i < tournament.getTeams().size(); i++) {
				teamList.add(((Team) tournament.getTeams().get(i).clone()));
			}
		}
		catch(CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		ArrayList<Team> randTeamList = new ArrayList<Team>();
		Random rand = new Random();
		int size = teamList.size();
		for (int i=0; i < size; i++) {												/*randomly assign teams to a list, used to create matches*/
			int r = rand.nextInt(teamList.size());
				Team teamName = teamList.get(r);
				randTeamList.add(teamName);
				teamList.remove(r);													/*removes the added team so it is not added again*/
		}

		List<Match> matches = new ArrayList<Match>();
		int i = 0;
		while (i < randTeamList.size()) {											/*assigns the random list of teams to matches */
			if ((randTeamList.size() % 2) != 1) {									/*checks if the number of teams is even*/
				Match match = new Match();
				match.setTeam1(randTeamList.get(i));
				match.setTeam2(randTeamList.get(i+1));
				matches.add(match);
				i = i+2;
			} else {
				randTeamList.remove(rand.nextInt(randTeamList.size()));				/*uneven number of teams*/
				continue;
			}
		}
		tournament.setMatches(matches);
	}

	@Override
	public void editSchedule(Tournament tournament) {								/*edit the tournament schedule*/
		// TODO Auto-generated method stub
		
	}

	@Override
	public Team[] getSchedule(Tournament tournament) {								/*get the tournament schedule*/
		// TODO Auto-generated method stub
		return null;
	}
	public String getType() {														/*get the type (format) of the tournament (divisions or single elimination)*/
		return "s";
	}
}