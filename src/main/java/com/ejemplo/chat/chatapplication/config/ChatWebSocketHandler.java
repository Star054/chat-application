package config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessions = new HashMap<>(); // Mapa de usuario -> sesión

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        String username = getUsernameFromSession(session); // Obtén el nombre de usuario de alguna manera
        sessions.put(username, session); // Asocia la sesión con el nombre de usuario
        System.out.println("Nuevo usuario conectado: " + username);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            String payload = message.getPayload();
            String[] parts = payload.split(":", 2);
            if (parts.length > 1) {
                String recipient = parts[0].trim(); // Nombre del destinatario
                String msg = parts[1].trim(); // El mensaje

                WebSocketSession recipientSession = sessions.get(recipient);
                if (recipientSession != null && recipientSession.isOpen()) {
                    recipientSession.sendMessage(new TextMessage(msg));
                } else {
                    session.sendMessage(new TextMessage("Usuario no disponible o desconectado"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        String username = getUsernameFromSession(session); // Obtén el nombre de usuario de la sesión
        sessions.remove(username); // Elimina la sesión de la lista
        System.out.println("Usuario desconectado: " + username);
    }

    // Método para obtener el nombre de usuario desde la sesión
    private String getUsernameFromSession(WebSocketSession session) {
        // Aquí puedes agregar lógica para obtener el nombre de usuario desde el token o handshake
        return session.getId(); // Placeholder: Usa el ID de la sesión por ahora
    }
}
