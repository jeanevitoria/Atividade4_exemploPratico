package views;

import models.Bike;
import models.Reservation;

public interface RentalView {
    void exibirBike(Bike bike);
    void exibirDetalhesReserva(Reservation reserva);
    void exibirMensagem(String mensagem);
}
