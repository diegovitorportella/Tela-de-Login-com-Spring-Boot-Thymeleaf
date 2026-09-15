package com.example.SecureLoginPUC.controller;

import com.example.SecureLoginPUC.model.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecureLoginController {

    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userRequest", new UserRegisterRequest());
        return "register";
    }

    @PostMapping("/register")
public String processRegistration(@ModelAttribute("userRequest") UserRegisterRequest request, Model model) {
    System.out.println("--- DADOS RECEBIDOS NO CADASTRO ---");
    System.out.println("Nome: " + request.getNome());
    System.out.println("Email: " + request.getEmail());
    System.out.println("CPF: " + request.getCpf());
    System.out.println("Senha: " + request.getSenha());
    System.out.println("Confirmacao: " + request.getConfirmacaoSenha());

    // Validação 1: Nulos ou vazios
    if (request.getNome() == null || request.getNome().trim().isEmpty() ||
        request.getEmail() == null || request.getEmail().trim().isEmpty() ||
        request.getCpf() == null || request.getCpf().trim().isEmpty() ||
        request.getRg() == null || request.getRg().trim().isEmpty() ||
        request.getInstituicao() == null || request.getInstituicao().trim().isEmpty() ||
        request.getEndereco() == null || request.getEndereco().trim().isEmpty() ||
        request.getSenha() == null || request.getSenha().trim().isEmpty()) {

        System.out.println("FALHA: Algum campo obrigatório veio nulo ou vazio.");
        model.addAttribute("error", "Por favor, preencha todos os campos obrigatórios.");
        return "register";
    }

    // Validação 2: Senhas divergentes
    if (!request.getSenha().equals(request.getConfirmacaoSenha())) {
        System.out.println("FALHA: As senhas não conferem.");
        model.addAttribute("error", "As senhas não coincidem.");
        return "register";
    }

    // Validação 3: E-mail duplicado
    if (userDetailsManager.userExists(request.getEmail())) {
        System.out.println("FALHA: E-mail já cadastrado.");
        model.addAttribute("error", "Este e-mail já possui cadastro.");
        return "register";
    }

    // Criação do usuário
    UserDetails newUser = User.builder()
            .username(request.getEmail())
            .password(passwordEncoder.encode(request.getSenha()))
            .roles("USER")
            .build();

    userDetailsManager.createUser(newUser);
    System.out.println("SUCESSO: Usuário criado com sucesso!");

    return "redirect:/login?registered=true";
}

    @GetMapping("/recoverpassword")
    public String showRecoverPasswordForm() {
        return "recoverpassword";
    }

    @PostMapping("/recoverpassword")
    public String processRecovery(String email, Model model) {
        model.addAttribute("message", "Se o e-mail estiver correto, você receberá um link de recuperação.");
        return "recoverpassword";
    }
}