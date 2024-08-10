package day10.BaiTapList;
import java.util.*;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private List<Purchase> purchaseHistory;

    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.purchaseHistory = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Purchase> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void addPurchase(Purchase purchase) {
        purchaseHistory.add(purchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

class Purchase {
    private String purchaseId;
    private String productName;
    private double price;
    private Date date;

    public Purchase(String purchaseId, String productName, double price, Date date) {
        this.purchaseId = purchaseId;
        this.productName = productName;
        this.price = price;
        this.date = date;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId='" + purchaseId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}

class CustomerManagementSystem {
    private Set<Customer> customers;

    public CustomerManagementSystem() {
        customers = new HashSet<>();
    }

    public void addCustomer(Customer customer) {
        if (customers.add(customer)) {
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Customer already exists.");
        }
    }

    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        System.out.println("Customer not found.");
        return null;
    }

    public void displayPurchaseHistory(String customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            System.out.println("Purchase history for customer: " + customer.getName());
            for (Purchase purchase : customer.getPurchaseHistory()) {
                System.out.println(purchase);
            }
        }
    }

    public double calculateTotalSpent(String customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            double total = 0;
            for (Purchase purchase : customer.getPurchaseHistory()) {
                total += purchase.getPrice();
            }
            return total;
        }
        return 0;
    }

    public void listPurchasesWithinDateRange(Date startDate, Date endDate) {
        for (Customer customer : customers) {
            for (Purchase purchase : customer.getPurchaseHistory()) {
                if (purchase.getDate().after(startDate) && purchase.getDate().before(endDate)) {
                    System.out.println(purchase);
                }
            }
        }
    }

    public static void main(String[] args) {
        CustomerManagementSystem cms = new CustomerManagementSystem();

        Customer customer1 = new Customer("C001", "Alice", "alice@example.com");
        Customer customer2 = new Customer("C002", "Bob", "bob@example.com");

        cms.addCustomer(customer1);
        cms.addCustomer(customer2);

        Purchase purchase1 = new Purchase("P001", "Laptop", 1500.00, new Date(2024, 7, 20));
        Purchase purchase2 = new Purchase("P002", "Smartphone", 800.00, new Date(2024, 7, 21));

        customer1.addPurchase(purchase1);
        customer1.addPurchase(purchase2);

        cms.displayPurchaseHistory("C001");
        System.out.println("Total spent by customer C001: $" + cms.calculateTotalSpent("C001"));

        Date startDate = new Date(2024, 7, 19);
        Date endDate = new Date(2024, 7, 22);
        System.out.println("Purchases within date range:");
        cms.listPurchasesWithinDateRange(startDate, endDate);
    }
}
