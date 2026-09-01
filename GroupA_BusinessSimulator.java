import java.util.Scanner;

public class GroupA_BusinessSimulator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("===== KABS SUPERMARKET =====");

        String[] items = {"Sugar", "Salt", "Porridge", "Bread"};
        double[] prices = {555.66, 6666.00, 44444.00, 444.00};
        int[] quantities = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i] + " UGX " + prices[i]);
            System.out.print("Enter quantity for " + items[i] + ": ");

            while (true) {
                if (input.hasNextInt()) {
                    quantities[i] = input.nextInt();
                    if (quantities[i] >= 0) {
                        break;
                    }
                    System.out.print("Quantity cannot be negative. Please enter a valid quantity: ");
                } else {
                    System.out.print("Invalid input. Please enter a whole number: ");
                    input.next();
                }
            }
        }

        System.out.println();
        double total = 0;
        double[] afterDiscount = new double[items.length];
        String[] discountMsg = new String[items.length];

        for (int i = 0; i < items.length; i++) {
            afterDiscount[i] = calculateSubtotal(i, prices[i], quantities[i]);

            if (i == 0) {
                if (quantities[i] >= 5) {
                    discountMsg[i] = "(5% discount applied)";
                } else {
                    discountMsg[i] = "(no discount — fewer than 5)";
                }
            } else if (i == 1) {
                discountMsg[i] = "(no discount)";
            } else if (i == 2) {
                if (quantities[i] >= 3) {
                    discountMsg[i] = "(UGX 5000 discount applied)";
                } else {
                    discountMsg[i] = "(no discount — fewer than 3)";
                }
            } else if (i == 3) {
                if (quantities[i] >= 2) {
                    discountMsg[i] = "(10% discount applied)";
                } else {
                    discountMsg[i] = "(no discount — fewer than 2)";
                }
            }

            total += afterDiscount[i];
        }

        printReceipt(items, quantities, afterDiscount, discountMsg, total);
        input.close();
    }

    public static double calculateSubtotal(int index, double price, int quantity) {
        double sub = price * quantity;
        double finalPrice = sub;

        if (index == 0 && quantity >= 5) {
            finalPrice = sub - (sub * 0.05);
        } else if (index == 2 && quantity >= 3) {
            finalPrice = sub - 5000;
        } else if (index == 3 && quantity >= 2) {
            finalPrice = sub - (sub * 0.10);
        }

        return finalPrice;
    }

    public static void printReceipt(String[] items, int[] quantities, double[] afterDiscount,
                                    String[] discountMsg, double total) {
        System.out.println("\n===== RECEIPT =====");

        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i] + " x " + quantities[i] + " = UGX "
                    + formatMoney(afterDiscount[i]) + "     " + discountMsg[i]);
        }

        System.out.println("------------------------------------------");
        System.out.println("TOTAL = UGX " + formatMoney(total));
    }

    public static String formatMoney(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        } else {
            return String.format("%.2f", value);
        }
    }
}