package billboard;

import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class BillboardController extends NotificationBroadcasterSupport implements BillboardControllerMBean {

    private Billboard billboard;
    private long SEQUENCE_NUMBER = 1;

    public void BillboardPanelController(){

    }

    public void changeTextAdd(String text) {
        if(billboard != null){
            this.billboard.setAddText(text);
        }
    }

    public void changeAdd() {
        if(billboard != null){
            this.billboard.nextAdd();
        }
    }

    public void changeTime(int time) {
        this.billboard.changeTime(time);
    }

    public void turnBillboardOff() {
        if(billboard != null){
            this.billboard.turnOfBillboard();
        }

    }

    public void setLightBulbsPanel(Billboard billboard) {
        this.billboard = billboard;
        this.billboard.addBillboardChangedEventListener(new BillboardChangedEventListener() {

            public void onBillboardChangedEvent(BillboardChangedEvent e) {
                createAndSendNotification(e);
            }
        });
    }


    public void turnBillboardOn() {
        if(billboard != null){
            this.billboard.turnOnBillboard();
        }
    }


    protected void createAndSendNotification(BillboardChangedEvent e) {
        Notification notification = new Notification(
                Notification.class.toString(),
                e.getSource(),
                SEQUENCE_NUMBER++,
                System.currentTimeMillis(),
                "Billboard" + e.getSource() + (e.isWasTurnedOn() ? " was turned on." : " was turned off") +  (e.isTextWasChanged() ? " text was changed." : "") + (e.isTimeWasChanged() ? " time was changed " : ""));
        super.sendNotification(notification);
    }

    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
        this.billboard.addBillboardChangedEventListener(new BillboardChangedEventListener() {

            public void onBillboardChangedEvent(BillboardChangedEvent e) {
                createAndSendNotification(e);
            }
        });
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{
                Notification.class.toString()
        };

        String name = "Billboard changed notification";
        String description = "Billboard changed in the adds panel controlled by this MBean";
        MBeanNotificationInfo info =
                new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }


}
