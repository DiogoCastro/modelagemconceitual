package com.diogoneves.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.diogoneves.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
