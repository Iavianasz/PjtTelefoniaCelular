package br.com.PjtTelefoniaCelular.telephony;

import br.com.PjtTelefoniaCelular.postpaid.Postpaid;
import br.com.PjtTelefoniaCelular.prepaid.Prepaid;

import java.util.GregorianCalendar;
import java.util.Scanner;

// Definição da classe
public class Telephony {
    // Arrays para armazenar assinantes pré-pagos e pós-pagos
    private Prepaid[] prePaids;

    private int numPrePaids;
    private Postpaid[] postPaids;
    private int numPostPaids;
    Scanner entrada = new Scanner(System.in);

    // Construtor da classe
    public Telephony() {
        prePaids = new Prepaid[10];
        numPrePaids = 0;
        postPaids = new Postpaid[10];
        numPostPaids = 0;
    }

    // Método para registrar um assinante
    public void registerSubscriber() {
        String name;
        long cpf;
        int option, celNumber;

        do {
            System.out.println("\nAssinaturas: ");
            System.out.println("1- Pré-pago \n2- Pós-pago");
            System.out.print("Opção desejada: ");
            option = entrada.nextInt();
        } while (option != 1 && option != 2);
        // Verifica se há espaço para cada tipo de assinante e realiza o cadastro
        if (option == 1 && numPrePaids >= prePaids.length) {
            System.out.println("Não foi possivel realizar o cadastro de assinantes pré pagos!");
        } else if (option == 2 && numPostPaids >= postPaids.length) {
            System.out.println("Não foi possivel realizar o cadastro de assinantes pós pagos!");
        } else {
            System.out.print("\nNome: ");
            name = entrada.next();
            System.out.print("CPF: ");
            cpf = entrada.nextLong();
            System.out.print("Número de telefone: ");
            celNumber = entrada.nextInt();
            // Cadastro de assinantes pré-pagos e pós-pagos
            if (option == 1) {
                prePaids[numPrePaids] = new Prepaid(cpf, name, celNumber);

                this.numPrePaids++;

                System.out.println("Cadastro concluído!\n");
            } else {
                float subscription;
                System.out.print("Valor da assinatura: ");
                subscription = entrada.nextFloat();


                postPaids[numPostPaids] = new Postpaid(cpf, name, celNumber, subscription);


                this.numPostPaids++;

                System.out.println("Cadastro concluído!.\n");
            }
        }
    }

    // Métodos auxiliares para adicionar assinantes pré-pagos e pós-pagos aos arrays
    public void addPrePaid(Prepaid prepaid) {
        if (numPrePaids < prePaids.length) {
            prePaids[numPrePaids++] = prepaid;
        } else {
            System.out.println("Tamanho de assinantes pré-pagos atingido.");
        }
    }

    public void addPostPaid(Postpaid postpaid) {
        if (numPostPaids < postPaids.length) {
            postPaids[numPostPaids++] = postpaid;
        } else {
            System.out.println("Tamanho de assinantes pós-pagos atingido.");
        }
    }


    private void registerPrePaid(String name, long cpf, int celNumber) {
        // Criar e adicionar o assinante pré-pago ao vetor
        Prepaid prePaid = new Prepaid(cpf, name, celNumber);
        addPrePaid(prePaid);
        System.out.println("Assinante pré-pago cadastrado com sucesso.");
    }


    private void registerPostPaid(String name, long cpf, int celNumber) {



        Postpaid postPaid = new Postpaid(cpf, name, celNumber, celNumber);
        addPostPaid(postPaid);
        System.out.println("Assinante pós-pago cadastrado com sucesso.");
    }

    // Método para listar assinantes pré-pagos e pós-pagos
    public void listSubscribers() {
        if (this.numPrePaids > 0) {
            System.out.println("Assinantes pré-pago: ");


            for (int i = 0; i < this.numPrePaids; i++) {

                System.out.println((i + 1) + " - " + this.prePaids[i].toString() + ";\n");
            }
        } else {
            System.out.println("Não possui assinantes pré-pago cadastrados.\n");
        }


        if (this.numPostPaids > 0) {
            System.out.println("\nAssinantes pos-pago: ");


            for (int i = 0; i < this.numPostPaids; i++) {

                System.out.println((i + 1) + " - " + this.postPaids[i].toString() + ";\n");
            }
        } else {
            System.out.println("Não possui assinantes pós-pago cadastrados.\n");
        }

    }
    // Métodos para localizar assinantes pré-pagos e pós-pagos pelo CPF
    public Prepaid locatePrePaid(long cpf) {
        for (int i = 0; i < numPrePaids; i++) {
            if (prePaids[i].getCpf() == cpf) {
                return prePaids[i];
            }
        }
        return null;
    }

    public Postpaid locatePosPaid(long cpf) {
        for (int i = 0; i < numPostPaids; i++) {
            if (postPaids[i].getCpf() == cpf) {
                return postPaids[i];
            }
        }
        return null;
    }




