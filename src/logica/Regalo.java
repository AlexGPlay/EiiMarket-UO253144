package logica;

public class Regalo {

	String codigo;
	String categoria;
	String denominacion;
	int puntos;
	
	public Regalo(String codigo, String categoria, String denominacion, int puntos) {
		super();
		this.codigo = codigo;
		this.categoria = categoria;
		this.denominacion = denominacion;
		this.puntos = puntos;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString(){
		String str = codigo;
		return str;
	}
}