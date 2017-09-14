package logica;

public class ArticuloCarrito {
	
	Articulo articulo;
	int cantidad;
	
	public ArticuloCarrito(Articulo art, int c){
		articulo = art;
		cantidad = c;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
