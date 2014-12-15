package FinalProject3270;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Splash {

	int counter; // to update and move the progress bar
	Timer timer; // to start the progress bar
	JProgressBar bar; // shows time until app launch

	Splash() {
		// Holds the image to splash and timer
		JWindow window = new JWindow();
		JFrame frame = new JFrame();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		// sets the layout
		bottom.setLayout(new FlowLayout());
		frame.setLayout(new BorderLayout());

		// initialize the timer, first argument is the delay in the
		// actionListener executing
		// delay in milliseconds

		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// counter continues to update the value that displays on the
				// progress bar
				counter++;
				bar.setValue(counter);
			}
		});

		// sets the progress bar to start at 0 and to increment by the length of
		// time of the second argument
		bar = new JProgressBar(0, 6);
		// current value of the progress bar
		bar.setValue(0);
		// displays the percent finished by the porogress bar
		bar.setStringPainted(true);
		// add a image to the panel
		top.add(new JLabel(new ImageIcon(
				("/Users/tremaynegraham/Desktop/funny.jpg"))));
		// add a label
		bottom.add(new JLabel("Welcome"));
		// add the progress bar
		bottom.add(bar);

		// set the size,location, and layout of the splash screen
		window.add(top, BorderLayout.CENTER);
		window.add(bottom, BorderLayout.SOUTH);
		window.setSize(300, 300);
		window.setLocation(500, 150);
		window.setVisible(true);

		// the timer starts the action listener for the timer which progresses
		// the bar
		// the thread.sleep delays the execution of the program in order for the
		// splash screen to stay up for
		// a 7000 milliseconds seconds
		try {
			timer.start();
			Thread.sleep(7000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// disposes of the splash window
		window.dispose();
	}

	public static void main(String[] args) {

		Splash sp = new Splash();

	}

}