package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import hexagonAdapter.HexagonAdapter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class HexagonDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldForX;
	private JTextField textFieldForY;
	private JTextField textFieldForRadius;
	private int radius;
	private boolean confirmed;
	private Color outlineColor;
	private Color innerColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HexagonDlg dialog = new HexagonDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HexagonDlg() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCenterX = new JLabel("Change X of the center:");
			lblCenterX.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
			gbc_lblCenterX.anchor = GridBagConstraints.WEST;
			gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterX.gridx = 0;
			gbc_lblCenterX.gridy = 1;
			contentPanel.add(lblCenterX, gbc_lblCenterX);
		}
		{
			textFieldForX = new JTextField();
			textFieldForX.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_textFieldForX = new GridBagConstraints();
			gbc_textFieldForX.anchor = GridBagConstraints.WEST;
			gbc_textFieldForX.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForX.gridx = 1;
			gbc_textFieldForX.gridy = 1;
			contentPanel.add(textFieldForX, gbc_textFieldForX);
			textFieldForX.setColumns(10);
		}
		{
			JLabel lblCenterY = new JLabel("Change Y of the center:");
			lblCenterY.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
			gbc_lblCenterY.anchor = GridBagConstraints.WEST;
			gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterY.gridx = 0;
			gbc_lblCenterY.gridy = 2;
			contentPanel.add(lblCenterY, gbc_lblCenterY);
		}
		{
			textFieldForY = new JTextField();
			GridBagConstraints gbc_textFieldForY = new GridBagConstraints();
			gbc_textFieldForY.anchor = GridBagConstraints.WEST;
			gbc_textFieldForY.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForY.gridx = 1;
			gbc_textFieldForY.gridy = 2;
			contentPanel.add(textFieldForY, gbc_textFieldForY);
			textFieldForY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Change radius of the hexagon:");
			lblRadius.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.WEST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 3;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			JButton btnInnerColor = new JButton("Inner color");
			btnInnerColor.setHorizontalAlignment(SwingConstants.LEFT);
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 innerColor = JColorChooser.showDialog(null, "Choos color", Color.black);
			            if (innerColor == null) {
			            	innerColor = Color.black;
			            }
				}
			});
			{
				textFieldForRadius = new JTextField();
				textFieldForRadius.addKeyListener(new KeyAdapter() {
				    @Override
				    public void keyTyped(KeyEvent e) {
				        char c = e.getKeyChar();
				        if (!Character.isDigit(c) && c!= KeyEvent.VK_BACK_SPACE && c!= KeyEvent.VK_ENTER) {
				            e.consume(); 
				            JOptionPane.showMessageDialog(null, "Radius must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				});
				GridBagConstraints gbc_textFieldForRadius = new GridBagConstraints();
				gbc_textFieldForRadius.anchor = GridBagConstraints.WEST;
				gbc_textFieldForRadius.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldForRadius.gridx = 1;
				gbc_textFieldForRadius.gridy = 3;
				contentPanel.add(textFieldForRadius, gbc_textFieldForRadius);
				textFieldForRadius.setColumns(10);
			}
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.anchor = GridBagConstraints.WEST;
			gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnInnerColor.gridx = 0;
			gbc_btnInnerColor.gridy = 4;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		}
		{
			JButton btnOutlineColor = new JButton("Outline color");
			btnOutlineColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					outlineColor = JColorChooser.showDialog(null, "Choos color", Color.black);
		            if (outlineColor == null) {
		            	outlineColor = Color.black;
		            }
				}
			});
			btnOutlineColor.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
			gbc_btnOutlineColor.anchor = GridBagConstraints.WEST;
			gbc_btnOutlineColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnOutlineColor.gridx = 0;
			gbc_btnOutlineColor.gridy = 5;
			contentPanel.add(btnOutlineColor, gbc_btnOutlineColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (textFieldForX.getText().isEmpty() || textFieldForY.getText().isEmpty() || textFieldForRadius.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							radius = Integer.parseInt(textFieldForRadius.getText());
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
						confirmed = false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void modifyHexagon(HexagonAdapter hexagon) {
	    setTextFieldForX(hexagon.getX());
	    setTextFieldForY(hexagon.getY());
	    setTextFieldForRadius(hexagon.getRadius());
	    outlineColor=hexagon.getBorderColor();
	    innerColor=hexagon.getAreaColor();
	    setVisible(true);

	    if (isConfirmed()) {
	    	int newX = Integer.parseInt(textFieldForX.getText());
	    	int newY = Integer.parseInt(textFieldForY.getText());
	    	int radius=Integer.parseInt(textFieldForRadius.getText());
	    	
	    	if (radius <= 0) {
	            JOptionPane.showMessageDialog(this, "R must be > 0");
	            return;
	        }
	    	
	    	hexagon.setX(newX);
	    	hexagon.setY(newY);
	    	
	    	hexagon.setAreaColor(innerColor);
	    	hexagon.setBorderColor(outlineColor);	
	    }
	}
	
	public boolean isConfirmed() {
		return confirmed;
	}
	public int getRadius() {
		return radius;
	}
	public void setTextFieldForX(int a) {
		textFieldForX.setText(Integer.toString(a));
	}
	public void setTextFieldForY(int a) {
		textFieldForY.setText(Integer.toString(a));
	}
	public void setTextFieldForRadius(int a) {
		textFieldForRadius.setText(Integer.toString(a));
	}
	public void regularTextFields() {
		textFieldForX.setEditable(false);
		textFieldForY.setEditable(false);
	}
	public Color getOutlineColor() {
		return outlineColor;
	}
	public Color getInnerColor() {
		return innerColor;
	}
	

}
