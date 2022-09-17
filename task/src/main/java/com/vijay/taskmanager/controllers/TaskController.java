package com.vijay.taskmanager.controllers;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vijay.taskmanager.SendEmail;
import com.vijay.taskmanager.SendEmailOtp;
import com.vijay.taskmanager.dto.Task;
import com.vijay.taskmanager.dto.TaskUser;
import com.vijay.taskmanager.services.TaskService;
import com.vijay.taskmanager.users.services.UserService;

@Controller
@Scope(value = "request")
public class TaskController {
	@Autowired(required = true)
	private TaskService taskService;
	@Autowired(required = true)
	private UserService userService;

	@RequestMapping("/")
	public String listTasks(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return "signinPage";
		} else {
			List<Task> tasks = taskService.listTask(username);
			model.addAttribute("tasks", tasks);
			return "index";
		}
	}

	@RequestMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("username");
		return "signinPage";
	}

	@RequestMapping(value = "/signupPage")
	public String signupUser() {
		return "signupPage";
	}

	@RequestMapping(value = "/forgotPassPage")
	public String resetUser() {
		return "forgotPassPage";
	}

	@RequestMapping(value = "/signinPage")
	public String signinUser() {
		return "signinPage";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("taskUser") TaskUser taskUser, HttpSession session, Model model) {
		boolean result = userService.verifyUser(taskUser);
		if (result) {
			session.setAttribute("username", taskUser.getUsername());
			return "redirect:/";
		} else {
			model.addAttribute("error", "login failed");
			return "signinPage";
		}
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signupUser(@ModelAttribute("taskUser") TaskUser taskUser, HttpSession session, Model model) {
		boolean verifyUser = userService.verifyUserSignup(taskUser);
		if (verifyUser) {
			userService.saveUser(taskUser);
			model.addAttribute("success", "Signup Successful kindly login");
			return "signinPage";
		} else {
			model.addAttribute("error", "Username already exist");
			return "signupPage";
		}

	}

	@RequestMapping(value = "reset", method = RequestMethod.POST)
	public String resetUser(@ModelAttribute("taskUser") TaskUser taskUser, @RequestParam("otp") int otp, Model model,
			HttpSession session) {
		int sessionOtp = (Integer) session.getAttribute("sessionOtp");
		if (sessionOtp == otp) {
			String email = (String) session.getAttribute("email");
			TaskUser user = userService.getUser(email);
			user.setPassword(taskUser.getPassword());
			userService.updateUser(user);
			model.addAttribute("success", "Password reset successful kindly login");
			return "signinPage";
		} else {
			model.addAttribute("error", "Invalid OTP");
			return "forgotPassPage";
		}

	}

//	@RequestMapping(value = "reset", method = RequestMethod.POST)
//	public String resetUser(@ModelAttribute("taskUser") TaskUser taskUser,Model model) {
//		boolean verifyReset = userService.verifyUserReset(taskUser);
//		
//		if (verifyReset) {
//			userService.updateUser(taskUser);
//			model.addAttribute("success", "Password reset successful kindly login");
//			return "signinPage";
//		} else {
//			model.addAttribute("error", "Invalid OTP");
//			return "forgotPassPage";
//		}
//
//	}
	@RequestMapping("verifyemail")
	public @ResponseBody String verifyResetEmail(@RequestParam("email") String email) { // @ResponseBody used to tell
																						// not returning view
		String msg = "";
		boolean result = userService.verifyEmailReset(email);
		if (!result) {
			msg = "email not found";
		}
		return msg;
	}

	@RequestMapping(value = "taskcreate", method = RequestMethod.POST)
	public String newTask(@ModelAttribute("task") Task task, Model model) {
		UUID uuid = UUID.randomUUID();
		String uid = uuid.toString();
		int id = uid.hashCode();
		task.setId(id);
		taskService.saveTask(task);
		return "redirect:/";
	}

	@RequestMapping(value = "taskupdate", method = RequestMethod.POST)
	public String updateTask(@ModelAttribute("task") Task task, Model model) {
		taskService.updateTask(task);
		return "redirect:/";
	}

	@RequestMapping("delete")
	public ModelAndView deleteTask(@RequestParam("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		Task task = new Task();
		task.setId(id);
		taskService.deleteTask(task);
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}

	@Autowired
	private SendEmail sendEmailOtp;

	@RequestMapping("/sendotp")
	public String sendOtp(@ModelAttribute("taskUser") TaskUser taskUser, HttpSession session, Model model) {
		String email = taskUser.getEmail();
		session.setAttribute("email", email);
		System.out.println("Email Attribute: " + email);
		boolean result = userService.verifyEmailReset(email);
		if (result) {
			int otp = Integer.parseInt(new DecimalFormat("0000").format(new Random().nextInt(9999)));
			System.out.println("generated otp" + otp);
			sendEmailOtp.send(email, "Password Reset", "YOUR OTP: " + otp);
			session.setAttribute("sessionOtp", otp);
			model.addAttribute("success", "OTP send successful, check your mail inbox or spam ");
			return "resetPass";
		} else {
			model.addAttribute("error", "Invalid Email");
			return "forgotPassPage";
		}
	}
	

	/*
	 * public TaskService getTaskService() { return taskService; }
	 * 
	 * public void setTaskService(TaskService taskService) { this.taskService =
	 * taskService; }
	 * 
	 * public UserService getUserService() { return userService; }
	 * 
	 * public void setUserService(UserService userService) { this.userService =
	 * userService; }
	 */
}
