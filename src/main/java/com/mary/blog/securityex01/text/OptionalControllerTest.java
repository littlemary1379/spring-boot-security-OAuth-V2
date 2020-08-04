package com.mary.blog.securityex01.text;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mary.blog.securityex01.model.User;
import com.mary.blog.securityex01.repository.UserRepository;

@RestController
public class OptionalControllerTest {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/test/user/{id}")
	public User 옵셔널_유저찾기(@PathVariable int id) {
//		Optional<User> userOptinal = userRepository.findById(id);
//		User user;
//		if(userOptinal.isPresent()) {
//			user=userOptinal.get();
//		}else {
//			user=new User();
//		}
//		User user = userRepository.findById(id).orElseGet(()-> {

//			@Override
//			public User get() {
//				System.out.println("orElseGet : 빈 객체");
	//			return new User();
//			}
	//	});
		
		User user = userRepository.findById(id)
				.orElseThrow(()-> {
				
						return new NullPointerException("값이 없습니다,.");
		
				});
		
		
		return user;
	}
}
