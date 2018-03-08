package uo.asw.dbManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Property {

		@Id
		@GeneratedValue
		private long id; 
		private String property;
		private String value;
		
		@ManyToOne
		private Incidence incidence;
		
		public Property() {}
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}

		public Incidence getIncidence() {
			return incidence;
		}

		public void setIncidence(Incidence incidence) {
			this.incidence = incidence;
		}
		
}
