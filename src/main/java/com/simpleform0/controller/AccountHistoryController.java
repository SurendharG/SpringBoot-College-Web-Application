package com.simpleform0.controller;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.simpleform0.model.AccountHistory;
import com.simpleform0.model.Applications;
import com.simpleform0.model.Student;
import com.simpleform0.model.UserssModel;
import com.simpleform0.service.AccountService;
import jakarta.servlet.http.HttpSession;
@Controller
public class AccountHistoryController {
	private final AccountService accService;
	@Autowired
	public AccountHistoryController(AccountService accService) {
		this.accService=accService;
	}
	Integer  un;
	String pa;
	@GetMapping("/acclogin")
	public String getaccess(Model model,HttpSession session) {
		 un = (Integer) session.getAttribute("un");
		 pa = (String) session.getAttribute("pa");
		UserssModel authenticated = accService.authenticate(un,pa);
		if(authenticated!=null) {	
			int acn = accService.accesscheck(un);
			if(acn==4||acn==1) {
				    model.addAttribute(model);
			    return "View_page_Acc";}
			else {
				return "login_page";		}
		}
		else {
			return "login_page";
		}
	}
	
	@GetMapping("/payfees")
	public String getStudent(@ModelAttribute Student student,Model model) {
			UserssModel authenticated = accService.authenticate(un,pa);
			System.out.println(authenticated);
			if(authenticated!=null) {	
				int acn = accService.accesscheck(un);
				if(acn==4) {
//					model.addAttribute("checkregnoifrequest", new Student());
					return "get_regno_forfees";
				    }
				else if(acn==1) {
					model.addAttribute("error","Soory Admin You Dont Have a Access To Pay fees");
					return "View_page_Acc";
				    }
				else {
					return "login_page";
				}
			}
			else {
				return "redirect:/logoutmain";
			}
		}
	
	@PostMapping("/get_regno_forfees")
	public String askid(@ModelAttribute Student student, Model model) {
		System.out.println("Asking Id request : " + student);
		Student check= accService.CheckRegnoif(student.getRegno());
		if (check == null) {
			model.addAttribute("errorMessage", "Reg no is Not existing");
			return "get_regno_forfees";
		} else {
			model.addAttribute("student", check);
//			model.addAttribute("regnof",student.getRegno());
//			model.addAttribute("namef",student.getName());
//			model.addAttribute("pending_feesf", student.getPending_fees());
//			model.addAttribute("coursef", student.getCourse());
			model.addAttribute("usernamef", un);
			model.addAttribute("FeesPayRequest", new AccountHistory());
			return "pay_fees_for_student";
			}
	}
	@PostMapping("/payfees")
	public String payingfees(@ModelAttribute AccountHistory ach, Model model) {
		AccountHistory h=accService.save(ach);
			System.out.println("1");
			accService.updatefees(ach.getAmount_paid(),ach.getName(),ach.getRegno());
			model.addAttribute("successmessage", "Fees Paid");
			return "confirmfeespaid";
	}
	@GetMapping("/accounthistory")
	public String Accounthistory(@ModelAttribute AccountHistory ach,Model model) {
			UserssModel authenticated = accService.authenticate(un,pa);
			System.out.println(authenticated);
			if(authenticated!=null) {	
				int acn = accService.accesscheck(un);
				if(acn==4||acn==1) {
					return "get_dates";
				    }
				else {
					return "login_page";
				}
			}
			else {
				return "redirect:/logoutmain";
			}
		}
	@PostMapping("/accounthistory")
	public String GetAccountHistory(@ModelAttribute AccountHistory ach, Model model) {
        String dateString = ach.getName();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date dates = sdf.parse(dateString, new ParsePosition(0));
	    List<AccountHistory> l=accService.GetHistory(ach.getDate(),dates);
	   if(l.isEmpty()) {
		   model.addAttribute("error","There is no transacation on that Dates");
			return "get_dates";
	   }
	   else {
	    model.addAttribute("Listes", l);
	    System.out.println(l);
	    return "Account_History";}
	}
	

	@GetMapping("/logoutacc")
	public String logout(HttpSession session,Model model) {
	    un = 0;
	    pa = null;
	    session.removeAttribute("un"); 
	    session.removeAttribute("pa");
		model.addAttribute("loginRequest", new UserssModel());
	    return "confirmlogout";
	}
}