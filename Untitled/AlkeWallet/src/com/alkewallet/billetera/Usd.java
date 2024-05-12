package com.alkewallet.billetera;

public class Usd  implements IMoneda{

    String nombre;
    String simbol;
    String plural_name;
    Double valor;
    
    
    public Usd() {

    }

    public Usd(String nombre,String simbol,String plural_name, Double valor) {
        this.nombre = nombre;
        this.simbol = simbol;
        this.plural_name = plural_name;
        this.valor = valor;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

 
	public void setSimbol(String simbol) {
		this.simbol = simbol;
	}


	public void setPlural_name(String plural_name) {
		this.plural_name = plural_name;
	}

	@Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public Double getValor() {
       return this.valor;
    }

	@Override
	public String getPluralName() {
		return this.plural_name;
	}
	
	@Override
	  public String getSimbol() {
			return simbol;
	}


}
