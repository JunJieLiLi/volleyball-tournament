import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class AddPlayerFrame extends JFrame {									/* class to create a frame to enter/edit player details */

	private static final long serialVersionUID = 1L;
	JLabel l1, l2, l3, l4, l5, errorLabel;
	JTextField t1, t2, t3, t4, t5;
	JButton b1, b2;
	JPanel p1, p2, p3, p4;
	Player p;
	private int ind, pi;
	static Team te;
	static Tournament to;
	static java.util.List<Tournament> tournaments;
	private JFrame f;

	public AddPlayerFrame(int p, int index, Tournament tour, JFrame fr, java.util.List<Tournament> tou) {		/*constructor for the player frame, used when a player is being edited*/
		f = fr;
		to = tour;
		tournaments = tou;
		ind = index;
		generateFrame(p);
	}

	public AddPlayerFrame(int index, Tournament tour, JFrame fr, java.util.List<Tournament> tou) {				/*constructor for the player frame, used when a player is being added*/
		f = fr;
		to = tour;
		tournaments = tou;
		ind = index;
		generateFrame(-1);
	}

	public void generateFrame(int m) {												/* method to create the frame */
		pi = m;
		te = to.getTeams().get(ind);
		f.setTitle("Add Player");
		f.setSize(600, 400);
		f.setLayout(new GridLayout(4, 1));
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p4 = new JPanel();
		p4.setLayout(new FlowLayout());

		l1 = new JLabel("Name: ");
		if (m != -1) {
			Player p = te.getPlayers().get(m);
			t1 = new JTextField(p.getName(), 20);
			t2 = new JTextField(String.valueOf(p.getAge()), 10);
			t3 = new JTextField(String.valueOf(p.getHeight()), 10);
			t4 = new JTextField(String.valueOf(p.getWeight()), 10);
			t5 = new JTextField(p.getGender(), 2);
		} else {
			t1 = new JTextField(20);
			t2 = new JTextField(10);
			t3 = new JTextField(10);
			t4 = new JTextField(10);
			t5 = new JTextField(2);
		}
		p1.add(l1);
		p1.add(t1);
		l2 = new JLabel("Age: ");														/*player age field*/
		p1.add(l2);
		p1.add(t2);
		l3 = new JLabel("Height (cm): ");												/*player height field*/
		p2.add(l3);
		p2.add(t3);
		l4 = new JLabel("Weight (lbs): ");												/*player wieght field*/
		p2.add(l4);
		p2.add(t4);
		l5 = new JLabel("Gender (M/F/other): ");										/*player gender field*/
		p2.add(l5);
		p2.add(t5);
		errorLabel = new JLabel("");
		b1 = new JButton("Submit Player");												/*submit button*/
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((t1.getText().trim().isEmpty()) || (t2.getText().trim().isEmpty())	/*checks if all fields are filled*/
						|| (t3.getText().trim().isEmpty()) || (t4.getText().trim().isEmpty())
						|| (t5.getText().trim().isEmpty())) {
					errorLabel.setForeground(Color.RED);
					errorLabel.setText("Must fill out all fields to Add Player!");		/*warning if a field is empty*/
					p3.add(errorLabel);
				} else {																/*get the input from fields*/

					String name = t1.getText().trim();
					int age = Integer.parseInt(t2.getText().trim());
					float height = Float.valueOf(t3.getText().trim());
					float weight = Float.valueOf(t4.getText().trim());
					String gender = t5.getText().trim();
					p = new Player(name, gender, age, height, weight);
					int k = tournaments.get(tournaments.indexOf(to)).getTeams().indexOf(te);
					Team tempT = tournaments.get(tournaments.indexOf(to)).getTeams().get(k);
					if (pi != -1) {
						Player tempP = tempT.getPlayers().get(pi);
						tempP.setName(name);
						tempP.setAge(age);
						tempP.setHeight(height);
						tempP.setWeight(weight);
						tempP.setGender(gender);
					} else {
						tempT.addPlayer(p);
					}
					updateFile();
					removePanels();
					EditTeamFrame etf = new EditTeamFrame(ind, to, f, tournaments);
				}
			}
		});

		b2 = new JButton("Back");														/*back button*/
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePanels();
				EditTeamFrame etf = new EditTeamFrame(ind, to, f, tournaments);
			}
		});
		p3.add(b1);
		p3.add(errorLabel);
		p4.add(b2);

		f.add(p4);
		f.add(p1);
		f.add(p2);
		f.add(p3);
		f.revalidate();
		f.repaint();
	}

	private void removePanels() {														/*method to remove panels so the new panels can be added*/
		f.remove(p1);
		f.remove(p2);
		f.remove(p3);
		f.remove(p4);
	}

	private void updateFile() {															/*method to update the file that stores the tournaments*/
		try {
			FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tournaments);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}