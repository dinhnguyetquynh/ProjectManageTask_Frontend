package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.html.StyleSheet.BoxPainter;

public class Panel_DetailTask extends Panel{
    public Panel_DetailTask() {
//        setTitle("Task Management");
//        setSize(700, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
    	
        // Panel chính với BoxLayout
       setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Panel trái (Nội dung Task)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tiêu đề Task
        JLabel titleLabel = new JLabel("Task1");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        leftPanel.add(titleLabel,BorderLayout.NORTH);

        // Người làm
        Box boxCenter = Box.createVerticalBox();
        
        JPanel staffPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel personLbl = new JLabel("Các thành viên thực hiện:");
        JTextField searchTxt = new JTextField(15);
        RoundedButton searchBtn = new RoundedButton("Tìm kiếm",10);
        staffPanel.add(personLbl);
        staffPanel.add(searchTxt);
        staffPanel.add(searchBtn);
        staffPanel.setMaximumSize(new Dimension(700, 50));
        staffPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        boxCenter.add(staffPanel);
        Box.createVerticalStrut(10);
        
        
        //Tên các thành viên thực hiện
        JPanel listStaff = new JPanel();
        listStaff.setLayout(new BoxLayout(listStaff, BoxLayout.Y_AXIS));
        listStaff.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 150));
      
        Box staffBox = Box.createHorizontalBox();
        JLabel nameStaff = new JLabel("Đinh Nguyệt Quỳnh");
        RoundedButton deleteBtn = new RoundedButton("Xóa", 10);
        staffBox.add(nameStaff);
        staffBox.add(Box.createHorizontalStrut(5));
        staffBox.add(deleteBtn);
        listStaff.add(staffBox);
        listStaff.setMaximumSize(new Dimension(750, 50));
        listStaff.setAlignmentX(Component.LEFT_ALIGNMENT);
        boxCenter.add(listStaff);
        
        // Trạng thái
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        statusPanel.setOpaque(false);
        JLabel statusLabel = new JLabel("Trạng thái: ");
        String[] options = {"Mới giao","Đang làm","Hoàn thành"};
		JComboBox<String> comboBoxStatus = new JComboBox<>(options);
		comboBoxStatus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 String selectedOption = (String) comboBoxStatus.getSelectedItem();
	                System.out.println("Selected: " + selectedOption);
				
			}
		});
        comboBoxStatus.setBackground(Color.decode("#04BF8A")); // Màu xanh dương đậm
        comboBoxStatus.setForeground(Color.WHITE);
        Font fontStatus = new Font("Arial", Font.BOLD, 15);
        comboBoxStatus.setFont(fontStatus);
        statusPanel.add(statusLabel);
        statusPanel.add(comboBoxStatus);
        statusPanel.setMaximumSize(new Dimension(700, 50));
        statusPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        boxCenter.add(statusPanel);

        // Ngày tháng
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel dateLabel = new JLabel("Dates:");
        JLabel dateValueLbl = new JLabel("10/12/2024 - 1/1/2025");
        datePanel.add(dateLabel);
        datePanel.add(dateValueLbl);
        datePanel.setMaximumSize(new Dimension(700, 30));
        datePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        boxCenter.add(datePanel);
       

        // Mô tả
        JPanel desPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel descriptionLabel = new JLabel("Description",SwingConstants.LEFT);
        desPanel.add(descriptionLabel);
        desPanel.setMaximumSize(new Dimension(700, 30));
        desPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        boxCenter.add(desPanel);
        
        JTextArea descriptionArea = new JTextArea(10, 40);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JScrollPane scrollPane1 = new JScrollPane(descriptionArea);
        scrollPane1.setPreferredSize(new Dimension(400, 100));
        scrollPane1.setMaximumSize(new Dimension(400, 100));
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel desAreaPanel = new JPanel();
        desAreaPanel.setLayout(new BorderLayout());
        desAreaPanel.add(scrollPane1, BorderLayout.CENTER);
        desAreaPanel.setMaximumSize(new Dimension(700, 300));
        desAreaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        boxCenter.add(desAreaPanel);
        boxCenter.add(Box.createVerticalStrut(10));
        

        // Subtask
        JPanel subTask = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        
        JLabel subTaskLabel = new JLabel("SubTask:");
        subTask.add(subTaskLabel);
        RoundedButton subTaskBtn = new RoundedButton("+ Công việc con", 10);
        subTask.add(subTaskBtn);
        
        subTask.setMaximumSize(new Dimension(700, 300));
        subTask.setAlignmentX(Component.LEFT_ALIGNMENT);
        boxCenter.add(subTask);

        // Button Lưu
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.setMaximumSize(new Dimension(700, 100));
        RoundedButton saveButton = new RoundedButton("LƯU",10);
        saveButton.setBackground(new Color(255, 182, 193)); // Màu hồng nhạt
        saveButton.setForeground(Color.white);
        saveButton.setPreferredSize(new Dimension(100, 40));
        southPanel.add(saveButton);
        // Thêm vào leftPanel
        leftPanel.add(boxCenter,BorderLayout.CENTER);
        leftPanel.add(southPanel,BorderLayout.SOUTH);

        // Panel phải (Bình luận)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(224, 224, 224)); 
        rightPanel.setPreferredSize(new Dimension(100, 900));// Màu xám nhạt
//        rightPanel.setPreferredSize(new Dimension(200, 500));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Người bình luận
        JPanel commentPanel = new JPanel();
        commentPanel.setLayout(new BorderLayout());
        commentPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        Box commentCenter = Box.createVerticalBox();
        JLabel commenterLabel = new JLabel("Vũ Minh");
        JLabel editingLabel = new JLabel("Đang chỉnh sửa");
        
        commentCenter.add(commenterLabel);
        commentCenter.add(editingLabel);
        commentPanel.add(commentCenter);
        rightPanel.add(commentCenter,BorderLayout.CENTER);
        // Ô nhập bình luận
        JPanel commentSouth = new JPanel();
        
        JTextField commentField = new JTextField("Write comment");
        commentField.setPreferredSize(new Dimension(600, 30));
        commentField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        commentSouth.add(commentField);
        // Thêm vào rightPanel
       
        rightPanel.add(commentSouth,BorderLayout.SOUTH);

        // Thêm hai panel vào mainPanel
        add(leftPanel);
        add(rightPanel);

    
    }

    

}
