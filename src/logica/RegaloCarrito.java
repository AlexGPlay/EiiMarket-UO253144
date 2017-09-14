package logica;

public class RegaloCarrito {
	
	Regalo regalo;
	int cantidad;
	
	public RegaloCarrito(Regalo reg, int c){
		regalo = reg;
		cantidad = c;
	}

	public Regalo getRegalo() {
		return regalo;
	}

	public void setRegalo(Regalo regalo) {
		this.regalo = regalo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
