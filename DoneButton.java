package P0600;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class DoneButton extends JButton implements ActionListener {
	
	private Row row;
	
	public DoneButton(Row row) {
		this.row = row;
		initGUI();
	}
	
	private void initGUI() {
		this.setText("Done");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// If is right guess
		
		//else
		row.UnlockNextRow();
	}
}
