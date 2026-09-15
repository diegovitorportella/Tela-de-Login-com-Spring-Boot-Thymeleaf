package com.example.SecureLoginPUC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordRecoveryEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nao-responda@secureloginpuc.com");
        message.setTo(to);
        message.setSubject("Recuperação de Senha - Sistema PUC");
        message.setText("Olá!\n\n"
                + "Recebemos uma solicitação de recuperação de senha para o seu e-mail.\n\n"
                + "Clique no link abaixo para redefinir sua credencial:\n"
                + "http://localhost:8080/resetpassword?token=simulacao123\n\n"
                + "Se você não solicitou essa mudança, apenas ignore esta mensagem.");

        mailSender.send(message);
    }
}