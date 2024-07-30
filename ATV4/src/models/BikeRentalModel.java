package models;

import java.util.List;

public class BikeRentalModel {
    private List<Customer> clientes;
    private List<Bike> bikes;
    private List<Reservation> reservas;

    public BikeRentalModel(List<Customer> clientes, List<Bike> bikes, List<Reservation> reservas) {
        this.clientes = clientes;
        this.bikes = bikes;
        this.reservas = reservas;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public Bike buscarBike() {
        for (Bike bike : bikes) {
            if (bike.isDisponivel()) {
                return bike;
            }
        }
        return null;
    }

    public Bike buscarBikePorNome(String nome) {
        for (Bike bike : bikes) {
            if (bike.getModelo().equalsIgnoreCase(nome)) {
                return bike;
            }
        }
        return null;
    }

    public boolean reservarBike(Bike bike, Reservation reserva) {
        if (bike.isDisponivel()) {
            bike.setDisponivel(false);
            reservas.add(reserva);
            return true;
        }
        return false;
    }
}
