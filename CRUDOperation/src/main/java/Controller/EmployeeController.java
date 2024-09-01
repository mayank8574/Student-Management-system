package Controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import model.Employee;
import services.EmployeeServices;

@Controller
@RequestMapping
public class EmployeeController {

	@Autowired
	EmployeeServices employeeServices;
	@GetMapping("/")
	public String home(Model m)
	{
		m.addAttribute("employee", employeeServices.getAllEmp());
		m.addAttribute("title", "Employee Report");
		return "EmployeeReport";
	}
	//lode add employee form
	@GetMapping("addEmployee")
	public String addEmp(@ModelAttribute("employee") Employee employee)
	{
		return "AddEmployee";
	}
	//save employee form
	//@PostMapping("/insertEmployee")
	@RequestMapping(value="/insertEmployee",method = RequestMethod.POST)
	public String insertEmployee(@Valid @ModelAttribute("employee") Employee employee,
			BindingResult result,HttpSession session, @RequestParam("email") String email,
			@RequestParam("profile") CommonsMultipartFile file)
	{
		System.out.println("Controller");
		System.out.println(file.getOriginalFilename());
		if(result.hasErrors()) { 
			return "AddEmployee"; }
		
		Employee emp=employeeServices.getByEmail(email); 
		if(emp==null)
		{
			
			String filetype=file.getContentType();
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getContentType());
			if(!(filetype.indexOf("png")>=0 || filetype.indexOf("jpeg")>=0))
			{
				session.setAttribute("msg", "Please Upload a Valid Image File");
				return "registrationpage";
			}
			else
			{
				String fn=file.getOriginalFilename();
				String img=email+"."+ fn.substring(fn.lastIndexOf(".")+1);
				
				employeeServices.addEmp(employee,img);
				System.out.println(file.getOriginalFilename());
				byte[] data=file.getBytes();
				String path=session.getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"Profiles"+File.separator+img;
				try
				{
					FileOutputStream fos=new FileOutputStream(path);
					System.out.println(path);
					fos.write(data);
					fos.close();
				
				}
				catch (Exception e) {
					System.out.println("Some error");
					// TODO: handle exception
				}
				session.setAttribute("msg", "Your Registration is Successfully Completed.");
				return "redirect:/employeeReport";
			}
//			
		}
		else
		{
			session.setAttribute("msg","Email Id already exist");
			return "AddEmployee";
		}
		//return "AddEmployee";
		
	}
	//lode employee data
	@GetMapping("employeeReport")
	public String lodeEmployee(Model m)
	{
		m.addAttribute("employee", employeeServices.getAllEmp());
		m.addAttribute("title", "Employee Report");
		return "EmployeeReport";
	}
	//lode edit form
	@GetMapping("/editEmployee/{id}")
	public String lodeEditForm(@PathVariable(value="id") Long id, Model m)
	{
		Employee emp=employeeServices.getById(id);
		
		System.out.println(emp);
		m.addAttribute("employee", emp);
		m.addAttribute("title", "Edit Employee");
		return "EditEmployee";
	}
	@PostMapping("/editEmployee/updateEmployee")
	public String updateEmp(@ModelAttribute("updateEmployee") Employee emp)
	{
		employeeServices.updateEmp(emp);
		return "redirect:/employeeReport";
	}
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Long id)
	{
		employeeServices.deleteEmployee(id);
		return "redirect:/employeeReport";
	}
}