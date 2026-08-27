public class GroupA_BusinessSimulator {
    public static void main(String[] args) {
        System.out.println("==== KABS SUPERMARKET ====\n");
        // Display the supermarket heading.

        String[] items = {"Sugar", "Salt", "Porridge", "Bread"};

        double[] prices = {555.66, 6666.00, 44444.00, 444.00};

        int[] quantities = {4, 2, 2, 2};

        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i] + " UGX " + prices[i]);
            // Print the available products and their prices.
        }

        double total = 0;
        double[] afterDiscount = new double[4];
        String[] discountMsg = {"", "", "", ""};

        // Loop through each item and use the method to work out its discounted subtotal
        for (int i = 0; i < items.length; i++) {
            // Calculate each item's subtotal and apply eligible discount.
            afterDiscount[i] = calculateSubtotal(i, prices[i], quantities[i]);

            // Work out the discount message separately, just for display on the receipt.
            if (i == 0) { // Sugar
                if (quantities[i] >= 5) {
                    discountMsg[i] = "(5% discount applied)";
                } else {
                    discountMsg[i] = "(no discount — fewer than 5)";
                }

            } else if (i == 1) { // Salt
                // Salt has no discount message.

            } else if (i == 2) { // Porridge
                if (quantities[i] >= 3) {
                    discountMsg[i] = "(UGX 5000 discount applied)";
                } else {
                    discountMsg[i] = "(no discount — fewer than 3)";
                }

            } else if (i == 3) { // Bread
                if (quantities[i] >= 2) {
                    discountMsg[i] = "(10% discount applied)";
                }
            }

            total = total + afterDiscount[i];
        }

        // Method call: prints the itemised receipt
        printReceipt(items, quantities, afterDiscount, discountMsg, total);
    }

    // Method 1: works out ONE item's discounted subtotal.
    // "index" tells us which item it is (0=Sugar, 1=Salt, 2=Porridge, 3=Bread),
    // so we know which discount rule to apply.
    public static double calculateSubtotal(int index, double price, int quantity) {
        double sub = price * quantity;
        double finalPrice = sub;

        if (index == 0 && quantity >= 5) {           // Sugar
            finalPrice = sub - (sub * 0.05);
        } else if (index == 1) {                      // Salt
            // No discount, ever
        } else if (index == 2 && quantity >= 3) {    // Porridge
            finalPrice = sub - 5000;
        } else if (index == 3 && quantity >= 2) {    // Bread
            finalPrice = sub - (sub * 0.10);
        }

        return finalPrice;
    }

    // Method 2: prints the itemised receipt using the values calculated in main
    public static void printReceipt(String[] items, int[] quantities, double[] afterDiscount,
                                    String[] discountMsg, double total) {
        System.out.println(" \n==== RECEIPT====\n");

        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i] + " x " + quantities[i] + " = UGX "
                    + afterDiscount[i] + "     " + discountMsg[i]);
            // Print the final receipt and the total amount to pay.
        }

        System.out.println("\n------------------------------------------");
        System.out.println("TOTAL = UGX " + total);
        System.out.println("");
    }
}