package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import controller.Controller.Tool;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import model.DrawingModel;
import javax.swing.SwingConstants;


public class FrmDrawing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final DrawingModel model = new DrawingModel();
	private final PnlDrawing pnlDrawing = new PnlDrawing(model);
	private final Controller controller = new Controller(model,pnlDrawing);
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
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
		northPanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		JButton btnDelete = new JButton("Delete");
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
		northPanel.add(btnDelete);
		
		
		JButton btnModify = new JButton("Modify");
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
		northPanel.add(btnModify);
		
		JButton btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setTool(Tool.LINE);
			}
		});
		northPanel.add(btnLine);
		
		JButton btnCircle = new JButton("Circle");
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setTool(Tool.CIRCLE);
			}
		});
		northPanel.add(btnCircle);
		
		JButton btnDonut = new JButton("Donut");
		btnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setTool(Tool.DONUT);
			}
		});
		northPanel.add(btnDonut);


		JButton btnPoint = new JButton("Point");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setTool(Tool.POINT);
			}
		});
		northPanel.add(btnPoint);
		
		JButton btnRectangle = new JButton("Rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setTool(Tool.RECTANGLE);
			}
		});
		
		JButton btnHexagon = new JButton("Hexagon");
		btnHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setTool(Tool.HEXAGON);
			}
		});
		northPanel.add(btnHexagon);
		northPanel.add(btnRectangle);
	}

}
