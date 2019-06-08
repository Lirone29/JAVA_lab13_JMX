package billboard;

import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        JFrame mainFrame = new JFrame("Billboard Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Billboard billboard = new Billboard();

        mainFrame.setContentPane(billboard);
        mainFrame.pack();
        mainFrame.setVisible(true);


        MyBeanAgent agent = new MyBeanAgent(billboard);
        System.out.println("MyBeanAgent is running...");
    }
}
