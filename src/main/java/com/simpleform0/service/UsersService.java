package com.simpleform0.service;
import org.springframework.stereotype.Service;
import com.simpleform0.Repositery.UsersRepository;
import com.simpleform0.model.UsersModel;
@Service
public class UsersService {
	private final UsersRepository usersRepository;
    public UsersService(UsersRepository userRepository) {
        this.usersRepository = userRepository;
    }
    public UsersModel registerUser(String login,String email, String password) {
        if (login != null && password != null) {
            UsersModel usersModel = new UsersModel();
            usersModel.setEmail(email);
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            return usersRepository.save(usersModel);
        }
        return null; 
    }
    public UsersModel authenticate(String login, String password) {
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}