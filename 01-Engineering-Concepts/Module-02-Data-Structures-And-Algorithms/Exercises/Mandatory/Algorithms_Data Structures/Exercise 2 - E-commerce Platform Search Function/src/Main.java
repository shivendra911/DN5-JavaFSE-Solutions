// E-commerce Platform Search Function
// Implementing Linear Search and Binary Search

public class Main {

    // Linear Search - goes through each product one by one
    // Time Complexity: O(n) - worst case checks all elements
    public static Product linearSearch(Product[] products, int targetId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productId == targetId) {
                return products[i];
            }
        }
        return null; // not found
    }

    // Binary Search - array must be sorted by productId
    // Time Complexity: O(log n) - much faster for large data
    public static Product binarySearch(Product[] products, int targetId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (products[mid].productId == targetId) {
                return products[mid];
            } else if (products[mid].productId < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // not found
    }

    public static void main(String[] args) {

        // sample products array (sorted by productId for binary search)
        Product[] products = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Phone", "Electronics"),
                new Product(103, "Shoes", "Fashion"),
                new Product(104, "Watch", "Accessories"),
                new Product(105, "Bag", "Fashion")
        };

        System.out.println("=== Linear Search ===");
        Product result1 = linearSearch(products, 103);
        if (result1 != null) {
            System.out.println("Found: " + result1);
        } else {
            System.out.println("Product not found");
        }

        System.out.println("\n=== Binary Search ===");
        Product result2 = binarySearch(products, 103);
        if (result2 != null) {
            System.out.println("Found: " + result2);
        } else {
            System.out.println("Product not found");
        }

        // searching for something that doesn't exist
        System.out.println("\n=== Searching for id 999 ===");
        Product result3 = linearSearch(products, 999);
        if (result3 == null) {
            System.out.println("Product with id 999 not found.");
        }

        System.out.println("\n=== Analysis ===");
        System.out.println("Linear Search: O(n) - checks each element");
        System.out.println("Binary Search: O(log n) - faster but needs sorted array");
        System.out.println("For large product catalogs, Binary Search is better.");
    }
}
