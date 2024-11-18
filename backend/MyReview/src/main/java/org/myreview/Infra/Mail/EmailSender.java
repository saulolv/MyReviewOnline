package org.myreview.Infra.Mail;

public interface EmailSender {
    void send(String to, String email);
}
