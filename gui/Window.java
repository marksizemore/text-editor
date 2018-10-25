package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import file.FileOperations;

/**
 * Makes a GUI window.
 * 
 * @author Mark Sizemore
 */
public class Window implements ActionListener
{
	JButton open;
	JButton newButton;
	JButton save;
	JFrame saveFrame;
	JFrame window;
	JPanel topPanel;
	JScrollPane scroll;
	JTextArea document;
	
	/**
	 * Constructor, initializes all class variables and builds the GUI window
	 */
	public Window()
	{
		window = new JFrame();
		topPanel = new JPanel();
		document = new JTextArea();	
		scroll = new JScrollPane(document);
		
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setSize(500, 500);
		
		topPanel.setLayout(new FlowLayout());
		
		open = new JButton("Open");
		open.addActionListener(this);
		topPanel.add(open);
		
		newButton = new JButton("New");
		newButton.addActionListener(this);
		topPanel.add(newButton);
		
		save = new JButton("Save");
		save.addActionListener(this);
		topPanel.add(save);
		
		window.add(scroll, BorderLayout.CENTER);
		window.add(topPanel, BorderLayout.NORTH);

		topPanel.setVisible(true);
		window.setVisible(true);
	}

	/**
	 * Makes the GUI respond to button clicks.
	 * 
	 * @param e the ActionEvent to appropriately respond to
	 */
	public void actionPerformed(ActionEvent e) 
	{
	
		if(e.getActionCommand().equals("Open"))
		{
			openFile();
		}
		else if(e.getActionCommand().equals("New"))
		{
			/* creates a window asking if the user would like to save changes
			 * before creating a new document */
			saveFrame = new JFrame("Save changes?");
			saveFrame.setLayout(new FlowLayout());
			saveFrame.setBounds(window.getWidth()/2 - 122, 
					window.getHeight()/2 - 30, 245, 60);
			
			JButton yes = new JButton("Yes");
			yes.addActionListener(this);
			saveFrame.add(yes);
			
			JButton no = new JButton("No");
			no.addActionListener(this);
			saveFrame.add(no);
			
			saveFrame.setVisible(true);
		}
		else if(e.getActionCommand().equals("Save"))
		{
			saveFile();
		}
		/* the user selected to save before creating a new document */
		else if(e.getActionCommand().equals("Yes"))
		{
			saveFrame.dispose();
			saveFile();
			document.setText("");
		}
		/* the user selected not to save changes before creating
		 * a new document */
		else
		{
			saveFrame.dispose();
			document.setText("");
		}
	}
	
	/**
	 * Creates a file chooser window to let the user pick where 
	 * to save the file.
	 */
	public void saveFile()
	{
		JFileChooser chooser = new JFileChooser();
		int save = chooser.showSaveDialog(null);
		chooser.setVisible(true);
	
		if(save == JFileChooser.APPROVE_OPTION)
		{
			FileOperations.save(document.getText(), chooser.getSelectedFile());
		}
	}
	
	/**
	 * Creates a file chooser window to allow the user to 
	 * choose which file to open.
	 */
	public void openFile()
	{
		JFileChooser chooser = new JFileChooser();
		int open = chooser.showOpenDialog(null);
		chooser.setVisible(true);
		
		if(open == JFileChooser.APPROVE_OPTION)
		{
			String text = FileOperations.open(chooser.getSelectedFile());
			document.setText(text);
		}
	}
}
