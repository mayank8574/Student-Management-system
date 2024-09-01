package services;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dao.EmployeeDao;
import model.Employee;
@Component
@Service
public class EmployeeServices {
	@Autowired
	EmployeeDao employeeDao;
	//add employee
	public void addEmp(Employee emp,String file)
	{
		employeeDao.addEmployee(emp,file);
	}
	//get all employee
	public List<Employee> getAllEmp()
	{
		return employeeDao.getAllEmp();
	}
	//get emp by email
	public Employee getByEmail(String email)
	{
		System.out.println("Service");
		return employeeDao.getEmpByEmail(email);
	}
	//get emp by id
	public Employee getById(Long id)
	{
		return employeeDao.getEmpById(id);
	}
	// update employee
	public void updateEmp(Employee emp)
	{
		employeeDao.updateEmp(emp);
	}
	//delete employee 
	public void deleteEmployee(Long id)
	{
		employeeDao.deleteEmp(id);
	}
}