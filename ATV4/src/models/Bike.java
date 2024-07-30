package models;

public class Bike {
    private String marca;
    private String modelo;
    private int ano;
    private double taxaDiaria;
    private boolean disponivel;

    public Bike(String marca, String modelo, int ano, double taxaDiaria) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.taxaDiaria = taxaDiaria;
        this.disponivel = true;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public double getTaxaDiaria() {
        return taxaDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
