package br.com.PjtTelefoniaCelular.subscribers;
import br.com.PjtTelefoniaCelular.call.Call;

public class Subscribers {
   protected long cpf;
   protected String name;
   protected int celNumber;
   private int numOfCalls;

   private Call[] calls;

   public Subscribers(long cpf, String name, int celNumber, int numOfCalls) {
      this.cpf = cpf;
      this.name = name;
      this.celNumber = celNumber;
      this.calls = new Call[numOfCalls];
      this.numOfCalls = 0;
   }

   public Long getCpf() {

      return cpf;
   }

   public String getName(){
      return name;
   }

   public int getCelNumber(){
      return celNumber;
   }

   @Override
   public String toString() {
      return "CPF: " + cpf + "\nNome: " + name + "\nNúmero: " + celNumber + "\nNúmero de Chamadas: " + numOfCalls;
   }








}






