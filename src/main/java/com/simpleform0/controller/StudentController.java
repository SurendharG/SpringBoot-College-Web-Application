package com.simpleform0.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.simpleform0.model.Student;
import com.simpleform0.model.UserssModel;
import com.simpleform0.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController implements ErrorController{
	private final StudentService studentService ;
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService=studentService;
	}
	Integer un;
	String pa;
	@GetMapping("/staffloginunpa")
	public String getaccess(Model model,HttpSession session) {
		 un = (Integer) session.getAttribute("un");
		 pa = (String) session.getAttribute("pa");
		UserssModel authenticated = studentService.authenticate(un,pa);
		System.out.println(authenticated);
		System.out.println(authenticated);
		System.out.println(un);
		if(authenticated!=null) {	
			int acn = studentService.accesscheck(un);
			System.out.println(acn);
			if(acn==2) {
				 model.addAttribute("staffunpa", new Student());
				    model.addAttribute(model);
			    return "View_page_staff";
			    }
			else {
				return "login_page";
			}
		}
		else {
			return "redirect:/logoutmain";
		}
	}
	@GetMapping("/addstudent")
	public String getStudent(@ModelAttribute Student student,Model model) {
			UserssModel authenticated = studentService.authenticate(un,pa);
			System.out.println(authenticated);
			if(authenticated!=null) {	
				int acn = studentService.accesscheck(un);
				if(acn==2) {
					model.addAttribute("checkregnoifrequest", new Student());
					return "get_regno_add";
				    }
				else {
					return "login_page";
				}
			}
			else {
				return "redirect:/logoutmain";
			}
		}
	@PostMapping("/addstudent")
	public String askid(@ModelAttribute Student student, Model model) {
		System.out.println("Asking Id request : " + student);
		Student check= studentService.CheckRegnoif(student.getRegno());
		if (check != null) {
			model.addAttribute("errorMessage", "Reg no is alreaty existing");
			model.addAttribute("checkregnoifrequest",new Student());
			return "get_regno_add";
		} else {
			model.addAttribute("AddStudentRequest", new Student());
			model.addAttribute("regnopass",student.getRegno());
			return "add_student";
			}
	}
	@GetMapping("/addingStudent")
	public String addingStudentpost(@ModelAttribute Student student,Model model) {
			UserssModel authenticated = studentService.authenticate(un,pa);
			System.out.println(authenticated);
			if(authenticated!=null) {	
				int acn = studentService.accesscheck(un);
				if(acn==2) {
					model.addAttribute("checkregnoifrequest", student);
					return "get_regno_add";
				    }
				else {
					return "login_page";
				}
			}
			else {
				return "redirect:/logoutmain";
			}
		}
	@PostMapping("/addingStudent")
	public String updateuser(@ModelAttribute Student student, Model model) {
		System.out.println("userupdating request : " + student);
		Student d=studentService.save(student);
		if(d!=null) {
			model.addAttribute("successmessage", "Student Detail added Successfull");
			model.addAttribute("checkregnoifrequest", student);
			return "get_regno_add";
		}
		else {
		model.addAttribute("errorMessage", "Student detail Not added Please Try Again");
		model.addAttribute("AddStudentRequest", student);
		return "add_student";
	}}	
	
	@GetMapping("/askregforedit")
	public String getaskid(@ModelAttribute Student student,Model model) {
		UserssModel authenticated = studentService.authenticate(un,pa);
		System.out.println(authenticated);
		if(authenticated!=null) {	
			int acn = studentService.accesscheck(un);
			if(acn==2) {
				System.out.println("4");
				model.addAttribute("checkregnoedit",new Student());
				return "get_regno_edit";
			}
			else {
				return "login_page";
			}
		}
		else {
			return "redirect:/logoutmain";
		}
	}
	@PostMapping("/askregforedit")
	public String postaskregforedit(@ModelAttribute Student student, Model model) {
		System.out.println("Asking Id request : " + student);
		Student check= studentService.CheckRegnoif(student.getRegno());
		if (check == null) {
			model.addAttribute("errorMessage", "Reg no is Not existing");
			model.addAttribute("checkregnoedit",new Student());
			return "get_regno_edit";
		} else {
			model.addAttribute("EditstudentRequest", student);
			Student list = studentService.getAlldetails(student.getRegno());
			model.addAttribute("studentDetails", list);
//			model.addAttribute("regnopass",student.getRegno());
			return "Edit_student";
			}
	}
	@PostMapping("/updatestudent")
	public String updatestudent(@ModelAttribute Student student, Model model) {
		System.out.println("userupdating request : " + student);
		Student d=studentService.save(student);
		if(d!=null) {
			model.addAttribute("successmessage", "Student Detail Update Successfull");
			model.addAttribute("checkregnoedit",new Student());
			return "get_regno_edit";
		}
		else {
		model.addAttribute("errorMessage", "Student detail Not Updated Please Try Again");
		model.addAttribute("checkregnoedit",new Student());
		return "get_regno_edit";
	}}
	
	@GetMapping("/askregfordelete")
	public String getidfordelete(@ModelAttribute Student student,Model model) {
		UserssModel authenticated = studentService.authenticate(un,pa);
		System.out.println(authenticated);
		if(authenticated!=null) {	
			int acn = studentService.accesscheck(un);
			if(acn==2) {
				model.addAttribute("checkregnodelete",new Student());
				return "get_regno_delete";
			}
			else {
				return "login_page";
			}
		}
		else {
			return "redirect:/logoutmain";
		}
	}
	@PostMapping("/askregfordelete")
	public String postgetidfordelete(@ModelAttribute Student student, Model model) {
		System.out.println("Asking Id request : " + student);
		Student check= studentService.CheckRegnoif(student.getRegno());
		if (check == null) {
			model.addAttribute("errorMessage", "Reg no is Not existing");
			model.addAttribute("checkregnodelete", student);
			return "get_regno_delete";
		} else {
			model.addAttribute("deletestudentRequest",new Student());
			Student list = studentService.getAlldetails(student.getRegno());
			model.addAttribute("studentDetails", list);
			model.addAttribute("something",list.getRegno());
			System.out.println(list);
			System.out.println("studentDetails");
			return "delete_student";
			}
	}
	@PostMapping("/deletestudent")
	public String deletestudent(@ModelAttribute Student std, Model model) {
		System.out.println("userupdating request : " + std);
		System.out.println("this is ");
		System.out.println("this is "+std.getRegno());
		String d=studentService.deleting(std.getRegno());
		System.out.println("this is "+d);
		if(d!=null) {
			model.addAttribute("successmessage", "Student Detail Deleted Successfull");
			model.addAttribute("deletestudent",std);
			return "get_regno_delete";
		}
		else {
		model.addAttribute("errorMessage", "Student detail Not Deleted Please Try Again");
		model.addAttribute("deletestudent",std);
		return "get_regno_delete";
	}}
	@GetMapping("/showallstudentforstaff")
	public String showallstudentforstaff(@ModelAttribute Student student,Model model) {
		UserssModel authenticated = studentService.authenticate(un,pa);
		System.out.println(authenticated);
		if(authenticated!=null) {	
			int acn = studentService.accesscheck(un);
			if(acn==2) {
				List<Student> list = studentService.getAllstudent();
				if(list==null) {
				model.addAttribute("errorMessage", "There is no data ");
				model.addAttribute("ShowallStudent", list);
				}
				else {model.addAttribute("ShowallStudent", list);}
				System.out.println(list);
				return "show_all_student_details";}
			else {return "login_page";}}
		else {	return "redirect:/logoutmain";}
		}
	
	@GetMapping("/studentlogin")
	public String getloginPage(Model model) {
		model.addAttribute("studentloginRequest", new Student());
		return "student_login_page";
	}
	@PostMapping("/studentlogin")
	public String studentlogin(@ModelAttribute Student student, Model model) {
		System.out.println("login request : " + student);
		Student authenticated=studentService.authticatingstudent(student.getRegno(),student.getPassword());
//		if(authenticated!=null) {
//			un=usersModel.getPersonalid();
//			pa=usersModel.getPassword();
//		}
		if (authenticated != null) {
//			session.setAttribute("un", usersModel.getPersonalid());
//			session.setAttribute("pa", usersModel.getPassword());
			Student list = studentService.getAlldetails(student.getRegno());
			model.addAttribute("studentDetails", list);
			return "student_details_ownview";
			}
			else {
			model.addAttribute("errorMessage", "Incorrect Reg No or Password");
			model.addAttribute("studentloginRequest", student);
			return "student_login_page";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/logoutmain")
	public String logout(HttpSession session) {
	    un = 0;
	    pa = null;
	    session.removeAttribute("un"); 
	    session.removeAttribute("pa");
	    return "login_page";
	}
	
}