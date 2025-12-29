package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JToggleButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;

public class FrmTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTest frame = new FrmTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("OOIT grupa 4");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_pnlCenter.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_pnlCenter.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlCenter.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlCenter.setLayout(gbl_pnlCenter);

		JToggleButton tglbtnCrvena = new JToggleButton("Crvena");
		tglbtnCrvena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dlm.addElement(tglbtnCrvena.getText());
			}
		});
		buttonGroup.add(tglbtnCrvena);
		GridBagConstraints gbc_tglbtnCrvena = new GridBagConstraints();
		gbc_tglbtnCrvena.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCrvena.gridx = 0;
		gbc_tglbtnCrvena.gridy = 0;
		pnlCenter.add(tglbtnCrvena, gbc_tglbtnCrvena);

		JLabel lblCrvena = new JLabel("Crvena");
		GridBagConstraints gbc_lblCrvena = new GridBagConstraints();
		gbc_lblCrvena.anchor = GridBagConstraints.WEST;
		gbc_lblCrvena.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrvena.gridx = 1;
		gbc_lblCrvena.gridy = 0;
		pnlCenter.add(lblCrvena, gbc_lblCrvena);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		pnlCenter.add(scrollPane, gbc_scrollPane);

		JList lstBoje = new JList();

		lstBoje.setModel(dlm);
		scrollPane.setViewportView(lstBoje);

		JToggleButton tglbtnPlava = new JToggleButton("Plava");
		tglbtnPlava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlm.addElement(tglbtnPlava.getText());
			}
		});
		buttonGroup.add(tglbtnPlava);
		GridBagConstraints gbc_tglbtnPlava = new GridBagConstraints();
		gbc_tglbtnPlava.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPlava.gridx = 0;
		gbc_tglbtnPlava.gridy = 1;
		pnlCenter.add(tglbtnPlava, gbc_tglbtnPlava);

		JLabel lblPlava = new JLabel("Plava");
		GridBagConstraints gbc_lblPlava = new GridBagConstraints();
		gbc_lblPlava.anchor = GridBagConstraints.WEST;
		gbc_lblPlava.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlava.gridx = 1;
		gbc_lblPlava.gridy = 1;
		pnlCenter.add(lblPlava, gbc_lblPlava);

		JToggleButton tglbtnZuta = new JToggleButton("Zuta");
		tglbtnZuta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dlm.addElement(tglbtnZuta.getText());
			}
		});

		buttonGroup.add(tglbtnZuta);
		GridBagConstraints gbc_tglbtnZuta = new GridBagConstraints();
		gbc_tglbtnZuta.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnZuta.gridx = 0;
		gbc_tglbtnZuta.gridy = 2;
		pnlCenter.add(tglbtnZuta, gbc_tglbtnZuta);

		JLabel lblZuta = new JLabel("Zuta");
		GridBagConstraints gbc_lblZuta = new GridBagConstraints();
		gbc_lblZuta.anchor = GridBagConstraints.WEST;
		gbc_lblZuta.insets = new Insets(0, 0, 5, 5);
		gbc_lblZuta.gridx = 1;
		gbc_lblZuta.gridy = 2;
		pnlCenter.add(lblZuta, gbc_lblZuta);

		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		JButton btnIspisi = new JButton("Antistres dugme :-)");
		btnIspisi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Antistres dugme", "Poruka", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		pnlSouth.add(btnIspisi);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JButton btnAddColor = new JButton("Dodaj boju");
		btnAddColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgTest dlgDodajBoju=new DlgTest();
				dlgDodajBoju.setVisible(true);
				
				if (dlgDodajBoju.isOk()) {
					//nakon zatvaranja dijaloga izvrsava se ovaj deo koda
					String red = dlgDodajBoju.getTextFieldRed().getText();
					String green = dlgDodajBoju.getTextFieldGreen().getText();
					String blue = dlgDodajBoju.getTextFieldBlue().getText();
					String stringColor = red + " " + green + " " + blue;
					dlm.addElement(stringColor);
					Color color = new Color(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
					pnlCenter.setBackground(color);
				}
			}
		});
		GridBagConstraints gbc_btnAddColor = new GridBagConstraints();
		gbc_btnAddColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddColor.gridx = 2;
		gbc_btnAddColor.gridy = 2;
		pnlCenter.add(btnAddColor, gbc_btnAddColor);

		// labela koja menja boju
		JLabel lblDodatneBoje = new JLabel("Dodatne boje");
		GridBagConstraints gbc_lblDodatneBoje = new GridBagConstraints();
		gbc_lblDodatneBoje.anchor = GridBagConstraints.EAST;
		gbc_lblDodatneBoje.insets = new Insets(0, 0, 0, 5);
		gbc_lblDodatneBoje.gridx = 0;
		gbc_lblDodatneBoje.gridy = 3;
		pnlCenter.add(lblDodatneBoje, gbc_lblDodatneBoje);
		// padajuca lista
		JComboBox<String> comboBoxBoje = new JComboBox<String>();
		comboBoxBoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String choosenColor = comboBoxBoje.getSelectedItem().toString();
				dlm.addElement(choosenColor);
				switch (choosenColor) {
				case "Zelena":
					lblDodatneBoje.setForeground(Color.green);
					break;
				case "Ljubicasta":
					lblDodatneBoje.setForeground(Color.magenta);
					break;
				case "Narandzasta":
					lblDodatneBoje.setForeground(Color.orange);
					break;
				default:
					break;
				}
			}
		});
		comboBoxBoje.setModel(new DefaultComboBoxModel<String>(new String[] { "Ljubicasta", "Zelena", "Narandzasta" }));
		GridBagConstraints gbc_comboBoxBoje = new GridBagConstraints();
		gbc_comboBoxBoje.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxBoje.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxBoje.gridx = 1;
		gbc_comboBoxBoje.gridy = 3;
		pnlCenter.add(comboBoxBoje, gbc_comboBoxBoje);

		// dodavanje tekstualnog polja
		JTextField textFieldDodatnaBoja = new JTextField();
		textFieldDodatnaBoja.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dlm.addElement(textFieldDodatnaBoja.getText());
					textFieldDodatnaBoja.setText("");
				}
			}
		});
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		pnlCenter.add(textFieldDodatnaBoja, gbc_textField);
		textFieldDodatnaBoja.setColumns(10);
		
		JButton btnIzmeniBoju = new JButton("Izmeni boju");
		btnIzmeniBoju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexOfSelectedElement=lstBoje.getSelectedIndex();
				
				if (indexOfSelectedElement >= 0) {
					String selectedElement = dlm.getElementAt(indexOfSelectedElement);
					String[] rgbColors = selectedElement.split(" ");
					DlgTest dlgIzmenaBoje = new DlgTest();
					dlgIzmenaBoje.getTextFieldRed().setText(rgbColors[0]);
					dlgIzmenaBoje.getTextFieldGreen().setText(rgbColors[1]);
					dlgIzmenaBoje.getTextFieldBlue().setText(rgbColors[2]);
					dlgIzmenaBoje.setVisible(true);
					
					if (dlgIzmenaBoje.isOk()) {
						//nakon zatvaranja dijaloga izvrsava se ovaj deo koda
						String red = dlgIzmenaBoje.getTextFieldRed().getText();
						String green = dlgIzmenaBoje.getTextFieldGreen().getText();
						String blue = dlgIzmenaBoje.getTextFieldBlue().getText();
						String stringColor = red + " " + green + " " + blue;

						
						//zbog modifikacije treba da pregazimo vrednost
						dlm.remove(indexOfSelectedElement);
						dlm.add(indexOfSelectedElement, stringColor);

						Color color = new Color(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
						pnlCenter.setBackground(color);
					} 
				}
				
				
			}
		});
		GridBagConstraints gbc_btnIzmeniBoju = new GridBagConstraints();
		gbc_btnIzmeniBoju.gridx = 2;
		gbc_btnIzmeniBoju.gridy = 3;
		pnlCenter.add(btnIzmeniBoju, gbc_btnIzmeniBoju);

		// odabir boje iz palete - 3. zadatak
		JButton btnBojaPozadine = new JButton("Boja pozadine");
		btnBojaPozadine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color backColor = JColorChooser.showDialog(null, "Choose background color", Color.black);
				if (backColor != null)
					pnlCenter.setBackground(backColor);
			}
		});
		pnlSouth.add(btnBojaPozadine);
	}

}