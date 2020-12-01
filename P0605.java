package P0600;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class P0605 extends JFrame {
	public static Color[] buttonColors = { Color.green, Color.orange, Color.yellow, Color.red, Color.gray, Color.cyan};
	public static Color[] resultColors;
	
	public static JButton btnres1, btnres2, btnres3, btnres4;

	JPanel pnlMain;
	JPanel pnlRows;
	JPanel pnlTop;
	JPanel pnlBottom;
	
	JPanel pnlOptions;	
	Row pnlRow;
	
	Color green;
	Color brown;
	
	public static int numberRows;
	public static int currentRowIndex;

	public static void main(String[] args) {
		P0605 fr = new P0605();
		fr.setExtendedState(MAXIMIZED_BOTH);
		fr.setVisible(true);
	}

	public P0605() {
		super("MasterMind");
		green = new Color(0, 176, 80);
		brown = new Color(139,69,19);
		numberRows = 12;
		currentRowIndex = 0;
		resultColors = new Color[4];
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,800);
		
		btnres1 = new JButton();
		btnres2 = new JButton();
		btnres3 = new JButton();
		btnres4 = new JButton();
		
		pnlMain = new JPanel();
		pnlRows = new JPanel();
		pnlTop = new JPanel();
		pnlBottom = new JPanel();
		
		pnlOptions = new JPanel();

		pnlRows.setBackground(brown);
		pnlTop.setBackground(green);
		pnlBottom.setBackground(green);
		
		pnlOptions.setBackground(brown);
		
		pnlRows.setLayout(new BoxLayout(pnlRows, BoxLayout.Y_AXIS));
		pnlMain.setLayout(new BorderLayout());
		
		pnlTop.setPreferredSize(new Dimension(200, 50));
		pnlBottom.setPreferredSize(new Dimension(200, 100));
		
		pnlOptions.setPreferredSize(
			new Dimension(this.getWidth()*2, this.getHeight())
		);		

		btnres1.setPreferredSize(new Dimension(50, 50));
		btnres2.setPreferredSize(new Dimension(50, 50));
		btnres3.setPreferredSize(new Dimension(50, 50));
		btnres4.setPreferredSize(new Dimension(50, 50));
		
		btnres1.setEnabled(false);
		btnres2.setEnabled(false);
		btnres3.setEnabled(false);
		btnres4.setEnabled(false);
		createColorResult();
		
		for(int i = 0; i < 4; i++) {
			System.out.println(resultColors[i]);			
		}
		
		for(int i = 0; i<numberRows; i++) {
			if(i == 0) {
				pnlRows.add(new Row(false));				
			}
			else {
				pnlRows.add(new Row());
			}
		}
		pnlBottom.add(btnres1);
		pnlBottom.add(btnres2);
		pnlBottom.add(btnres3);
		pnlBottom.add(btnres4);
		
		pnlMain.add(BorderLayout.CENTER, pnlRows);
		pnlMain.add(BorderLayout.NORTH, pnlTop);
		pnlMain.add(BorderLayout.SOUTH, pnlBottom);
		
		getContentPane().add(BorderLayout.CENTER, pnlMain);
		// getContentPane().add(BorderLayout.EAST, pnlOptions);		
	}
	
	/**
	 *  Créé une combinaison de couleur aléatoirement
	 */
	private void createColorResult() {
		Random rand = new Random();
		List<Color> colors = SetList(buttonColors);
		
		for(int i = 0; i < 4; i++) {
			int ran = rand.nextInt(colors.size());
			resultColors[i] = colors.get(ran);
			colors.remove(ran);
		}
	}
	
	public Row[] getRows() {
		List<Row> rows = new ArrayList<>();
		for(int i = 0; i < pnlRows.getComponentCount(); i++) {
			rows.add((Row)pnlRows.getComponent(i));
		}
		Row[] newRows = new Row[rows.size()];
		rows.toArray(newRows);

		return newRows;
	}
	
	private List<Color> SetList(Color[] guessColors) {
		// TODO Auto-generated method stub
		List<Color> colors = new ArrayList<Color>();
		for(Color c : guessColors) {
			colors.add(c);
		}
		
		return colors;
	}
}




