import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class EditTeamFrame extends JFrame {																	/*class to create the edit team frame*/
	static Tournament to;
	static java.util.List<Tournament> tournaments;
	static Team te;
	private JFrame f,f1,ff;
	private int i,ind;
	JLabel l1;
	JTextField tf1;
	JPanel p1,p2,p3,p4,pp1;
	JButton b1,b2,b3,b4,bb;

	public EditTeamFrame(int index,Tournament t,JFrame fr,java.util.List<Tournament> tour) {			/*constuctor*/
		to = t;
		f = fr;
		tournaments = tour;
		ind = index;
		te = to.getTeams().get(index);
		f.setTitle("Edit Team");
		f.setSize(600,600);
   		f.setLayout(new GridLayout(4, 1));

		p1 = new JPanel();
      	p1.setLayout(new FlowLayout());
		p2 = new JPanel();
      	p2.setLayout(new FlowLayout());
		p3 = new JPanel();
      	p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
		p4 = new JPanel();
      	p4.setLayout(new FlowLayout());

		tf1 = new JTextField(te.getTeamName(),20);
		b1 = new JButton("Back");																		/*back button*/
		b1.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				removePanels();
				f1 = new TeamFrame(to,f,tournaments);
		   	}
		});
		b2 = new JButton("Submit");																		/*submit button*/
		b2.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				removePanels();
				te.setTeamName(tf1.getText());
				updateFile();
				f1 = new TeamFrame(to,f,tournaments);
		   	}          
		});
		b3 = new JButton("Delete");																		/*delete button*/
		b3.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				tournaments.get(tournaments.indexOf(to)).getTeams().remove(te);
				updateFile();
				removePanels();
				f1 = new TeamFrame(to,f,tournaments);
		   	}          
		});
		b4 = new JButton("Add Player");																	/*add player button*/
		b4.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				removePanels();
				f1 = new AddPlayerFrame(ind,to,f,tournaments);
		   	}
		});
		for (i=0; i<te.getPlayers().size();i++) {
			pp1 = new JPanel();
			l1 = new JLabel((i+1)+". "+te.getPlayers().get(i).getName());
			pp1.add(l1);
			JButton bb1 = new JButton("Edit Player");													/*edit player button*/
			bb1.addActionListener(new ActionListener() {
				final int j=i;
		   		public void actionPerformed(ActionEvent e) {
					removePanels();
					ff = new AddPlayerFrame(j,ind,to,f,tournaments);
		   	 	}
		  	});
			JButton bb2 = new JButton("Delete Player");													/*delete player button*/
			bb2.addActionListener(new ActionListener() {
				final int j=i;
		   		public void actionPerformed(ActionEvent e) {
					Team temptt = tournaments.get(tournaments.indexOf(to)).getTeams().get(ind);
					temptt.getPlayers().remove(temptt.getPlayers().get(j));
					removePanels();
					updateFile();
					ff = new EditTeamFrame(ind,to,f,tournaments);
		   	 	}
		  	});
			pp1.add(bb1);
			pp1.add(bb2);
			p3.add(pp1);
		}

		p1.add(b1);
		p1.add(b3);
		p2.add(tf1);
		p4.add(b2);
		p4.add(b4);

		f.add(p1);
		f.add(p2);
		f.add(p3);
		f.add(p4);
		f.revalidate();
		f.repaint();
	}

	private void removePanels() {														/*method to remove panels so the new panels can be added*/
		f.remove(p1);
		f.remove(p2);
		f.remove(p3);
		f.remove(p4);
	}
	private void updateFile() {															/*method to update the file that stores the tournaments */
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