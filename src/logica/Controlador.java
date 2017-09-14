package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Controlador {

	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<Articulo> articulos = new ArrayList<Articulo>();
	private ArrayList<Regalo> regalos = new ArrayList<Regalo>();
	private ArrayList<Categoria> categorias = new ArrayList<Categoria>();

	private ArrayList<Articulo> filtroCategoria = new ArrayList<Articulo>();
	private ArrayList<Articulo> destacados = new ArrayList<Articulo>();

	private ArrayList<ArticuloCarrito> carrito = new ArrayList<ArticuloCarrito>();
	private ArrayList<RegaloCarrito> regalosAdquiridos = new ArrayList<RegaloCarrito>();

	public Cliente loggedClient;

	private double precioTotal;
	private Categoria descuento;
	private int numDescuento;

	private final String articulosFile = "src/files/articulos.dat";
	private final String clientesFile = "src/files/clientes.dat";
	private final String regalosFile = "src/files/regalos.dat";


	public Controlador(){
		checkCategoria();
		generaDescuento();

		if(cargaClientes()==1)
			System.err.print("Error al cargar los clientes\n");

		if(cargaArticulos()==1)
			System.err.print("Error al cargar los articulos\n");

		if(cargaRegalos()==1)
			System.err.print("Error al cargar los regalos\n");

		loggedClient = null;
		generaDestacados();

	}

	public Categoria getDescuento(){
		return descuento;
	}

	public void generaDescuento(){
		int random = ThreadLocalRandom.current().nextInt(0, categorias.size());
		numDescuento = random;
		descuento = categorias.get(random);
	}

	public int getNumeroDescuento() {
		return numDescuento;
	}

	public ArrayList<Articulo> getDestacados(){
		return destacados;
	}

	public ArrayList<Articulo> getFiltroCategoria(){
		return filtroCategoria;
	}

	public ArrayList<Categoria> getCategorias(){
		return categorias;
	}

	public double getPrecioTotal(){
		actualizaPrecio();
		return precioTotal;
	}

	public ArrayList<RegaloCarrito> getRegalos(){
		return regalosAdquiridos;
	}

	public ArrayList<Regalo> listRegalos(){
		return regalos;
	}

	public int getPuntosGanados(){
		int p = 0;

		for(int i=0;i<carrito.size();i++){
			p += carrito.get(i).getArticulo().getPuntos()*carrito.get(i).getCantidad();
		}

		return p;
	}

	public int getPuntosCompra(){
		int temp = (int)precioTotal;
		double temp2 = (precioTotal-temp);
		
		if(temp2!=0.00)
			temp++;
		
		
		if(temp>loggedClient.getPuntos())
			return loggedClient.getPuntos();

		else
			return temp;
	}

	public int getPuntosGastados(){
		int p = 0;

		for(int i=0;i<regalosAdquiridos.size();i++){
			p += regalosAdquiridos.get(i).getRegalo().getPuntos()*regalosAdquiridos.get(i).getCantidad();
		}

		return p;
	}

	private void generaDestacados() {
		int rand;
		Articulo temp;

		for(int i=0;i<5;i++) {

			do {
				rand = (int)(Math.random()*articulos.size());
				temp = articulos.get(rand);
			}while(destacados.contains(temp));

			destacados.add(articulos.get(rand));
		}

	}

	private int cargaClientes(){
		BufferedReader br;
		FileInputStream fr;

		try {
			fr = new FileInputStream(clientesFile);
			br = new BufferedReader(new InputStreamReader(fr, "ISO-8859-1"));

			String str = br.readLine();
			String[] all;

			while(str!=null){
				all = str.split(";");

				clientes.add(new Cliente(all[0], all[1], all[2], all[3], all[4], Integer.parseInt(all[5]), all[6], Integer.parseInt(all[7])));

				str = br.readLine();
			}

			br.close();
			fr.close();

		} catch (Exception e) {
			return 1;
		}

		return 0;
	}

	private int cargaRegalos(){
		BufferedReader br;
		FileInputStream fr;

		try {
			fr = new FileInputStream(regalosFile);
			br = new BufferedReader(new InputStreamReader(fr, "ISO-8859-1"));

			String str = br.readLine();
			String[] all;

			while(str!=null){
				all = str.split(";");

				regalos.add(new Regalo(all[0], all[1], all[2], Integer.valueOf(all[3])));

				str = br.readLine();
			}

			br.close();
			fr.close();

		} catch (Exception e) {
			return 1;
		}

		return 0;

	}

	private int cargaArticulos(){
		BufferedReader br;
		FileInputStream fr;

		try {
			fr = new FileInputStream(articulosFile);
			br = new BufferedReader(new InputStreamReader(fr, "ISO-8859-1"));

			String str = br.readLine();
			String[] all;

			while(str!=null){
				all = str.split(";");

				if(all[1].equals(descuento.getNombre()))
					articulos.add(new Articulo(all[0], all[1], all[2], all[3], all[4], ((Double.parseDouble(all[5]))*0.9), Integer.parseInt(all[6]), Integer.parseInt(all[7])));


				else
					articulos.add(new Articulo(all[0], all[1], all[2], all[3], all[4], Double.parseDouble(all[5]), Integer.parseInt(all[6]), Integer.parseInt(all[7])));

				checkSubcategoria(all[1], all[2]);

				str = br.readLine();
			}

			br.close();
			fr.close();

		} catch (Exception e) {
			return 1;
		}

		return 0;
	}

	private void checkCategoria(){
		categorias.add(new Categoria("Consolas y Videojuegos"));
		categorias.add(new Categoria("Fotografía y Vídeo"));
		categorias.add(new Categoria("Telefonía Móvil"));
		categorias.add(new Categoria("Ordenadores y Tablets"));
		categorias.add(new Categoria("Videovigilancia"));
	}

	private void checkSubcategoria(String categoria, String subcategoria) {
		Categoria temp = null;

		for(int i=0;i<categorias.size();i++) {
			if(categorias.get(i).getNombre().equals(categoria)) 
				temp = categorias.get(i);
		}

		for(int i=0;i<temp.sizeSubcategorias();i++) {
			if(temp.get(i).getNombre().equals(subcategoria))
				return;
		}

		temp.add(new Categoria(subcategoria));

	}

	public void newRegistro(String str){
		String[] all = str.split(";");

		clientes.add(new Cliente(all[0], all[1], all[2], all[3], all[4], Integer.parseInt(all[5]), all[6], Integer.parseInt(all[7])));
		logRegistrado();
	}

	public int registraUsuario(String linea){

		BufferedWriter bf;
		FileWriter fl;
		PrintWriter pw;

		try {
			fl = new FileWriter(new File(clientesFile), true);
			bf = new BufferedWriter(fl);
			pw = new PrintWriter(bf);

			pw.println();
			pw.print(linea);

			pw.close();
			bf.close();
			fl.close();
		} 

		catch (Exception e) {
			return 1;
		}

		newRegistro(linea);

		return 0;
	}

	public boolean checkTelefono(String tlf){

		if(tlf.length()!=9)
			return false;

		return true;
	}

	public void logRegistrado(){
		Cliente temp = clientes.get(clientes.size()-1);
		loggedClient = temp;
	}

	public boolean login(String usuario, String password){
		Cliente client = null;

		for(int i=0;i<clientes.size();i++){
			Cliente temp = clientes.get(i);

			if(temp.getUsuario().toUpperCase().equals(usuario.toUpperCase()) && temp.getPassword().equals(password)){
				client = temp;
				break;
			}
		}

		if(client!=null){
			loggedClient = client;
			return true;
		}

		else
			return false;
	}

	public void logout(){
		carrito.clear();
		loggedClient = null;
	}

	public boolean nombreDisponible(String user){

		for(int i=0;i<clientes.size();i++){
			Cliente temp = clientes.get(i);
			String nombre = temp.getUsuario();
			nombre = nombre.toUpperCase();
			user = user.toUpperCase();

			if(nombre.equals(user))
				return false;
		}

		return true;
	}

	public boolean passwordValida(char[] pass){
		boolean letra = false;
		boolean numeros = false;

		if(pass.length<8)
			return false;

		for(int i=0;i<pass.length;i++){
			char temp = pass[i];

			if(Character.isAlphabetic(temp))
				letra = true;

			if(Character.isDigit(temp))
				numeros = true;
		}

		if(letra && numeros)
			return true;

		return false;
	}

	public boolean dniDisponible(String dni){

		if(!dniReal(dni))
			return false;

		for(int i=0;i<clientes.size();i++){
			Cliente temp = clientes.get(i);
			String cif = temp.getNif();
			cif = cif.toUpperCase();
			dni = dni.toUpperCase();

			if(cif.equals(dni))
				return false;
		}

		return true;
	}

	private boolean dniReal(String dni){

		char[] letraDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D',  'X',  'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
		int ident = 0;
		String nums = "";

		if(dni.length()<8 || dni.length()>9)
			return false;

		if(dni.length()==8)
			dni = "0" + dni;

		if(!Character.isLetter(dni.charAt(8)))
			return false;

		for(int i=0;i<8;i++){

			if(!Character.isDigit(dni.charAt(i)))
				return false;    

			nums += dni.charAt(i);
		}

		ident = Integer.parseInt(nums);
		ident %= 23;

		if((Character.toUpperCase(dni.charAt(8)))!=letraDni[ident])
			return false;

		return true;
	}

	public ArrayList<Articulo> getArticulos(){
		return articulos;
	}

	public void generarFiltroCategoria(int c) {
		String categoria = categorias.get(c).getNombre();
		filtroCategoria.clear();

		for(int i=0;i<articulos.size();i++) {
			if(articulos.get(i).getCategoria().equals(categoria)) 
				filtroCategoria.add(articulos.get(i));	
		}

	}

	public ArrayList<Articulo> filtraTexto(String t){
		ArrayList<Articulo> filtroTexto = new ArrayList<Articulo>();

		for(int i=0;i<articulos.size();i++){
			if(articulos.get(i).getDenominacion().toUpperCase().contains(t.toUpperCase())){
				filtroTexto.add(articulos.get(i));
			}

		}

		return filtroTexto;
	}

	public ArrayList<ArticuloCarrito> getCarrito(){
		return carrito;
	}

	private void actualizaPrecio(){
		double t = 0;

		for(int i=0;i<carrito.size();i++){
			t += (carrito.get(i).getArticulo().getPrecio() * carrito.get(i).getCantidad());
		}

		precioTotal = t;
	}

	public int comprar(Articulo a, int cantidad){
		boolean localizado = false;
		int j = 0;

		for(int i=0;i<carrito.size();i++){
			if(carrito.get(i).getArticulo().getDenominacion().equals(a.getDenominacion())){
				if(carrito.get(i).getCantidad()+cantidad <= carrito.get(i).getArticulo().getStock()){
					carrito.get(i).setCantidad(carrito.get(i).getCantidad()+cantidad);
					j = i;
				}
				else{
					return 1;
				}
				localizado = true;
			}
		}

		if(localizado){
			if(carrito.get(j).getCantidad()==0)
				carrito.remove(j);
		}

		else
			carrito.add(new ArticuloCarrito(a, cantidad));

		actualizaPrecio();
		return 0;
	}

	public Cliente getLogged(){
		return loggedClient;
	}

	public int guardaFactura(String factura, String dni){

		BufferedWriter bf;
		FileWriter fl;
		PrintWriter pw;

		try {
			fl = new FileWriter(new File(dni + ".txt"), false);
			bf = new BufferedWriter(fl);
			pw = new PrintWriter(bf);

			pw.print(factura);

			pw.close();
			bf.close();
			fl.close();
		} 

		catch (Exception e) {
			return 1;
		}

		return 0;
	}

	public void actualizaStock(){

		for(int i=0;i<carrito.size();i++){
			carrito.get(i).getArticulo().setStock(carrito.get(i).getArticulo().getStock()-carrito.get(i).getCantidad());
		}

	}

	public void actualizaClientes(){

		BufferedWriter bf;
		FileOutputStream fl;
		PrintWriter pw;

		File f = new File(clientesFile);
		f.delete();

		try {
			fl = new FileOutputStream(new File(clientesFile), true);
			bf = new BufferedWriter(new OutputStreamWriter(fl,"ISO-8859-1"));
			pw = new PrintWriter(bf);
			String linea;

			linea = createText(clientes.get(0));
			pw.print(linea);

			for(int i=1;i<clientes.size();i++){
				linea = createText(clientes.get(i));
				pw.println();
				pw.print(linea);
			}

			pw.close();
			bf.close();
			fl.close();
		} 

		catch (Exception e) { }

	}

	private String createText(Cliente c){
		String text = "";
		text += c.getNombre() + ";";
		text += c.getApellidos() + ";";
		text += c.getNif() + ";";
		text += c.getUsuario() + ";";
		text += c.getPassword() + ";";
		text += c.getTelefono() + ";";
		text += c.getTarjetaBanco() + ";";
		text += c.getPuntos();

		return text;
	}

	public int carritoSize() {
		int total = 0;

		for(int i=0;i<carrito.size();i++) {
			total+=carrito.get(i).getCantidad();
		}

		return total;
	}

	public ArticuloCarrito articuloDetected(Articulo a){

		ArticuloCarrito car = null;

		for(int i=0;i<carrito.size();i++){
			Articulo temp = carrito.get(i).getArticulo();

			if(temp.equals(a))
				return carrito.get(i);
		}


		return car;
	}

}
