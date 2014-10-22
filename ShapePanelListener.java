import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ShapePanelListener implements ActionListener {
	public static int shapeType;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("DNA Pen Shape Panel");
		JPanel panel =new JPanel();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(2,2));
        JButton square=new JButton();
        square.setIcon(new ImageIcon(("images/square.png")));
        JButton filledSquare=new JButton();
        filledSquare.setIcon(new ImageIcon(("images/filledsquare.png")));
        JButton line=new JButton();
        line.setIcon(new ImageIcon(("images/line.png")));
        JButton filledCircle=new JButton();
        filledCircle.setIcon(new ImageIcon(("images/filledcircle.png")));
        panel.add(square);
        panel.add(line);
        panel.add(filledSquare);
        panel.add(filledCircle);
        frame.pack(); 
        frame.setVisible(true);
        square.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FreeGridActionListener.canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				FreeGridActionListener.canvas.pressed=5;
				shapeType=1;
			}
		});
		line.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FreeGridActionListener.canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				FreeGridActionListener.canvas.pressed=5;
				shapeType=2;
			}
		});
		filledSquare.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FreeGridActionListener.canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				FreeGridActionListener.canvas.pressed=5;
				shapeType=3;
			}
		});
		filledCircle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FreeGridActionListener.canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				FreeGridActionListener.canvas.pressed=5;
				shapeType=4;
			}
		});
        
	}

}
