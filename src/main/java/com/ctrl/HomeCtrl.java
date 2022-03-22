package com.ctrl;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.URL;

import java.nio.charset.Charset;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.config.SpringRestClient;
import com.dao.UserDao;
import com.entity.Books;
import com.entity.User;
import com.entity.UserLogin;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;


@Controller

public class HomeCtrl {

	@Autowired
	ServletContext context;

	@Autowired
	UserDao userDao;

	@Autowired
	UserLogin userlogin;

	@Autowired
	User user;

	@Autowired
	Books book;



	@RequestMapping(value = "/home")
	public String home(Model m) {
		m.addAttribute("user", userlogin);
		return "home";
	}

	@RequestMapping("/register")
	public String register(Model m) {
		m.addAttribute("user", user);
		return "register";
	}

	@SuppressWarnings("unchecked")
	public void callAPI(Model m) throws Exception {
		URL seatURL = new URL("http://localhost:9090/books");
		// Return the JSON Response from the API
		BufferedReader br = new BufferedReader(new InputStreamReader(seatURL.openStream(), Charset.forName("UTF-8")));
		String readAPIResponse = " ";
		StringBuilder jsonString = new StringBuilder();
		while ((readAPIResponse = br.readLine()) != null) {
			jsonString.append(readAPIResponse);
		}
		JSONArray jsonArray = new JSONArray(jsonString.toString());
		@SuppressWarnings("rawtypes")
		List<LinkedTreeMap> yourList = new Gson().fromJson(jsonArray.toString(), ArrayList.class);
		List<Books> bk = new ArrayList<>();
		for (@SuppressWarnings("rawtypes")
		LinkedTreeMap l : yourList) {
			System.out.println(l.get("id"));
			System.out.println(l.get("name"));
			System.out.println(l.get("author"));
			Books bo = new Books();
			bo.setId(String.valueOf(String.valueOf(l.get("id"))));
			bo.setName(String.valueOf(String.valueOf(l.get("name"))));
			bo.setAuthor(String.valueOf(String.valueOf(l.get("author"))));
			bk.add(bo);
		}
		m.addAttribute("book", bk);
		System.out.println(bk);
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User u, Model m) {
		List<User> users = this.userDao.getAll();
		boolean flag = false;
		for (User usr : users) {
			if (usr.getUsername().equals(u.getUsername())) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			if (u.getName() != "" && u.getUsername() != "null" && u.getPassword() != "") {
				this.userDao.save(u);
				u.setUsername("");
				u.setName("");
				u.setPassword("");
				return "home";
			}
			return "register";
		} else {
			u.setUsername("");
			u.setName("");
			u.setPassword("");
			return "register";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") UserLogin us, Model m) throws Exception {

		List<User> users = this.userDao.getAll();
		boolean flag = false;
		for (User u : users) {
			if (us.getUsername().equals(u.getUsername()) && us.getPassword().equals(u.getPassword())) {
				flag = true;
				break;
			}
		}
		if (flag == true) {

		  callAPI(m);
			
			return "books";
		} else {
			us.setUsername("");
			us.setPassword("");
			return "home";
		}

	}

	@RequestMapping(value = "/deleteBook",method=RequestMethod.POST)
	public String deleteBook(Model m,@RequestParam long book_id) throws Exception {
		SpringRestClient client = new SpringRestClient();
		client.delete(book_id);
		System.out.println(book_id);
		callAPI(m);
		return "books";
	}
	@RequestMapping(value = "/updateBook",method=RequestMethod.POST)
	public String updateBook(HttpServletRequest req,Model m){
		return "updateBook";
	}
	
	@RequestMapping(value = "/updateBooks",method=RequestMethod.POST)
	public String updateBooks(HttpServletRequest req,Model m) throws Exception{
		SpringRestClient client = new SpringRestClient();
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		System.out.println(id);
		System.out.println(name);
		System.out.println(author);
        Books bk = new Books();
		bk.setId(id);
		bk.setName(name);
		bk.setAuthor(author);
		client.update(bk,id);
		callAPI(m);
		return "books";
	}

	
	@RequestMapping(value = "/addbooks",method=RequestMethod.POST)
	public String addBookDetails(Model m, HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		System.out.println(id);
		System.out.println(name);
		System.out.println(author);
        Books bk = new Books();
		bk.setId(id);
		bk.setName(name);
		bk.setAuthor(author);
		SpringRestClient client = new SpringRestClient();
		client.create(bk);
	    m.addAttribute(id,id);
		callAPI(m);
	   
		return "books";
	}

	@RequestMapping(path = "/showBook")
	public String showBook(Model m) throws Exception {
		callAPI(m);
		return "books";
	}

}
