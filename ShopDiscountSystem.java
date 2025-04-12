import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopDiscountSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<OrderItem> orderItems = new ArrayList<>();

        System.out.print("Enter the number of order items: ");
        int numItems = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numItems; i++) {
            System.out.println("Enter details for item " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Quantity: ");
            int qty = scanner.nextInt();
            System.out.print("Unit Price: ");
            double unitPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            OrderItem item = new OrderItem(name, qty, unitPrice);
            orderItems.add(item);
            scanner.close();
        }

        // Display the order summary
        System.out.println("\nOrder Summary:");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-20s %-10s %-10s %-10s %-10s %-10s%n",
                "Name", "Qty", "Unit Price", "Order Value", "Discount%", "Total Price");
        System.out.println("-------------------------------------------------------------------");

        double grandTotal = 0.0;

        for (OrderItem item : orderItems) {
            System.out.printf("%-20s %-10d $%-9.2f $%-9.2f %-10.2f%% $%-9.2f%n",
                    item.getName(),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getTotalValueBeforeDiscount(),
                    item.getDiscount() * 100,
                    item.getTotalPrice());
            grandTotal += item.getTotalPrice();
        }

        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-50s $%-10.2f%n", "Grand Total:", grandTotal);
    }
}

class OrderItem {
    private String name;
    private int quantity;
    private double unitPrice;
    private double discount;
    private double totalPrice;
    private double totalValueBeforeDiscount;

    public OrderItem(String name, int quantity, double unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        calculateTotalValueBeforeDiscount();
        calculateDiscount();
        calculateTotalPrice();
    }

    private void calculateTotalValueBeforeDiscount() {
        totalValueBeforeDiscount = quantity * unitPrice;
    }

    private void calculateDiscount() {
        if (totalValueBeforeDiscount >= 100) {
            discount = 0.10;
        } else {
            discount = 0.0;
        }
    }

    private void calculateTotalPrice() {
        totalPrice = totalValueBeforeDiscount * (1 - discount);
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalValueBeforeDiscount() {
        return totalValueBeforeDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
