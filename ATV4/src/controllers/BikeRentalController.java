package controllers;

import models.Bike;
import models.BikeRentalModel;
import models.Reservation;
import views.RentalView;

import java.time.LocalDate;
import java.util.List;

public class BikeRentalController implements RentalView {
    private BikeRentalModel modelo;

    public BikeRentalController(BikeRentalModel modelo) {
        this.modelo = modelo;
    }

    @Override
    public void exibirBike(Bike bike) {
        System.out.print("-------------------------------------\n");
        System.out.println("Detalhes da Bicicleta:");
        System.out.print("-------------------------------------\n");
        System.out.println("Marca: " + bike.getMarca());
        System.out.println("Modelo: " + bike.getModelo());
        System.out.println("Ano: " + bike.getAno());
        System.out.println("Taxa Diária: R$ " + bike.getTaxaDiaria());
        System.out.println("Disponível: " + (bike.isDisponivel() ? "Sim" : "Não"));
    }

    @Override
    public void exibirDetalhesReserva(Reservation reserva) {
        System.out.print("-------------------------------------\n");
        System.out.println("Detalhes da Reserva:");
        System.out.print("-------------------------------------\n");
        System.out.println("Data de Início: " + reserva.getDataInicio());
        System.out.println("Data de Fim: " + reserva.getDataFim());
        System.out.println("Custo Total: R$ " + reserva.calcularCustoTotal(modelo.getBikes().get(0)));
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void buscarBike() {
        Bike bike = modelo.buscarBike();
        if (bike != null) {
            exibirBike(bike);
        } else {
            exibirMensagem("Nenhuma bicicleta disponível.");
        }
    }

    public void listarTodasBikes() {
        List<Bike> bikes = modelo.getBikes();
        if (!bikes.isEmpty()) {
            for (Bike bike : bikes) {
                exibirBike(bike);
                System.out.println();
            }
        } else {
            exibirMensagem("Nenhuma bicicleta cadastrada.");
        }
    }

    public void buscarBikePorNome(String nome) {
        Bike bike = modelo.buscarBikePorNome(nome);
        if (bike != null) {
            exibirBike(bike);
        } else {
            exibirMensagem("Nenhuma bicicleta encontrada com o nome: " + nome);
        }
    }

    public boolean reservarBike(Bike bike, Reservation reserva) {
        boolean sucesso = modelo.reservarBike(bike, reserva);
        if (sucesso) {
            exibirDetalhesReserva(reserva);
            return true;
        } else {
            exibirMensagem("Bicicleta não está disponível.");
            return false;
        }
    }
    public boolean reservarBike(Bike bike, LocalDate dataInicio, LocalDate dataFim) {
        // Verificar se a data de início é válida
        LocalDate hoje = LocalDate.now();
        if (dataInicio.isBefore(hoje)) {
            exibirMensagem("A data de início não pode ser no passado.");
            return false;
        }

        // Verificar se a data de fim é válida
        if (dataFim.isBefore(dataInicio)) {
            exibirMensagem("A data de fim não pode ser anterior à data de início.");
            return false;
        }

        Reservation reserva = new Reservation(dataInicio, dataFim);
        boolean sucesso = modelo.reservarBike(bike, reserva);
        if (sucesso) {
            exibirDetalhesReserva(reserva);
            return true;
        } else {
            exibirMensagem("Bicicleta não está disponível.");
            return false;
        }
    }
}
