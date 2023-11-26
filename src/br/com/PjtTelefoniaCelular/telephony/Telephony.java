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


    public Telephony(int maxPrePaids, int maxPostPaids) {
        prePaids = new Prepaid[maxPrePaids];
        numPrePaids = 0;
        postPaids = new Postpaid[maxPostPaids];
        numPostPaids = 0;
    }


    public void cadastrarAssinante() {
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
        Postpaid postPaid = new Postpaid(cpf, name, celNumber, celNumber);
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

    public Postpaid localizarPosPago(long cpf) {
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
    }
//teste1

}













