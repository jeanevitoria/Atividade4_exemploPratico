package main;

import models.Bike;
import models.Customer;
import models.Reservation;
import models.BikeRentalModel;
import controllers.BikeRentalController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Bike> bikes = new ArrayList<>();
        bikes.add(new Bike("Trek", "Marlin 7", 2022, 15.00));
        bikes.add(new Bike("Specialized", "Rockhopper", 2021, 12.00));

        List<Customer> clientes = new ArrayList<>();
        clientes.add(new Customer("Alice", "alice@example.com", "123-456-7890"));
        clientes.add(new Customer("Bob", "bob@example.com", "098-765-4321"));

        List<Reservation> reservas = new ArrayList<>();
        BikeRentalModel modelo = new BikeRentalModel(clientes, bikes, reservas);

        BikeRentalController controlador = new BikeRentalController(modelo);

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.print("-------------------------------------\n");
            System.out.println("Sistema de Aluguel de Bicicletas");
            System.out.print("-------------------------------------\n");
            System.out.println("1. Buscar uma bicicleta");
            System.out.println("2. Reservar uma bicicleta");
            System.out.println("3. Sair");
            System.out.print("-------------------------------------\n");
            System.out.print("Escolha uma opção: \n");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcao) {
                case 1:
                    System.out.print("-------------------------------------\n");
                    System.out.println("Buscar por:");
                    System.out.print("-------------------------------------\n");
                    System.out.println("1. Listar todas as bicicletas");
                    System.out.println("2. Buscar por nome");
                    System.out.print("Escolha uma opção: ");
                    int subOpcao = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (subOpcao) {
                        case 1:
                            controlador.listarTodasBikes();
                            break;

                        case 2:
                            System.out.print("\nDigite o nome da bicicleta: ");
                            String nomeBike = scanner.nextLine();
                            controlador.buscarBikePorNome(nomeBike);
                            break;

                        default:
                            System.out.println("\nOpção inválida. Por favor, tente novamente.");
                            break;
                    }
                    break;

                case 2:
                    LocalDate dataInicio = null;
                    LocalDate dataFim = null;
                    boolean datasValidas = false;

                    while (!datasValidas) {
                        System.out.print("\nDigite a data de início (dd/MM/yyyy): ");
                        String dataInicioStr = scanner.nextLine();

                        System.out.print("Digite a data de fim (dd/MM/yyyy): ");
                        String dataFimStr = scanner.nextLine();

                        try {
                            dataInicio = LocalDate.parse(dataInicioStr, formatter);
                            dataFim = LocalDate.parse(dataFimStr, formatter);

                            // Tentar reservar a bicicleta com as datas fornecidas
                            Bike bikeParaReservar = modelo.buscarBike();
                            if (bikeParaReservar != null) {
                                datasValidas = controlador.reservarBike(bikeParaReservar, dataInicio, dataFim);
                            } else {
                                System.out.println("Nenhuma bicicleta disponível para reservar.");
                                datasValidas = true;
                            }
                        } catch (Exception e) {
                            System.out.println("Formato de data inválido. Por favor, use o formato dd/MM/yyyy.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }
}
