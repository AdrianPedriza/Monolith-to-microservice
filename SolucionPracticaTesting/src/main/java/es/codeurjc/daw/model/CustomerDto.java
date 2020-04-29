package es.codeurjc.daw.model;

public class CustomerDto {

    private Long id;

    private String name;
    private double credit;

    public CustomerDto(){}

    public CustomerDto(String  name, double credit){
        this.name = name;
        this.credit = credit;
    }

    public CustomerDto(double credit){
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