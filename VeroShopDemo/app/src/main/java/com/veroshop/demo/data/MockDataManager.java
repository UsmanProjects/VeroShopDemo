package com.veroshop.demo.data;

import com.veroshop.demo.models.CartItem;
import com.veroshop.demo.models.Category;
import com.veroshop.demo.models.Order;
import com.veroshop.demo.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockDataManager {

    private static MockDataManager instance;
    private List<Product> allProducts;
    private List<CartItem> cartItems;
    private List<Order> orders;
    private List<Category> categories;

    private MockDataManager() {
        initProducts();
        initCategories();
        initOrders();
        cartItems = new ArrayList<>();
    }

    public static synchronized MockDataManager getInstance() {
        if (instance == null) {
            instance = new MockDataManager();
        }
        return instance;
    }

    private void initProducts() {
        allProducts = new ArrayList<>();
        // Pizza
        allProducts.add(new Product(1, "Margherita Pizza", "Classic tomato sauce with mozzarella and fresh basil. A timeless Italian favorite made with the finest ingredients.", 12.99, 4.8f, 245, "Pizza", "🍕"));
        allProducts.add(new Product(2, "Pepperoni Feast", "Loaded with premium pepperoni slices on a rich tomato base with stretchy mozzarella cheese.", 14.99, 4.7f, 189, "Pizza", "🍕"));
        allProducts.add(new Product(3, "BBQ Chicken Pizza", "Grilled chicken, BBQ sauce, red onions, and cilantro on a crispy crust.", 15.99, 4.6f, 156, "Pizza", "🍕"));
        allProducts.add(new Product(4, "Veggie Supreme", "A rainbow of fresh vegetables including bell peppers, mushrooms, olives, and onions.", 13.49, 4.5f, 132, "Pizza", "🍕"));
        // Burger
        allProducts.add(new Product(5, "Classic Smash Burger", "Double smashed patties with American cheese, pickles, onions, and special sauce.", 9.99, 4.9f, 312, "Burger", "🍔"));
        allProducts.add(new Product(6, "Spicy Jalapeño Burger", "Crispy fried chicken with fresh jalapeños, pepper jack cheese, and chipotle mayo.", 10.99, 4.7f, 201, "Burger", "🍔"));
        allProducts.add(new Product(7, "Mushroom Swiss Burger", "Juicy beef patty topped with sautéed mushrooms and melted Swiss cheese.", 11.49, 4.6f, 167, "Burger", "🍔"));
        allProducts.add(new Product(8, "Veggie Burger", "Black bean and quinoa patty with avocado, lettuce, tomato, and herb aioli.", 9.49, 4.4f, 98, "Burger", "🍔"));
        // Drinks
        allProducts.add(new Product(9, "Fresh Orange Juice", "Cold-pressed oranges squeezed fresh to order. No added sugar or preservatives.", 4.99, 4.8f, 178, "Drinks", "🍊"));
        allProducts.add(new Product(10, "Mango Smoothie", "Creamy blend of fresh mango, banana, and yogurt topped with chia seeds.", 5.99, 4.9f, 223, "Drinks", "🥭"));
        allProducts.add(new Product(11, "Iced Matcha Latte", "Premium ceremonial grade matcha whisked with oat milk over ice.", 5.49, 4.6f, 145, "Drinks", "🍵"));
        allProducts.add(new Product(12, "Classic Cola", "The original refreshing cola served ice cold.", 2.99, 4.3f, 89, "Drinks", "🥤"));
        // Dessert
        allProducts.add(new Product(13, "Chocolate Lava Cake", "Warm chocolate cake with a gooey molten center, served with vanilla ice cream.", 7.99, 4.9f, 289, "Dessert", "🍰"));
        allProducts.add(new Product(14, "Strawberry Cheesecake", "Creamy New York-style cheesecake with fresh strawberry compote.", 6.99, 4.8f, 234, "Dessert", "🍓"));
        allProducts.add(new Product(15, "Tiramisu", "Classic Italian dessert with mascarpone, espresso-soaked ladyfingers, and cocoa.", 6.49, 4.7f, 198, "Dessert", "☕"));
        allProducts.add(new Product(16, "Mango Sorbet", "Light and refreshing dairy-free mango sorbet with a hint of lime.", 4.49, 4.5f, 112, "Dessert", "🍧"));
    }

    private void initCategories() {
        categories = new ArrayList<>();
        categories.add(new Category("Pizza", "🍕", 4));
        categories.add(new Category("Burger", "🍔", 4));
        categories.add(new Category("Drinks", "🥤", 4));
        categories.add(new Category("Dessert", "🍰", 4));
    }

    private void initOrders() {
        orders = new ArrayList<>();
        List<CartItem> order1Items = new ArrayList<>();
        order1Items.add(new CartItem(allProducts.get(0), 2));
        order1Items.add(new CartItem(allProducts.get(8), 1));
        orders.add(new Order("#ORD-1001", "Jul 10, 2026", order1Items, 30.97, "Delivered", "Cash"));

        List<CartItem> order2Items = new ArrayList<>();
        order2Items.add(new CartItem(allProducts.get(4), 1));
        order2Items.add(new CartItem(allProducts.get(12), 1));
        orders.add(new Order("#ORD-1002", "Jul 15, 2026", order2Items, 17.98, "Processing", "Credit Card"));

        List<CartItem> order3Items = new ArrayList<>();
        order3Items.add(new CartItem(allProducts.get(9), 2));
        order3Items.add(new CartItem(allProducts.get(13), 1));
        orders.add(new Order("#ORD-1003", "Jul 16, 2026", order3Items, 18.97, "Shipped", "Wallet"));
    }

    // Products
    public List<Product> getAllProducts() { return allProducts; }

    public List<Product> getProductsByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> searchProducts(String query) {
        List<Product> result = new ArrayList<>();
        String lower = query.toLowerCase();
        for (Product p : allProducts) {
            if (p.getName().toLowerCase().contains(lower) ||
                    p.getCategory().toLowerCase().contains(lower)) {
                result.add(p);
            }
        }
        return result;
    }

    public Product getProductById(int id) {
        for (Product p : allProducts) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    // Categories
    public List<Category> getCategories() { return categories; }

    // Cart
    public List<CartItem> getCartItems() { return cartItems; }

    public void addToCart(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
    }

    public void removeFromCart(int productId) {
        cartItems.removeIf(item -> item.getProduct().getId() == productId);
    }

    public void updateCartItemQuantity(int productId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == productId) {
                if (quantity <= 0) {
                    removeFromCart(productId);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    public double getCartTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getSubtotal();
        }
        return total;
    }

    public int getCartItemCount() {
        int count = 0;
        for (CartItem item : cartItems) {
            count += item.getQuantity();
        }
        return count;
    }

    public void clearCart() { cartItems.clear(); }

    // Orders
    public List<Order> getOrders() { return orders; }

    public void placeOrder(List<CartItem> items, double total, String paymentMethod) {
        String orderId = "#ORD-" + (1004 + orders.size());
        String date = "Jul 17, 2026";
        List<CartItem> copy = new ArrayList<>(items);
        orders.add(0, new Order(orderId, date, copy, total, "Processing", paymentMethod));
    }

    public String getLastOrderId() {
        if (!orders.isEmpty()) return orders.get(0).getOrderId();
        return "#ORD-0000";
    }
}
