package domain;
/**
 * 
 * Класс данных о должности
 *
 */
public class Speciality {
	//Идентификатор направления подготовки
	private Long id;
	
	//Направление подготовки бакалавриат
	private String namespeciality;
	
	//Профиль подготовки
	private String profile;
	
	public Speciality() {
	}
	
	public Speciality(String namespeciality, String profile) {
		this.namespeciality = namespeciality;
		this.profile = profile;
	}
	
	public Speciality(Long id,String namespeciality, String profile) {
		this.id =id;
		this.namespeciality = namespeciality;
		this.profile=profile;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNamespeciality() {
		return namespeciality;
	}
	
	public void setNamespeciality(String namespeciality) {
		this.namespeciality = namespeciality;
	}
	
	public String getProfile() {
		return profile;
	}
	
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	@Override
	public String toString() {
		return "Speciality {" + "id = " + id +
				", NameSpeciality = " + namespeciality +
				", Profile = " + profile + "}";
		
		}
	}


