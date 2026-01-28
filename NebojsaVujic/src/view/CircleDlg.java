package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class CircleDlg extends JDialog {

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
			CircleDlg dialog = new CircleDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CircleDlg() {
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCenterX = new JLabel("Change X of the center:");
			GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
			gbc_lblCenterX.anchor = GridBagConstraints.WEST;
			gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterX.gridx = 0;
			gbc_lblCenterX.gridy = 1;
			contentPanel.add(lblCenterX, gbc_lblCenterX);
		}
		{
			textFieldForX = new JTextField();
			GridBagConstraints gbc_textFieldForX = new GridBagConstraints();
			gbc_textFieldForX.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForX.gridx = 1;
			gbc_textFieldForX.gridy = 1;
			contentPanel.add(textFieldForX, gbc_textFieldForX);
			textFieldForX.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Change Y of the center:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 2;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			textFieldForY = new JTextField();
			GridBagConstraints gbc_textFieldForY = new GridBagConstraints();
			gbc_textFieldForY.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForY.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForY.gridx = 1;
			gbc_textFieldForY.gridy = 2;
			contentPanel.add(textFieldForY, gbc_textFieldForY);
			textFieldForY.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Change radius of the center:");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 3;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
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
			gbc_textFieldForRadius.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForRadius.gridx = 1;
			gbc_textFieldForRadius.gridy = 3;
			contentPanel.add(textFieldForRadius, gbc_textFieldForRadius);
			textFieldForRadius.setColumns(10);
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
			GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
			gbc_btnOutlineColor.anchor = GridBagConstraints.WEST;
			gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnOutlineColor.gridx = 0;
			gbc_btnOutlineColor.gridy = 4;
			contentPanel.add(btnOutlineColor, gbc_btnOutlineColor);
		}
		{
			JButton btnInnerColor = new JButton("Inner color");
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    innerColor = JColorChooser.showDialog(null, "Choos color", Color.black);
		            if (innerColor == null) {
		            	innerColor = Color.black;
		            }
				}
			});
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.anchor = GridBagConstraints.WEST;
			gbc_btnInnerColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnInnerColor.gridx = 0;
			gbc_btnInnerColor.gridy = 5;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
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
						dispose();
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void modifyCircle(Circle circle) {
	    setTextFieldForX(circle.getCenter().getXCoordinate());
	    setTextFieldForY(circle.getCenter().getYCoordinate());
	    setTextFieldForRadius(circle.getRadius());
	    outlineColor=circle.getColor();
	    innerColor=circle.getInnerColor();
	    confirmed=false;
	    setVisible(true);

	    if (isConfirmed()) {
	    	int newX = Integer.parseInt(textFieldForX.getText());
	        int newY = Integer.parseInt(textFieldForY.getText());
	        int r = Integer.parseInt(textFieldForRadius.getText());

	        circle.setCenter(new Point(newX, newY));
	        try {
				circle.setRadius(r);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        circle.setColor(getOutlineColor());
	        circle.setInnerColor(getInnerColor());
	    }
	}
	
	public boolean isConfirmed() {
        return confirmed;
    }

    public int getRadius() {
        return radius;
    }
	public void setTextFieldForX(int a){
		 textFieldForX.setText(Integer.toString(a));
	}
	public void setTextFieldForY(int a){
		 textFieldForY.setText(Integer.toString(a));
	}
	public void setTextFieldForRadius(int a){
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

	public void setOutlineColor(Color c) {
		// TODO Auto-generated method stub
		outlineColor=c;
	}

	public void setInnerColor(Color c) {
		// TODO Auto-generated method stub
		innerColor=c;
	}

}
