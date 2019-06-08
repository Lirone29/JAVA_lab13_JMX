package billboard;

import java.util.EventListener;

public interface BillboardChangedEventListener extends EventListener {
    void onBillboardChangedEvent(BillboardChangedEvent e);
}
