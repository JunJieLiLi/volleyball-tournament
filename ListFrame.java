import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

class ListFrame extends JFrame {												/*class for creating and displaying the list of tournaments*/

	private static final long serialVersionUID = 1L;
	TeamFrame f;
 	JPanel p1,listPane,p2;
	JButton ba;
 	JLabel l1,l2;
	static int i;
	static java.util.List<Tournament> to;
	static Tournament tt;
	private JFrame fra,ff;

 	ListFrame(JFrame fr,java.util.List<Tournament> tournaments) {				/*creates the frame to display the tournaments*/
		fra = fr;
		to = tournaments;
		fra.setTitle("List Teams");
       	fra.setSize(400,800);
		listPane=new JPanel();
      	listPane.setLayout(new BoxLayout(listPane,BoxLayout.Y_AXIS));
		p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		ba = new JButton("Back");												/*back button*/
		ba.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				removePanels();
				TournamentInit lf = new TournamentInit(fra,to);
		   	}          
		});
		p2.add(ba);
		for (i=0; i<tournaments.size();i++) {									/*gets all the tournments from the list of tournaments*/
			p1 = new JPanel();
            p1.setPreferredSize(new Dimension(400,65));
            p1.setMaximumSize(p1.getPreferredSize());
			l1 = new JLabel((i+1)+". "+"Name: \t"+tournaments.get(i).getName());
			p1.add(l1);
			l2 = new JLabel("Deadline: \t"+tournaments.get(i).getDeadline());
			p1.add(l2);
			JButton b = new JButton("Edit Tournament");							/*edit tournament button (one for each tournament)*/
			JButton bb = new JButton("View Schedule");							/*view schedule button*/
			b.addActionListener(new ActionListener() {
				final int j=i;
		   		public void actionPerformed(ActionEvent e) {
					tt = to.get(j);
					if (tt.getEditable()) {
						if (!tt.isDeadline()) {
							tt.setEditable(false);
						}
						removePanels();
						f = new TeamFrame(to.get(j),fra,to);
					}
		   	 	}          
		  	});
			bb.addActionListener(new ActionListener() {
				final int j=i;
		   		public void actionPerformed(ActionEvent e) {
					tt = to.get(j);
					if (!tt.isDeadline()) {
						removePanels();
						ff = new ScheduleFrame(tt,fra,to);
					}
		   	 	}          
		  	});
			p1.add(b);
			p1.add(bb);
			listPane.add(p1);
		}
		Component glue = Box.createVerticalGlue();
		listPane.add(glue);
		fra.add(p2);
		fra.add(listPane);
		fra.revalidate();
		fra.repaint();
	}
	private void removePanels() {												/*removes the panels from the frame so the updated panels can be added*/
		fra.remove(listPane);
		fra.remove(p2);
	}
}
