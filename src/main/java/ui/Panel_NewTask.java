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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Panel_NewTask extends JPanel{

	
	
	public Panel_NewTask() {
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int screenWidth = screenSize.width;
//		int screenHeight = screenSize.height;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(screenWidth,screenHeight);
//		setLocationRelativeTo(null);
//	    setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		JTextField nameTxt = new JTextField();
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
		JTextField dayStartTxt = new JTextField(10);
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
		JTextField dayEndTxt = new JTextField(30);
		dayEndTxt.setFont(fontLbl);
		dayEnd.add(dayEndLbl);
		dayEnd.add(Box.createHorizontalStrut(25));
		dayEnd.add(dayEndTxt);
		
		//Nhân viên tham gia 
		Box staff = Box.createHorizontalBox();
		staff.setPreferredSize(new Dimension(800, 35));
		staff.setMaximumSize(new Dimension(800, 35));
		JLabel staffLbl = new JLabel("Người tham gia");
		staffLbl.setFont(fontLbl);
		String[] options = {"Nguyễn Mi","Trần A","Đinh Hùng","Lê Trí"};
		JComboBox<String> comboBox = new JComboBox<>(options);
		comboBox.setFont(fontLbl);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 String selectedOption = (String) comboBox.getSelectedItem();
	                System.out.println("Selected: " + selectedOption);
				
			}
		});
		staff.add(staffLbl);
		staff.add(Box.createHorizontalStrut(14));
		staff.add(comboBox);
		
		Box jobDes = Box.createHorizontalBox();
		jobDes.setPreferredSize(new Dimension(800, 150));
		jobDes.setMaximumSize(new Dimension(800, 150));
		JLabel jobDesLbl = new JLabel("Mô tả công việc");
		jobDesLbl.setFont(fontLbl);
		JTextArea jobDesTxt = new JTextArea(20, 50);
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
	      
	      southPanel.add(btnNewTask);
	      southPanel.setBackground(Color.white);
	      
	      add(southPanel,BorderLayout.SOUTH);
	      
		
	}

}
