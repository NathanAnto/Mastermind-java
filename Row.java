package P0600;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;


@SuppressWarnings("serial")
/**
 * Créée unev ligne pour que le joueur devine
 * 
 * @author Nathan Antonietti
 */
public class Row extends JPanel {

	public boolean isLocked;
	public Color[] resColors = new Color[4];

	JPanel pnlButtons, pnlHints;

	DoneButton btnDone;
	ColorButton btn1, btn2, btn3, btn4;
	JButton btnHint1, btnHint2, btnHint3, btnHint4;
	
	// private int colorindex;
	private Color[] hintColors = { Color.black, Color.white };
	private Color brown;

	private int inArray = 0;
	private int inGoodPos = 0;

	public Row() {
		isLocked = true;
		initGUI();
	}

	public Row(boolean isLocked) {
		this.isLocked = isLocked;
		initGUI();
	}

	private void initGUI() {
		brown = new Color(139, 69, 19);

		this.setLayout(new FlowLayout());

		pnlButtons = new JPanel();
		pnlHints = new JPanel();

		pnlButtons.setLayout(new FlowLayout());
		pnlHints.setLayout(new GridLayout(2, 2));

		btnDone = new DoneButton(this);

		btn1 = new ColorButton();
		btn2 = new ColorButton();
		btn3 = new ColorButton();
		btn4 = new ColorButton();

		btnDone.setPreferredSize(new Dimension(70, 30));
		btn1.setPreferredSize(new Dimension(50, 50));
		btn2.setPreferredSize(new Dimension(50, 50));
		btn3.setPreferredSize(new Dimension(50, 50));
		btn4.setPreferredSize(new Dimension(50, 50));

		btnDone.addActionListener(new DoneButton(this));
		btn1.addActionListener(new ColorButton());
		btn2.addActionListener(new ColorButton());
		btn3.addActionListener(new ColorButton());
		btn4.addActionListener(new ColorButton());

		btnHint1 = new JButton();
		btnHint2 = new JButton();
		btnHint3 = new JButton();
		btnHint4 = new JButton();

		btnHint1.setPreferredSize(new Dimension(25, 25));
		btnHint2.setPreferredSize(new Dimension(25, 25));
		btnHint3.setPreferredSize(new Dimension(25, 25));
		btnHint4.setPreferredSize(new Dimension(25, 25));

		btnHint1.setBackground(Color.gray);
		btnHint2.setBackground(Color.gray);
		btnHint3.setBackground(Color.gray);
		btnHint4.setBackground(Color.gray);

		pnlButtons.setBackground(brown);
		this.setBackground(brown);

		pnlButtons.add(btnDone);
		pnlButtons.add(btn1);
		pnlButtons.add(btn2);
		pnlButtons.add(btn3);
		pnlButtons.add(btn4);

		pnlHints.add(btnHint1);
		pnlHints.add(btnHint2);
		pnlHints.add(btnHint3);
		pnlHints.add(btnHint4);

		this.add(pnlButtons);
		this.add(pnlHints);

		LockButtons();
	}

	/**
	 * @return Retourne la ligne actuel
	 */
	public Row getRow() {
		return this;
	}

	private void LockButtons() {
		if (isLocked) {
			// For each button in row
			for (int i = 0; i < pnlButtons.getComponentCount(); i++) {
				pnlButtons.getComponent(i).setEnabled(false);
			}

			// For each button in hints
			for (int i = 0; i < pnlHints.getComponentCount(); i++) {
				pnlHints.getComponent(i).setEnabled(false);
			}
		}
	}

	private void UnLockButtons() {
		if (isLocked) {
			// For each button in row
			for (int i = 0; i < pnlButtons.getComponentCount(); i++) {
				pnlButtons.getComponent(i).setEnabled(true);
			}

			// For each button in hints
			for (int i = 0; i < pnlHints.getComponentCount(); i++) {
				pnlHints.getComponent(i).setEnabled(true);
			}
		}
	}

