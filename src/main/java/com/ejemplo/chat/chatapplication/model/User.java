package model;

import model.User; // Ajuste en la importación del modelo User
import repository.UserRepository; // Ajuste en la importación del repositorio

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String registerUser(
            @RequestParam("phone_number") String phoneNumber,
            @RequestParam("username") String username,
            RedirectAttributes redirectAttributes) {

        // Verificamos si ya existe un usuario con ese número o nombre
        if (userRepository.existsByPhoneNumber(phoneNumber) || userRepository.existsByUsername(username)) {
            redirectAttributes.addFlashAttribute("error", "El usuario o número de teléfono ya existe.");
            return "redirect:/register"; // Redirige con un mensaje de error
        }

        // Guardamos el nuevo usuario en la base de datos
        User newUser = new User(phoneNumber, username);
        userRepository.save(newUser);

        return "redirect:/login"; // Redirige a la página de login o a donde corresponda
    }
}
