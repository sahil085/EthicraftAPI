package com.iskcon.EthicraftAPI.securityservices;


import com.iskcon.EthicraftAPI.domain.User;
import com.iskcon.EthicraftAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author kamal berriga
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public User find(String userName) {
		return userRepository.findByEmail(userName);
	}

	public User find(Long id) {
		return userRepository.getOne(id);
	}
}
