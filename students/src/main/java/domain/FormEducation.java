package domain;
/**
 * 
 * Класс данных о квалификации
 *
 */
public class FormEducation {
	
	// Идентификатор формы обучения
		private Long id;
		// Форма обученния
		private String nameform;
		
		public FormEducation() {
		}
		
		
		
		public FormEducation(Long id, String nameform) {
		this.id = id;
		this.nameform = nameform;
		}
		
		public FormEducation(String nameform) {
			this.nameform = nameform;
			}
		
		public Long getId() {
		return id;
		}
		
		public void setId(Long id) {
		this.id = id;
		}
		public String getNameform() {
		return nameform;
		}
		public void setNameform(String nameform) {
		this.nameform = nameform;
		}
		
		@Override
		public String toString() {
		return "FormEducation {" + "id = " + id + ", nameform = " + nameform + "}";
		}


}
