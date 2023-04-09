package com.example.cashcard;


import jakarta.persistence.*;

//import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.EntityGraph;

//@Entity
//public record CashCard(@Id Long id, Double amount) {
//    public CashCard() {
//        this(0L, 0.0);
//    }
//}

//@Entity
////@Table(name = "cash_card")
//public record CashCard(@Id Long id, Double amount) {
//}

@Entity
public class CashCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;

    private String owner;

    public CashCard() {

    }

    public CashCard(Long id, Double amount, String owner) {
        this.id = id;
        this.amount = amount;
        this.owner = owner;
    }

    public CashCard(Long id, Double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
