package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

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

public class RectangleDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldForUpperX;
	private JTextField textFieldForUpperY;
	private JTextField textFieldForHeight;
	private JTextField textFieldForWidth;
	private boolean confirmed;
	private Color outlineColor;
	private Color innerColor;
	private int height;
	private int width;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RectangleDlg dialog = new RectangleDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RectangleDlg() {
		setTitle("Vujic Nebojsa IT60-2023");
		setModal(true);
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
			JLabel lblUpperX = new JLabel("Change statr coordinate(X) of the rectangle:");
			GridBagConstraints gbc_lblUpperX = new GridBagConstraints();
			gbc_lblUpperX.anchor = GridBagConstraints.EAST;
			gbc_lblUpperX.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperX.gridx = 0;
			gbc_lblUpperX.gridy = 1;
			contentPanel.add(lblUpperX, gbc_lblUpperX);
		}
		{
			textFieldForUpperX = new JTextField();
			GridBagConstraints gbc_textFieldForUpperX = new GridBagConstraints();
			gbc_textFieldForUpperX.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForUpperX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForUpperX.gridx = 1;
			gbc_textFieldForUpperX.gridy = 1;
			contentPanel.add(textFieldForUpperX, gbc_textFieldForUpperX);
			textFieldForUpperX.setColumns(10);
		}
		{
			JLabel lblUpperY = new JLabel("Change statr coordinate(Y) of the rectangle:");
			GridBagConstraints gbc_lblUpperY = new GridBagConstraints();
			gbc_lblUpperY.anchor = GridBagConstraints.EAST;
			gbc_lblUpperY.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperY.gridx = 0;
			gbc_lblUpperY.gridy = 2;
			contentPanel.add(lblUpperY, gbc_lblUpperY);
		}
		{
			textFieldForUpperY = new JTextField();
			GridBagConstraints gbc_textFieldForUpperY = new GridBagConstraints();
			gbc_textFieldForUpperY.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForUpperY.gridx = 1;
			gbc_textFieldForUpperY.gridy = 2;
			contentPanel.add(textFieldForUpperY, gbc_textFieldForUpperY);
			textFieldForUpperY.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Change the height of the rectangle:");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.anchor = GridBagConstraints.WEST;
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 3;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			textFieldForHeight = new JTextField();
			textFieldForHeight.addKeyListener(new KeyAdapter() {
			    @Override
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if (!Character.isDigit(c) && c!= KeyEvent.VK_BACK_SPACE && c!= KeyEvent.VK_ENTER) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Height must be positive nummber!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			GridBagConstraints gbc_textFieldForHeight = new GridBagConstraints();
			gbc_textFieldForHeight.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForHeight.gridx = 1;
			gbc_textFieldForHeight.gridy = 3;
			contentPanel.add(textFieldForHeight, gbc_textFieldForHeight);
			textFieldForHeight.setColumns(10);
		}
		{
			JLabel Width = new JLabel("Change the width of the rectangle");
			GridBagConstraints gbc_Width = new GridBagConstraints();
			gbc_Width.insets = new Insets(0, 0, 5, 5);
			gbc_Width.anchor = GridBagConstraints.WEST;
			gbc_Width.gridx = 0;
			gbc_Width.gridy = 4;
			contentPanel.add(Width, gbc_Width);
		}
		{
			textFieldForWidth = new JTextField();
			textFieldForWidth.addKeyListener(new KeyAdapter() {
			    @Override
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if (!Character.isDigit(c) && c!= KeyEvent.VK_BACK_SPACE && c!= KeyEvent.VK_ENTER) {
			            e.consume(); 
			            JOptionPane.showMessageDialog(null, "Width must be positive nummber!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			GridBagConstraints gbc_textFieldForWidth = new GridBagConstraints();
			gbc_textFieldForWidth.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldForWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldForWidth.gridx = 1;
			gbc_textFieldForWidth.gridy = 4;
			contentPanel.add(textFieldForWidth, gbc_textFieldForWidth);
			textFieldForWidth.setColumns(10);
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
						if (textFieldForUpperX.getText().isEmpty() || textFieldForUpperY.getText().isEmpty()
								|| textFieldForHeight.getText().isEmpty() || textFieldForWidth.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							height = Integer.parseInt(textFieldForHeight.getText());
							width = Integer.parseInt(textFieldForWidth.getText());
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
						confirmed=false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void modifyRectangle(Rectangle rectangle) {
		Point upperLeftPoint=new Point();
		upperLeftPoint=rectangle.getUpperLeftPoint();
		setTextFieldForX(upperLeftPoint.getXCoordinate());
		setTextFieldForY(upperLeftPoint.getYCoordinate());
		setTextFieldForHeight(rectangle.getHeight());
		setTextFieldForWidth(rectangle.getWidth());
	    outlineColor=rectangle.getColor();
	    innerColor=rectangle.getInnerColor();
	    setVisible(true);

	    if (isConfirmed()) {
	    	int newX = Integer.parseInt(textFieldForUpperX.getText());
	    	int newY = Integer.parseInt(textFieldForUpperY.getText());
	    	int width=Integer.parseInt(textFieldForWidth.getText());
	    	int height=Integer.parseInt(textFieldForHeight.getText());
	    	Point upperLeft =new Point();
	    	upperLeft.setXCoordinate(newX);
	    	upperLeft.setYCoordinate(newY);
	    	rectangle.setUpperLeftPoint(upperLeft);
	    	rectangle.setHeight(height);
	    	rectangle.setWidth(width);

	    	rectangle.setColor(getOutlineColor());
	    	rectangle.setInnerColor(getInnerColor());
	    }
	}
	
	
	public boolean isConfirmed() {
		return confirmed;
	}
	public int getHeightRect() {
		return height;
	}
	public int getWidthRect() {
		return width;
	}
	public void setTextFieldForX(int a){
		 textFieldForUpperX.setText(Integer.toString(a));
	}
	public void setTextFieldForY(int a){
		 textFieldForUpperY.setText(Integer.toString(a));
	}
	public void setTextFieldForWidth(int a){
		 textFieldForWidth.setText(Integer.toString(a));
	}
	public void setTextFieldForHeight(int a){
		 textFieldForHeight.setText(Integer.toString(a));
	}
	
	public void regularTextFields() {
		textFieldForUpperX.setEditable(false);
		textFieldForUpperY.setEditable(false);
	}
	public Color getOutlineColor() {
		return outlineColor;
	}
	public Color getInnerColor() {
		return innerColor;
	}


}