    // Método para realizar recarga em assinante pré-pago
    public void doRecharge() {

        GregorianCalendar date = new GregorianCalendar();
        float value;
        long cpf;


        System.out.println("\nCPF: ");
        cpf = entrada.nextLong();


        if (this.locatePrePaid(cpf) != null) {
            System.out.println("Digite a data da recarga");
            date = returnDate();
            do {
                System.out.println("\nDigite o valor da recarga: ");
                value = entrada.nextFloat();
            } while (value<= 0);


            Prepaid located = this.locatePrePaid(cpf);
            located.recharge(date, value);
        } else {
            System.out.println("Assinante pré-pago'" + cpf + "' não encontrado!");
        }
    }

    // Método para obter a data
    public GregorianCalendar returnDate() {
        int date, month, year;
        boolean validity;

        do {


            do {
                System.out.print("Dia: ");
                date = entrada.nextInt();
            } while (date < 1 || date > 31);

            // método de exibir e solicitar mês
            month = showMonth();


            if (((month == 4 || month == 6 || month == 9 || month == 11) && date > 30) || (month == 2 && date > 28)) {
                validity = false;
                System.out.println("Data " + date + "/" + month + " inválida!");
                System.out.println("Insira novamente a data.\n");
            } else {
                validity = true;
            }
        } while (validity == false);


        do {
            System.out.print("Ano: ");
            year = entrada.nextInt();
        } while (year < 1877 || String.valueOf(year).length() != 4); // 1877 = ano que telefonia chegou ao br

        // Criação de um objeto com a data obtida
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, date);
        return calendar;
    }
    // Método para exibir e obter o mês
    public int showMonth() {
        System.out.println("1 - Janeiro");
        System.out.println("2 - Fevereiro");
        System.out.println("3 - Março");
        System.out.println("4 - Abril");
        System.out.println("5 - Maio");
        System.out.println("6 - Junho");
        System.out.println("7 - Julho");
        System.out.println("8 - Agosto");
        System.out.println("9 - Setembro");
        System.out.println("10 - Outubro");
        System.out.println("11 - Novembro");
        System.out.println("12 - Dezembro\n");

        int answer;

        do {
            System.out.print("Mês: ");
            answer = entrada.nextInt();
        } while (answer < 1 || answer > 12); // ...enquanto a resposta nao for valida

        return answer;
    }


    // Método para realizar chamada
    public void makeCall() {

        int option, duration;
        long cpf;

        System.out.println("\nAssinaturas");
        System.out.println("1- Pré-pago \n2- Pós-pago");


        do {
            System.out.print("Digite a opção: ");
            option = entrada.nextInt();
        } while (option != 1 && option != 2); // repete até que o usuário insira um valor válido

        System.out.println("\nCPF: ");
        cpf = entrada.nextLong();

        // Realiza chamada conforme o tipo de assinatura
        if (option == 1 && this.locatePrePaid(cpf) != null) {

            System.out.println("Digite a duração da chamada: ");
            duration = entrada.nextInt();
            System.out.println("Digite a data da chamada");
            GregorianCalendar dateFuncao =  returnDate();


            Prepaid located = this.locatePrePaid(cpf);
            located.makeCall(dateFuncao, duration);

        } else if (option == 2 && this.locatePosPaid(cpf) != null) {

            System.out.println("Digite a duração da chamada: ");
            duration = entrada.nextInt();
            System.out.println("Digite a data da chamada");
            GregorianCalendar dateFuncao = returnDate();


            Postpaid located = this.locatePosPaid(cpf);
            located.makeCall(dateFuncao, duration);

        } else {
            System.out.println("Assinante '" + cpf + "' não encontrado!\n");
        }

    }

    // Método para imprimir faturas
    private void printInvoice() {
        int month = showMonth();

        System.out.println("\nFaturas");
        // pre pagos
        System.out.println("\nPre Pago: ");

        if (this.numPrePaids > 0) {

            for (int i = 0; i < this.numPrePaids; i++) {

                this.prePaids[i].printInvoice(month);
            }
        } else {
            System.out.println("Não há assinantes pré-pago cadastros.\n");
        }


        System.out.println("Pos Pago: ");

        if (numPostPaids > 0) {

            for (int i = 0; i < numPostPaids; i++) {

                this.postPaids[i].printInvoice(month);
            }
        } else {
            System.out.println("Não há assinantes pós-pago cadastros.");
        }
    }



    // Método principal para executar o programa
    public static void main(String[] args) {


        Telephony tel = new Telephony();

        try (Scanner entrada = new Scanner(System.in)) {
            int op;


            do {
                System.out.println("1- Cadastrar assinante");
                System.out.println("2- Listar assinantes");
                System.out.println("3- Fazer chamada");
                System.out.println("4- Fazer recarga");
                System.out.println("5- Imprimir faturas");
                System.out.println("6- Fechar o programa \n");

                System.out.print("Opção: ");
                op = entrada.nextInt();


                switch (op) {
                    case 1:
                        tel.registerSubscriber();
                        break;
                    case 2:
                        tel.listSubscribers();
                        break;
                    case 3:
                        tel.makeCall();
                        break;
                    case 4:
                        tel.doRecharge();
                        break;
                    case 5:
                        tel.printInvoice();
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Opção inválida!\n");
                        break;
                }

            } while (op != 6);
        }
    }
    }

















