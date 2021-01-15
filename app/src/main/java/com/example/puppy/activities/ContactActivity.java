package com.example.puppy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.puppy.R;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
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
            String smtpHostServer = "smtp.mail.com";
            String emailAddress = etEmailAddress.getText().toString();
            String emailText = etEmailText.getText().toString();

            Properties prop = System.getProperties();
            prop.put("mail.smtp.host", smtpHostServer);

            Session session = Session.getInstance(prop, null);

            try {
                MimeMessage  msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("mymail@gmail.com"));
                InternetAddress[] addresses = {new InternetAddress(emailAddress)};
                msg.setRecipients(Message.RecipientType.TO, addresses);
                msg.setSubject("Pet App Test");
                msg.setSentDate(new Date());
                msg.setText(emailText);

                Transport.send(msg);
            }catch (MessagingException e){
                System.out.println("Exception sending the email");
                e.printStackTrace();
            }
        });
    }
}