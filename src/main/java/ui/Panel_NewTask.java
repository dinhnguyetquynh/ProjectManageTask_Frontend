package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Project;
import model.Request;
import model.Task;
import model.User;
import service.MessageListener;
import service.Service;

public class Panel_NewTask extends JPanel implements MessageListener{

	
	List<User> listUser = new ArrayList<User>();
	private Box checkBoxContainer;
	private JTextField nameTxt;
	private JTextField dayStartTxt;
	private JTextField dayEndTxt;
	private JTextArea jobDesTxt;
	private JComboBox priorityComboBox;
	public Panel_NewTask(Project project) {

		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(1600, 100));
		northPanel.setBackground(Color.white);
		JLabel titleLbl = new JLabel("TẠO CÔNG VIỆC MỚI",SwingConstants.CENTER);
		Font titleFont = new Font("Inter", Font.BOLD, 30);
		titleLbl.setFont(titleFont);
		northPanel.add(titleLbl);
		add(northPanel,BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		centerPanel.setPreferredSize(new Dimension(1600, 400));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
		centerPanel.setBackground(Color.white);
		Box formVertical = Box.createVerticalBox();
		
		Font fontLbl = new Font("Inter", Font.BOLD, 18);
		
		//Tên công việc
		Box nameTask = Box.createHorizontalBox();
		nameTask.setPreferredSize(new Dimension(800, 35));
		nameTask.setMaximumSize(new Dimension(800, 35));
		
		
		JLabel nameLbl = new JLabel("Tên công việc");
		nameLbl.setFont(fontLbl);
		 nameTxt = new JTextField();
		nameTxt.setFont(fontLbl);
		nameTask.add(nameLbl);
		nameTask.add(Box.createHorizontalStrut(20));
		nameTask.add(nameTxt);
		
		//Ngày bắt đầu
		Box dayStart = Box.createHorizontalBox();
		dayStart.setPreferredSize(new Dimension(800, 35));
		dayStart.setMaximumSize(new Dimension(800, 35));
		JLabel dayStartLbl = new JLabel("Ngày bắt đầu");
		dayStartLbl.setFont(fontLbl);
		dayStartTxt = new JTextField(10);
		dayStartTxt.setFont(fontLbl);
		dayStart.add(dayStartLbl);
		dayStart.add(Box.createHorizontalStrut(28));
		dayStart.add(dayStartTxt);
		
		//Ngày kết thúc
		Box dayEnd = Box.createHorizontalBox();
		dayEnd.setPreferredSize(new Dimension(800, 35));
		dayEnd.setMaximumSize(new Dimension(800, 35));
		JLabel dayEndLbl = new JLabel("Ngày kết thúc");
		dayEndLbl.setFont(fontLbl);
		dayEndTxt = new JTextField(30);
		dayEndTxt.setFont(fontLbl);
		dayEnd.add(dayEndLbl);
		dayEnd.add(Box.createHorizontalStrut(25));
		dayEnd.add(dayEndTxt);
		
		//Box độ ưu tiên
		Box priorityBox = Box.createHorizontalBox();
		priorityBox.setPreferredSize(new Dimension(800, 35));
		priorityBox.setMaximumSize(new Dimension(800, 35));
		JLabel priorityLbl = new JLabel("Độ ưu tiên");
		priorityLbl.setFont(fontLbl);

		String[] priorityLevels = {"Thấp", "Trung bình", "Cao"};
		priorityComboBox = new JComboBox<>(priorityLevels);
		priorityComboBox.setFont(fontLbl);
		priorityComboBox.setPreferredSize(new Dimension(300, 35));

		priorityBox.add(priorityLbl);
		priorityBox.add(Box.createHorizontalStrut(20));
		priorityBox.add(priorityComboBox);
		
		//Nhân viên tham gia 
		Box staff = Box.createHorizontalBox();
		staff.setPreferredSize(new Dimension(800, 100));
		staff.setMaximumSize(new Dimension(800, 100));
		JLabel staffLbl = new JLabel("Chọn người tham gia");
		staffLbl.setFont(fontLbl);
		
		
		checkBoxContainer = Box.createVerticalBox(); // chứa các checkbox

		for (User user: listUser) {
		    JCheckBox checkBox = new JCheckBox(user.getName());
		    checkBox.setFont(fontLbl);
		    checkBox.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            JCheckBox source = (JCheckBox) e.getSource();
		            if (source.isSelected()) {
		                System.out.println("Selected: " + source.getText());
		            } else {
		                System.out.println("Unselected: " + source.getText());
		            }
		        }
		    });
		    checkBoxContainer.add(checkBox);
		    checkBoxContainer.add(Box.createVerticalStrut(10)); // khoảng cách giữa các checkbox
		}
		JScrollPane checkBoxScrollPane = new JScrollPane(checkBoxContainer);
		checkBoxScrollPane.setPreferredSize(new Dimension(500, 150)); // kích thước scroll
		checkBoxScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		checkBoxScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		checkBoxScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		staff.add(staffLbl);
		staff.add(Box.createHorizontalStrut(14));
		staff.add(checkBoxScrollPane);
		
		Box jobDes = Box.createHorizontalBox();
		jobDes.setPreferredSize(new Dimension(800, 100));
		jobDes.setMaximumSize(new Dimension(800, 100));
		JLabel jobDesLbl = new JLabel("Mô tả công việc");
		jobDesLbl.setFont(fontLbl);
		jobDesTxt = new JTextArea(20, 50);
		JScrollPane scrollPane = new JScrollPane(jobDesTxt);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
		jobDesTxt.setFont(fontLbl);
		jobDes.add(jobDesLbl);
		jobDes.add(Box.createHorizontalStrut(14));
		jobDes.add(scrollPane);
		
		Box subTask = Box.createHorizontalBox();
		subTask.setPreferredSize(new Dimension(800, 35));
		subTask.setMaximumSize(new Dimension(800, 35));
		JLabel subTaskLbl = new JLabel("Công việc con");
		subTaskLbl.setFont(fontLbl);
		JButton subTaskBtn = new JButton("+ Công việc con");
		subTaskBtn.setFont(fontLbl);
		subTaskBtn.setBackground(Color.white);
		subTaskBtn.setMaximumSize(new Dimension(300, 35));
		
		
		subTask.add(subTaskLbl);
		subTask.add(Box.createHorizontalStrut(22));
		subTask.add(subTaskBtn);
		
		

	
		
		
		
		formVertical.add(nameTask);
		formVertical.add(Box.createVerticalStrut(30));
		formVertical.add(dayStart);
		formVertical.add(Box.createVerticalStrut(30));
		formVertical.add(dayEnd);
		formVertical.add(Box.createVerticalStrut(30));
		
		formVertical.add(priorityBox);
		formVertical.add(Box.createVerticalStrut(30));
		
		formVertical.add(staff);
		formVertical.add(Box.createVerticalStrut(30));
		formVertical.add(jobDes);
		formVertical.add(Box.createVerticalStrut(30));
		formVertical.add(subTask);
		
		centerPanel.add(formVertical);
		
		add(centerPanel,BorderLayout.CENTER);
		
		  JPanel southPanel = new JPanel();
	      southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,50,10));
	      southPanel.setPreferredSize(new Dimension(150, 100));
	      
	      RoundedButton btnNewTask = new RoundedButton("Tạo",15);
	      btnNewTask.setPreferredSize(new Dimension(160, 50));
	      btnNewTask.setBackground(Color.decode("#F299C2"));
	      btnNewTask.setForeground(Color.decode("#FFFFFF"));
	      btnNewTask.setFont(new Font("Arial", Font.BOLD, 20));
	      
	      btnNewTask.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	      
	      
	      southPanel.add(btnNewTask);
	      southPanel.setBackground(Color.white);
	      
	      add(southPanel,BorderLayout.SOUTH);
	      
	      //ADD UI VÀO LISTENER
	      Service.getInstance().addMessageListener(this);
	      
	
	}
	@Override
	public void onMessageReceived(Request<?> request) {
		System.out.println("OnMessage ở Panel_NewTask được gọi");
		String message = request.getMessage();
		if(message.equals("LISTS_USER_PROJECT")) {
			listUser = (List<User>) request.getData();
			
			checkBoxContainer.removeAll();
			
			  
	        Font fontLbl = new Font("Inter", Font.BOLD, 18); // tạo lại font nếu cần
	        
	        // Vẽ lại checkbox mới
	        for (User user : listUser) {
	            JCheckBox checkBox = new JCheckBox(user.getName());
	            checkBox.setFont(fontLbl);
	            checkBox.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    JCheckBox source = (JCheckBox) e.getSource();
	                    if (source.isSelected()) {
	                        System.out.println("Selected: " + source.getText());
	                    } else {
	                        System.out.println("Unselected: " + source.getText());
	                    }
	                }
	            });
	            checkBoxContainer.add(checkBox);
	            checkBoxContainer.add(Box.createHorizontalStrut(10));
	        }
	        
	        // Update UI
	        checkBoxContainer.revalidate();
	        checkBoxContainer.repaint();
	    }

		
	}


}
