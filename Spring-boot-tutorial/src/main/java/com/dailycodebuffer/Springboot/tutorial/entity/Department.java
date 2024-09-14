package com.dailycodebuffer.Springboot.tutorial.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	// @GeneratedValue(strategy=GenerationType.AUTO, generator="my_entity_seq_gen")
	// @SequenceGenerator(name="my_entity_seq_gen", sequenceName="MY_ENTITY_SEQ")
	
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DATA")
    // @SequenceGenerator(sequenceName = "my_seq", allocationSize = 1, name = "SEQ_DATA")
	private long departmentId;
//	@NotBlank(message="Please add departmentName")
//	@Length(max=5,min=1)
//	@Size(max=10,min=0)
//	@Email
//	@Positive
//	@Negative
//	@PositiveOrZero
//	@NegativeOrZero
//	@Pastdept_name
//	@PastOrPresent
//	@Future
//	@FutureOrPresent
	
//	@NotEmpty(message="departmentName can't be empty")
	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
	
//	public Department() {}
//	
//	public Department(long departmentId, String departmentName, String departmentAddress, String departmentCode) {
//		super();
//		this.departmentId = departmentId;
//		this.departmentName = departmentName;
//		this.departmentAddress = departmentAddress;
//		this.departmentCode = departmentCode;
//	}
//	public long getDepartmentId() {
//		return departmentId;
//	}
//	public void setDepartmentId(long departmentId) {
//		this.departmentId = departmentId;
//	}
//	public String getDepartmentName() {
//		return departmentName;
//	}
//	public void setDepartmentName(String departmentName) {
//		this.departmentName = departmentName;
//	}
//	public String getDepartmentAddress() {
//		return departmentAddress;
//	}
//	public void setDepartmentAddress(String departmentAddress) {
//		this.departmentAddress = departmentAddress;
//	}
//	public String getDepartmentCode() {
//		return departmentCode;
//	}
//	public void setDepartmentCode(String departmentCode) {
//		this.departmentCode = departmentCode;
//	}
//
//	@Override
//	public String toString() {
//		return "Deparrtment [departmentId=" + departmentId + ", departmentName=" + departmentName
//				+ ", departmentAddress=" + departmentAddress + ", departmentCode=" + departmentCode + "]";
//	}
	
}
