package me.micartey.blast.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.micartey.blast.bodies.MailBody;
import me.micartey.blast.mail.SmtpMailer;
import me.micartey.blast.mail.SmtpMessage;
import me.micartey.blast.responses.ErrorResponse;
import me.micartey.blast.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/mail/")
public class EmailController {

    @CrossOrigin
    @PostMapping("send")
    public ResponseEntity<Response> onSend(@RequestBody MailBody mailBody) {
        log.info("preparing...");

        try {
            new SmtpMessage(
                    new SmtpMailer(
                            mailBody.getHost(),
                            mailBody.getPort(),
                            mailBody.isStarttls()
                    ).createSession(
                            mailBody.getAuth().getMail(),
                            mailBody.getAuth().getPassword()
                    ),
                    mailBody.getAuth().getMail(),
                    mailBody.getMessage().getRecipient(),
                    mailBody.getMessage().getSubject(),
                    mailBody.getMessage().getText()
            ).send();
        } catch(MessagingException exception) {
            log.error("Something went wrong: " + exception);
            return ResponseEntity.internalServerError().body(new ErrorResponse(exception.getMessage()));
        }

        log.info("send!");

        return ResponseEntity.ok(null);
    }

}

