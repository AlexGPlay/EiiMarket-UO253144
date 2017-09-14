package logica;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Articulo {

	String codigo;
	String categoria;
	String subcategoria;
	String denominacion;
	String descripcion;
	double precio;
	int puntos;
	int stock;
	String imagen;


	public Articulo(String codigo, String categoria, String subcategoria, String denominacion, String descripcion, double precio, int puntos, int stock) {
		super();
		this.codigo = codigo;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.precio = precio;
		this.puntos = puntos;
		this.stock = stock;

		rutaImagen();
	}

	
	
	public String getImagen(){
		return imagen;
	}

	private void rutaImagen(){
		imagen = "/imgProductos/" + codigo + ".jpg";
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
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString(){
		String str = codigo;
		return str;
	}
	
	public double redondear(double numero, int decimales) {
	    BigDecimal bd = new BigDecimal(numero);
	    bd = bd.setScale(decimales, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
