//package org.institutsaintjean.gestionbancaire.mail;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//
//@CrossOrigin("*")
//@Controller
//@RequestMapping("api/car/mail")
//public class SimpleEmailController {
//
//    @Autowired
//    public JavaMailSender emailSender;
//
//    @ResponseBody
//    @RequestMapping("sendEmail")
//    public String sendSimpleEmail(@RequestBody Mail message) throws Exception{
//
//        // Create a Simple MailMessage.
////
////        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
////        simpleMailMessage.setTo(message.getEmailReceiver());
////        simpleMailMessage.setSubject(message.getTitle());
////        simpleMailMessage.setText(message.getContent());
//
//
//
//        MimeMessage messa = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(messa);
//
//        helper.setSubject("This is an HTML email");
//        helper.setFrom("carexperts075@gmail.com");
//        helper.setTo(message.getEmailReceiver());
//
//        boolean html = true;
//
//        helper.setText("<b>Hey guys</b>,<br><i>Welcome to my new home</i><br> <h2>Exemple de titre</h2><br><h3 style=\"color: blue;\">salut brice!</h3>", html);
//        // Send Message!
//        this.emailSender.send(messa);
//
//        return "Email envoyé!";
//
//    }
//
//
//
//}
