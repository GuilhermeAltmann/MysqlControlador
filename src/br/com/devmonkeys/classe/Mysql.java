package br.com.devmonkeys.classe;

import java.io.IOException;
import java.util.Scanner;


public class Mysql {

	private boolean status;
	private Runtime run;
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Mysql() {
		
		this.run = Runtime.getRuntime();
	}

	public boolean verificarStatus() throws IOException {
		
		
		Process p = this.run.exec("/usr/local/bin/mysql.server status");
		
		Scanner scanner = new Scanner(p.getInputStream());
		String resultado = scanner.useDelimiter("$$").next();
		
		scanner.close();
		
		if(resultado.trim().equals("ERROR! MySQL is not running")) {
			
			this.setStatus(false);
			return false;
		}
		
		this.setStatus(true);
		return true;
	}
	
	public boolean iniciar() throws IOException {
		
		this.run.exec("/usr/local/bin/mysql.server start");

		this.setStatus(true);
		return this.verificarStatus();
	}
	
	public boolean parar() throws IOException {
		
		Process p = this.run.exec("/usr/local/bin/mysql.server stop");
		
		this.setStatus(false);
		return this.verificarStatus();
	}
}
