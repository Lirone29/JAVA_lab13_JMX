package billboard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Billboard extends JPanel implements IBillboard {

    private static final Color OFF_COLOR = Color.LIGHT_GRAY;
    private static final Color OFF_COLOR_HOVER = Color.GRAY;
    private static final Color ON_COLOR = Color.PINK;
    private static final Color ON_COLOR_HOVER = Color.ORANGE;

    private int currentAdd = -1;
    private int TIME = 5;
    private static final int numberOfAds = 6;
    private static final Dimension BILLBOARD_DIMENSION = new Dimension(500, 400);

    private List<add> addsList;
    private List<BillboardChangedEventListener> billboardChangedEventListeners;
    JTextPane textPane;

    public Billboard(){
        super();
        textPane = new JTextPane();

        setLayout(new FlowLayout());
        addsList = new ArrayList<add>();
        for(int i = 0; i< numberOfAds; i++){
            addsList.add(new add("Add number " + i));
        }

        for(int i = 0; i <numberOfAds; i++){
            System.out.println( addsList.get(i).getText());
        }

        textPane.setText(addsList.get(0).getText());
        currentAdd = 0;
        
        billboardChangedEventListeners = new LinkedList<BillboardChangedEventListener>();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(BILLBOARD_DIMENSION);
        this.setBackground(OFF_COLOR);
        this.add(textPane);
    }

    public void nextAdd() {
        if(!addsList.isEmpty()){
            if(currentAdd < numberOfAds)currentAdd++;
            else currentAdd=0;
        }
        textPane.setText(addsList.get(currentAdd).getText());
        generateLightChangedEvent(currentAdd,true, true,true);
        revalidate();
    }

    public void changeTime(int time) {
        this.TIME = time;
        generateLightChangedEvent(currentAdd,true, false,true);

    }

    public void setAddText(String text) {
        if(currentAdd!=-1 && !addsList.isEmpty()){
            addsList.get(currentAdd).setText(text);
            generateLightChangedEvent(currentAdd,true, true,false);
            textPane.setText(addsList.get(currentAdd).getText());
            revalidate();
        }
    }

    public void turnOnBillboard() {
        Color currentColor = getBackground();
        if(currentColor.equals(OFF_COLOR)){
            setBackground(ON_COLOR);
            textPane.setText(addsList.get(0).getText());
            generateLightChangedEvent(currentAdd,true, false,false);
        }
    }

    public void turnOfBillboard() {
        Color currentColor = getBackground();
        if(currentColor.equals(ON_COLOR)){
            setBackground(OFF_COLOR);
            textPane.setText(null);
            generateLightChangedEvent(currentAdd,false, false,false);
        }
    }

    public void addBillboardChangedEventListener(BillboardChangedEventListener listener) {
       this.billboardChangedEventListeners.add(listener);
    }

    private void generateLightChangedEvent(Integer addNumber,boolean wasTurnedOn,boolean textWasChanged, boolean timeWasChanged) {
        BillboardChangedEvent event = new BillboardChangedEvent(addNumber, wasTurnedOn, textWasChanged,timeWasChanged);
        for (BillboardChangedEventListener listener : billboardChangedEventListeners) {
            listener.onBillboardChangedEvent(event);
        }
        }


}
