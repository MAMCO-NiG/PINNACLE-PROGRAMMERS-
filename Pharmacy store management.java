import java.util.HashMap;
import java.util.Map;

// Interface for the FUGASHUA Pharmacy Store
interface PharmacyStore {
    void addItem(String itemName, double itemPrice, int quantity);
    void removeItem(String itemName);
    void updateItemQuantity(String itemName, int quantity);
    double calculateTotalCost();
    void displayItems();
}

// Class to represent the Item
class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "Item Name: " + name + "\t Price: N" + price + "\t Quantity: " + quantity;
    }
}

// Class to implement the PharmacyStore interface
class OnlinePharmacyStore implements PharmacyStore {
    private Map<String, Item> items = new HashMap<>();

    @Override
    public void addItem(String itemName, double itemPrice, int quantity) {
        if (items.containsKey(itemName)) {
            Item existingItem = items.get(itemName);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            Item newItem = new Item(itemName, itemPrice, quantity);
            items.put(itemName, newItem);
        }
    }
//Error Handling
    @Override
    public void removeItem(String itemName) {
        if (items.containsKey(itemName)) {
            items.remove(itemName);
        } else {
            System.out.println("Error: Item not found.");
        }
    }

    @Override
    public void updateItemQuantity(String itemName, int quantity) {
        if (items.containsKey(itemName)) {
            Item existingItem = items.get(itemName);
            existingItem.setQuantity(quantity);
        } else {
            System.out.println("Error: Item not found.");
        }
    }

    @Override
    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Item item : items.values()) {
            totalCost += item.getTotalCost();
        }
        return totalCost;
    }

    @Override
    public void displayItems() {
        System.out.println("Items in the FUGASHUA Pharmacy Store:");
        for (Item item : items.values()) {
            System.out.println(item);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create an instance of the OnlinePharmacyStore
        OnlinePharmacyStore pharmacyStore = new OnlinePharmacyStore();

        // Add items to the store
        pharmacyStore.addItem("Paracetamol", 10.99, 5);
        pharmacyStore.addItem("Tramadol", 5.99, 10);
        pharmacyStore.addItem("Vitamin - C", 7.49, 3);

        // Display the items in the store
        pharmacyStore.displayItems();

        // Update the quantity of an item
        pharmacyStore.updateItemQuantity("Tramadol", 15);

        // Display the updated items in the store
        pharmacyStore.displayItems();

        // Calculate the total cost of all items in the store
        double totalCost = pharmacyStore.calculateTotalCost();
        System.out.println("Total Cost: N" + totalCost);
    }
}