package Sort;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import geometry.Donut;
import geometry.Point;

public class SortFrm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldForX;
	private JTextField textFieldForY;
	private JTextField textFieldForOR;
	private JTextField textFieldForIR;
	private DefaultListModel<Donut> listModel;
	private JList<Donut> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortFrm frame = new SortFrm();
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
	
	public SortFrm() {
		setTitle("Vujic Nebojsa IT60-2023");
		listModel = new DefaultListModel<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		JPanel centralPanel = new JPanel();
		contentPane.add(centralPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centralPanel = new GridBagLayout();
		gbl_centralPanel.columnWidths = new int[]{0, 165, 0, 0, 0, 0};
		gbl_centralPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_centralPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_centralPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		centralPanel.setLayout(gbl_centralPanel);
		
		JLabel lblForX = new JLabel("Unesite X koordinatu");
		GridBagConstraints gbc_lblForX = new GridBagConstraints();
		gbc_lblForX.anchor = GridBagConstraints.WEST;
		gbc_lblForX.insets = new Insets(0, 0, 5, 5);
		gbc_lblForX.gridx = 1;
		gbc_lblForX.gridy = 0;
		centralPanel.add(lblForX, gbc_lblForX);
		
		textFieldForX = new JTextField();
		textFieldForX.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c) &&  !(c == KeyEvent.VK_MINUS && textFieldForX.getText().isEmpty())
		        		&& c!= KeyEvent.VK_ENTER && c!= KeyEvent.VK_BACK_SPACE ) {
		            e.consume(); 
		            JOptionPane.showMessageDialog(null, "Dozvoljen je unos broja i minusa, ali samo na pocetku!", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		GridBagConstraints gbc_textFieldForX = new GridBagConstraints();
		gbc_textFieldForX.anchor = GridBagConstraints.WEST;
		gbc_textFieldForX.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldForX.gridx = 2;
		gbc_textFieldForX.gridy = 0;
		centralPanel.add(textFieldForX, gbc_textFieldForX);
		textFieldForX.setColumns(10);
		
		JLabel lblForY = new JLabel("Unesite Y koordinatu:");
		GridBagConstraints gbc_lblForY = new GridBagConstraints();
		gbc_lblForY.anchor = GridBagConstraints.WEST;
		gbc_lblForY.insets = new Insets(0, 0, 5, 5);
		gbc_lblForY.gridx = 1;
		gbc_lblForY.gridy = 1;
		centralPanel.add(lblForY, gbc_lblForY);
		
		textFieldForY = new JTextField();
		textFieldForY.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c) &&  !(c == KeyEvent.VK_MINUS && textFieldForY.getText().isEmpty())
		        		&& c!= KeyEvent.VK_ENTER && c!= KeyEvent.VK_BACK_SPACE ) {
		            e.consume(); 
		            JOptionPane.showMessageDialog(null, "Dozvoljen je unos broja i minusa, ali samo na pocetku!", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		GridBagConstraints gbc_textFieldForY = new GridBagConstraints();
		gbc_textFieldForY.anchor = GridBagConstraints.WEST;
		gbc_textFieldForY.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldForY.gridx = 2;
		gbc_textFieldForY.gridy = 1;
		centralPanel.add(textFieldForY, gbc_textFieldForY);
		textFieldForY.setColumns(10);
		
		//Definisanje Add button-a
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xStr = textFieldForX.getText();
		        String yStr = textFieldForY.getText();
		        String orStr = textFieldForOR.getText();
		        String irStr = textFieldForIR.getText();

		        if (xStr.isEmpty() || yStr.isEmpty() || orStr.isEmpty() || irStr.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena", "Poruka", JOptionPane.INFORMATION_MESSAGE);
		            return;
		        }
				
				try {
					 int x=Integer.parseInt(textFieldForX.getText());
	             	 int y=Integer.parseInt(textFieldForY.getText());
		             int or=Integer.parseInt(textFieldForOR.getText());
		             int ir=Integer.parseInt(textFieldForIR.getText());
		 
		        if (ir >= or) {
				     JOptionPane.showMessageDialog(null, "Obim unutrasnjeg kruga mora biti manji od vanjskog!", "Poruka", JOptionPane.ERROR_MESSAGE);
				     return;
			    }
			    if (ir <= 0) {
				     JOptionPane.showMessageDialog(null, "Obim mora biti veci od nula!", "Poruka", JOptionPane.ERROR_MESSAGE);
				     return;
			    }
			    if (or <= 0) {
				     JOptionPane.showMessageDialog(null, "Obim mora biti veci od nula!", "Poruka", JOptionPane.ERROR_MESSAGE);
				     return;
			    }
			         Donut donut =new Donut (new Point(x,y),or,ir);
			         listModel.addElement(donut);
				}catch (Error ex) {
					JOptionPane jOptionPane = new JOptionPane();
					jOptionPane.setMessage("Pogresan unos");
					return;
				}
			}
		});
		
		
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 4;
		gbc_btnAdd.gridy = 1;
		centralPanel.add(btnAdd, gbc_btnAdd);
		
		JLabel lblForOR = new JLabel("Unesite vanjski precnik:");
		GridBagConstraints gbc_lblForOR = new GridBagConstraints();
		gbc_lblForOR.anchor = GridBagConstraints.WEST;
		gbc_lblForOR.insets = new Insets(0, 0, 5, 5);
		gbc_lblForOR.gridx = 1;
		gbc_lblForOR.gridy = 2;
		centralPanel.add(lblForOR, gbc_lblForOR);
		
		textFieldForOR = new JTextField();
		textFieldForOR.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c) && c!= KeyEvent.VK_BACK_SPACE && c!= KeyEvent.VK_ENTER) {
		            e.consume(); 
		            JOptionPane.showMessageDialog(null, "Dozvoljen je unos broja (radius mora biti pozitivan)!", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		GridBagConstraints gbc_textFieldForOR = new GridBagConstraints();
		gbc_textFieldForOR.anchor = GridBagConstraints.WEST;
		gbc_textFieldForOR.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldForOR.gridx = 2;
		gbc_textFieldForOR.gridy = 2;
		centralPanel.add(textFieldForOR, gbc_textFieldForOR);
		textFieldForOR.setColumns(10);
		
		//Definisanje sort button-a
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i=0; i<listModel.size()-1 ; i++) {
					
					for (int j=0; j<listModel.size() -1 - i;j++) {
						Donut donut1=listModel.get(j);
						Donut donut2=listModel.get(j +1);
						
						double Area1= Math.PI * Math.pow(donut1.getRadius(), 2) - Math.PI * Math.pow(donut1.getInnerRadius(), 2);
						double Area2= Math.PI * Math.pow(donut2.getRadius(), 2) - Math.PI * Math.pow(donut2.getInnerRadius(), 2);
					    if(Area2 < Area1) {
							listModel.set(j, donut2);
							listModel.set(j + 1, donut1);
						}
						
					}
				}
			}
		});
		GridBagConstraints gbc_btnSort = new GridBagConstraints();
		gbc_btnSort.insets = new Insets(0, 0, 5, 0);
		gbc_btnSort.gridx = 4;
		gbc_btnSort.gridy = 2;
		centralPanel.add(btnSort, gbc_btnSort);
		
		JLabel lblForIR = new JLabel("Unesite unutrasnji precnik");
		GridBagConstraints gbc_lblForIR = new GridBagConstraints();
		gbc_lblForIR.insets = new Insets(0, 0, 5, 5);
		gbc_lblForIR.anchor = GridBagConstraints.WEST;
		gbc_lblForIR.gridx = 1;
		gbc_lblForIR.gridy = 3;
		centralPanel.add(lblForIR, gbc_lblForIR);
		
		textFieldForIR = new JTextField();
		textFieldForIR.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c) && c!= KeyEvent.VK_BACK_SPACE && c!= KeyEvent.VK_ENTER) {
		            e.consume(); 
		            JOptionPane.showMessageDialog(null, "Dozvoljen je unos broja (radius mora biti pozitivan)!", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		GridBagConstraints gbc_textFieldForIR = new GridBagConstraints();
		gbc_textFieldForIR.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldForIR.anchor = GridBagConstraints.WEST;
		gbc_textFieldForIR.gridx = 2;
		gbc_textFieldForIR.gridy = 3;
		centralPanel.add(textFieldForIR, gbc_textFieldForIR);
		textFieldForIR.setColumns(10);
		
		list = new  JList<>(listModel);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 5;
		centralPanel.add(list, gbc_list);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);	
		
	}
}
