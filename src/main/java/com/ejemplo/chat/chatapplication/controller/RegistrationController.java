package controller;

import com.ejemplo.chat.model.User;

import com.ejemplo.chat.controller.model.user
import com.ejemplo.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    // Muestra el formulario de registro
    @GetMapping
    public String showRegistrationForm() {
        return "register"; // Retorna la vista de registro (register.html)
    }

    // Maneja el envío del formulario de registro
    @PostMapping
    public String registerUser(String phone_number, String username) {
        // Verificamos si ya existe un usuario con ese número o nombre
        if (userRepository.existsByPhoneNumber(phone_number) || userRepository.existsByUsername(username)) {
            return "redirect:/register?error=true"; // Si ya existe, redirige con error
        }

        // Guardamos el nuevo usuario en la base de datos
        User newUser = new User(phone_number, username);
        userRepository.save(newUser);

        return "redirect:/login"; // Redirige a la página de login o a donde corresponda
    }
}
