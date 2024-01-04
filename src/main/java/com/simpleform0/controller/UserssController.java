package com.simpleform0.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpleform0.model.Applications;
import com.simpleform0.model.Reviews;
import com.simpleform0.model.UserssModel;
import com.simpleform0.service.UserssService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserssController implements ErrorController{
	private final UserssService userssService;

	@Autowired
	public UserssController(UserssService userssService) {
		this.userssService = userssService;
	}
	int un;
	String pa;	
	@GetMapping("/")
	public String index(@ModelAttribute Applications application,Model model) {
		model.addAttribute("successmessage", "Application Succesfully Submitted");
		int rc=userssService.getreviewscount();
		double r=userssService.getreviewsaverage();
        double ra = Math.round(r * 10.0) / 10.0;
		model.addAttribute("reviewcount", rc);
		model.addAttribute("reviewavg", ra);

		
		return "index";
	}
	@GetMapping("/adduser")
	public String getAdduserpage(@ModelAttribute UserssModel usersModel,Model model) {
		UserssModel authenticated = userssService.authenticate(un,pa);
		if (authenticated != null) {
		model.addAttribute("AdduserRequest", new UserssModel());
		return "add_user";
		} else {
			return "login_page";
		}
	}
	@PostMapping("/adduser")
	public String adduser(@ModelAttribute UserssModel userssModel, Model model) {
		System.out.println("useradding request : " + userssModel);
		UserssModel AddedUser = userssService.AddingUser(userssModel.getPersonalid(), userssModel.getUsername(),
				userssModel.getPassword(), userssModel.getAccess());
		if (AddedUser == null) {
			model.addAttribute("errorMessage", "Sorry...!! this Personal Id Already Existing");
			model.addAttribute("AdduserRequest", userssModel);
			return "add_user";
		}
		model.addAttribute("succsessmessage", "Users Details Addes Successfully");
		model.addAttribute("AdduserRequest", userssModel);
		return "redirect:/adduser";
	}
	
	@GetMapping("/login")
	public String getloginPage(Model model) {
		model.addAttribute("loginRequest", new UserssModel());
		return "login_page";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute UserssModel usersModel, Model model,HttpSession session) {
		System.out.println("login request : " + usersModel);
		if(usersModel.getPersonalid()>0 && usersModel.getPersonalid()<=2147483646)
		{
		UserssModel authenticated = userssService.authenticate(usersModel.getPersonalid(), usersModel.getPassword());
		if(authenticated!=null) {
			un=usersModel.getPersonalid();
			pa=usersModel.getPassword();
		}
		if (authenticated != null) {
			session.setAttribute("un", usersModel.getPersonalid());
			session.setAttribute("pa", usersModel.getPassword());
			
			int acn = userssService.accesscheck(usersModel.getPersonalid());
			if(acn==4) {
				return "View_page";
			}
			else if(acn==2) {
				model.addAttribute("staffunpa", usersModel);
				return "redirect:/staffloginunpa";
			}
			else if(acn==3) {
				model.addAttribute("admunpa", usersModel);
				return "redirect:/admlogin";
			}
			else {
				model.addAttribute("loginRequest", usersModel);
				return "login_page";
			}}
			else {
			model.addAttribute("errorMessage", "Incorrect PersonalId or Password");
			model.addAttribute("loginRequest", usersModel);
			return "login_page";
		}}
		else {
			model.addAttribute("errorMessage", "Incorrect PersonalId or Password");
			model.addAttribute("loginRequest", usersModel);
			return "login_page";
		}
	}
	
	@GetMapping("/stafflogin")
    public String StaffLogin(UserssModel userssModel) {
        return "redirect:/staffloginunpa";
    }
	@GetMapping("/askid")
	public String getaskid(@ModelAttribute UserssModel usersModel,Model model) {
		UserssModel authenticated = userssService.authenticate(un,pa);
		if (authenticated != null) {
			model.addAttribute("editRequest", new UserssModel());
			return "ask_pid";
		} else {
			return "login_page";
		}
	}
	@PostMapping("/askid")
	public String askid(@ModelAttribute UserssModel usersModel, Model model) {
		System.out.println("Asking Id request : " + usersModel);
		Optional<UserssModel> authenticatedd= userssService.Askid(usersModel.getPersonalid());
		if (authenticatedd == null) {
			model.addAttribute("errorMessage", "Incorrect PersonalId");
			model.addAttribute("editRequest", usersModel);
			return "ask_pid";
		} else {
			model.addAttribute("updateuser", new UserssModel());
			UserssModel ad=userssService.Edit(usersModel.getPersonalid());
			model.addAttribute("getpid",ad.getPersonalid());
			model.addAttribute("getuname", ad.getUsername());
			model.addAttribute("getpassword", ad.getPassword());
			model.addAttribute("getaccess", ad.getAccess());
			return "Edit_user_page";}
	}
	
	@PostMapping("/updateuser")
	public String updateuser(@ModelAttribute UserssModel usm, Model model) {
		System.out.println("userupdating request : " + usm);
		boolean UUD=userssService.Update(usm.getPersonalid(),usm.getUsername(),usm.getPassword(),usm.getAccess());
		if(UUD) {
			model.addAttribute("successmessage", "Update Successfull");
			model.addAttribute("editRequest", usm);
			return "ask_pid";
		}
		else {
		model.addAttribute("errorMessage", "Data Not Updated Please Try Again");
		model.addAttribute("updateuser", usm);
		return "Edit_user_page";
	}}		
	@GetMapping("/askpidfordelete")
	public String getaskidfordelete(@ModelAttribute UserssModel usersModel,Model model) {
		UserssModel authenticated = userssService.authenticate(un,pa);
		if (authenticated != null) {
			model.addAttribute("deleteRequest", new UserssModel());
			return "ask_pidfordelete";
		} else {
			return "login_page";
		}
	}
	@PostMapping("/askpidfordelete")
	public String askidfordelete(@ModelAttribute UserssModel usersModel, Model model) {
		System.out.println("Asking Id request : " + usersModel);
		Optional<UserssModel> authenticatedd= userssService.Askid(usersModel.getPersonalid());
		if (authenticatedd == null) {
			model.addAttribute("errorMessage", "Incorrect PersonalId");
			model.addAttribute("deleteRequest", usersModel);
			return "ask_pidfordelete";
		} else {
			model.addAttribute("deleteuser", new UserssModel());
			UserssModel ad=userssService.Edit(usersModel.getPersonalid());
			model.addAttribute("getpid",ad.getPersonalid());
			model.addAttribute("getuname", ad.getUsername());
			model.addAttribute("getpassword", ad.getPassword());
			model.addAttribute("getaccess", ad.getAccess());
			return "delete_user_page";}}
	@PostMapping("/deleteuser")
	public String deleteuser(@ModelAttribute UserssModel usm, Model model) {
		System.out.println("userupdating request : " + usm);
		System.out.println(usm.getPersonalid());
		boolean UUD=userssService.delete(usm.getPersonalid());
		if(UUD) {
			model.addAttribute("successmessage", " User Detail Delete Successfull");
			model.addAttribute("deleteRequest", usm);
			return "ask_pidfordelete";
		}
		else {
		model.addAttribute("errorMessage", "details was not Deleted Please Try Again");
		model.addAttribute("deleteRequest", usm);
		return "ask_pidfordelete";
	}}
	@GetMapping("/showall")
	public String getalluser(@ModelAttribute UserssModel usm,Model model ) {	
		UserssModel authenticated = userssService.authenticate(un,pa);
		if (authenticated != null) {
			List<UserssModel> list = userssService.getAlluser();
			if(list==null) {
				model.addAttribute("errorMessage", "There is no data ");
				model.addAttribute("ShowalluserRequest", list);
				}
			else {
				model.addAttribute("ShowalluserRequest", list);}
		System.out.println(list);
			return "show_all_user_details";
		} else {
			return "login_page";
		}
	}	
	@GetMapping("/allreviews")
	public String getAllrevoews(@ModelAttribute Reviews application,Model model) {
		List<Reviews> l=userssService.getallReviews();
		System.out.println(l);
		model.addAttribute("Alreview", l);
		return "review_page";
	}
	@GetMapping("/writereview")
	public String WriteReview(Model model) {
		model.addAttribute("wrireReviewreq", new Reviews());
		return "write_review";
	}
	
	@PostMapping("/writereview")
	public String postreview(@ModelAttribute Reviews review, Model model) {
		int c=userssService.saveReview(review);
		if(c==1) {
			model.addAttribute("succsessmessage", "Your Review Submitted ");
			model.addAttribute("wrireReviewreq", new Reviews());
				return "write_review";}
		else if(c==2) {
			model.addAttribute("errorMessage", "Your already Reviewed... in this Mail id");
			model.addAttribute("wrireReviewreq", new Reviews());
			return "write_review";
		}
		else {
			model.addAttribute("errorMessage", "Something Went Wronng Please Try Again ");
			model.addAttribute("wrireReviewreq", new Reviews());
			return "write_review";
		}
	}
	
	
	
	@GetMapping("/logout")
	public String logout() {
	un=0;
	pa=null;
	System.out.println("un login "+un);
	System.out.println("pa login "+pa);
	return "redirect:/logoutmain";
	}
	@GetMapping("/logoutadm")
	public String logoutad() {
	un=0;
	pa=null;
	System.out.println("un login "+un);
	System.out.println("pa login "+pa);
	return "redirect:/logoutadmin";
	}
}