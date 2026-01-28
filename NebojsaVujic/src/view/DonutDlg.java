package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import geometry.Donut;
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

public class DonutDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldForCenterX;
	private JTextField textFieldForCenterY;
	private JTextField textFieldForInnerRadius;
	private JTextField textFieldForOuterRadius;
	private boolean confirmed;
	private Color outlineColor;
	private Color innerColor;
	private int inner;
	private int outer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DonutDlg dialog = new DonutDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DonutDlg() {
		setTitle("Vujic Nebojsa IT60-2023");
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCenterX = new JLabel("Change center coordinate(X):");
			GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
			gbc_lblCenterX.anchor = GridBagConstraints.WEST;
			gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterX.gridx = 0;
			gbc_lblCenterX.gridy = 1;
			contentPanel.add(lblCenterX, gbc_lblCenterX);
		}
		{
			textFieldForCenterX = new JTextField();
			textFieldForCenterX.addKeyListener(new KeyAdapter() {
			    @Override
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if (!Character.isDigit(c) && c!= KeyEvent.VK_BACK_SPACE && c!= KeyEvent.VK_ENTER) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			GridBagConstraints gbc_textFieldForCenterX = new GridBagConstraints();
			gbc_textFieldForCenterX.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForCenterX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForCenterX.gridx = 1;
			gbc_textFieldForCenterX.gridy = 1;
			contentPanel.add(textFieldForCenterX, gbc_textFieldForCenterX);
			textFieldForCenterX.setColumns(10);
		}
		{
			JLabel lblCenterY = new JLabel("Change center coordinate(Y):");
			GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
			gbc_lblCenterY.anchor = GridBagConstraints.WEST;
			gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterY.gridx = 0;
			gbc_lblCenterY.gridy = 2;
			contentPanel.add(lblCenterY, gbc_lblCenterY);
		}
		{
			textFieldForCenterY = new JTextField();
			textFieldForCenterY.addKeyListener(new KeyAdapter() {
			    @Override
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if (!Character.isDigit(c) && c!= KeyEvent.VK_BACK_SPACE && c!= KeyEvent.VK_ENTER) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			GridBagConstraints gbc_textFieldForCenterY = new GridBagConstraints();
			gbc_textFieldForCenterY.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForCenterY.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForCenterY.gridx = 1;
			gbc_textFieldForCenterY.gridy = 2;
			contentPanel.add(textFieldForCenterY, gbc_textFieldForCenterY);
			textFieldForCenterY.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Change inner radius:");
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.anchor = GridBagConstraints.WEST;
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 0;
			gbc_lblInnerRadius.gridy = 3;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			textFieldForInnerRadius = new JTextField();
			textFieldForInnerRadius.addKeyListener(new KeyAdapter() {
			    @Override
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if (!Character.isDigit(c) && c!= KeyEvent.VK_BACK_SPACE && c!= KeyEvent.VK_ENTER) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			GridBagConstraints gbc_textFieldForInnerRadius = new GridBagConstraints();
			gbc_textFieldForInnerRadius.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForInnerRadius.gridx = 1;
			gbc_textFieldForInnerRadius.gridy = 3;
			contentPanel.add(textFieldForInnerRadius, gbc_textFieldForInnerRadius);
			textFieldForInnerRadius.setColumns(10);
		}
		{
			JLabel lblOuterRadius = new JLabel("Change outer radius:");
			GridBagConstraints gbc_lblOuterRadius = new GridBagConstraints();
			gbc_lblOuterRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblOuterRadius.anchor = GridBagConstraints.WEST;
			gbc_lblOuterRadius.gridx = 0;
			gbc_lblOuterRadius.gridy = 4;
			contentPanel.add(lblOuterRadius, gbc_lblOuterRadius);
		}
		{
			textFieldForOuterRadius = new JTextField();
			textFieldForOuterRadius.addKeyListener(new KeyAdapter() {
			    @Override
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if (!Character.isDigit(c) && c!= KeyEvent.VK_BACK_SPACE && c!= KeyEvent.VK_ENTER) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			GridBagConstraints gbc_textFieldForOuterRadius = new GridBagConstraints();
			gbc_textFieldForOuterRadius.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForOuterRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForOuterRadius.gridx = 1;
			gbc_textFieldForOuterRadius.gridy = 4;
			contentPanel.add(textFieldForOuterRadius, gbc_textFieldForOuterRadius);
			textFieldForOuterRadius.setColumns(10);
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
			gbc_btnOutlineColor.gridy = 5;
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
			gbc_btnInnerColor.gridy = 6;
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
						if (textFieldForCenterX.getText().isEmpty() || textFieldForCenterY.getText().isEmpty() 
							|| textFieldForInnerRadius.getText().isEmpty() || textFieldForOuterRadius.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
							} else {
								inner=Integer.parseInt(textFieldForInnerRadius.getText());
								outer=Integer.parseInt(textFieldForOuterRadius.getText());
								if (inner >= outer) {
						            JOptionPane.showMessageDialog(null, "Inner radius cannot be greater than outer radius!", "Error", JOptionPane.ERROR_MESSAGE);
						            return;
						        }
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
	
	public void modifyDonut(Donut donut) {
	    setTextFieldForX(donut.getCenter().getXCoordinate());
	    setTextFieldForY(donut.getCenter().getYCoordinate());
	    setTextFieldForInnerRadius(donut.getInnerRadius());
	    setTextFieldForOuetRadius(donut.getRadius());
	    outlineColor=donut.getColor();
	    innerColor=donut.getInnerColor();
	    confirmed=false;
	    setVisible(true);

	    if (isConfirmed()) {
	    	int newX = Integer.parseInt(textFieldForCenterX.getText());
	    	int newY = Integer.parseInt(textFieldForCenterY.getText());
	    	int innerRadius=Integer.parseInt(textFieldForInnerRadius.getText());
	    	int outerRadius=Integer.parseInt(textFieldForOuterRadius.getText());
	    	Point center =new Point();
	    	center.setXCoordinate(newX);
	    	center.setYCoordinate(newY);
	    	donut.setInnerRadius(innerRadius);
	    	donut.setCenter(center);
	    	try {
				donut.setRadius(outerRadius);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	donut.setColor(getOutlineColor());
	    	donut.setInnerColor(getInnerColor());
	    }
	}
	
	public boolean isConfirmed() {
		return confirmed;
	}
	public int getInner(){
		return inner;
	}
	public int getOuter() {
		return outer;
	}
	public void setTextFieldForX(int a){
		textFieldForCenterX.setText(Integer.toString(a));
	}
	public void setTextFieldForY(int a){
		textFieldForCenterY.setText(Integer.toString(a));
	}
	public void setTextFieldForInnerRadius(int a){
		textFieldForInnerRadius.setText(Integer.toString(a));
	}
	public void setTextFieldForOuetRadius(int a){
		textFieldForOuterRadius.setText(Integer.toString(a));
	}
	
	public void regularTextFields() {
		textFieldForCenterX.setEditable(false);
		textFieldForCenterY.setEditable(false);
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