package models;

import java.time.LocalDate;

public class Reservation {
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Reservation(LocalDate dataInicio, LocalDate dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public double calcularCustoTotal(Bike bike) {
        long dias = java.time.Duration.between(dataInicio.atStartOfDay(), dataFim.atStartOfDay()).toDays();
        return dias * bike.getTaxaDiaria();
    }
}
