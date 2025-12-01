package pt.ipp.estg.doa.store.payments;

import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilPayment;

import java.time.LocalDate;
import java.util.List;

public class PaymentManager extends AbstractManager<Payment> {

    public PaymentManager() {
        super(new CSVUtilPayment());
    }

    public List<Payment> findByOrder(int orderId) {
        List<Payment> payments = findAll();
        return payments.stream()
                .filter(payment -> payment.getOrder().getId() == orderId)
                .toList();
    }

    public List<Payment> findByStatus(PaymentMethod method) {
        List<Payment> payments = findAll();
        return payments.stream()
                .filter(payment -> payment.getPaymentMethod().equals(method))
                .toList();
    }

    public List<Payment> findByDataRange(LocalDate beginDate, LocalDate endDate) {
        List<Payment> payments = findAll();
        return payments.stream()
                .filter(payment -> !payment.getPaymentDate().isBefore(beginDate)
                        && !payment.getPaymentDate().isAfter(endDate))
                .toList();
    }

    public double calculateTotalReceived(){
        List<Payment> payments = findAll();
        return payments.stream().mapToDouble(Payment::getAmount).sum();
    }

    @Override
    public void validate(Payment payment) throws ManagerValidationException {
        if (payment.getOrder() == null  || payment.getOrder().getId() == 0) {
            throw new ManagerValidationException("Order is required");
        }
        if (payment.getAmount() <= 0) {
            throw new ManagerValidationException("Amount is required");
        }
        if (payment.getPaymentMethod() == null) {
            throw new ManagerValidationException("Payment Method is required");
        }
    }
}
