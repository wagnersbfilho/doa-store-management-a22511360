package pt.ipp.estg.doa.store.payments;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilPayment;

public class PaymentManager extends AbstractManager<Payment> {

    public PaymentManager() {
        super(new CSVUtilPayment());
    }
}
