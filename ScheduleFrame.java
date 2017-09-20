import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScheduleFrame extends JFrame {												/*class to create and display the schedule frame(either divisions or single elmination)*/
	JLabel j1,j2;
	JButton b1, ba;
	JPanel p1, p2,p3;
	Divisions d1;
	JTextArea f1;
    Team t1;
    Match m1;
	SingleElimination s1;
	JFrame f;
	static java.util.List<Tournament> tournaments;
	TournamentFrame tou1;

	public ScheduleFrame(Tournament tt,JFrame fra,java.util.List<Tournament> to){   	/*constructor*/
		f=fra;
		tournaments = to;
		ba = new JButton("Back");														/*back button*/
		ba.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				removePanels(f);
				ListFrame lf = new ListFrame(f,tournaments);
		   	}          
		});

		f.setTitle("Schedule Frame");
		f.setSize(600,400);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		j2 = new JLabel("");

		String[] names = new String[tt.getTeams().size()]; 
		if (tt.getFormat().getType().equals("s")) {										/*get the format of the tournament*/
			j1 = new JLabel("Single Elimination Schedule: ");
			SingleElimination s = new SingleElimination();
			if (!tt.getMatched()) {
				tt.setMatched(true);
				s.setSchedule(tt);   													/* set the schedule for single elimination*/
			}
		}
		else if (tt.getFormat().getType().equals("d")) {								/*get the format of the tournament*/
			j1 = new JLabel("Divisions Schedule: ");
			Divisions d = new Divisions();
			if (!tt.getMatched()) {
				tt.setMatched(true);
				d.setSchedule(tt);   													/*set the schedule for divisions*/
			}
		}

		p1.add(j1);
		p3.add(ba);
		f.add(p3);
		if (tt.getFormat().getType().equals("s")) {
			for (int i=0; i<tt.getMatches().size(); i++) { 
				names[i] =  tt.getMatches().get(i).getTeam1().getTeamName();    			/* get the name of teams and store them in the name list */
				names[i+1] =  tt.getMatches().get(i).getTeam2().getTeamName();
				j2.setText(j2.getText()+" Match ["+names[i]+" .vs. "+names[i+1]+"] ");      /* display each of team in the text area to be displayed*/ 
				p2.add(j2);
			}
		}
		else {
			for (int j=0; j<tt.getNumDivs(); j++) {
				j2.setText(j2.getText()+"Division "+j+1+" {");
				for (int i=0; i<tt.getMatches().size(); i++) { 
					names[i] =  tt.getMatches().get(i).getTeam1().getTeamName();    			/* get the name of teams and store them in the name list */
					names[i+1] =  tt.getMatches().get(i).getTeam2().getTeamName();
					j2.setText(j2.getText()+" Match ["+names[i]+" .vs. "+names[i+1]+"] ");      /* display each of team in the text area to be displayed*/ 
					p2.add(j2);
				}
				j2.setText(j2.getText()+" }");
			}
		}
		f.add(p1);
		f.add(p2);
		f.revalidate();
		f.repaint();
	}

	private void removePanels(JFrame f) {												/*removes the panels from the frame so the updated panels can be added*/
		f.remove(p1);
		f.remove(p2);
		f.remove(p3);
	}	
}
