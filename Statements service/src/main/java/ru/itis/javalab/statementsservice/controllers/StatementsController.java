package ru.itis.javalab.statementsservice.controllers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.statementsservice.security.jwt.utils.JwtDecoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@RequiredArgsConstructor
@RestController
public class StatementsController {

    @Autowired
    private JwtDecoder jwtDecoder;

    @GetMapping("/statements")
    public ResponseEntity<?> getStatement(@RequestHeader("TOKEN") String token) {
        String names = jwtDecoder.getUserFromJwt(token).getFirstName() + jwtDecoder.getUserFromJwt(token).getLastName();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            try {
                channel.basicPublish("", "calls", null, names.getBytes());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return ResponseEntity.ok().build();
    }


}
