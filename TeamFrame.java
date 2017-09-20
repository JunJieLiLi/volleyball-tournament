import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class TeamFrame extends JFrame {														/* class for creating and displaying all teams */

	private static final long serialVersionUID = 1L;
	JLabel name,deadline;
	JButton b1,b2,b3,b4;
	JComboBox<String> cb;
	JPanel p1,p2,p3,p4;
	static Tournament to;
	static java.util.List<Tournament> tournaments;
	private JFrame f,f1,f2;
	
	TeamFrame(Tournament t,JFrame fr,java.util.List<Tournament> tour) {					/* Creates frame for editing teams */
		f = fr;
		tournaments = tour;
		to = t;
		f.setTitle("Manage Teams");
		f.setSize(400,600);
   		f.setLayout(new GridLayout(4, 1));
		p1 = new JPanel();
      	p1.setLayout(new FlowLayout());
		p2 = new JPanel();
      	p2.setLayout(new FlowLayout());
		p3 = new JPanel();
      	p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
		p4 = new JPanel();
		p4.setLayout(new FlowLayout());

		name = new JLabel("Name: " + t.getName());
		deadline = new JLabel("Deadline: " + t.getDeadline());
		String[] names = new String[t.getTeams().size()];
		for (int i=0; i<t.getTeams().size(); i++) {										/*creates the array of team names*/
			names[i] = t.getTeams().get(i).getTeamName();
		}
		cb = new JComboBox<String>(names);												/*uses the array to populate the combobox*/
		b1 = new JButton("Add Team");													/*add team button*/
		b1.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				removePanels();
				f1 = new AddTeamFrame(to,f,tournaments);
		   	}          
		});
		b2 = new JButton("Edit Team");													/*edit team button*/
		b2.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				int index = cb.getSelectedIndex();
				removePanels();
				f2 = new EditTeamFrame(index,to,f,tournaments);
		   	}          
		});
		b3 = new JButton("Back");														/*back button*/
		b3.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				removePanels();
				ListFrame lf = new ListFrame(f,tournaments);
		   	}          
		});
		b4 = new JButton("Delete");														/*delete team button*/
		b4.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				tournaments.remove(to);
				updateFile();															/*calls the function to update the file that stores tournament data*/
				removePanels();
				ListFrame lf = new ListFrame(f,tournaments);
		   	}          
		});
		for (int i=0; i<t.getTeams().size();i++) {										/*displays the team names*/
			JLabel temp = new JLabel((i+1)+". Name: "+t.getTeams().get(i).getTeamName());
			p3.add(temp);
		} 
		
		p4.add(b3);
		p4.add(b4);		
		p1.add(name);
		p1.add(deadline);
		p2.add(b1);	
		p2.add(cb);
		p2.add(b2);
		f.add(p4);
		f.add(p1);
		f.add(p2);
		f.add(p3);
		f.revalidate();
		f.repaint();
	}
	private void removePanels() {														/*removes panels from the frame so new panels can be added*/
		f.remove(p1);
		f.remove(p2);
		f.remove(p3);
		f.remove(p4);
	}
	private void updateFile() {															/*function to update the file which stores all tournament information*/
		try {
				FileOutputStream fileOut = new FileOutputStream("data.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(tournaments);											/*stores the tournaments as objects using the serializable feature of java*/
				out.close();
				fileOut.close();
			}
			catch(FileNotFoundException ex) {
				ex.printStackTrace();		
			}
			catch(IOException ex) {
				ex.printStackTrace();		
			}
	}
}