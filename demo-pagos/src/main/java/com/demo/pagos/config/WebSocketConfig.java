package com.demo.pagos.config;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.demo.pagos.security.WebSocketPrincipal;
import com.demo.pagos.services.JwtService;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {

        @Autowired
    private JwtService jwtTokenProvider;

    /**
     * Configure message broker options.
     * @param config the message broker registry
     */
   @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * Register STOMP endpoints.
     * @param registry the STOMP endpoint registry
     */
 @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        String token = request.getHeaders().getFirst("Authorization");
                        if (token != null && token.startsWith("Bearer ")) {
                            token = token.substring(7);
                            String username = jwtTokenProvider.getUserNameFromToken(token);
                            if (username != null && jwtTokenProvider.validateToken(token)) {
                                return new WebSocketPrincipal(username);
                            }
                        }
                        return null;
                    }
                })
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
