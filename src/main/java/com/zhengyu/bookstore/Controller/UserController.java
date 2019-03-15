package com.zhengyu.bookstore.Controller;

import com.zhengyu.bookstore.model.User;
import com.zhengyu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String password;
	private String role;
	private String intro;

	@Autowired
	private UserService userService;

	@ResponseBody
	@PostMapping("/addUser")
	public User addUser(User user)
	{
		return userService.insert(user);
	}

	@ResponseBody
	@GetMapping("/getAllUsers")
	public List<User> getAllUser()
	{
		return userService.getAllUsers();
	}

	@ResponseBody
	@PostMapping("/deleteByUserId")
	public boolean deleteByUserId(int userId){
		return userService.deleteUser(userId);
	}

}
