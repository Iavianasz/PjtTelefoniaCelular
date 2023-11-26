package br.com.PjtTelefoniaCelular.telephony;

import br.com.PjtTelefoniaCelular.postpaid.Postpaid;
import br.com.PjtTelefoniaCelular.prepaid.Prepaid;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class Telephony {
    private Prepaid[] prePaids;
    private int numPrePaids;
    private Postpaid[] postPaids;
    private int numPostPaids;


    public Telephony() {
        prePaids = new Prepaid[10];
        numPrePaids = 0;
        postPaids = new Postpaid[10];
        numPostPaids = 0;
    }


    public void registerSubscriber() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o tipo de assinante (1 - Pré-pago, 2 - Pós-pago): ");
        int typeSubscriber = scanner.nextInt();

        scanner.nextLine(); // Limpar o buffer do teclado

        System.out.println("Informe o nome do assinante: ");
        String name = scanner.nextLine();

        System.out.println("Informe o CPF do assinante: ");
        long cpf = scanner.nextLong();

        System.out.println("Informe o número do telefone do assinante: ");
        int celNumber = scanner.nextInt();

        if (typeSubscriber == 1) {
            registerPrePaid(name, cpf, celNumber);
        } else if (typeSubscriber == 2) {
            registerPostPaid(name, cpf, celNumber);
        } else {
            System.out.println("Opção inválida.");
        }
        scanner.close();
    }


    public void addPrePaid(Prepaid prepaid) {
        if (numPrePaids < prePaids.length) {
            prePaids[numPrePaids++] = prepaid;
        } else {
            System.out.println("Limite de assinantes pré-pagos atingido.");
        }
    }

    public void addPostPaid(Postpaid postpaid) {
        if (numPostPaids < postPaids.length) {
            postPaids[numPostPaids++] = postpaid;
        } else {
            System.out.println("Limite de assinantes pós-pagos atingido.");
        }
    }

    // Método privado para cadastrar assinante pré-pago
    private void registerPrePaid(String name, long cpf, int celNumber) {
        // Criar e adicionar o assinante pré-pago ao vetor
        Prepaid prePaid = new Prepaid(cpf, name, celNumber);
        addPrePaid(prePaid);
        System.out.println("Assinante pré-pago cadastrado com sucesso.");
    }

    // Método privado para cadastrar assinante pós-pago
    private void registerPostPaid(String name, long cpf, int celNumber) {


        // Criar e adicionar o assinante pós-pago ao vetor
        Postpaid postPaid = new Postpaid(cpf, name, celNumber);
        addPostPaid(postPaid);
        System.out.println("Assinante pós-pago cadastrado com sucesso.");
    }


    public void listSubscribers() {
        System.out.println("Assinantes Pré-Pagos:");
        for (int i = 0; i < numPrePaids; i++) {
            System.out.println("CPF: " + prePaids[i].getCpf() +
                    ", Nome: " + prePaids[i].getName() +
                    ", Número: " + prePaids[i].getCelNumber());
        }

        System.out.println("\nAssinantes Pós-Pagos:");
        for (int i = 0; i < numPostPaids; i++) {
            System.out.println("CPF: " + postPaids[i].getCpf() +
                    ", Nome: " + postPaids[i].getName() +
                    ", Número: " + postPaids[i].getCelNumber());
        }
    }

    public Prepaid locatePrePaid(long cpf) {
        for (int i = 0; i < numPrePaids; i++) {
            if (prePaids[i].getCpf() == cpf) {
                return prePaids[i];
            }
        }
        return null; // Retorna null se não encontrar o assinante pré-pago com o CPF fornecido
    }

    public Postpaid locatePosPaid(long cpf) {
        for (int i = 0; i < numPostPaids; i++) {
            if (postPaids[i].getCpf() == cpf) {
                return postPaids[i];
            }
        }
        return null; // Retorna null se não encontrar o assinante pós-pago com o CPF fornecido
    }

    public void imprimirFaturas() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o mês desejado (1 a 12): ");
        int month = scanner.nextInt();

        System.out.println("Faturas do mês " + month + " para assinantes Pré-Pagos:");

        for (int i = 0; i < numPrePaids; i++) {
            System.out.println("Dados do assinante:");
            System.out.println("CPF: " + prePaids[i].getCpf());
            System.out.println("Nome: " + prePaids[i].getName());
            System.out.println("Número: " + prePaids[i].getCelNumber());

            prePaids[i].printInvoice(month);

            System.out.println(); // Adiciona uma linha em branco entre as faturas
        }

        System.out.println("Faturas do mês " + month + " para assinantes Pós-Pagos:");

        for (int i = 0; i < numPostPaids; i++) {
            System.out.println("Dados do assinante:");
            System.out.println("CPF: " + postPaids[i].getCpf());
            System.out.println("Nome: " + postPaids[i].getName());
            System.out.println("Número: " + postPaids[i].getCelNumber());

            postPaids[i].printInvoice(month);

            System.out.println(); // Adiciona uma linha em branco entre as faturas
        }

        float valueTotalInvoice = calculateTotalValueInvoice();
        System.out.println("Valor total da fatura para todos os assinantes pós-pagos: R$ " + valueTotalInvoice);
        scanner.close();
    }

    private float calculateTotalValueInvoice() {
        float total = 0;
        for (int i = 0; i < numPostPaids; i++) {
            total += postPaids[i].calculateTotalValueInvoice();
        }
        return total;
    }

    public void doRecharge() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o CPF do assinante pré-pago para recarga: ");
        long cpf = scanner.nextLong();

        // Localiza o assinante pré-pago
        Prepaid prePaidSubscriber = locatePrePaid(cpf);

        if (prePaidSubscriber != null) {
            // Assinante pré-pago encontrado
            System.out.println("Informe o valor da recarga: ");
            float valueRecharge = scanner.nextFloat();

            // Cria a data da recarga (supondo que existe a classe Recarga)
            GregorianCalendar dateRecharge = new GregorianCalendar();

            // Registra a recarga no assinante pré-pago
            prePaidSubscriber.recharge(dateRecharge, valueRecharge);

            System.out.println("Recarga realizada com sucesso!");
        } else {
            System.out.println("Assinante não encontrado. Verifique o CPF.");
        }
        scanner.close();
    }

    public void makeCall() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar o tipo do assinante e CPF
        System.out.println("Digite o tipo do assinante (PrePago ou PosPago): ");
        String typeSubscriber = scanner.next();
        System.out.println("Digite o CPF do assinante: ");
        long cpf = scanner.nextLong();

        int duration;
        String data;

        // Localizar o assinante
        if (typeSubscriber.equalsIgnoreCase("PrePago")) {
            Prepaid subscriberPrepaid = locatePrePaid(cpf);
            if (subscriberPrepaid != null) {
                // Assinante PrePago localizado, solicitar a duração e a data da chamada
                System.out.println("Digite a duração da chamada em minutos: ");
                duration = scanner.nextInt();
                System.out.println("Digite a data da chamada (formato dd/MM/yyyy): ");
                data = scanner.next();

                // Registrar a chamada para o assinante PrePago
                subscriberPrepaid.makeCall(parseData(data), duration);
            } else {
                System.out.println("Assinante PrePago não encontrado.");
            }
        } else if (typeSubscriber.equalsIgnoreCase("PosPago")) {
            Postpaid subscriberPostpaid = locatePosPaid(cpf);
            if (subscriberPostpaid != null) {
                // Assinante PosPago localizado, solicitar a duração e a data da chamada
                System.out.println("Digite a duração da chamada em minutos: ");
                duration = scanner.nextInt();
                System.out.println("Digite a data da chamada (formato dd/MM/yyyy): ");
                data = scanner.next();

                // Registrar a chamada para o assinante PosPago
                subscriberPostpaid.makeCall(parseData(data), duration);
            } else {
                System.out.println("Assinante PosPago não encontrado.");
            }
        } else {
            System.out.println("Tipo de assinante inválido.");
        }

        scanner.close();
    }

    // Método para converter a string de data para um objeto GregorianCalendar (você precisa implementar este método)
    private GregorianCalendar parseData(String data) {
        return null;
    }


    public static void main(String[] args) {
        
        Telephony telephony = new Telephony(); // Substitua os argumentos pelos valores desejados

        telephony.registerSubscriber();

    }
//teste1

}













