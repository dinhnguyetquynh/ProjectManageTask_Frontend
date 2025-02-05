package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.RenderingHints;


import javax.swing.JButton;

public class RoundedButton extends JButton{
	 private int radius; // Độ cong của góc

	    public RoundedButton(String text, int radius) {
	        super(text);
	        this.radius = radius;
	        setOpaque(false); // Đảm bảo vẽ lại nút
	        setContentAreaFilled(false);
	        setBorderPainted(false);
	        setFocusPainted(false);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        // Vẽ nền bo tròn
	        g2.setColor(getBackground());
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

	        // Vẽ viền nút
	        g2.setColor(getForeground());
	        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

	        super.paintComponent(g);
	        g2.dispose();
	    }
}
