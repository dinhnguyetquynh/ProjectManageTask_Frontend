package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import design.CheckListItem;
import design.CheckListRenderer;

public class ChiTietProject extends JFrame implements ActionListener{

	private JButton btnLuu;
	private JTextField txtTenDuAn;
	private JTextField txtNgayBatDau;
	private JTextField txtNgayKetThuc;
	private JTextArea txtMoTa;
	private ArrayList<String> listRemoveUser;

	public ChiTietProject() {
		Font font = new Font("Arial", Font.PLAIN, 18);
		listRemoveUser = new ArrayList<String>();
		setLayout(new BorderLayout());

		JPanel pCen = new JPanel();

		Box b = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6, b7, b8;

		b.add(Box.createVerticalStrut(15));
		b.add(b1 = Box.createHorizontalBox());
		JLabel lblTitle;
		b1.add(lblTitle = new JLabel("Chi tiết dự án"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

		// Tên dự án
		b.add(Box.createVerticalStrut(15));
		b.add(b2 = Box.createHorizontalBox());
		JLabel lblTen;
		b2.add(lblTen = new JLabel("Tên dự án:"));
		b2.add(txtTenDuAn = new JTextField(20));
		
		// Mô tả
		b.add(Box.createVerticalStrut(15));
		b.add(b8 = Box.createHorizontalBox());
		JLabel lblMoTa;
		b8.add(lblMoTa = new JLabel("Mô tả:"));
		txtMoTa = new JTextArea(3,20);
		// Cài đặt chữ tự động xuống dòng
		txtMoTa.setLineWrap(true);
		txtMoTa.setWrapStyleWord(true);
		JScrollPane scrollMota = new JScrollPane(txtMoTa);
		scrollMota.setBorder(null);
		b8.add(scrollMota);

		// Ngày bắt đầu
		b.add(Box.createVerticalStrut(15));
		b.add(b3 = Box.createHorizontalBox());
		JLabel lblNgayBatDau;
		b3.add(lblNgayBatDau = new JLabel("Ngày bắt đầu:"));
		b3.add(txtNgayBatDau = new JTextField(20));

		// Ngày kết thúc
		b.add(Box.createVerticalStrut(15));
		b.add(b4 = Box.createHorizontalBox());
		JLabel lblNgayKetThuc;
		b4.add(lblNgayKetThuc = new JLabel("Ngày kết thúc:"));
		b4.add(txtNgayKetThuc = new JTextField(20));

		// Danh sách người tham gia
		b.add(Box.createVerticalStrut(15));
		b.add(b5 = Box.createHorizontalBox());
		JLabel lblNguoiThamGia;
		b5.add(lblNguoiThamGia = new JLabel("Danh sách người tham gia:"));
		b5.add(Box.createHorizontalGlue());

		DefaultListModel<CheckListItem> model = new DefaultListModel<>();

		List<String> listUserName = List.of("user 1", "user 2", "user 3", "user 4");
		for (String name : listUserName) {
			model.addElement(new CheckListItem(name));
		}

		JList<CheckListItem> list = new JList<>(model);

		list.setCellRenderer(new CheckListRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		List<String> listItem = new ArrayList<String>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				JList list = (JList) event.getSource();
				int index = list.locationToIndex(event.getPoint());
				CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
				item.setSelected(!item.isSelected()); // Toggle selected state
				if (item.isSelected()) {
					listItem.add(item.toString());
					listRemoveUser.remove(item.toString());
				} else {
					listItem.remove(item.toString());
					listRemoveUser.add(item.toString());
				}
				list.repaint(list.getCellBounds(index, index));// Repaint cell
			}
		});
		
		for (int i = 0; i < listUserName.size(); i++) {
			CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
			item.setSelected(!item.isSelected());
		}
		
		// Số dòng hiển thị tối đa
		list.setVisibleRowCount(6);
		
		JScrollPane scrollUser = new JScrollPane(list);
		
		b.add(Box.createVerticalStrut(5));
		b.add(b6 = Box.createHorizontalBox());
		b6.add(scrollUser);

		// Button tạo dự án
		b.add(Box.createVerticalStrut(35));
		b.add(b7 = Box.createHorizontalBox());
		b7.add(btnLuu = new JButton("Lưu"));
		btnLuu.setBackground(new java.awt.Color(0, 235, 235));
		btnLuu.setMaximumSize(new Dimension(195, 40));
		btnLuu.setPreferredSize(new Dimension(195, 40));
		btnLuu.setFont(font);
		btnLuu.setFocusable(false);
		
		// Tuỳ chỉnh font và kích thước
		list.setFont(font);
		// label
		lblTen.setFont(font);
		lblMoTa.setFont(font);
		lblNgayBatDau.setFont(font);
		lblNgayKetThuc.setFont(font);
		lblNguoiThamGia.setFont(font);
		
		lblTen.setPreferredSize(new Dimension(125, 30));
		lblMoTa.setPreferredSize(new Dimension(125, 30));
		lblNgayBatDau.setPreferredSize(new Dimension(125, 30));
		lblNgayKetThuc.setPreferredSize(new Dimension(125, 30));
		
		// button
		btnLuu.setFont(font);
		
		// text
		txtTenDuAn.setFont(font);
		txtNgayBatDau.setFont(font);
		txtNgayKetThuc.setFont(font);
		txtMoTa.setFont(font);
		
		// thêm viền và padding
		CompoundBorder compoundBorder = new CompoundBorder(new LineBorder(Color.gray, 1), new EmptyBorder(0, 5, 0, 5));
		txtTenDuAn.setBorder(compoundBorder);
		txtNgayBatDau.setBorder(compoundBorder);
		txtNgayKetThuc.setBorder(compoundBorder);
		txtMoTa.setBorder(compoundBorder);

		// example data
		txtTenDuAn.setText("Dự án ...");
		txtMoTa.setText("Không có gì đặc biệt...");
		txtNgayBatDau.setText("01/01/2001");
		txtNgayKetThuc.setText("02/02/2002");
		
		// add action
		btnLuu.addActionListener(this);
		
		pCen.add(b);
		add(pCen, BorderLayout.CENTER);
		
		setSize(570, 630);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLuu)) {
			luuChiTietProject();
		}
		
	}

	private void luuChiTietProject() {
		// Xử lý....
		
		// Kiểm tra nếu có user bị remove
		if (listRemoveUser.size() > 0) {
			Object[] options = {"Huỷ", "Đồng ý"};
			JLabel message = new JLabel("<html>Bạn muốn xoá những nhân viên sau khỏi Dự án ?<br>" + listRemoveUser.toString() + "</html>");
			message.setFont(new Font("Arial", Font.PLAIN, 15));
			int thongBao = JOptionPane.showOptionDialog(
					this, 
					message,
					"Chú ý",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,
					options[1]
				);
			// Đồng ý
			if(thongBao == 1) {
				// update data
			}
		}
		
	}
}
