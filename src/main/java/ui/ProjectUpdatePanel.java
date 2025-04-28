package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.Priority;
import model.Project;
import model.Request;
import model.Status;
import model.Task;
import model.User;
import service.MessageListener;
import service.Service;
import service.ServiceMessage;
import utils.GsonHelper;

public class ProjectUpdatePanel extends JPanel implements MessageListener {
	private JTextField titleField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea descriptionArea;
    private JPanel participantsPanel;
	private List<User> listUser;
	private Project project;


    public ProjectUpdatePanel(Project project) {
    	this.project=project;
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1200, 850)); // chiếm gần full màn hình

        int textFieldWidth = 800;
        int textFieldHeight = 35;
        Font font = new Font("Arial", Font.BOLD, 30);
        JLabel nameScreen = new JLabel("CẬP NHẬT THÔNG TIN PROJECT");
        nameScreen.setFont(font);
        add(nameScreen);
        
        Font font1 = new Font("Arial", Font.BOLD, 16);
        // Title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(font1);
        titleLabel.setPreferredSize(new Dimension(100, textFieldHeight));
        titlePanel.add(titleLabel);

        titleField = new JTextField(project.getTitle());
        titleField.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        titlePanel.add(titleField);
        add(titlePanel);

        // Start Date
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setFont(font1);
        startDateLabel.setPreferredSize(new Dimension(100, textFieldHeight));
        startDatePanel.add(startDateLabel);

        startDateField = new JTextField(project.getStartDate()+"");
        startDateField.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        startDatePanel.add(startDateField);
        add(startDatePanel);

        // End Date
        JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setFont(font1);
        endDateLabel.setPreferredSize(new Dimension(100, textFieldHeight));
        endDatePanel.add(endDateLabel);

        endDateField = new JTextField(project.getEndDate()+"");
        endDateField.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        endDatePanel.add(endDateField);
        add(endDatePanel);

        // Description
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(font1);
        descriptionLabel.setPreferredSize(new Dimension(100, textFieldHeight));
        descriptionPanel.add(descriptionLabel);

        descriptionArea = new JTextArea(8, 20);
        descriptionArea.setText(project.getDescription());
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        descriptionScroll.setPreferredSize(new Dimension(textFieldWidth, 150));
        descriptionPanel.add(descriptionScroll);
        add(descriptionPanel);

        // Participants Label
        JPanel participantsLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel participantsLabel = new JLabel("Participants:");
        participantsLabel.setFont(font1);
        participantsLabel.setPreferredSize(new Dimension(100, textFieldHeight));
        participantsLabelPanel.add(participantsLabel);
        add(participantsLabelPanel);

        // Participants Panel
        participantsPanel = new JPanel();
        participantsPanel.setLayout(new BoxLayout(participantsPanel, BoxLayout.Y_AXIS));
        participantsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        add(participantsPanel);

        // Sample participants
        JScrollPane participantsScroll = new JScrollPane(participantsPanel);
        participantsScroll.setPreferredSize(new Dimension(500, 150)); 
        participantsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        participantsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(participantsScroll);
        

        
        // Update Button
//        JButton updateButton = new JButton("Update Project");
        RoundedButton updateButton = new RoundedButton("CẬP NHẬT", 15);
        updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateButton.setPreferredSize(new Dimension(200, 40));
        updateButton.setBackground(Color.decode("#F299C2"));
        updateButton.setForeground(Color.decode("#FFFFFF"));
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					updateProject();
					GUI_HOME.backToProjectList();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(updateButton);
        
        Service.getInstance().addMessageListener(this);
        SwingUtilities.invokeLater(() -> {
    	    ServiceMessage sm = ServiceMessage.getInstance();
    	    String request = sm.createMessage("LISTS_USER_PROJECT", sm.createObjectJson("projectId", project.getId() + ""));
    	    System.out.println("Req phía client Panel_Update là:" + request);
    	    Service.getInstance().sendMessage(request);
    	});

    }



    private void updateProject() throws ParseException {

    	String title = titleField.getText();
	 	
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();
        
        // Định dạng chuỗi
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        // Chuyển đổi từ String sang Date
        java.sql.Date startD = new java.sql.Date(formatter.parse(startDate).getTime());
        java.sql.Date endD = new java.sql.Date(formatter.parse(endDate).getTime());
        
        
        String description = descriptionArea.getText();
        int id = project.getId();
        Project updateProject = new Project(id,title, description, startD, endD);
        
        List<User> selectedUsers = new ArrayList<>();

        for (Component component : participantsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    String selectedName = checkBox.getText();
                    for (User user : listUser) {
                        if (user.getName().equals(selectedName)) {
                            selectedUsers.add(user);
                            break;
                        }
                    }
                }
            }
        }
        
        ServiceMessage sm = ServiceMessage.getInstance();
        //dùng gson chuyển newTask thành String
        String projectUD = GsonHelper.toJson(updateProject);
        // dùng gson chuyển selected thành string
        String selectedUser = GsonHelper.toJson(selectedUsers);
        
     // Tạo object json (theo key)
        String projectObject = sm.createObjectJson("project", projectUD);
        String usersObject = sm.createObjectJson("selectedUsers", selectedUser);
        
        String requestJson = sm.createMessage("UPDATE_PROJECT",projectObject, usersObject);

     // Giờ gửi requestJson này qua socket cho server
        Service.getInstance().sendMessage(requestJson);
        System.out.println("Request update là:"+requestJson);

    }

	@Override
	public void onMessageReceived(Request<?> request) {
		String message = request.getMessage();
		if(message.equals("LISTS_USER_PROJECT")) {
			listUser = (List<User>) request.getData();
			 // Xóa tất cả thành phần cũ
	        participantsPanel.removeAll();
	        
	        // Thêm các user từ listUser vào participantsPanel
	        for (User user : listUser) {
	            JCheckBox checkBox = new JCheckBox(user.getName());
	            checkBox.setMaximumSize(new Dimension(1000, 30));
	            participantsPanel.add(checkBox);
	        }

	        // Cập nhật lại UI
	        participantsPanel.revalidate();
	        participantsPanel.repaint();
		}
		
	}

}
