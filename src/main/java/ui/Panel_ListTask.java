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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.Project;
import model.Request;
import model.Task;
import service.MessageListener;
import service.Service;

public class Panel_ListTask extends JPanel implements MessageListener{
	 
	
	private List<Task> listTask = null;
	private JTable table;
	private JScrollPane scrollPane;
	 public Panel_ListTask(Project project) {
		setLayout(new BorderLayout());
//		
//		//NORTH
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel,BoxLayout.Y_AXIS));
		northPanel.setBackground(Color.white);
//		//Tiêu đề
		JLabel titleLbl = new JLabel("DANH SÁCH CÔNG VIỆC");
		Font font = new Font("Arial", Font.BOLD, 30);
		titleLbl.setFont(font);
		titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		
//        
//		
//	
//		//Tên dự án
		JPanel projectNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		projectNamePanel.setBackground(Color.white);
		JLabel projectNameLbl = new JLabel("Dự Án:"+project.getTitle());
		Font nameFont = new Font("Arial", Font.BOLD, 25);
		projectNameLbl.setFont(nameFont);
		projectNameLbl.setBorder(BorderFactory.createEmptyBorder(0, 125, 20, 0));
		projectNamePanel.add(projectNameLbl);
		
		//Mô tả dự án 
		JPanel projectDescription = new JPanel(new FlowLayout(FlowLayout.LEFT));
		projectDescription.setBackground(Color.white);
		
		JTextArea descriptions = new JTextArea("Mô tả dự án:"+project.getDescription());
		Font boldFont = new Font("Arial", Font.BOLD, 14);
		descriptions.setFont(boldFont);
		projectDescription.add(descriptions);
		
		//Ngày dự án
		JPanel dateProject = new JPanel(new FlowLayout(FlowLayout.LEFT));
		dateProject .setBackground(Color.white);
		
		JLabel dateSE = new JLabel("Ngày bắt đầu:"+project.getStartDate()+"-"+"Ngày kết thúc:"+project.getEndDate());
		dateSE.setFont(boldFont);
		dateProject.add(dateSE);
		
		//Người tham gia dự án
		JPanel numOfProject = new JPanel(new FlowLayout(FlowLayout.LEFT));
		numOfProject.setBackground(Color.white);
		
		JLabel number = new JLabel("Số lượng người tham gia dự án:"+project.getNumberUser());
		number.setFont(boldFont);
		numOfProject.add(number);
		
		
		northPanel.add(Box.createVerticalStrut(10));
		northPanel.add(titleLbl);
		northPanel.add(Box.createVerticalStrut(10)); 
		northPanel.add(projectNamePanel);
		northPanel.add(Box.createVerticalStrut(10)); 
		northPanel.add(projectDescription);
		northPanel.add(Box.createVerticalStrut(10)); 
		northPanel.add(dateProject);
		northPanel.add(Box.createVerticalStrut(10)); 
		northPanel.add(numOfProject);
		
		add(northPanel,BorderLayout.NORTH);

		 // CENTER
        	JPanel centerPanel = new JPanel();
        	centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        	
        	  String[][] data = {};
        	  		
        	  		//id, title, description, priority1, createDate, dueDate1, stt

        	        // Tạo tên cột
        	        String[] columnNames = {"Id Task","Tên Task","Mô tả","Mức độ","Ngày tạo","Ngày giao","Trạng thái"};

        	        // Tạo JTable
        	        table = new JTable(data, columnNames);

        	        // Đặt bảng vào JScrollPane để có thanh cuộn nếu dữ liệu dài
        	       scrollPane = new JScrollPane(table);
        	        
        	        centerPanel.add(scrollPane);
        	 add(centerPanel, BorderLayout.CENTER);

//        //SOUTH : BUTTON CÔNG VIỆC MỚI 
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
//     
      //THEM UI VAO listener
      Service.getInstance().addMessageListener(this);

		
		
	}

	@Override
	public void onMessageReceived(Request<?> request) {
		 System.out.println("onMessageReceived() được gọi");
		String message = request.getMessage();
		if(message.equals("LIST_TASKS")) {
			listTask = (List<Task>) request.getData();
			for(Task t: listTask) {
				System.out.println("Task nhận được ở onmessage:"+t.toString());
			}
			if(listTask==null) {
				System.out.println("Không nhận được list Task");
			} else {
	            updateTable(listTask); // <<< cập nhật bảng ở đây
	        }
		}
	
		
	}
	
	private void updateTable(List<Task> listTasks) {
	    if ( listTasks == null) {
	    	System.out.println("Khong co listtask nao het");
	    }
	    
	    String[][] data = new String[listTasks.size()][7];
	    for (int i = 0; i < listTasks.size(); i++) {
	        Task task = listTasks.get(i);
	        data[i][0] = task.getId()+""; // hoặc task.getTaskName() tùy thuộc vào class Task bạn định nghĩa
	        data[i][1] = task.getTitle(); // người thực hiện
	        data[i][2] = task.getDescription();
	        data[i][3] = task.getPriority()+"";
	        data[i][4] = task.getCreateAt()+"";
	        data[i][5] = task.getDueDate()+""; // trạng thái
	        data[i][6] = task.getStatus()+""; 
	       
	    }

	    String[] columnNames = {"Id Task","Tên Task","Mô tả","Mức độ","Ngày tạo","Ngày giao","Trạng thái"};

	    table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
	}

	
}
