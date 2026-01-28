package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import geometry.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class PointDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldForX;
	private JTextField textFieldForY;
	private boolean confirmed;
	private Color selectedColor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PointDlg dialog = new PointDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PointDlg() {
		setTitle("Vujic Nebojsa IT60-2023");
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("Insert X coordinate");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 1;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			textFieldForX = new JTextField();
			textFieldForX.addKeyListener(new KeyAdapter() {
			    @Override
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if (!Character.isDigit(c) && c!= KeyEvent.VK_ENTER && c!= KeyEvent.VK_BACK_SPACE ) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			GridBagConstraints gbc_textFieldForX = new GridBagConstraints();
			gbc_textFieldForX.anchor = GridBagConstraints.WEST;
			gbc_textFieldForX.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForX.gridx = 1;
			gbc_textFieldForX.gridy = 1;
			contentPanel.add(textFieldForX, gbc_textFieldForX);
			textFieldForX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Insert Y coordinate");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 2;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			textFieldForY = new JTextField();
			textFieldForY.addKeyListener(new KeyAdapter() {
			    @Override
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if (!Character.isDigit(c) &&  c!= KeyEvent.VK_ENTER && c!= KeyEvent.VK_BACK_SPACE ) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			GridBagConstraints gbc_textFieldForY = new GridBagConstraints();
			gbc_textFieldForY.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForY.anchor = GridBagConstraints.WEST;
			gbc_textFieldForY.gridx = 1;
			gbc_textFieldForY.gridy = 2;
			contentPanel.add(textFieldForY, gbc_textFieldForY);
			textFieldForY.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Choos color");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedColor = JColorChooser.showDialog(null, "Choos color", Color.black);
		             if (selectedColor == null) {
		                 selectedColor = Color.black;
		             }
				}
			});
			GridBagConstraints gbc_brnColor = new GridBagConstraints();
			gbc_brnColor.insets = new Insets(0, 0, 0, 5);
			gbc_brnColor.gridx = 0;
			gbc_brnColor.gridy = 4;
			contentPanel.add(btnColor, gbc_brnColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (textFieldForX.getText().trim().isEmpty() || textFieldForY.getText().trim().isEmpty()) {
				            JOptionPane.showMessageDialog(null, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
				        } else {
				            confirmed = true;
				            dispose();
				            setVisible(false);
				        }
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirmed=false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void modifyPoint(Point point) {
	    setTextFieldForX(point.getX());
	    setTextFieldForY(point.getY());
	    selectedColor = point.getColor();
	    setVisible(true);

	    if (isConfirmed()) {
	    	int newX = Integer.parseInt(textFieldForX.getText());
	    	int newY = Integer.parseInt(textFieldForY.getText());
	    	point.setXCoordinate(newX);
	    	point.setYCoordinate(newY);
	    	point.setColor(getSelectedColor());
	        
	    }
	}

	public void regularTextFields() {
		this.textFieldForX.setEditable(false);
		this.textFieldForY.setEditable(false);
	}
	
	public void setTextFieldForX(int a){
		 textFieldForX.setText(Integer.toString(a));
	}
	public void setTextFieldForY(int a){
		 textFieldForY.setText(Integer.toString(a));
	}
	
	public Color getSelectedColor() {
		return selectedColor;
	}
	
	public boolean isConfirmed() {
		return confirmed;
	}

}
