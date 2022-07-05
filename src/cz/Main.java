package cz;

import cz.isgastreet.gui.FrameStreet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Marco Schulz
 */

public class Main
{

    public static void main(String[] args)
    {

        setGUILayout();
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                final FrameStreet mainFrame = new FrameStreet();

                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setSize(mainFrame.getMaximumSize());
                mainFrame.setLocationRelativeTo(null);
                mainFrame.pack();
                mainFrame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(runnable);

    }

    public static void setGUILayout()
    {
        try
        {
            
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex)
        {
            System.out.println(ex);
        }

    }

}
