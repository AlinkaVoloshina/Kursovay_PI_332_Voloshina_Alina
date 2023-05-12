package domain;

/**
 * 
 * Класс данных о квалификации
 *
 */

public class Qualification {
	
	// Идентификатор квалификации
	private Long id;
	// Наименование квалификации подготовки бакалавра/магистр/специалитет
	private String namequalification;
	
	public Qualification() {
	}
	
	public Qualification(String namequalification) {
	this.namequalification = namequalification;
	}
	
	public Qualification(Long id, String namequalification) {
	this.id = id;
	this.namequalification = namequalification;
	}
	
	public Long getId() {
	return id;
	}
	
	public void setId(Long id) {
	this.id = id;
	}
	public String getNamequalification() {
	return namequalification;
	}
	public void setNamequalification(String namequalification) {
	this.namequalification = namequalification;
	}
	
	@Override
	public String toString() {
	return "Qualification {" + "id = " + id + ", namequalification = " + namequalification
	+ "}";
	}

}
