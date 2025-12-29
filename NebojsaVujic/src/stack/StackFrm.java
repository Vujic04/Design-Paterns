package stack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.Stack;
import geometry.Donut;

public class StackFrm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Stack<Donut> stack;
    private DefaultListModel<Donut> listModel;
    private JList<Donut> list;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StackFrm frame = new StackFrm();
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
    public StackFrm() {
    	setTitle("Vujic Nebojsa IT60-2023");
        stack = new Stack<>();
        listModel = new DefaultListModel<>();

        setTitle("StackFrm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel northPanel = new JPanel();
        contentPane.add(northPanel, BorderLayout.NORTH);

        JPanel centralPanel = new JPanel();
        contentPane.add(centralPanel, BorderLayout.CENTER);
        GridBagLayout gbl_centralPanel = new GridBagLayout();
        gbl_centralPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_centralPanel.rowHeights = new int[]{0, 0, 90, 0, 0};
        gbl_centralPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_centralPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        centralPanel.setLayout(gbl_centralPanel);

        JLabel lblDonut = new JLabel("Donut lista");
        GridBagConstraints gbc_lblDonut = new GridBagConstraints();
        gbc_lblDonut.anchor = GridBagConstraints.WEST;
        gbc_lblDonut.insets = new Insets(0, 0, 5, 0);
        gbc_lblDonut.gridx = 5;
        gbc_lblDonut.gridy = 1;
        centralPanel.add(lblDonut, gbc_lblDonut);

        // Dugme za dodavanje Donut-a
        JButton btnNewDonut = new JButton("Dodaj donut");
        btnNewDonut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StackDlg dialog = new StackDlg(StackFrm.this);
                dialog.setVisible(true);
                if(dialog.isConfirmed()) {
                    Donut donut = dialog.getDonut();
                    stack.push(donut);
                    updateList();
                    
                }
            }
        });

        GridBagConstraints gbc_btnNewDonut = new GridBagConstraints();
        gbc_btnNewDonut.anchor = GridBagConstraints.NORTH;
        gbc_btnNewDonut.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewDonut.gridx = 1;
        gbc_btnNewDonut.gridy = 2;
        centralPanel.add(btnNewDonut, gbc_btnNewDonut);

        // Lista sa Donut-ima
        list = new JList<>(listModel);
        GridBagConstraints gbc_list = new GridBagConstraints();
        gbc_list.gridheight = 2;
        gbc_list.fill = GridBagConstraints.BOTH;
        gbc_list.gridx = 5;
        gbc_list.gridy = 2;
        centralPanel.add(list, gbc_list);
        
        
        //Definisanje button-a za uklanjanje
        JButton btnDeleteDonut = new JButton("Ukloni donut");
        btnDeleteDonut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!stack.isEmpty()) {
        			 Donut topDonut=stack.peek();
        			 StackDlg dialog = new StackDlg(StackFrm.this,topDonut);
                     dialog.setVisible(true);
                     
                     if (dialog.isConfirmed()) {
                    	 stack.pop();
                    	 updateList();
                    	 dialog.setVisible(false);
                     }
                     
        		}else {
        			JOptionPane.showMessageDialog(null, "Stek je prazan!", "Poruka", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        GridBagConstraints gbc_btnDeleteDonut = new GridBagConstraints();
        gbc_btnDeleteDonut.insets = new Insets(0, 0, 0, 5);
        gbc_btnDeleteDonut.gridx = 1;
        gbc_btnDeleteDonut.gridy = 3;
        centralPanel.add(btnDeleteDonut, gbc_btnDeleteDonut);

        JPanel southPanel = new JPanel();
        contentPane.add(southPanel, BorderLayout.SOUTH);
    }

    private void updateList() {
        listModel.clear();
        for (int i = stack.size() - 1; i >= 0; i--) {
            listModel.addElement(stack.get(i));
        }
    }
}
