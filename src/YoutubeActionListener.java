import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

/**
 * Created by IntelliJ IDEA.
 * User: shikhar
 * Date: 8/15/13
 * Time: 9:25 PM
 */
public class YoutubeActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    	 //To change body of implemented methods use File | Settings | File Templates.
        Desktop desktop = Desktop.getDesktop();
        try {
            URI updateLink = new URI("http://www.youtube.com/user/dnapen");
            desktop.browse(updateLink);
        } catch (URISyntaxException e1) {
            e1.printStackTrace(System.out);
            JOptionPane.showMessageDialog(MainFrame.mainFrame, "Exception occurred.",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e2) {
            e2.printStackTrace(System.out);
            JOptionPane.showMessageDialog(MainFrame.mainFrame, "Exception occurred.",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    	
    }
}
