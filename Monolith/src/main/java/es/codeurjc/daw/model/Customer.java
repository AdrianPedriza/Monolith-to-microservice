package es.codeurjc.daw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double credit;

    public Customer(){}

    public Customer(String  name, double credit){
        this.name = name;
        this.credit = credit;
    }

    public Customer(double credit){
        this.credit = credit;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


    public void setCredit(double credit) {
        this.credit = credit;
    }


    public double getCredit() {
        return credit;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


}