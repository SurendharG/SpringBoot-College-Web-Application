package com.simpleform0.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.simpleform0.model.Applications;
import com.simpleform0.model.Student;
import com.simpleform0.model.UserssModel;
import com.simpleform0.service.ApplicationsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ApplicationsController {
	private final ApplicationsService applyService;
	
	@Autowired
	public ApplicationsController(ApplicationsService applyService) {
		this.applyService=applyService;
	}
	Integer un;
	String pa;
	@GetMapping("/admlogin")
	public String getaccess(Model model,HttpSession session) {
		 un = (Integer) session.getAttribute("un");
		 pa = (String) session.getAttribute("pa");
		UserssModel authenticated = applyService.authenticate(un,pa);
		if(authenticated!=null) {	
			int acn = applyService.accesscheck(un);
			System.out.println(acn);
			if(acn==3||acn==1) {
				 model.addAttribute("admunpa", new Applications());
				    model.addAttribute(model);
			    return "View_page_Adm";
			    }
			else {
				return "login_page";
			}
		}
		else {
			return "login_page";
		}
	}
	
	@GetMapping("/applicationform")
	public String applicationform(@ModelAttribute Applications application,Model model) {
		model.addAttribute("ApplicationRequest",new Applications());
		model.addAttribute("Abc","hi");
		return "application_form";
	}
	
	@PostMapping("/applicationformconfirm")
	public String postapplicationformconfirm(@ModelAttribute Applications applications,Model model) {
		model.addAttribute("Applicationconfirmation",new Applications());
			model.addAttribute("Applicationconfirm", applications);
			return "applicationconfirm";
	}
	@PostMapping("/applicationformconfirming")
	public String postapplicationformconfirming(@ModelAttribute Applications applications,Model model) {
		model.addAttribute("Applicationconfirmation",new Applications());
			model.addAttribute("Applicationconfirm", applications);
			model.addAttribute("Abcde","hi");
			return "application_form";
	}
	

	@PostMapping("/applicationform")
	public String postapplicationform(@ModelAttribute Applications applications,Model model) {
		Applications a=applyService.applyform(applications);
		if(a!=null) {
			return "confirm";
		}
		else {
			model.addAttribute("errormessage", "Application Not Submitted");
			model.addAttribute("ApplicationRequest",new Applications());
			return "application_form";	
		}
	}
	@GetMapping("/showallapplications")
	public String showallapplication(@ModelAttribute Applications applications,Model model) {
		UserssModel authenticated = applyService.authenticate(un,pa);
		System.out.println(authenticated);
		if(authenticated!=null) {	
			int acn = applyService.accesscheck(un);
			if(acn==3||acn==1) {
				List<Applications> list = applyService.getAllApplication();				
				if(list.isEmpty()) {
				model.addAttribute("errorMessage", "There is no data ");
				return "confirmnullapplication";
				}
				else {
					System.out.println();
					model.addAttribute("applications", list);}
				return "show_all_applications";}
			else {return "login_page";}}
		else {	return "redirect:/logoutmain";}
		}
	@GetMapping("/admitthrowapplication/{aid}")
	public String askid(@ModelAttribute Applications applications, Model model,@PathVariable("aid") int aid) {
	Applications ap=applyService.getApplicationById(aid);
	model.addAttribute("appIP", ap);
	model.addAttribute("Admission", new Student());
	return "admission_entry";
	}
	@PostMapping("/admitthrowapplication/{aid}")
	public String dhbjhv(@ModelAttribute Student student, @ModelAttribute Applications applications,Model model ,@PathVariable("aid") int aid){
		int d=applyService.save(student);	
		System.out.println(d);
		System.out.println(student);
		if(d==1) {
			applyService.deleteapplication(aid);
			List<Applications> list = applyService.getAllApplication();
			model.addAttribute("applications", list);
			model.addAttribute("successmessage","Admission Done");
			return "ConfirmAdmissionEntry";}
		else if(d==2) {
			model.addAttribute("applicationss", student);
			Applications api=applyService.getApplicationById(aid);
			model.addAttribute("appIPs", api);
			model.addAttribute("errormessage","Reg No already Exiting");
			return "admission_entry";}
		
		List<Applications> list = applyService.getAllApplication();
		model.addAttribute("applications", list);
		model.addAttribute("errormessage","Admission Not Done Please Try Again");
		return "show_all_applications";
	}
	@GetMapping("/editstudentadm")
	public String getStudent(Model model) {
			UserssModel authenticated = applyService.authenticate(un,pa);
			System.out.println(authenticated);
			if(authenticated!=null) {	
				int acn = applyService.accesscheck(un);
				if(acn==3||acn==1) {
					return "get_regnofromadm";
				    }
				else {
					return "login_page";
				}
			}
			else {
				return "redirect:/logoutmain";
			}
		}
	@PostMapping("/editstudentadm")
	public String postaskregforedit(@ModelAttribute Student student, Model model) {
		System.out.println("Asking Id request : " + student);
		Student check= applyService.CheckRegnoif(student.getRegno());
		if (check == null) {
			model.addAttribute("errorMessage", "Reg no is Not existing");
			return "get_regnofromadm";
		} else {
			model.addAttribute("EditstudentRequestFromadm", student);
			Student list = applyService.getAlldetails(student.getRegno());
			model.addAttribute("studentDetails", list);
			System.out.println(list);
			System.out.println(list);
			System.out.println(list);
			return "Edit_student_fromadm";
			}
	}
	@PostMapping("/updatestudentadm")
	public String updatestudentadm(@ModelAttribute Student student, Model model) {
		System.out.println("Asking Id request : " + student);
		Student check= applyService.saving(student);
		if (check != null) {
			model.addAttribute("successmessage", "Student Detail Update Successfull");
			return "confirmupdatestudetnadm";
		} else {
			model.addAttribute("errorMessage", "Student detail Not Updated Please Try Again");
			return "get_regnofromadm";
			}
	}
	@GetMapping("/deleteapplication/{aid}")
	public String deleteApplication(@ModelAttribute Applications applications, Model model,@PathVariable("aid") int aid) {
	UserssModel authenticated = applyService.authenticate(un,pa);
		System.out.println(authenticated);
		if(authenticated!=null) {	
			int acn = applyService.accesscheck(un);
			if(acn==3||acn==1) {
			applyService.deleteapplication(aid);
				return "redirect:/showallapplications";}
			else {
				return "login_page";
			}
		}
		else {
			return "redirect:/logoutmain";
		}}
	@GetMapping("/admitnewstudentadm")
	public String getStudent(@ModelAttribute Student student,Model model) {
			UserssModel authenticated = applyService.authenticate(un,pa);
			System.out.println(authenticated);
			if(authenticated!=null) {	
				int acn = applyService.accesscheck(un);
				if(acn==3||acn==1) {
					model.addAttribute("checkregnoifrequestadm", new Student());
					return "get_regno_add_adm";
				    }
				else {
					return "login_page";
				}
			}
			else {
				return "redirect:/logoutmain";
			}
		}
	@PostMapping("/admitnewstudentadm")
	public String askid(@ModelAttribute Student student, Model model) {
		System.out.println("Asking Id request : " + student);
		Student check= applyService.CheckRegnoif(student.getRegno());
		if (check != null) {
			model.addAttribute("errorMessage", "Reg no is alreaty existing");
			model.addAttribute("checkregnoifrequestadm",new Student());
			return "get_regno_add_adm";
		} else {
			model.addAttribute("AddStudentRequest", new Student());
			model.addAttribute("regnopass",student.getRegno());
			return "add_student_adm";
			}
	}
	@GetMapping("/addingStudentadm")
	public String addingStudentpost(@ModelAttribute Student student,Model model) {
			UserssModel authenticated = applyService.authenticate(un,pa);
			System.out.println(authenticated);
			if(authenticated!=null) {	
				int acn = applyService.accesscheck(un);
				if(acn==3||acn==1) {
					model.addAttribute("checkregnoifrequestadm", student);
					return "get_regno_add_adm";
				    }
				else {	
					return "login_page";
				}
			}
			else {
				return "redirect:/logoutmain";
			}
		}
	@PostMapping("/addingStudentadm")
	public String updateuser(@ModelAttribute Student student, Model model) {
		System.out.println("userupdating request : " + student);
		Student d=applyService.saving(student);
		if(d!=null) {
			model.addAttribute("successmessage", "Student Detail added Successfull");
			model.addAttribute("checkregnoifrequestadm", student);
			return "confirmstudentaddesadm";
		}
		else {
		model.addAttribute("errorMessage", "Student detail Not added Please Try Again");
		model.addAttribute("AddStudentRequest", student);
		return "get_regno_add_adm";
	}}	
	
	@GetMapping("/logoutadmin")
	public String logout(HttpSession session,Model model) {
	    un = 0;
	    pa = null;
	    session.removeAttribute("un"); 
	    session.removeAttribute("pa");
		model.addAttribute("loginRequest", new UserssModel());
	    return "confirmlogout";
	}
}







