package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LineDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldForX1;
	private JTextField textFieldForY1;
	private JTextField textFieldForX2;
	private JTextField textFieldForY2;
	private Color selectedColor;
	private boolean confirmed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LineDlg dialog = new LineDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LineDlg() {
		setTitle("Vujic Nebojsa IT60-2023");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX1 = new JLabel("Change X1 coordinate:");
			GridBagConstraints gbc_lblX1 = new GridBagConstraints();
			gbc_lblX1.insets = new Insets(0, 0, 5, 5);
			gbc_lblX1.anchor = GridBagConstraints.EAST;
			gbc_lblX1.gridx = 0;
			gbc_lblX1.gridy = 0;
			contentPanel.add(lblX1, gbc_lblX1);
		}
		{
			textFieldForX1 = new JTextField();
			textFieldForX1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
			        if (!Character.isDigit(c) &&  !(c == KeyEvent.VK_MINUS && textFieldForX1.getText().isEmpty())
			        		&& c!= KeyEvent.VK_ENTER && c!= KeyEvent.VK_BACK_SPACE ) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			});
			GridBagConstraints gbc_textFieldForX1 = new GridBagConstraints();
			gbc_textFieldForX1.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForX1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForX1.gridx = 1;
			gbc_textFieldForX1.gridy = 0;
			contentPanel.add(textFieldForX1, gbc_textFieldForX1);
			textFieldForX1.setColumns(10);
		}
		{
			JLabel lblY1 = new JLabel("Change Y1 coordinate:");
			GridBagConstraints gbc_lblY1 = new GridBagConstraints();
			gbc_lblY1.anchor = GridBagConstraints.EAST;
			gbc_lblY1.insets = new Insets(0, 0, 5, 5);
			gbc_lblY1.gridx = 0;
			gbc_lblY1.gridy = 1;
			contentPanel.add(lblY1, gbc_lblY1);
		}
		{
			textFieldForY1 = new JTextField();
			textFieldForY1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
			        if (!Character.isDigit(c) &&  !(c == KeyEvent.VK_MINUS && textFieldForY1.getText().isEmpty())
			        		&& c!= KeyEvent.VK_ENTER && c!= KeyEvent.VK_BACK_SPACE ) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			});
			GridBagConstraints gbc_textFieldForY1 = new GridBagConstraints();
			gbc_textFieldForY1.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForY1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForY1.gridx = 1;
			gbc_textFieldForY1.gridy = 1;
			contentPanel.add(textFieldForY1, gbc_textFieldForY1);
			textFieldForY1.setColumns(10);
		}
		{
			JLabel lblX2 = new JLabel("Change X2 coordinate:");
			GridBagConstraints gbc_lblX2 = new GridBagConstraints();
			gbc_lblX2.anchor = GridBagConstraints.EAST;
			gbc_lblX2.insets = new Insets(0, 0, 5, 5);
			gbc_lblX2.gridx = 0;
			gbc_lblX2.gridy = 2;
			contentPanel.add(lblX2, gbc_lblX2);
		}
		{
			textFieldForX2 = new JTextField();
			textFieldForX2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
			        if (!Character.isDigit(c) &&  !(c == KeyEvent.VK_MINUS && textFieldForX2.getText().isEmpty())
			        		&& c!= KeyEvent.VK_ENTER && c!= KeyEvent.VK_BACK_SPACE ) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			});
			GridBagConstraints gbc_textFieldForX2 = new GridBagConstraints();
			gbc_textFieldForX2.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForX2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForX2.gridx = 1;
			gbc_textFieldForX2.gridy = 2;
			contentPanel.add(textFieldForX2, gbc_textFieldForX2);
			textFieldForX2.setColumns(10);
		}
		{
			JLabel lblY2 = new JLabel("Change Y2 coordinate:");
			GridBagConstraints gbc_lblY2 = new GridBagConstraints();
			gbc_lblY2.anchor = GridBagConstraints.EAST;
			gbc_lblY2.insets = new Insets(0, 0, 5, 5);
			gbc_lblY2.gridx = 0;
			gbc_lblY2.gridy = 3;
			contentPanel.add(lblY2, gbc_lblY2);
		}
		{
			textFieldForY2 = new JTextField();
			textFieldForY2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
			        if (!Character.isDigit(c) &&  !(c == KeyEvent.VK_MINUS && textFieldForY2.getText().isEmpty())
			        		&& c!= KeyEvent.VK_ENTER && c!= KeyEvent.VK_BACK_SPACE ) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			});
			GridBagConstraints gbc_textFieldForY2 = new GridBagConstraints();
			gbc_textFieldForY2.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForY2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForY2.gridx = 1;
			gbc_textFieldForY2.gridy = 3;
			contentPanel.add(textFieldForY2, gbc_textFieldForY2);
			textFieldForY2.setColumns(10);
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
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.anchor = GridBagConstraints.WEST;
			gbc_btnColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnColor.gridx = 0;
			gbc_btnColor.gridy = 4;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (textFieldForX1.getText().trim().isEmpty() || textFieldForY1.getText().trim().isEmpty() 
							|| textFieldForX2.getText().trim().isEmpty() || textFieldForY2.getText().trim().isEmpty()) {
				            JOptionPane.showMessageDialog(null, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
				        } else {
				            confirmed = true;
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
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void modifyLine(Line line) {
		Point startPoint = new Point();
		Point endPoint = new Point();
		startPoint=line.getStartPoint();
		endPoint=line.getEndPoint();
	    setTextFieldForX1(startPoint.getXCoordinate());
	    setTextFieldForY1(startPoint.getYCoordinate());
	    setTextFieldForX2(endPoint.getXCoordinate());
	    setTextFieldForY2(endPoint.getYCoordinate());
	    setVisible(true);

	    if (isConfirmed()) {
	    	int newX1 = Integer.parseInt(textFieldForX1.getText());
	    	int newY1 = Integer.parseInt(textFieldForY1.getText());
	    	int newX2 = Integer.parseInt(textFieldForX2.getText());
	    	int newY2 = Integer.parseInt(textFieldForY2.getText());
	    	
	    	startPoint.setXCoordinate(newX1);
	    	startPoint.setYCoordinate(newY1);
	    	endPoint.setXCoordinate(newX2);
	    	endPoint.setYCoordinate(newY2);
	    	line.setStartPoint(startPoint);
	    	line.setEndPoint(endPoint);
	    	line.setColor(getSelectedColor());
	        
	    }
	}
	
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setTextFieldForX1(int a){
		 textFieldForX1.setText(Integer.toString(a));
	}
	public void setTextFieldForX2(int a){
		 textFieldForX2.setText(Integer.toString(a));
	}
	public void setTextFieldForY1(int a){
		 textFieldForY1.setText(Integer.toString(a));
	}
	public void setTextFieldForY2(int a){
		 textFieldForY2.setText(Integer.toString(a));
	}
	
	public Color getSelectedColor() {
		return selectedColor;
	}
	
	public void regularTextFields() {
		textFieldForX1.setEditable(false);
		textFieldForX2.setEditable(false);
		textFieldForY1.setEditable(false);
		textFieldForY2.setEditable(false);
	}

}
