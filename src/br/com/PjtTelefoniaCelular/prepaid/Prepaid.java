package br.com.PjtTelefoniaCelular.prepaid;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import br.com.PjtTelefoniaCelular.call.Call;
import br.com.PjtTelefoniaCelular.subscribers.Subscribers;
import br.com.PjtTelefoniaCelular.recharge.Recharge;

public class Prepaid extends Subscribers {
    private Call[] calls;
    private Recharge[] recharges;
    private int numRecharges;
    private float credit;
    public int totalCalls;

    // Construtor
    public Prepaid(long cpf, String name, int celNumber) {
        super(cpf, name, celNumber);
        this.recharges = new Recharge[2];
        this.calls = new Call[10];
        this.numRecharges = 0;

    }

    // Método para registrar uma chamada
    public float makeCall(GregorianCalendar date, int duration) {
        float costCall = 1.45f * duration;

        if (this.numOfCalls >= this.calls.length){
            System.out.println("Não existem espaços para novas chamadas.");
            return 0f;
        }

        if (costCall > this.credit) {
            System.out.println("Saldo insuficiente para efetuar a chamada.");
            System.out.println("Recarregue seu pré-pago.");
            return 0f;
        }

        for (int i = 0; i <= this.numOfCalls; i++){
            if (this.calls[i] == null){
                Call call = new Call(date, duration);
                this.calls[i] = call;
                this.credit -= costCall;
                System.out.println("Chamada realizada com sucesso");
            }
        }
        this.numOfCalls++;
        return 1f;
    }

    // Método para registrar uma recarga
    public void recharge(GregorianCalendar date, float value) {
        if (numRecharges >= this.recharges.length) {
            System.out.println("Não há espaço suficiente para novas recargas.");
        }
        else {

            for (int i = 0; i <= numRecharges; i++){
                if (recharges[i] == null){
                    Recharge recharge = new Recharge(date, value);
                    this.recharges[i] = recharge;
                    this.credit += value;
                    System.out.println("Recarga realizada com sucesso");
                }

            }

            this.numRecharges++;
        }

    }

    // Método para imprimir a fatura
    public void printInvoice(int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        float rechargesTotalValue = 0;
        float callsTotalValue = 0;

        System.out.println("Informações do Assinante: " + this.toString());
        if(this.numOfCalls <= 0){
            System.out.println("Não houveram chamadas");
        } else {
            System.out.println("Dados da chamada:");
            for(int j = 0; j <= this.numOfCalls; j++){
                if (this.calls[j] != null && this.calls[j].getDate().get(GregorianCalendar.MONTH) == (month - 1)) {
                    System.out.println("Data: " + sdf.format(this.calls[j].getDate().getTime()));
                    System.out.println("Duração: " + this.calls[j].getDuration());
                    System.out.println("Custo: " + this.calls[j].getDuration() * 1.45);
                    callsTotalValue+=this.calls[j].getDuration()  * 1.45f;
                }
            }
            System.out.println("Valor total das chamadas no mês de " + getMonth(month) + ": R$" + callsTotalValue);
        }

        if(this.numRecharges <= 0){
            System.out.println("Não houveram recargas");
        } else {
            System.out.println("\nInformações da Recarga");
            for(int k = 0; k <= this.numRecharges; k++){
                if (this.recharges[k] != null && this.recharges[k].getDate().get(GregorianCalendar.MONTH) == (month - 1)) {
                    System.out.println("\nData da recarga: " + sdf.format(this.recharges[k].getDate().getTime()));
                    System.out.println("Valor da recarga: " + this.recharges[k].getValue());
                    rechargesTotalValue+=this.recharges[k].getValue();
                }
            }
            System.out.println("Valor total de recargas no mês de " + getMonth(month) + ": R$" + rechargesTotalValue);
        }

    }




    public String getMonth(int option){
        switch(option) {
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
            default: return "Inválido";
        }
    }



}