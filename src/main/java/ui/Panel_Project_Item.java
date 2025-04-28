package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import com.google.gson.Gson;

import design.Constants;
import model.Project;
import service.MessageListener;
import service.Service;
import service.ServiceMessage;

public class Panel_Project_Item extends JPanel implements ActionListener{

	private JTextArea txtAreaNgay;
	
	private JTextArea txtAreaGhiChu;
	private JButton btnTrash;
	private Project project;
	private String projectName;
	private String role;

	private JButton btnUpdate;
	
	public Panel_Project_Item(Project p) {
		this.project = p;
		initComponents();
		initActions();
	}
	
	public String getProjectName() {
		projectName = project.getTitle();
		return projectName;
	}

	private void initActions() {
		 MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Color(225,225,225));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.LIGHT_GRAY);
			}
		};
		// Tạo sự kiện riêng cho và txt Ngày
		// dispatchEvent(e) -> Nếu click vào 2 thằng trên thì chuyển sự kiện cho panel xử lý 
		MouseAdapter mouseAdapterChild = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispatchEvent(e);
			}
		};
		this.addMouseListener(mouseAdapter);
		txtAreaNgay.addMouseListener(mouseAdapter);
		txtAreaNgay.addMouseListener(mouseAdapterChild);
		txtAreaGhiChu.addMouseListener(mouseAdapter);
		txtAreaGhiChu.addMouseListener(mouseAdapterChild);
	}

	private void initComponents() {
		role = GUI_HOME.getRole();
		Font font = Constants.DEFAULT_FONT;
		Box b = Box.createVerticalBox();
		Box b1,b2;
		
		b.add(Box.createVerticalStrut(5));
		b.add(b1 = Box.createHorizontalBox());
		JLabel lblTenDuAn, lblSoNguoi;
		b1.setBorder(new EmptyBorder(0, 10, 0, 10)); 
		b1.add(lblTenDuAn = new JLabel(project.getTitle()));
//		b1.add(Box.createHorizontalStrut(10));
		b1.add(Box.createHorizontalGlue()); // Thêm dòng này để lbl dự án sát trái
		
		b1.add(lblSoNguoi = new JLabel(project.getNumberUser()+""));
		b1.add(txtAreaNgay = new JTextArea("Từ: " + project.getStartDate() + "\n" + "Đến: " + project.getEndDate()));
		
		String text = project.getDescription();
		if (text.length() > 85) {
			int index = text.substring(0, 80).lastIndexOf(" ");
			b1.add(txtAreaGhiChu = new JTextArea(text.substring(0, index) + "..."));	
			txtAreaGhiChu.setPreferredSize(new Dimension(265, 70));
			txtAreaGhiChu.setMaximumSize(new Dimension(265, 70));
		} else {
			b1.add(txtAreaGhiChu = new JTextArea(text));	
			if (text.length() < 62) {
				txtAreaGhiChu.setPreferredSize(new Dimension(265, 42));
				txtAreaGhiChu.setMaximumSize(new Dimension(265, 42));
			}
		}
		
//		b1.add(Box.createHorizontalStrut(10));
//		btnTrash = new JButton("");
//		if(role == "Manager") {
//			b1.add(btnTrash);
//			btnTrash.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("img\\24-trash.png")));
//		}
//		
//		
//		// Tạo màu trong suốt cho button
//		btnTrash.setBorderPainted(false);
//		btnTrash.setContentAreaFilled (false);
//		btnTrash.setFocusPainted (false);
		if ("Manager".equals(role)) {
		    btnTrash = new JButton("");
		    btnTrash.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("img\\24-trash.png")));
		    btnTrash.setBorderPainted(false);
		    btnTrash.setContentAreaFilled(false);
		    btnTrash.setFocusPainted(false);
		    btnTrash.addActionListener(this);
		    b1.add(btnTrash);
		}
		
		if("Manager".equals(role)) {
			btnUpdate = new JButton("");
//			btnUpdate.setFont(font);
//			btnUpdate.setPreferredSize(new Dimension(120, 35));
//			btnUpdate.setMaximumSize(new Dimension(120, 35));
			btnUpdate.addActionListener(this); 
			btnUpdate.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("img\\update3.png")));
			btnUpdate.setBorderPainted(false);
			btnUpdate.setContentAreaFilled(false);
			btnUpdate.setFocusPainted(false);
			
			
			b1.add(btnUpdate);
		}
		
		
		lblTenDuAn.setFont(font);
		lblSoNguoi.setFont(font);
		txtAreaNgay.setFont(font);
		txtAreaGhiChu.setFont(font);
		
		// Chỉnh kích thước cho các thành phần
		lblTenDuAn.setPreferredSize(new Dimension(300, 35));
		lblTenDuAn.setMaximumSize(new Dimension(300, 35));
		
		lblSoNguoi.setPreferredSize(new Dimension(150, 35));
		lblSoNguoi.setMaximumSize(new Dimension(150, 35));
		
		txtAreaNgay.setPreferredSize(new Dimension(160, 65));
		
		// Không cho chỉnh sửa ngày và ghi chú, chỉnh màu trong suốt
		txtAreaNgay.setEditable(false);
		txtAreaNgay.setOpaque(false);
		txtAreaNgay.setBorder(new EmptyBorder(10, 0, 0, 0));
		txtAreaGhiChu.setEditable(false);
		txtAreaGhiChu.setOpaque(false);
		
		// Cài đặt chữ tự động xuống dòng
		txtAreaGhiChu.setLineWrap(true);
		txtAreaGhiChu.setWrapStyleWord(true);
		
		// add action
		btnTrash.addActionListener(this);
		
		add(b);
		setMaximumSize(new Dimension(1060, 95));
		setPreferredSize(new Dimension(1060, 95));
		setBackground(new Color(225,225,225));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTrash)) {
			Gson gson = new Gson();
			JOptionPane.showMessageDialog(null, "Xác nhận xoá project: " + getProjectName() + " ?");
		    ServiceMessage sm = ServiceMessage.getInstance();
	        String request = sm.createMessage("DELETE_PROJECT", sm.createObjectJson("projectId", project.getId()+""));
	        System.out.println(request);
	        Service.getInstance().sendMessage(request);
		}else if(o.equals(btnUpdate)) {
			GUI_HOME.showProjectUpdate(project);
			
		}
		
	}
}
