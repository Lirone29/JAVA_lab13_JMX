package billboard;

public interface IBillboard {

    public void nextAdd();

    public void changeTime(int time);

    public void setAddText(String text);

    public void turnOnBillboard();

    public void turnOfBillboard();

    public void addBillboardChangedEventListener(BillboardChangedEventListener listener);

}
