package billboard;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class MyBeanAgent {
    private MBeanServer mbs = null;

    public MyBeanAgent(Billboard billboard) {

        // Get the platform MBeanServer
        mbs = ManagementFactory.getPlatformMBeanServer();

        BillboardController billboardController = new BillboardController();
        billboardController.setBillboard(billboard);
        ObjectName name = null;

        try {
            // Uniquely identify the MBeans and register them with the platform MBeanServer
            name = new ObjectName("BillboardController:name=BillboardController");
            mbs.registerMBean(billboardController, name);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
