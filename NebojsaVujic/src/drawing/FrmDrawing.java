package drawing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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


public class FrmDrawing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

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
		
		PnlDrawing pnlDrawing = new PnlDrawing();
		pnlDrawing.setBackground(Color.WHITE);
		contentPane.add(pnlDrawing, BorderLayout.CENTER);
		
		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shape selectedShape = pnlDrawing.getSelectedShape();
				
				if (selectedShape !=null) {
		            int response = JOptionPane.showConfirmDialog(null, 
		                		"Do you want to delete this shape?\n\n" , 
		                        "Delete confirmation", 
		                        JOptionPane.OK_CANCEL_OPTION, 
		                        JOptionPane.WARNING_MESSAGE);
		            
		            if (response == JOptionPane.OK_OPTION) {
		            	pnlDrawing.getShapes().remove(selectedShape);
		            	pnlDrawing.repaint();
		                }; 
		                return;
		            }else {
		            	JOptionPane.showMessageDialog(null, "No shape selected to delete", "Message", JOptionPane.INFORMATION_MESSAGE);
		            }
		        }
		});
		
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.RED);
		northPanel.add(btnDelete);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Shape> allShapes = pnlDrawing.getShapes();
				for(Shape item: allShapes)
				{
					if(item.isSelected())
					{
						if(item instanceof Point)
						{
							Point point = (Point) item; 
				            PointDlg dialog = new PointDlg();  
				            dialog.modifyPoint(point);  
				            
				            if (dialog.isConfirmed()) {
				                repaint();
				                pnlDrawing.setSelected("Selected");
				            }

						}else if (item instanceof Line) {
			    			Line line = (Line) item; 
			    			LineDlg dialogLine = new LineDlg();  
			    			dialogLine.modifyLine(line);  
			    			
			    			if (dialogLine.isConfirmed()) {
			    				repaint();
			    				pnlDrawing.setSelected("Selected");
			    			}
			    		}else if (item instanceof Donut) {
			    			Donut donut = (Donut) item; 
			    			DonutDlg dialogDonut = new DonutDlg(); 
			    			dialogDonut.modifyDonut(donut);
			    			
			    			if (dialogDonut.isConfirmed()) {
			    				repaint();
			    				pnlDrawing.setSelected("Selected");
			    			}
			    		}else if (item instanceof Circle) {
			    			Circle circle = (Circle) item; 
			    			CircleDlg dialogCircle = new CircleDlg(); 
			    			dialogCircle.modifyCircle(circle);
			    			
			    			if (dialogCircle.isConfirmed()) {
			    				repaint();
			    				pnlDrawing.setSelected("Selected");
			    			}
			    		}else if (item instanceof Rectangle) {
			    			Rectangle rectangle = (Rectangle) item; 
			    			RectangleDlg dialogRectangle = new RectangleDlg(); 
			    			dialogRectangle.modifyRectangle(rectangle);
			    			
			    			if (dialogRectangle.isConfirmed()) {
			    				repaint();
			    				pnlDrawing.setSelected("Selected");
			    			}
			    		}
						item.setSelected(false);
						pnlDrawing.repaint();
						break;
					}
					
				}
			}
		});
		btnModify.setForeground(Color.WHITE);
		btnModify.setBackground(Color.ORANGE);
		northPanel.add(btnModify);
		
		JButton btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setSelected("Line");
			}
		});
		northPanel.add(btnLine);
		
		JButton btnCircle = new JButton("Circle");
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setSelected("Circle");
			}
		});
		northPanel.add(btnCircle);
		
		JButton btnDonut = new JButton("Donut");
		btnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setSelected("Donut");
			}
		});
		northPanel.add(btnDonut);
		
		//Definisanje point button-a
		JButton btnPoint = new JButton("Point");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setSelected("Point");
			}
		});
		northPanel.add(btnPoint);
		
		JButton btnRectangle = new JButton("Rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setSelected("Rectangle");
			}
		});
		northPanel.add(btnRectangle);
	}

}
