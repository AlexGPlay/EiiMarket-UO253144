package logica;

import java.util.ArrayList;

public class Categoria {
	
	ArrayList<Categoria> subcategorias;
	String nombre;
	
	public Categoria(String nombre) {
		subcategorias = new ArrayList<Categoria>();
		this.nombre = nombre;
	}
	
	public void addSubcategoria(Categoria c){
		subcategorias.add(c);
	}

	public ArrayList<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(ArrayList<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void add(Categoria c) {
		subcategorias.add(c);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int sizeSubcategorias() {
		return subcategorias.size();
	}
	
	public Categoria get(int i) {
		return subcategorias.get(i); 
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	
}
