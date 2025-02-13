package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Account;
import model.Project;
import model.Request;
import service.MessageListener;
import service.Service;

import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Panel;
import javax.swing.JButton;

public class GUI_HOME extends JFrame implements MessageListener {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable projectTable;
	public CardLayout cardLayout;
	private JPanel centerPanel;
	private DefaultListModel listModel;
	private JList menuList;
	
	public static void screenHome(Account account) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//Dòng system để chỉnh hình ảnh không bể
					System.setProperty("sun.java2d.uiScale", "1.0");
				GUI_HOME frame = new GUI_HOME(account);
				frame.setVisible(true);
				} catch (Exception e) {
				e.printStackTrace();
			}
			}
		});
	}
	
//	public static void main(String[] args) {
//		screenHome();
//	}
	



	private Account account;
	public GUI_HOME(Account account) {
		super(account.getRole());
		this.account = account;
		
		
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
        ImageIcon icon = new ImageIcon(GUI_HOME.class.getResource("/icon/user3.png"));
        JLabel iconUser = new JLabel();
        iconUser.setIcon(icon);
        iconUser.setText("Nguyễn My");
        iconUser.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
        northPanel.add(iconUser,BorderLayout.EAST);
        
        add(northPanel, BorderLayout.NORTH);
        
        //WEST
        //Menu
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

        menuPanel.add(createMenuItem("Trang chủ", true,"HOME"));
        menuPanel.add(createMenuItem("Thông báo", true,"NOTIFY"));

        menuPanel.add(createMenuItem("Dự Án", true,"PROJECT"));
        menuPanel.add(createMenuItem("	Danh sách dự án", false,"LIST_PROJECT"));
        menuPanel.add(createMenuItem("	Lịch sử", false,"HISTORY"));
        
        if(account.getRole().equals("Manager")) {
        	   menuPanel.add(createMenuItem("Nhân viên", true,"STAFF"));
               menuPanel.add(createMenuItem("  Danh sách nhân viên", false,"LIST_STAFF"));
               menuPanel.add(createMenuItem("  Tài khoản", false,"ACCOUNT"));
        }
     
        

        add(menuPanel,BorderLayout.WEST);
        

        
        //CENTER
        cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);
        
        Panel_DanhSachProject home= new Panel_DanhSachProject();
        centerPanel.add(home);
        
        
        
        add(centerPanel,BorderLayout.CENTER);

        
        Service.getInstance().addMessageListener(this);
		
		
	}
		
	public String getRole() {
		return account.getRole();
	}
	// hàm chỉnh các menuItem
	  private JLabel createMenuItem(String text, boolean isBold,String panelName) {
	        JLabel label = new JLabel(text);
	        label.setFont(new Font("Arial", isBold ? Font.BOLD : Font.PLAIN, 18));
	        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10)); 
	        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        label.addMouseListener(new MouseAdapter() {
	        	
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		  try {
	        			  	removePreviousPanel();// Xóa panel cũ trước khi thêm panel mới
	        			  	JPanel newPanel = createPanelByName(panelName);
	        			  	 if (newPanel != null) {
	        	                    centerPanel.add(newPanel, panelName);
	        	                    cardLayout.show(centerPanel, panelName);
	        	                    centerPanel.revalidate();
	        	                    centerPanel.repaint();
	        	                }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	        	}
	        	private JPanel createPanelByName(String panelName) {
	        		 switch (panelName) {
	        		 	case "HOME":
	        		 		return new Panel_DanhSachProject();
	        		 	case "LIST_PROJECT":
	        		 		return new Panel_DanhSachProject();
	        	        case "NOTIFY":
	        	            return new Panel_ThongBao();
	        	        case "HISTORY":
	        	            return new Panel_LichSu();
	        	        case"LIST_STAFF":
	        	        	return new Panel_DanhSachNhanVien();
	        	        // Thêm các panel khác nếu cần
	        	        default:
	        	            return null;
	        	    }
				}
				private void removePreviousPanel() {
					  Component[] components = centerPanel.getComponents();
					    for (Component comp : components) {
					        centerPanel.remove(comp); // Xóa tất cả panel cũ
					    }
					
				}
				@Override
	        	public void mouseEntered(MouseEvent e) {
	        		label.setForeground(Color.BLUE);
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		label.setForeground(Color.BLACK);
	        	}
	        	
			});
	        return label;
	    }


	@Override
	public void onMessageReceived(Request<?> request) {
		String message = request.getMessage();
		if (message.equals("REGISTER")) {
			Object[] options = {"Huỷ","Xem"};
			int n = JOptionPane.showOptionDialog(this, 
					"Bạn vừa nhận được một yêu cầu đăng ký tài khoản", 
					"Chú ý", 
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.PLAIN_MESSAGE, 
					null, options, options[1]);
			if (n == 1) {
				Account acc = (Account) request.getData();
				acc.getUser().setManager((account.getUser()));;
				GUI_DuyetYeuCau.screenDuyetYeuCau(acc);
			}
		}
	}
	

}
