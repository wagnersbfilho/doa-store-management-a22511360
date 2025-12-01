package pt.ipp.estg.doa.store.payments;

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
    public boolean validate(Payment payment) {
        if (payment.getOrder() == null  || payment.getOrder().getId() == 0) {
            System.out.println("Order is required");
            return false;
        }
        if (payment.getAmount() <= 0) {
            System.out.println("Amount is required");
            return false;
        }
        if (payment.getPaymentMethod() == null) {
            System.out.println("Payment Method is required");
            return false;
        }
        return true;
    }
}
