package com.example.puppy.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.puppy.R;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactActivity extends AppCompatActivity {
    EditText etContactName;
    EditText etEmailAddress;
    EditText etEmailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.contact_activity_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etContactName = findViewById(R.id.etContactName);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etEmailText = findViewById(R.id.etEmailText);

        Button button = findViewById(R.id.btSend);
        button.setOnClickListener(v -> {
            new Thread(() -> {
                String smtpHostServer = "smtp.gmail.com";
                String emailAddress = etEmailAddress.getText().toString();
                String emailText = etEmailText.getText().toString();

                Properties props = System.getProperties();
                props.put("mail.smtp.host", smtpHostServer);

                Session session = Session.getInstance(props, null);
                sendMessage(session, emailAddress, "Email test", emailText);
            }).start();
        });
    }

    public void sendMessage(@NonNull Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-Type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "No Reply"));
            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", true));

            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail, false));

            Transport.send(msg);
            System.out.println("Message sent successfully");
            Toast.makeText(this, "Message sent successfully",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending message: " + e.getMessage());
        }
    }
}