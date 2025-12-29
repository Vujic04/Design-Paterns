package stack;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import geometry.Donut;
import geometry.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StackDlg extends JDialog {

	private static final long serialVersionUID = 1L;
    private boolean confirmed;
    private Donut donut;
    private JTextField textFieldForX;
    private JTextField textFieldForY;
    private JTextField textFieldForOR;
    private JTextField textFieldForIR;
    private int x;
    private int y;
    private int or;
    private int ir;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		try {
			JFrame frame =new JFrame();
			StackDlg dialog = new StackDlg(frame);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public StackDlg(JFrame parent) {
        super(parent, "Vujic Nebojsa IT60-2023", true);
        donutMaker();
        
    }
	public StackDlg(JFrame parent,Donut topDonut) {
		super(parent, "Vujic Nebojsa IT60-2023", true);
	    this.donut = topDonut;
	    this.x = topDonut.getCenter().getXCoordinate();
	    this.y = topDonut.getCenter().getYCoordinate();
	    this.or = topDonut.getRadius();
	    this.ir = topDonut.getInnerRadius();
	    donutMaker();
		donutDelete();	
	}

	public void donutMaker() {

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			
			//Definisanje Ok button-a
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
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
								confirmed=false;
								return;
							}
							if (ir <= 0) {
								JOptionPane.showMessageDialog(null, "Obim mora biti veci od nula!", "Poruka", JOptionPane.ERROR_MESSAGE);
								confirmed=false;
								return;
							}
							if (or <= 0) {
								JOptionPane.showMessageDialog(null, "Obim mora biti veci od nula!", "Poruka", JOptionPane.ERROR_MESSAGE);
								confirmed=false;
								return;
							}
							
							donut =new Donut (new Point(x,y),or,ir);
							confirmed=true;
							setVisible(false);
						}catch (Error ex) {
							JOptionPane jOptionPane = new JOptionPane();
							jOptionPane.setMessage("Pogresan unos");
							confirmed=false;
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			//Definisanje cancel button-a
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						donut=null;
						confirmed=false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel centralPanel = new JPanel();
			getContentPane().add(centralPanel, BorderLayout.CENTER);
			GridBagLayout gbl_centralPanel = new GridBagLayout();
			gbl_centralPanel.columnWidths = new int[]{0, 0, 0};
			gbl_centralPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_centralPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_centralPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			centralPanel.setLayout(gbl_centralPanel);
			{
				JLabel lblx = new JLabel("Unesite X osu:");
				GridBagConstraints gbc_lblx = new GridBagConstraints();
				gbc_lblx.anchor = GridBagConstraints.WEST;
				gbc_lblx.insets = new Insets(0, 0, 5, 5);
				gbc_lblx.gridx = 0;
				gbc_lblx.gridy = 1;
				centralPanel.add(lblx, gbc_lblx);
			}
			{
				textFieldForX = new JTextField();
				textFieldForX.setPreferredSize(new Dimension(5, 19));
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
				gbc_textFieldForX.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldForX.gridx = 1;
				gbc_textFieldForX.gridy = 1;
				centralPanel.add(textFieldForX, gbc_textFieldForX);
				textFieldForX.setColumns(10);
			}
			{
				JLabel lblY = new JLabel("Unesite Y osu:");
				GridBagConstraints gbc_lblY = new GridBagConstraints();
				gbc_lblY.anchor = GridBagConstraints.WEST;
				gbc_lblY.insets = new Insets(0, 0, 5, 5);
				gbc_lblY.gridx = 0;
				gbc_lblY.gridy = 2;
				centralPanel.add(lblY, gbc_lblY);
			}
			{
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
				gbc_textFieldForY.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldForY.gridx = 1;
				gbc_textFieldForY.gridy = 2;
				centralPanel.add(textFieldForY, gbc_textFieldForY);
				textFieldForY.setColumns(10);
			}
			{
				JLabel lblOR = new JLabel("Vanjski precnik:");
				GridBagConstraints gbc_lblOR = new GridBagConstraints();
				gbc_lblOR.anchor = GridBagConstraints.WEST;
				gbc_lblOR.insets = new Insets(0, 0, 5, 5);
				gbc_lblOR.gridx = 0;
				gbc_lblOR.gridy = 3;
				centralPanel.add(lblOR, gbc_lblOR);
			}
			{
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
				gbc_textFieldForOR.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldForOR.gridx = 1;
				gbc_textFieldForOR.gridy = 3;
				centralPanel.add(textFieldForOR, gbc_textFieldForOR);
				textFieldForOR.setColumns(10);
			}
			{
				JLabel lblIR = new JLabel("Unutrasnji precnik:");
				GridBagConstraints gbc_lblIR = new GridBagConstraints();
				gbc_lblIR.anchor = GridBagConstraints.WEST;
				gbc_lblIR.insets = new Insets(0, 0, 0, 5);
				gbc_lblIR.gridx = 0;
				gbc_lblIR.gridy = 4;
				centralPanel.add(lblIR, gbc_lblIR);
			}
			{
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
				gbc_textFieldForIR.anchor = GridBagConstraints.WEST;
				gbc_textFieldForIR.gridx = 1;
				gbc_textFieldForIR.gridy = 4;
				centralPanel.add(textFieldForIR, gbc_textFieldForIR);
				textFieldForIR.setColumns(10);
			}
		}
		{
			JPanel northPanel = new JPanel();
			getContentPane().add(northPanel, BorderLayout.NORTH);
		}
		
	}
	public void donutDelete () {
		textFieldForX.setText(Integer.toString(x));
		textFieldForY.setText(Integer.toString(y));
		textFieldForIR.setText(Integer.toString(ir));
		textFieldForOR.setText(Integer.toString(or));
		textFieldForX.setEditable(false);
		textFieldForY.setEditable(false);
		textFieldForIR.setEditable(false);
		textFieldForOR.setEditable(false);
	}

	public boolean isConfirmed() {
		// TODO Auto-generated method stub
		return confirmed;
	}

	public Donut getDonut() {
		// TODO Auto-generated method stub
		return donut;
	}

}
