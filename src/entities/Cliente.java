package entities;

import java.util.Scanner;

import utils.TadFilaCircular;

public class Cliente{
	
	private int idade; // pega a idade de geral
	private int contador = 0; // contador para contar quando será atendida a fila pref ou a normal
	
	TadFilaCircular fila; // instanciei duas filas, a preferencial e a normal
	TadFilaCircular filaPref;
	
	
	public Cliente() { // usei fila circular pq achei melhor nesse caso
		fila = new TadFilaCircular(20);
		filaPref = new TadFilaCircular(10);
	}
	
	public boolean isDeficiente() { // aqui verifica se a pessoa é deficiente, caso seja > 60 não irá perguntar.
		System.out.println("O cliente é deficiente? (sim/nao)\n");
		String deficiente;
		Scanner scan = new Scanner(System.in);
		deficiente = scan.nextLine();
		deficiente.toLowerCase();
		if(deficiente.equals("sim")) {
			return true;
		}else if(deficiente.equals("nao")) {
			return false;
		}else {
			System.out.println("Você não informou corretamente, então não entrará na fila preferencial como pessoa com deficiencia. ");
			return false;
		}
	}
	
	public void entrarFila() { // metodo usado para inserir alguém na fila.
		System.out.print("Sua idade: ");
		Scanner scan = new Scanner(System.in);
		this.idade = scan.nextInt();
		if(this.idade >= 60) {
			filaPref.enqueue(Integer.toString(this.idade) + " - idoso");
		}else if(this.idade >= 60 && this.isDeficiente()) {
			filaPref.enqueue(Integer.toString(this.idade) + " - idoso *deficiente");
		}else if(this.isDeficiente()) {
			filaPref.enqueue(Integer.toString(this.idade) + " - *Deficiente");
		}else {
			fila.enqueue(Integer.toString(this.idade));
		}
	}
	
	public void atender() { // aqui o caixa atende alguém.
		if(fila.isEmpty()) {
			if(!filaPref.isEmpty()) {
				System.out.println("Cliente atendido: " + filaPref.dequeue());
			}
		}else {
			if(this.contador < 2 && !filaPref.isEmpty()) {
				System.out.println("Cliente atendido: " + filaPref.dequeue());
				this.contador++;
				if(filaPref.isEmpty()) {
					System.out.println("A fila preferencial está vazia. ");
					this.contador = 4;
				}
			}else {
				System.out.println("Cliente atendido: " + fila.dequeue());
				this.contador = 0;
			}
		}
	}
	
	public void menu() { // menuzinho top feito por mim sz /by/Alex
		int op = 0;
		
		do {
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println("1 - Colocar cliente na fila\t\t    |");
			System.out.println("2 - Atender um cliente \t\t\t    |");
			System.out.println("3 - Mostrar pessoas na fila \t\t    |");
			System.out.println("0 - Fechar o banco ( o programa kk )\t    |");
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			Scanner scan = new Scanner(System.in);
			op = scan.nextInt();
			switch(op) {
				case 1:
					this.entrarFila();
					if(!fila.isEmpty()) {
						fila.imprimeFila();
					}
					if(!filaPref.isEmpty()) {
						filaPref.imprimeFila();
					}
					break;
				case 2:
					this.atender();
					if(fila.isEmpty() && filaPref.isEmpty()) {
						System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
						System.out.println("Às filas estão vazias. ");
						System.out.println("=-=-=-=-=-=-=-=-=-=-=-=\n");
					}
					break;
				case 3:
					if(!fila.isEmpty()) {
						fila.imprimeFila();
					}else if(!filaPref.isEmpty()) {
						filaPref.imprimeFila();
					}else {
						System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
						System.out.println("Às filas estão vazias. ");
						System.out.println("=-=-=-=-=-=-=-=-=-=-=-=\n");
					}
					break;
				case 0:
					System.exit(0);
					break;
			}
		}while(op != 0);
	}
	
}
