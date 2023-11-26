package br.com.PjtTelefoniaCelular.postpaid;

import br.com.PjtTelefoniaCelular.call.Call;
import br.com.PjtTelefoniaCelular.subscribers.Subscribers;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

// Definição da classe Postpaid, que herda de Subscribers
public class Postpaid extends Subscribers {
    private float subscription;

    // Construtor da classe
    public Postpaid(long cpf, String name, int celNumber, float subscription) {
        // Chama o construtor da classe base (Subscribers) para inicializar as informações básicas do assinante
        super(cpf, name, celNumber);
        // Inicializa o atributo específico para assinaturas pós-pagas
        this.subscription = subscription;
    }

    // Getter para obter o valor da assinatura
    public float getSubscription() {
        return subscription;
    }

    // Setter para modificar o valor da assinatura
    public void setSubscription(float subscription) {
        this.subscription = subscription;
    }

    // Método para realizar uma chamada
    public void makeCall(GregorianCalendar date, int duration) {
        if (this.calls.length == this.numOfCalls) {
            System.out.println("Não é viável efetuar uma chamada.");
        } else {
            for (int i = 0; i <= numOfCalls; i++) {
                if (this.calls[i] == null) {
                    this.calls[i] = new Call(date, duration);
                }
            }
            this.numOfCalls++;
            System.out.println("A chamada foi concluída com êxito.");
        }
    }

    // Método para imprimir a fatura
    public void printInvoice(int month) {

        float total = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Informações do assinante: " + this.toString());
        System.out.println("Valor da assinatura: " + this.subscription);

        if (this.numOfCalls <= 0) {
            System.out.println("Não ocorreram chamadas.");
        } else {
            System.out.println("Dados da chamada:");
            for (int i = 0; i < this.numOfCalls; i++) {
                if (this.calls[i] != null && this.calls[i].getDate().get(GregorianCalendar.MONTH) == month - 1) {
                    System.out.println("Data da chamada: " + sdf.format(this.calls[i].getDate().getTime()));
                    System.out.println("Duração: " + this.calls[i].getDuration() + " minutos;");
                    System.out.println("Custo: R$" + this.calls[i].getDuration() * 1.45);
                    total += this.calls[i].getDuration() * 1.45;
                }
            }

            System.out.println("\nValor total das chamadas: " + total);

            // Adiciona o valor da assinatura ao total
            total += this.subscription;
            System.out.println("\nValor total da fatura no mês de " + getMonth(month) + ": R$" + total);
        }
    }

    // Método auxiliar para obter o nome do mês a partir do número do mês
    public String getMonth(int option) {
        switch (option) {
            case 1: return "Janeiro";
            case 2: return "Fevereiro";
            case 3: return "Março";
            case 4: return "Abril";
            case 5: return "Maio";
            case 6: return "Junho";
            case 7: return "Julho";
            case 8: return "Agosto";
            case 9: return "Setembro";
            case 10: return "Outubro";
            case 11: return "Novembro";
            case 12: return "Dezembro";
            default: return "Mês inválido";
        }
    }
}
