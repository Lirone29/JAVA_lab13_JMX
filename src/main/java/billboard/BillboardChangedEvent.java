package billboard;

import java.util.EventObject;

public class BillboardChangedEvent extends EventObject {

    private boolean wasTurnedOn;
    private boolean textWasChanged;
    private boolean timeWasChanged;

    //If 3x true it means add was changed manually, not throught thread
    public BillboardChangedEvent(Integer source, boolean wasDisplayedOn, boolean textWasChanged, boolean timeWasChanged) {
        super(source);
        this.wasTurnedOn = wasDisplayedOn;
        this.textWasChanged = textWasChanged;
        this.timeWasChanged = timeWasChanged;
    }

    public Integer getSource() {
        return (Integer) super.getSource();
    }

    public boolean isTextWasChanged(){
        return this.textWasChanged;
    }

    public boolean isWasTurnedOn() {
        return wasTurnedOn;
    }

    public boolean isTimeWasChanged() {
        return timeWasChanged;
    }

}
