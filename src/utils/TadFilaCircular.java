package utils;

public class TadFilaCircular {
	private String[] dados;
	private int tamMax;
	
	private int posInicio;
	private int posFinal;
	private int qtdElementos;
	
	
	public TadFilaCircular(int tamMax) {
		dados = new String[tamMax];
		this.tamMax = tamMax;
		this.posInicio = 0;
		this.qtdElementos = 0;
		this.posFinal = 0;
	}
	
	public int getPosFinal() {
		return this.posFinal;
	}
	
	public String getPrimeiraPessoa() {
		return this.dados[this.posInicio];
	}
	
	public void destroy() {
		this.dados = null;
		System.gc();
	}
	
	public int length() {
		return this.qtdElementos;
	}
	
	public boolean isFull() {
		if(this.length() == this.tamMax) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isEmpty() {
		if(this.length() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void enqueue(String valor) {
		if(isFull()) {
			System.out.println("A fila tá cheia! ");
		}else {
			this.dados[this.posFinal] = valor;
			this.qtdElementos++;
			this.posFinal++;
			
			if(this.posFinal > tamMax -1) {
				this.posFinal = 0;
			}
		}
	}
	
	public void passarNaFrente(String valor) {
		if(isFull()) {
			System.out.println("A fila tá cheia! ");
		}else {
			for(int i = this.posFinal; i >= 0 ; i--) {
				this.dados[i + 1] = this.dados[i];
			}
			this.dados[this.posInicio] = valor;
			this.posFinal++;
			this.qtdElementos++;
			
			if(this.posFinal > this.tamMax -1) {
				this.posFinal = 0;
			}
		}
	}
	
	public String dequeue() {
		if(isEmpty()) {
			System.out.println("Fila vazia.");
			return null;
		}else {
			String elemento = this.dados[this.posInicio];
			this.qtdElementos--;
			this.posInicio++;
			
			if(this.posInicio > this.tamMax - 1) {
				this.posInicio = 0;
			}
			return elemento;
		}
	}
	
	
	public void imprimeFila() {
	  System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
	  if (isEmpty()) {
	    //System.out.println("fila vazia");
	  }
	  else{
	    for (int i = 0; i < length(); i++) {
	      if ((this.posInicio + i) < this.tamMax) {
	          System.out.println("Elemento [" + i + "] = " + this.dados[this.posInicio + i]);
	      }
	      else{
	        System.out.println("Elemento [" + i + "] = " + this.dados[this.posInicio + i - this.tamMax]);
	      }
	    }
	  }
	  System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
	}
}
