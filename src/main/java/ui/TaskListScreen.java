package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.JButton;

public class TaskListScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Dòng system để chỉnh hình ảnh không bể
			        System.setProperty("sun.java2d.uiScale", "1.0");
					TaskListScreen frame = new TaskListScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TaskListScreen() {
		
		//Lấy kích thước màn hình để hiển thị full màn 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenWidth,screenHeight);
		setLocationRelativeTo(null);
	    setExtendedState(JFrame.MAXIMIZED_BOTH); // Mở toàn màn hình
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Color.white);
		
		//PHẦN NORTH 
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.setPreferredSize(new Dimension(screenWidth, 60));
		northPanel.setBackground(Color.decode("#04BF8A"));
		
		//LoGo phía trái north
		JLabel logo = new JLabel("CompleteTask");
		logo.setForeground(Color.WHITE);
		Font logoFont = new Font("Lucida Handwriting", Font.ITALIC, 40);
		logo.setFont(logoFont);
		logo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
		northPanel.add(logo,BorderLayout.WEST);
	
		
		//icon user phía phải north
        ImageIcon icon = new ImageIcon(TaskListScreen.class.getResource("/icon/user3.png"));
        JLabel iconUser = new JLabel();
        iconUser.setIcon(icon);
        iconUser.setText("Nguyễn My");
        iconUser.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
        northPanel.add(iconUser,BorderLayout.EAST);
        
        add(northPanel, BorderLayout.NORTH);
        
        //Menu bên trái
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(250,screenHeight));
        menuPanel.setBackground(Color.white);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        
        //Tạo viền trái cho panel
        menuPanel.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createMatteBorder(0,0,0,2, Color.LIGHT_GRAY),
        		BorderFactory.createEmptyBorder(20, 10, 0, 0)
        		));
        // Tạo các mục menu
        menuPanel.add(createMenuItem("Trang chủ", true));
        menuPanel.add(createMenuItem("Thông báo", true));

        menuPanel.add(createMenuItem("Dự Án", true));
        menuPanel.add(createMenuItem("  Danh sách dự án", false));
        menuPanel.add(createMenuItem("  Lịch sử", false));

        menuPanel.add(createMenuItem("Nhân viên", true));
        menuPanel.add(createMenuItem("  Danh sách nhân viên", false));
        menuPanel.add(createMenuItem("  Tài khoản", false));
        
        
        add(menuPanel,BorderLayout.WEST);
      
		
		
	}
		
	// hàm chỉnh các menuItem
	  private static JLabel createMenuItem(String text, boolean isBold) {
	        JLabel label = new JLabel(text);
	        label.setFont(new Font("Arial", isBold ? Font.BOLD : Font.PLAIN, 18));
	        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10)); // Tạo khoảng cách
	        return label;
	    }

}
