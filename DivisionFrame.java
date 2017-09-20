import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

public class DivisionFrame extends JFrame{											/*class to create a display the Divisions frame, for view schedule*/
	JLabel j1,j2;
	JButton b1;
	JPanel p1;
	Divisions d1;
	JTextArea f1;
    Team t1;
    Match m1;
	SingleElimination s1;
	Divisions d;	
	Tournament tt;
	TournamentFrame tou1;	
	
	public DivisionFrame(Divisions d1){												/*constructor for the frame*/
		d=d1;		
		setTitle("Schedule Frame");		
		setSize(600,400);
		
		p1=new JPanel();		
		j1= new JLabel("Divisions Schedule: ");		
		f1 = new JTextArea();

		String[] names = new String[tt.getTeams().size()];							/*list of team names*/
		d1.setSchedule(tt);
		
		for(int i=0; i<tt.getTeams().size(); i++){				
			names[i] =  tt.getTeams().get(i).getTeamName();							/* get the name of teams and store them in the name list */
			f1.setText(names[i]);       											/* display each of team in the text area  to be displayed */
			
		}		
		p1.add(j1);		
		p1.add(f1);
	}
}