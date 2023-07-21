import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ex6_20220808003 {
    public static void main(String[] args) {
    }
}
abstract class Product implements Comparable<Product> {
    private String name;
    private double price;
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int compareTo(Product other){
        return Double.compare(this.price, other.price);
    }
    public String toString(){
        return getClass().getSimpleName() + "[name=" + name + ", price=" + price + "]";
    }
}

abstract class Book extends Product{
    private String author;
    private int pageCount;
    public Book(String name, double price, String author, int pageCount){
        super(name,price);
            this.author = author;
            this.pageCount = pageCount;
    }
    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }
}

class ReadingBook extends Book{
    private String genre;
    public ReadingBook(String name, double price, String author, int pageCount, String genre){
        super(name, price, author, pageCount);
        this.genre = genre;
    }
    public String getGenre() {
        return genre;
    }
}
class ColoringBook extends Book implements Colorable{
    private String color;

    public ColoringBook(String name, double price, String author, int pageCount) {
        super(name, price, author, pageCount);
        this.color = "none";
    }

    public String getColor() {
        return color;
    }

    public void paint(String color) {
        this.color = color;
    }
}

class ToyHorse extends Product implements Rideable {

    public ToyHorse(String name, double price) {
        super(name, price);
    }
    public void ride() {
        System.out.println("Vroom Vroom");
    }
}
class Bicycle extends Product implements Colorable, Rideable {
    private String color;
    public Bicycle(String name, double price) {
        super(name, price);
        this.color = "none";
    }
    public String getColor() {
        return color;
    }
    public void paint(String color) {
        this.color = color;
    }
    public void ride() {
        System.out.println("Vroom Vroom");
    }
}

class User {
    private String username;
    private String email;
    private PaymentMethod payment;
    private List<Product> cart;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.cart = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }

    public Product getProduct(int index) {
        if (index >= 0 && index < cart.size()) {
            return ((ArrayList<Product>) cart).get(index);
        }
        return null;
    }

    public void removeProduct(int index) {
        if (index >= 0 && index < cart.size()) {
            ((ArrayList<Product>) cart).remove(index);
        }
    }

    public void addProduct(Product product) {
        cart.add(product);
    }
    public void purchase() {
        double totalPrice = 0;
        for (Product product : cart) {
            totalPrice += product.getPrice();
        }
        if (payment != null && payment.pay(totalPrice)) {
            cart.clear();
            System.out.println("Purchase successful!");
        } else {
            System.out.println("Purchase failed.");
        }
    }
}

class CreditCard implements PaymentMethod {
    private long cardNumber;
    private String cardHolderName;
    private Date expirationDate;
    private int cvv;

    public CreditCard(long cardNumber, String cardHolderName, Date expirationDate, int cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public boolean pay(double amount) {
        return true;
    }
}

class PayPal implements PaymentMethod {
    private String username;
    private String password;

    public PayPal(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean pay(double amount) {
        return true;
    }
}

interface Colorable {
    void paint(String color);
}

interface Rideable {
    void ride();
}

interface PaymentMethod {
    boolean pay(double amount);
}