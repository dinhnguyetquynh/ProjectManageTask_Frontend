package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Panel_Task_Item extends JPanel{
	
	public Panel_Task_Item() {
	
		setLayout(new GridLayout(1, 6, 10, 0));// Đảm bảo các cột thẳng hàng với titleBox
		setPreferredSize(new Dimension(1600, 50));
        setMaximumSize(new Dimension(1600, 50));
        setMinimumSize(new Dimension(1600, 50)); 
        setBackground(Color.decode("#D9D9D9"));

        JLabel nameTask = new JLabel("Frontend", SwingConstants.CENTER);
        JLabel nameStaff = new JLabel("Nguyễn Lê Thiện", SwingConstants.CENTER);
        JLabel duration = new JLabel("7/2/2025", SwingConstants.CENTER);
        JLabel status = new JLabel("Đang làm", SwingConstants.CENTER);

        ImageIcon eyeIcon = new ImageIcon(Panel_Task_Item.class.getResource("/icon/visible.png"));
        JLabel detail = new JLabel(eyeIcon, SwingConstants.CENTER);
        detail.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		 JOptionPane.showMessageDialog(detail, "Xem chi tieet");
        	}
		});

        ImageIcon deleteIcon = new ImageIcon(Panel_Task_Item.class.getResource("/icon/delete.png"));
        JLabel delete = new JLabel(deleteIcon, SwingConstants.CENTER);

        Font itemFont = new Font("Arial", Font.PLAIN, 18);
        nameTask.setFont(itemFont);
        nameStaff.setFont(itemFont);
        duration.setFont(itemFont);
        status.setFont(itemFont);
        detail.setFont(itemFont);
        delete.setFont(itemFont);

        add(nameTask);
        add(nameStaff);
        add(duration);
        add(status);
        add(detail);
        add(delete);
	}

}
