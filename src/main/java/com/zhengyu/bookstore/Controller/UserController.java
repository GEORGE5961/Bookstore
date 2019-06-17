package com.zhengyu.bookstore.Controller;

import com.zhengyu.bookstore.Lucene_indexer;
import com.zhengyu.bookstore.Lucene_searcher;
import com.zhengyu.bookstore.Neo4j;
import com.zhengyu.bookstore.Rocksdb;
import com.zhengyu.bookstore.model.User;
import com.zhengyu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
//@Cacheable(value="user-key")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	//@PostMapping("/addUser")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
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
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id)
    {
        return userService.getUserById(id);
    }

	@ResponseBody
	//@PostMapping("/deleteByUserId")
    @RequestMapping(value = "/deleteByUserId/{id}", method = RequestMethod.DELETE)
	public boolean deleteByUserId(@PathVariable("id") int id){
		return userService.deleteUser(id);
	}

	@ResponseBody
	//@PostMapping("/updateUser")
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public void updateUser(User user)
	{
		userService.updateUser(user);
	}

	@ResponseBody
	@PostMapping("/verify")
	public int hasLogin(@RequestParam(value="username")String username, @RequestParam("password")String password)
	{
		return userService.verifyLogin(username, password);
	}

	@ResponseBody
	@PostMapping("/signup")
	public int signUp(@RequestParam(value="username")String username, @RequestParam("password")String password)
	{
		return userService.signUp(username, password);
	}

    @ResponseBody
    @PostMapping("/neo")
    public void neo(@RequestParam(value="name")String name)
    {
        Neo4j.test(name);
    }

	@ResponseBody
	@PostMapping("/rocksdb")
	public void rocksdb()
	{
		try{Rocksdb.test();}catch (Exception e){};
	}

	@ResponseBody
	@PostMapping("/indexall")
	public void indexall()
	{
			Lucene_indexer.indexall();
	}

	@ResponseBody
	@PostMapping("/searchall")
	public void searchall()
	{
		Lucene_searcher.searchall();
	}

}
