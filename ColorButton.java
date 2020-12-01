package P0600;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ColorButton extends JButton implements ActionListener {
	
	private int colorIndex;
	private Color[] buttonColors;
	
	public ColorButton() {
		colorIndex = 0;
		buttonColors = P0605.buttonColors;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();

		btn.setBackground(buttonColors[colorIndex]);		
		colorIndex++;
		
		if(colorIndex >= buttonColors.length)
			colorIndex = 0;
	}

}
