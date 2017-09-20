import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class AddTeamFrame extends JFrame {											/*class to create a frame to create/edit a team*/	

	private static final long serialVersionUID = 1L;
	JLabel l1, errorLabel;
	JTextField t1;
	JButton b1,b2,b3;
	JPanel p1,p3,p4;
	AddPlayerFrame f1;
	TeamFrame f2;
	static Tournament to;
	static java.util.List<Tournament> tournaments;
	static Team te;
	private JFrame f;

	public AddTeamFrame(Tournament t,JFrame fr,java.util.List<Tournament> tour) {	/*constructor*/
		f = fr;
		tournaments = tour;
		to = t;
		te = new Team("");
		f.setTitle("Add Team");														/*add team button*/
		f.setSize(600,400);
		f.setLayout(new GridLayout(4, 1));
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p4 = new JPanel();
		p4.setLayout(new FlowLayout());
		errorLabel = new JLabel("");

		l1 = new JLabel("Team Name: ");												/*field to enter team name*/
		t1 = new JTextField(20);
		p1.add(l1);
		p1.add(t1);
		b2 = new JButton("Submit Team");											/*submit team button*/
		b2.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
		   		if (t1.getText().trim().isEmpty()) {								/*checks if name field is filled*/
		   			errorLabel.setForeground(Color.RED);
					errorLabel.setText("Must fill out all fields to Add Team!");	/*prints a warning if the name field is empty*/
		   		} else {
				String name = t1.getText().trim();
				te.setTeamName(name);
				to.addTeam(te);
				updateFile();
				removePanels();
				f2 = new TeamFrame(to,f,tournaments);
		   		}
		   	 }          
		 });
		b3 = new JButton("Back");													/*back button*/
		b3.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				removePanels();
				TeamFrame tf = new TeamFrame(to,f,tournaments);
		   	}          
		});
		p4.add(b3);
		p3.add(b2);
		p3.add(errorLabel);
	
		f.add(p4);
		f.add(p1);
		f.add(p3);
		f.revalidate();
		f.repaint();
	}
	private void removePanels() {													/*method to remove panels so the new panels can be added*/
		f.remove(p1);
		f.remove(p3);
		f.remove(p4);
	}
	private void updateFile() {														/*method to update the file that stores the tournaments */
		try {
				FileOutputStream fileOut = new FileOutputStream("data.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(tournaments);
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