package domain;
/**
 *
 * класс данных о группах
 *
 */

public class Group {
	// Идентификатер группы
	private Long id;
	
	// факультет
	private String faculty;
	// Наименование группы
	private String name;
	
	// Курс
	private Integer course;
	
	// Количество студентов
	private Integer countstudent;
	
	// Количество подгрупп
	private Integer countsubgrroup;
	
	// Внешний ключ -ссылка на сущность Speciality
	private Long idspeciality;
	
	// Внешний ключ -ссылка на сущность Qualification
		private Long idqualification;
		
	// Внешний ключ -ссылка на сущность FormEducation
		private Long idformeducation;
		
	// Навигационное свойства - ссылка на направление
	private Speciality speciality;
		
	// Навигационное свойства - ссылка на квалификацию
	private Qualification qualification;
		
	// Навигационное свойства - ссылка на форму обучения
	private FormEducation formeducation;
				
	public Group() {
	}
	
//	public Group(String faculty, String name, Integer course, Integer countstudent, Integer countsubgrroup,
//			Speciality speciality, Qualification qualification, FormEducation formeducation) {
//		this.faculty = faculty;
//		this.name = name;
//		this.course = course;
//		this.countstudent = countstudent;
//		this.countsubgrroup = countsubgrroup;
//		this.speciality = speciality;
//		this.qualification = qualification;
//		this.formeducation = formeducation;
//	}
//	
//	public Group(String faculty, String name, Integer course, Integer countstudent, Integer countsubgrroup,
//			Long idspeciality, Long idqualification, Long idformeducation,
//			Speciality speciality, Qualification qualification, FormEducation formeducation) {
//		this.faculty = faculty;
//		this.name = name;
//		this.course = course;
//		this.countstudent = countstudent;
//		this.countsubgrroup = countsubgrroup;
//		this.speciality = speciality;
//		this.qualification = qualification;
//		this.formeducation = formeducation;
//		this.idspeciality = idspeciality;
//		this.idqualification = idqualification;
//		this.idformeducation = idformeducation;
//	}
//	

	public Group(Long id, String faculty, String name, Integer course, Integer countstudent, Integer countsubgrroup,
			Long idspeciality, Long idqualification,  Long idformeducation) {
		this.id = id;
		this.faculty = faculty;
		this.name = name;
		this.course = course;
		this.countstudent = countstudent;
		this.countsubgrroup = countsubgrroup;
		this.idspeciality = idspeciality;
		this.idqualification = idqualification;
		this.idformeducation = idformeducation;
	}
	
	public Group(Long id, String faculty, String name, Integer course, Integer countstudent, Integer countsubgrroup,
			Long idspeciality, Speciality speciality, Long idqualification,  Qualification qualification,Long idformeducation,
			 FormEducation formeducation) {
		this.id = id;
		this.faculty = faculty;
		this.name = name;
		this.course = course;
		this.countstudent = countstudent;
		this.countsubgrroup = countsubgrroup;
		this.speciality = speciality;
		this.qualification = qualification;
		this.formeducation = formeducation;
		this.idspeciality = idspeciality;
		this.idqualification = idqualification;
		this.idformeducation = idformeducation;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFaculty() {
		return faculty;
	}
	
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCourse() {
		return course;
	}
	
	public void setCourse(Integer course) {
		this.course = course;
	}
	
	public Integer getCountStudent() {
		return countstudent;
	}
	
	public void setCountStudent(Integer countstudent) {
		this.countstudent = countstudent;
	}
	
	public Integer getCountSubgrroup() {
		return countsubgrroup;
	}
	
	public void setCountSubgrroup(Integer countsubgrroup) {
		this.countsubgrroup = countsubgrroup;
	}
	
	public Speciality speciality () {
		return speciality;
	}
	public Long getIdSpeciality() {
		return idspeciality;
	}
	public void setIdSpeciality(Long idspeciality) {
		this.idspeciality = idspeciality;
	}
	public String getSpeciality() {
		return speciality.getNamespeciality();
	}
	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}
	
	public Qualification qualification () {
		return qualification;
	}
	public Long getIdQualification() {
		return idqualification;
	}
	public void setIdQualification(Long idqualification) {
		this.idqualification = idqualification;
	}
	public String getQualification() {
		return qualification.getNamequalification();
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	
	public FormEducation formeducation () {
		return formeducation;
	}
	public Long getIdFormEducation() {
		return idformeducation;
	}
	public void setIdFormEducation(Long idformeducation) {
		this.idformeducation = idformeducation;
	}
	public String getFormEducation() {
		return formeducation.getNameform();
	}
	public void setFormEducation(FormEducation formeducation) {
		this.formeducation = formeducation;
	}
//	public String getSpeciality() {
//		return speciality.getNamespeciality();
//	}
//	
//	public void setSpeciality(Speciality speciality) {
//		this.speciality = speciality;
//	}
//	
//	public String getQualification() {
//		return qualification.getNamequalification();
//	}
//	
//	public void setQualification(Qualification qualification) {
//		this.qualification = qualification;
//	}
//	
//	public String getFormEducation() {
//		return formeducation.getNameform();
//	}
//	
//	public void setFormEducation(FormEducation formeducation) {
//		this.formeducation = formeducation;
//	}
//	
//	public Long getIdSpeciality() {
//		return idspeciality;
//		}
//	public void setIdSpeciality(Long idspeciality) {
//	this.idspeciality = idspeciality;
//		}
//	
//	public Long getIdQualification() {
//	return idqualification;
//		}
//	public void setIdQualification(Long idqualification) {
//	this.idqualification = idqualification;
//		}
//
//	public Long getIdFormEducation() {
//	return idformeducation;
//		}
//	public void setIdFormEducation(Long idformeducation) {
//	this.idformeducation = idformeducation;
//		}
	@Override
	public String toString() {
	return "Group {"+ "id = " + id +
			", faculty = " + faculty +
			", name =" + name +
			", course =" + course +
			", countstudent =" + countstudent +
			", countsubgrroup =" + countsubgrroup +
			", namespeciality =" +
			", namequalification =" +
			", nameform =" + getIdSpeciality() + getIdQualification() + getIdFormEducation() +
			"}";
	}
}
