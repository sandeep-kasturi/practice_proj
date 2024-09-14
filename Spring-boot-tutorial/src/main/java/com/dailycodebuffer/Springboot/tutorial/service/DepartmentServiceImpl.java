package com.dailycodebuffer.Springboot.tutorial.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.dailycodebuffer.Springboot.tutorial.customValidation.CustomValidator;
import com.dailycodebuffer.Springboot.tutorial.entity.Department;
import com.dailycodebuffer.Springboot.tutorial.error.DepartmentNotFoundException;
import com.dailycodebuffer.Springboot.tutorial.exception.NotEmptyException;
import com.dailycodebuffer.Springboot.tutorial.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private CustomValidator customValidator;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public Department saveDepartment(Department department) throws NotEmptyException {
		boolean res = customValidator.notEmptyValidator(department.getDepartmentName());
		if(res) {
			throw new NotEmptyException(messageSource.getMessage("dept_name", null, LocaleContextHolder.getLocale()));
		}
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> fetchDepartmentList() {
		return departmentRepository.findAll();
	}

	@Override
	public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
		Optional<Department> department = departmentRepository.findById(departmentId);
			if(!department.isPresent())
			{
				throw new DepartmentNotFoundException("Department Not Available");
			}
		return department.get();
	}

	@Override
	public void deleteDepartmentById(Long departmentId) {
		departmentRepository.deleteById(departmentId);	
	}

	@Override
	public Department updateDepartment(Long departmentId, Department department) {
		Department depDB = departmentRepository.findById(departmentId).get();
		
		if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName()))
		{
			depDB.setDepartmentName(department.getDepartmentName());
		}
		if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode()))
		{
			depDB.setDepartmentCode(department.getDepartmentCode());
		}
		if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress()))
		{
			depDB.setDepartmentAddress(department.getDepartmentAddress());
		}
		
		return departmentRepository.save(depDB);
		
	}

	@Override
	public Department fetchDepartmentByName(String departmentName) {
		return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
	}
	
	
}
