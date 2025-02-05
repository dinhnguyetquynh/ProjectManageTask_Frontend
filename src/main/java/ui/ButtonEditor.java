package ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ButtonEditor extends DefaultCellEditor{
	  private JButton button;
	    private boolean isClicked;
	    private JTable table;
	    private int row, column;

	    public ButtonEditor(JTable table, ImageIcon icon, String actionType) {
	        super(new JTextField());
	        this.table = table;
	        this.button = new JButton(icon);
	        this.button.setOpaque(true);
	        this.button.setBorderPainted(false);

	        // Bắt sự kiện khi nhấn vào nút
	        this.button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					 isClicked = true;
		                fireEditingStopped();

		                if (actionType.equals("view")) {
		                    JOptionPane.showMessageDialog(table, "Xem chi tiết Task: " + table.getValueAt(row, 0));
		                } else if (actionType.equals("delete")) {
		                    JOptionPane.showMessageDialog(table, "Xóa Task: " + table.getValueAt(row, 0));
		                }
					
				}

	        	
	        	
	        });
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	        this.row = row;
	        this.column = column;
	        return button;
	    }

	    @Override
	    public Object getCellEditorValue() {
	        isClicked = false;
	        return null;
	    }

	    @Override
	    public boolean stopCellEditing() {
	        isClicked = false;
	        return super.stopCellEditing();
	    }

}
