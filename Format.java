import java.util.List;

public interface Format{									/*interface for the various formats*/
	public String getType();

	public void setSchedule(Tournament tournament);			/*set the schedule for the tournament*/

	public void editSchedule(Tournament tournament);		/*edit the tournament schedule*/

	Team[] getSchedule(Tournament tournament);				/*get the tournament schedule*/
}
