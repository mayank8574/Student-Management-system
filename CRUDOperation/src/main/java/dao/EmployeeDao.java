package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import model.Employee;

@Component
public class EmployeeDao {

	
	@Autowired
	HibernateTemplate hiberneteTemplate;
	//add employee
	@Transactional
	public void addEmployee(Employee emp,String file)
	{
		emp.setImage(file);
		hiberneteTemplate.save(emp);
	}
	//get all employee
	public List<Employee> getAllEmp()
	{
		return hiberneteTemplate.loadAll(Employee.class);
	}
	//get employee by id
	@Transactional
	public Employee getEmpById(Long id)
	{
		Employee emp= hiberneteTemplate.get(Employee.class, id);
		return emp;
	}
	@SuppressWarnings("deprecation")
	@Transactional
	public Employee getEmpByEmail(String email)
	{
		System.out.println(email);
		String sql="from Employee where email='"+email+"'";
		Employee emp=(Employee) hiberneteTemplate.execute(s->{
			Query q=s.createQuery(sql);
			return q.setMaxResults(1).uniqueResult();
		});
		return emp;
		
		
	}
	//update employee
	@Transactional
	public void updateEmp(Employee emp)
	{
		hiberneteTemplate.update(emp);
	}
	//delete employee
	@Transactional
	public void deleteEmp(Long id)
	{
		hiberneteTemplate.delete(hiberneteTemplate.load(Employee.class, id));
	}
}