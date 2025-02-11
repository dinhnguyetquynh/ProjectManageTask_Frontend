package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Panel_ListTask extends JPanel {
	 public Panel_ListTask() {
		setLayout(new BorderLayout());
		
		//NORTH
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel,BoxLayout.Y_AXIS));
		northPanel.setBackground(Color.white);
		//Tiêu đề
		JLabel titleLbl = new JLabel("DANH SÁCH CÔNG VIỆC");
		Font font = new Font("Arial", Font.BOLD, 30);
		titleLbl.setFont(font);
		titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        
		
	
		//Tên dự án
		JPanel projectNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		projectNamePanel.setBackground(Color.white);
		JLabel projectNameLbl = new JLabel("Dự Án: Xây dựng ứng dụng quản lý công việc");
		Font nameFont = new Font("Arial", Font.BOLD, 25);
		projectNameLbl.setFont(nameFont);
		projectNameLbl.setBorder(BorderFactory.createEmptyBorder(0, 125, 20, 0));
		projectNamePanel.add(projectNameLbl);
		
		northPanel.add(Box.createVerticalStrut(10));
		northPanel.add(titleLbl);
		northPanel.add(Box.createVerticalStrut(10)); 
		northPanel.add(projectNamePanel);
		
		add(northPanel,BorderLayout.NORTH);

		 	
		 // CENTER
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Tiêu đề cột
        JPanel titleBox = new JPanel(new GridLayout(1, 6, 10, 0)); // GridLayout giúp các cột đều nhau
        titleBox.setPreferredSize(new Dimension(1600, 55));
        titleBox.setMaximumSize(new Dimension(1600, 55));
        titleBox.setBackground(Color.white);

        JLabel nameTask = new JLabel("Tên task", SwingConstants.CENTER);
        JLabel nameStaff = new JLabel("Người thực hiện", SwingConstants.CENTER);
        JLabel duration = new JLabel("Thời hạn", SwingConstants.CENTER);
        JLabel status = new JLabel("Trạng thái", SwingConstants.CENTER);
        JLabel detail = new JLabel("Chi tiết", SwingConstants.CENTER);
        JLabel delete = new JLabel("Xóa", SwingConstants.CENTER);

        Font titleFont = new Font("Arial", Font.PLAIN, 20);
        nameTask.setFont(titleFont);
        nameStaff.setFont(titleFont);
        duration.setFont(titleFont);
        status.setFont(titleFont);
        detail.setFont(titleFont);
        delete.setFont(titleFont);

        titleBox.add(nameTask);
        titleBox.add(nameStaff);
        titleBox.add(duration);
        titleBox.add(status);
        titleBox.add(detail);
        titleBox.add(delete);

        // Panel chứa danh sách task
        JPanel taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        
        
        // Thêm nhiều task để kiểm tra thanh cuộn
        for (int i = 0; i < 20; i++) { // Số lượng task nhiều hơn để kích hoạt thanh cuộn
            Panel_Task_Item taskItem = new Panel_Task_Item();
            taskListPanel.add(taskItem);
            taskListPanel.add(Box.createVerticalStrut(5)); // Khoảng cách giữa các task
        }
        centerPanel.add(titleBox);
        centerPanel.add(Box.createVerticalStrut(20));
        JScrollPane taskScroll = new JScrollPane(taskListPanel);
        taskScroll.getViewport().setBackground(Color.white);
        centerPanel.add(taskScroll);
        centerPanel.setBackground(Color.white);
        add(centerPanel, BorderLayout.CENTER);

        
        
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
      
      add(southPanel,BorderLayout.SOUTH);
      
      
    
		
		
	}
	
	
}
