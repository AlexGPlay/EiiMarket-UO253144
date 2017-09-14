package logica;

public class Cliente {

	String nombre;
	String apellidos;
	String nif;
	String usuario;
	String password;
	int telefono;
	String tarjetaBanco;
	int puntos;
	
	public Cliente(String nombre, String apellidos, String nif, String usuario, String password, int telefono, String tarjetaBanco, int puntos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
		this.usuario = usuario;
		this.password = password;
		this.telefono = telefono;
		this.tarjetaBanco = tarjetaBanco;
		this.puntos = puntos;
	}
	
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getTarjetaBanco() {
		return tarjetaBanco;
	}
	public void setTarjetaBanco(String tarjetaBanco) {
		this.tarjetaBanco = tarjetaBanco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	@Override
	public String toString(){
		String st = usuario;
		
		return st;
	}
	
}
