package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgTest extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldRed;
	private JTextField textFieldGreen;
	private JTextField textFieldBlue;
	private boolean isOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgTest dialog = new DlgTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgTest() {
		setModal(true);
		setTitle("Dodavanje boje");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblRedColor = new JLabel("Red");
			GridBagConstraints gbc_lblRedColor = new GridBagConstraints();
			gbc_lblRedColor.anchor = GridBagConstraints.EAST;
			gbc_lblRedColor.insets = new Insets(0, 0, 5, 5);
			gbc_lblRedColor.gridx = 0;
			gbc_lblRedColor.gridy = 0;
			contentPanel.add(lblRedColor, gbc_lblRedColor);
		}
		{
			textFieldRed = new JTextField();
			GridBagConstraints gbc_textFieldRed = new GridBagConstraints();
			gbc_textFieldRed.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldRed.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldRed.gridx = 1;
			gbc_textFieldRed.gridy = 0;
			contentPanel.add(textFieldRed, gbc_textFieldRed);
			textFieldRed.setColumns(10);
		}
		{
			JLabel lblGreenColor = new JLabel("Green");
			GridBagConstraints gbc_lblGreenColor = new GridBagConstraints();
			gbc_lblGreenColor.anchor = GridBagConstraints.EAST;
			gbc_lblGreenColor.insets = new Insets(0, 0, 5, 5);
			gbc_lblGreenColor.gridx = 0;
			gbc_lblGreenColor.gridy = 1;
			contentPanel.add(lblGreenColor, gbc_lblGreenColor);
		}
		{
			textFieldGreen = new JTextField();
			GridBagConstraints gbc_textFieldGreen = new GridBagConstraints();
			gbc_textFieldGreen.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldGreen.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldGreen.gridx = 1;
			gbc_textFieldGreen.gridy = 1;
			contentPanel.add(textFieldGreen, gbc_textFieldGreen);
			textFieldGreen.setColumns(10);
		}
		{
			JLabel lblBlueColor = new JLabel("Blue");
			GridBagConstraints gbc_lblBlueColor = new GridBagConstraints();
			gbc_lblBlueColor.insets = new Insets(0, 0, 0, 5);
			gbc_lblBlueColor.anchor = GridBagConstraints.EAST;
			gbc_lblBlueColor.gridx = 0;
			gbc_lblBlueColor.gridy = 2;
			contentPanel.add(lblBlueColor, gbc_lblBlueColor);
		}
		{
			textFieldBlue = new JTextField();
			GridBagConstraints gbc_textFieldBlue = new GridBagConstraints();
			gbc_textFieldBlue.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldBlue.gridx = 1;
			gbc_textFieldBlue.gridy = 2;
			contentPanel.add(textFieldBlue, gbc_textFieldBlue);
			textFieldBlue.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							//preuzimanje vrednosti
							int red=Integer.parseInt(textFieldRed.getText());
							int green=Integer.parseInt(textFieldGreen.getText());
							int blue=Integer.parseInt(textFieldBlue.getText());
							//dodati i za ostale boje
							if(red>=0 && red<256) {
								isOk=true;
							}else {
								JOptionPane.showMessageDialog(null, "Vrednosti moraju biti u opsegu", 
										"Poruka", JOptionPane.ERROR_MESSAGE);
							}
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Vrednosti moraju biti numericke", 
									"Poruka", JOptionPane.ERROR_MESSAGE);
						} 
										
						setVisible(false);
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

	public JTextField getTextFieldRed() {
		return textFieldRed;
	}

	public void setTextFieldRed(JTextField textFieldRed) {
		this.textFieldRed = textFieldRed;
	}

	public JTextField getTextFieldGreen() {
		return textFieldGreen;
	}

	public void setTextFieldGreen(JTextField textFieldGreen) {
		this.textFieldGreen = textFieldGreen;
	}

	public JTextField getTextFieldBlue() {
		return textFieldBlue;
	}

	public void setTextFieldBlue(JTextField textFieldBlue) {
		this.textFieldBlue = textFieldBlue;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
}