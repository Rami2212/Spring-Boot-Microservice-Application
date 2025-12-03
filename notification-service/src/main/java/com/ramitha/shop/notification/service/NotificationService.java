package com.ramitha.shop.notification.service;

import com.ramitha.shop.order.event.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received OrderPlacedEvent for order: {}", orderPlacedEvent);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("shop@gmail.com");
            messageHelper.setTo(orderPlacedEvent.getEmail().toString());
            messageHelper.setSubject(String.format("Order Placed - %s", orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
                            <html>
                            <body>
                                <h1>Thank you for your order!</h1>
                                <p> Hi %s %s</p>
                                <p>Your order with order number <strong>%s</strong> has been successfully placed.</p>
                                <p>We will notify you once your order is shipped.</p>
                                <br/>
                                <p>Best regards,</p>
                                <p>The Shop Team</p>
                            </body>
                            </html>
                            """,
                    orderPlacedEvent.getFirstName().toString(),
                    orderPlacedEvent.getLastName().toString(),
                    orderPlacedEvent.getOrderNumber()));
        };

        try {
            javaMailSender.send(messagePreparator);
            log.info("Notification email sent to {}", orderPlacedEvent.getEmail());
        } catch (MailException e) {
            log.error("Failed to send notification email to {}", orderPlacedEvent.getEmail(), e);
            throw new RuntimeException("Failed to send notification email to " + orderPlacedEvent.getEmail(), e);
        }
    }
}
