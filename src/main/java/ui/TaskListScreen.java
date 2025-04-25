package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
		
		
		//PHẦN NORTH 
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.setPreferredSize(new Dimension(screenWidth, 60));
		northPanel.setBackground(Color.decode("#04BF8A"));
		
		//Logo Complete Task
		JLabel logo = new JLabel("CompleteTask");
		logo.setForeground(Color.WHITE);
		Font logoFont = new Font("Lucida Handwriting", Font.ITALIC, 40);
		logo.setFont(logoFont);
		logo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
		northPanel.add(logo,BorderLayout.WEST);
	
		
		//Icon User 
        ImageIcon icon = new ImageIcon(TaskListScreen.class.getResource("/icon/user3.png"));
        JLabel iconUser = new JLabel();
        iconUser.setIcon(icon);
        iconUser.setText("Nguyễn My");
        iconUser.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
        northPanel.add(iconUser,BorderLayout.EAST);
        
        add(northPanel, BorderLayout.NORTH);
        
        //WEST
        //Menu
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(220,screenHeight));
        menuPanel.setBackground(Color.white);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        
        //Tạo viền trái cho panel
        menuPanel.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createMatteBorder(0,0,0,2, Color.LIGHT_GRAY),
        		BorderFactory.createEmptyBorder(15, 10, 0, 0)
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
        
        //CENTER
        JPanel centerPanel = new JPanel(new BorderLayout());

        //Tiêu đề đầu trang "Danh sách công việc"
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.white);
        JLabel titleLbl = new JLabel("DANH SÁCH CÔNG VIỆC",SwingConstants.CENTER);
        titleLbl.setFont(new Font("Arial", Font.BOLD, 30));
        titleLbl.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        titlePanel.add(titleLbl,BorderLayout.NORTH);
        
        //Tên dự án
        JLabel projectNameLbl = new JLabel("Dự án: Xây dựng web chat app",SwingConstants.LEFT);
        projectNameLbl.setFont(new Font("Arial", Font.BOLD, 22));
        projectNameLbl.setBorder(BorderFactory.createEmptyBorder(10,30, 10, 10));
        titlePanel.add(projectNameLbl,BorderLayout.SOUTH);
        
        centerPanel.add(titlePanel,BorderLayout.NORTH);
        
        //Bảng Task
        
        //Dữ liệu giả
        String[][] data= {
        		{"Task1","Vũ Minh","1/1/2025","Đang làm","",""},
        		{"Task2","Minh Nhi","1/1/2025","Đang làm","",""}
        };
        
        //button xem chi tiết và button xóa
        ImageIcon eyesIcon = new ImageIcon(TaskListScreen.class.getResource("/icon/visible.png"));  
        ImageIcon deleteIcon = new ImageIcon(TaskListScreen.class.getResource("/icon/delete.png"));

        
        
        //Tên cột
        String[] columnNames = {"Tên Task","Người thực hiện","Thời hạn","Trạng thái","Chi tiết","Xóa"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        
        //Dùng Render hiển thị btn chi tiết và btn xóa
        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer(eyesIcon));
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer(deleteIcon));
        
        //Dùng Editor để gọi sự kiện cho btn chi tiết và btn xóa
        table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(table, eyesIcon, "view"));
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(table, deleteIcon, "delete"));
        
        //Thay đổi chiều cao của header
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        //Thay đổi chiều cao của các hàng trong bảng
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.white);
        centerPanel.add(scrollPane,BorderLayout.CENTER);
        
        
       //SOUTH : BUTTON CÔNG VIỆC MỚI 
       JPanel southPanel = new JPanel();
       southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,50,10));
       southPanel.setPreferredSize(new Dimension(100, 100));
       
       RoundedButton btnNewTask = new RoundedButton("Công việc mới",15);
       btnNewTask.setPreferredSize(new Dimension(160, 50));
       btnNewTask.setBackground(Color.decode("#F299C2"));
       btnNewTask.setForeground(Color.decode("#FFFFFF"));
       btnNewTask.setFont(new Font("Arial", Font.BOLD, 16));
       
       southPanel.add(btnNewTask);
       southPanel.setBackground(Color.white);
       centerPanel.add(southPanel,BorderLayout.SOUTH);
        
       
      
        
      
		
		
	}
		
	// hàm chỉnh các menuItem
	  private static JLabel createMenuItem(String text, boolean isBold) {
	        JLabel label = new JLabel(text);
	        label.setFont(new Font("Arial", isBold ? Font.BOLD : Font.PLAIN, 18));
	        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10)); // Tạo khoảng cách
	        return label;
	    }

}
