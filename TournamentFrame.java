import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class TournamentFrame extends JFrame {										/*class to create and display the tournament frame*/

	private static final long serialVersionUID = 1L;
	JTextField name,deadline, location, maxTeams, numDiv;
	JLabel nLabel,dLabel, lLabel, mLabel, errorLabel, fLabel, divLabel;
	JButton submit,ba;
	JRadioButton single, division;
	JPanel p1,p2,p21,p3,p4;
	static Format se;
	static java.util.List<Tournament> to;
	private JFrame f;

	public TournamentFrame(JFrame fr,java.util.List<Tournament> tour) {					/*constructor*/
		to = tour;
		f = fr;
		nLabel = new JLabel("Enter Name: ");
		name = new JTextField(20);
		dLabel = new JLabel("Enter Deadline (dd/mm/yyyy): ");
		deadline = new JTextField(10);
		lLabel = new JLabel("Enter Location: ");
		location = new JTextField(20);
		mLabel = new JLabel("Enter Maximum Number Teams: ");
		maxTeams = new JTextField(5);
		fLabel = new JLabel("Enter the Tournament Format");

        single = new JRadioButton("Single Elimination");
		division = new JRadioButton("Divisions");
        divLabel = new JLabel("");

		submit = new JButton("Submit");
		errorLabel = new JLabel("");

		se = new SingleElimination();

		division.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				divLabel.setText("Enter the desired number of Divisions: ");
				se = new Divisions();
				numDiv = new JTextField(5);
				p21.add(numDiv);
				p21.revalidate();
				p21.repaint();
			}
		});
		single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!divLabel.getText().trim().isEmpty()) {
					divLabel.setText("");
					se = new SingleElimination();
					p21.remove(numDiv);
					p21.revalidate();
					p21.repaint();
				}
			}
		});

        ButtonGroup group = new ButtonGroup();
        group.add(single);
        group.add(division);

		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p21 = new JPanel();
		p21.setLayout(new FlowLayout());
		p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p4 = new JPanel();
		p4.setLayout(new FlowLayout());

		fr.setTitle("Create Tournament");
      	fr.setSize(400,500);
      	fr.setLayout(new GridLayout(5, 1));
      	
		submit.addActionListener(new ActionListener() {
       		public void actionPerformed(ActionEvent e) {										/*checks if fields are empty*/
       			if ((name.getText().trim().isEmpty()) || (deadline.getText().trim().isEmpty()) || (location.getText().trim().isEmpty()) || (maxTeams.getText().trim().isEmpty())) {
       				errorLabel.setForeground(Color.RED);
       				errorLabel.setText("Must fill out all fields to create Tournament!"); 		/*warning if their is an empty field*/
       				p3.add(errorLabel);
       			}
       			else {																			/*get and save the input*/
					String n = name.getText();
					String d = deadline.getText();
					String l = location.getText();
					String m = maxTeams.getText();
					int nd = 1;
					if (division.isSelected()) {
						nd = Integer.parseInt(numDiv.getText()); 
					}
					Tournament t = new Tournament(d,n,l,m,nd,se);
					to.add(t);
					updateFile();
					removePanels();
					TournamentInit tourn = new TournamentInit(f,to);
       			}
       	 	}          
      	});
		ba = new JButton("Back");																/*back button*/
		ba.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				removePanels();
				TournamentInit lf = new TournamentInit(f,to);
		   	}
		});

		p4.add(ba);
		p1.add(nLabel);
		p1.add(name);
		p2.add(dLabel);
		p2.add(deadline);
		p1.add(lLabel);
		p1.add(location);
		p2.add(mLabel);
		p2.add(maxTeams);
		p2.add(fLabel);
		p21.add(single);
		p21.add(division);
		p21.add(divLabel);
		p3.add(submit);
		p3.add(errorLabel);

		f.add(p4);
		f.add(p1);
		f.add(p2);
		f.add(p21);
		f.add(p3);
		f.revalidate();
		f.repaint();
	}

	private void removePanels() {															/*method to remove panels so new panels can be added*/
		f.remove(p1);
		f.remove(p2);
		f.remove(p21);
		f.remove(p3);
		f.remove(p4);
	}
	private void updateFile() {																/*method to update the file that stores the tournaments	*/
		try {
				FileOutputStream fileOut = new FileOutputStream("data.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(to);
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
