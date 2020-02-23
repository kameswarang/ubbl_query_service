package ubbl.query_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ubbl.query_service.model.User;
import ubbl.query_service.repository.UserRepository;
import ubbl.query_service.service.interfaces.CustomUserService;

@Service
public class UserService implements UserDetailsService, CustomUserService {
    private UserRepository userRepo;
    private PasswordEncoder passEnc;
    
    @Autowired
    public UserService(UserRepository ur, PasswordEncoder pe) {
        this.userRepo = ur;
        this.passEnc = pe;
    }
    
    @Override
    public UserDetails loadUserByUsername(String u) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(u);
        if(user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User '" + u + "' not found");
    }
    
    @Override
    public void saveUser(User u) {
        u.setPassword(passEnc.encode(u.getPassword()));
        userRepo.save(u);
    }
}