package entity;

import reflection.myannotation.Id;
import reflection.myannotation.Table;

@Table(name = "khachhang")
public class Customer {
    @Id(autoIncrement = true)
    private int id;
    private double balance;
    private String name;
    private String email;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Customer() {
    }

    public Customer(int id, double balance, String name, String email) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