	/**
	 * Active la prochaine ligne si la réponse est fausse
	 */
	public void UnlockNextRow() {
		Color[] guessedColors = new Color[4];

		guessedColors = getGuessedColors();

		// if is correct guess
		if (isEqual(guessedColors)) {
			JOptionPane.showMessageDialog(this, "Bravo! Vous avez gagné.");
			SwingUtilities.getWindowAncestor(this).setVisible(false);

			P0605 newGame = new P0605();
			newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			newGame.setVisible(true);
			System.out.println("WIN !!!");

		} else {

			setHints(guessedColors);

			System.out.println("Next row");
			P0605 frame = (P0605) SwingUtilities.getWindowAncestor(this);
			Row[] rows = frame.getRows();
			isLocked = true;

			// if on last row !!!!
			if (!(P0605.currentRowIndex >= P0605.numberRows)) {
				System.out.println("Locked next row");
				rows[P0605.currentRowIndex].LockButtons();
				P0605.currentRowIndex++;

				if (P0605.currentRowIndex < P0605.numberRows) {
					System.out.println("Unlocked next row");
					rows[P0605.currentRowIndex].UnLockButtons();
				}
				else {
					loseGame();
				}
			} else {
				loseGame();
			}
		}
	}

	/**
	 * Récupère les couleurs devinées
	 * 
	 * @return les couleurs
	 */
	private Color[] getGuessedColors() {
		Color[] guessedColors = new Color[4];

		for (int i = 0; i < guessedColors.length; i++) {
			guessedColors[i] = pnlButtons.getComponent(i + 1).getBackground();
		}

		return guessedColors;
	}
	
	private void loseGame() {

		JOptionPane.showMessageDialog(this, "Bravo! Vous avez perdu.");
		SwingUtilities.getWindowAncestor(this).setVisible(false);

		P0605 newGame = new P0605();
		newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		newGame.setVisible(true);
		System.out.println("PERDU !!!");
	}

	/**
	 * Ajoute les indices
	 * 
	 * @param guessColors
	 */
	private void setHints(Color[] guessColors) {

		int index = 0;
		for (Color c : guessColors) {
			for (Color c2 : P0605.resultColors) {
				// si la couleur est dans le code final
				if (c == c2) {					
					if(c == P0605.resultColors[index]) {
						inGoodPos++;
					} else {
						inArray++;
					}
				}
			}
			index++;
		}
		setBlackColor();
		setWhiteColor();
	}

	private void setBlackColor() {
		for (int i = 0; i < inGoodPos; i++) {
			if (btnHint1.getBackground() == Color.gray) {
				btnHint1.setBackground(hintColors[0]);
			} else if (btnHint2.getBackground() == Color.gray) {
				btnHint2.setBackground(hintColors[0]);
			} else if (btnHint3.getBackground() == Color.gray) {
				btnHint3.setBackground(hintColors[0]);
			} else if (btnHint4.getBackground() == Color.gray) {
				btnHint4.setBackground(hintColors[0]);
			}
		}
	}

	private void setWhiteColor() {
		for (int i = 0; i < inArray; i++) {
			if (btnHint1.getBackground() == Color.gray) {
				btnHint1.setBackground(hintColors[1]);
			} else if (btnHint2.getBackground() == Color.gray) {
				btnHint2.setBackground(hintColors[1]);
			} else if (btnHint3.getBackground() == Color.gray) {
				btnHint3.setBackground(hintColors[1]);
			} else if (btnHint4.getBackground() == Color.gray) {
				btnHint4.setBackground(hintColors[1]);
			}
		}
	}

	/**
	 * Test si le joueur a gagné
	 * 
	 * @param guessColors les couleurs du joueurs
	 * @return vrai ou faux si le joueur gagne ou pas
	 */
	private boolean isEqual(Color[] guessColors) {

		if (P0605.resultColors[0] == guessColors[0] && P0605.resultColors[1] == guessColors[1]
		 && P0605.resultColors[2] == guessColors[2] && P0605.resultColors[3] == guessColors[3]) {
			return true;
		} else {
			return false;
		}
	}
}