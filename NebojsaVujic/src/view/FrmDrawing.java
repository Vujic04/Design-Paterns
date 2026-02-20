package view;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Observer.Observer;
import controller.Controller;
import controller.Controller.Tool;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.DrawingModel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class FrmDrawing extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final DrawingModel model = new DrawingModel();
	private final PnlDrawing pnlDrawing = new PnlDrawing(model);
	private final Controller controller = new Controller(model,pnlDrawing);
	private JButton btnDelete;
	private JButton btnModify;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToBack;
	private JButton btnBringToFront;



	
	public FrmDrawing() {
		setTitle("Nebojsa Vujic IT60-2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));


		pnlDrawing.setBackground(Color.WHITE);
		contentPane.add(this.pnlDrawing, BorderLayout.CENTER);
		System.out.println("MouseListeners on pnlDrawing: " + this.pnlDrawing.getMouseListeners().length);
		System.out.println(java.util.Arrays.toString(this.pnlDrawing.getMouseListeners()));

		
		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
				GridBagLayout gbl_northPanel = new GridBagLayout();
				gbl_northPanel.columnWidths = new int[]{96, 96, 96, 96, 0, 0, 94, 0};
				gbl_northPanel.rowHeights = new int[]{21, 21, 0};
				gbl_northPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_northPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				northPanel.setLayout(gbl_northPanel);
				
				JButton btnHexagon = new JButton("Hexagon");
				btnHexagon.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.setTool(Tool.HEXAGON);
					}
				});
				
				JButton btnCircle = new JButton("Circle");
				btnCircle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.setTool(Tool.CIRCLE);
					}
				});
				
				
						JButton btnPoint = new JButton("Point");
						btnPoint.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.setTool(Tool.POINT);
							}
						});
						GridBagConstraints gbc_btnPoint = new GridBagConstraints();
						gbc_btnPoint.fill = GridBagConstraints.BOTH;
						gbc_btnPoint.insets = new Insets(0, 0, 5, 5);
						gbc_btnPoint.gridx = 0;
						gbc_btnPoint.gridy = 0;
						northPanel.add(btnPoint, gbc_btnPoint);
				
				JButton btnLine = new JButton("Line");
				btnLine.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.setTool(Tool.LINE);
					}
				});
				GridBagConstraints gbc_btnLine = new GridBagConstraints();
				gbc_btnLine.fill = GridBagConstraints.BOTH;
				gbc_btnLine.insets = new Insets(0, 0, 5, 5);
				gbc_btnLine.gridx = 1;
				gbc_btnLine.gridy = 0;
				northPanel.add(btnLine, gbc_btnLine);
				GridBagConstraints gbc_btnCircle = new GridBagConstraints();
				gbc_btnCircle.fill = GridBagConstraints.BOTH;
				gbc_btnCircle.insets = new Insets(0, 0, 5, 5);
				gbc_btnCircle.gridx = 2;
				gbc_btnCircle.gridy = 0;
				northPanel.add(btnCircle, gbc_btnCircle);
				
				JLabel label = new JLabel("");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.fill = GridBagConstraints.BOTH;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 3;
				gbc_label.gridy = 0;
				northPanel.add(label, gbc_label);
				
				JButton btnRectangle = new JButton("Rectangle");
				btnRectangle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.setTool(Tool.RECTANGLE);
					}
				});
				
				btnToFront = new JButton("To front");
				btnToFront.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.toFront();
					}
				});
				GridBagConstraints gbc_btnToFront = new GridBagConstraints();
				gbc_btnToFront.fill = GridBagConstraints.BOTH;
				gbc_btnToFront.insets = new Insets(0, 0, 5, 5);
				gbc_btnToFront.gridx = 4;
				gbc_btnToFront.gridy = 0;
				northPanel.add(btnToFront, gbc_btnToFront);
				
				btnToBack = new JButton("To back");
				btnToBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.toBack();
					}
				});
				GridBagConstraints gbc_btnToBack = new GridBagConstraints();
				gbc_btnToBack.fill = GridBagConstraints.BOTH;
				gbc_btnToBack.insets = new Insets(0, 0, 5, 5);
				gbc_btnToBack.gridx = 5;
				gbc_btnToBack.gridy = 0;
				northPanel.add(btnToBack, gbc_btnToBack);
				
				btnUndo = new JButton("Undo");
				GridBagConstraints gbc_btnUndo = new GridBagConstraints();
				gbc_btnUndo.fill = GridBagConstraints.BOTH;
				gbc_btnUndo.insets = new Insets(0, 0, 5, 0);
				gbc_btnUndo.gridx = 6;
				gbc_btnUndo.gridy = 0;
				northPanel.add(btnUndo, gbc_btnUndo);
				btnUndo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.undo();
					}
				});
				GridBagConstraints gbc_btnRectangle = new GridBagConstraints();
				gbc_btnRectangle.fill = GridBagConstraints.BOTH;
				gbc_btnRectangle.insets = new Insets(0, 0, 0, 5);
				gbc_btnRectangle.gridx = 0;
				gbc_btnRectangle.gridy = 1;
				northPanel.add(btnRectangle, gbc_btnRectangle);
				
				JButton btnDonut = new JButton("Donut");
				btnDonut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.setTool(Tool.DONUT);
					}
				});
				GridBagConstraints gbc_btnDonut = new GridBagConstraints();
				gbc_btnDonut.fill = GridBagConstraints.BOTH;
				gbc_btnDonut.insets = new Insets(0, 0, 0, 5);
				gbc_btnDonut.gridx = 1;
				gbc_btnDonut.gridy = 1;
				northPanel.add(btnDonut, gbc_btnDonut);
				GridBagConstraints gbc_btnHexagon = new GridBagConstraints();
				gbc_btnHexagon.fill = GridBagConstraints.BOTH;
				gbc_btnHexagon.insets = new Insets(0, 0, 0, 5);
				gbc_btnHexagon.gridx = 2;
				gbc_btnHexagon.gridy = 1;
				northPanel.add(btnHexagon, gbc_btnHexagon);
				
				JLabel label_3 = new JLabel("");
				GridBagConstraints gbc_label_3 = new GridBagConstraints();
				gbc_label_3.fill = GridBagConstraints.BOTH;
				gbc_label_3.insets = new Insets(0, 0, 0, 5);
				gbc_label_3.gridx = 3;
				gbc_label_3.gridy = 1;
				northPanel.add(label_3, gbc_label_3);
				
				btnBringToFront = new JButton("Bring to front");
				btnBringToFront.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.bringToFront();
					}
				});
				GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
				gbc_btnBringToFront.fill = GridBagConstraints.BOTH;
				gbc_btnBringToFront.insets = new Insets(0, 0, 0, 5);
				gbc_btnBringToFront.gridx = 4;
				gbc_btnBringToFront.gridy = 1;
				northPanel.add(btnBringToFront, gbc_btnBringToFront);
				
				btnBringToBack = new JButton("Bring to back");
				btnBringToBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.bringToBack();
					}
				});
				GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
				gbc_btnBringToBack.fill = GridBagConstraints.BOTH;
				gbc_btnBringToBack.insets = new Insets(0, 0, 0, 5);
				gbc_btnBringToBack.gridx = 5;
				gbc_btnBringToBack.gridy = 1;
				northPanel.add(btnBringToBack, gbc_btnBringToBack);
				
				btnRedo = new JButton("Redo");
				GridBagConstraints gbc_btnRedo = new GridBagConstraints();
				gbc_btnRedo.fill = GridBagConstraints.BOTH;
				gbc_btnRedo.gridx = 6;
				gbc_btnRedo.gridy = 1;
				northPanel.add(btnRedo, gbc_btnRedo);
				btnRedo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.redo();
					}
				});
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnOutlineColor = new JButton("Outline color");
		panel.add(btnOutlineColor);
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c =JColorChooser.showDialog(FrmDrawing.this, "Choose outline color", controller.getOutlineColor());
				if(c!=null) {
					controller.setOutlineColor(c);
					btnOutlineColor.setBackground(c);
				}
			}
		});
		
		JButton btnInnerColor = new JButton("Inner color");
		panel.add(btnInnerColor);
		
		
		btnModify = new JButton("Modify");
		panel.add(btnModify);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = controller.getSelectionCount();
				if(count==0) {
					JOptionPane.showMessageDialog(null, "No shape selected to modify", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(count>1) {
					JOptionPane.showMessageDialog(null, "Select exectly one shape to modify", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				controller.modifySelected();
			}
		});
		
		btnModify.setForeground(Color.WHITE);
		btnModify.setBackground(Color.ORANGE);
		
		btnDelete = new JButton("Delete");
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!controller.hasSelection()) {
		            JOptionPane.showMessageDialog(null, "No shape selected to delete", "Message", JOptionPane.INFORMATION_MESSAGE);
		            return;
		        }
				
				int count = controller.getSelectionCount();

				
				
		            int response = JOptionPane.showConfirmDialog(null, 
		                		"Do you want to delete "+ count +" shape?\n\n" , 
		                        "Delete confirmation", 
		                        JOptionPane.OK_CANCEL_OPTION, 
		                        JOptionPane.WARNING_MESSAGE);
		            
		            if (response == JOptionPane.OK_OPTION) {
		            	controller.deleteSelected();
		                }
		                return;
		            
		        }
		});
		
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.RED);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c =JColorChooser.showDialog(FrmDrawing.this, "Choose inner color", controller.getInnerColor());
				if(c!=null) {
					controller.setInnerColor(c);
					btnInnerColor.setBackground(c);
				}
			}
		});
		controller.addObservers(this);
		update();
	}



	@Override
	public void update() {
		// TODO Auto-generated method stub
		int count = controller.getSelectionCount();
        btnDelete.setEnabled(count > 0);   
        btnModify.setEnabled(count == 1);  
       	btnUndo.setEnabled(controller.undoPosible());
       	btnRedo.setEnabled(controller.redoPosible());
       	btnToFront.setEnabled(count==1);
    	btnToBack.setEnabled(count==1);
    	btnBringToBack.setEnabled(count==1);
    	btnBringToFront.setEnabled(count==1);
       	
	}

}
