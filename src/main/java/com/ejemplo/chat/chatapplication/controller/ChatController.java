package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

    // Página de bienvenida donde el usuario ingresa su nombre
    @GetMapping("/")
    public String welcome() {
        return "bienvenida"; // Nombre del template de bienvenida
    }

    // Página del chat, pasando el nombre del usuario
    @GetMapping("/chat")
    public String chat(@RequestParam String username, Model model) {
        model.addAttribute("username", username); // Añadir el nombre del usuario al modelo
        return "chat"; // Nombre del template del chat
    }
}
