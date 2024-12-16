package service;

import com.ejemplo.chat.chatapplication.model.User;
import com.ejemplo.chat.chatapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Verifica si el número de teléfono ya existe
    public boolean checkIfUserExistsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    // Verifica si el nombre de usuario ya existe
    public boolean checkIfUserExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Registra al usuario en la base de datos
    public void registerUser(String phoneNumber, String username) {
        User newUser = new User();
        newUser.setPhoneNumber(phoneNumber);
        newUser.setUsername(username);
        userRepository.save(newUser);
    }
}
