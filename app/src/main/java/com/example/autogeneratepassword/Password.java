package com.example.autogeneratepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;

public class Password extends AppCompatActivity {

    int len;
    TextView textView;
    Button copyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        Bundle bundle = getIntent().getExtras();
        len = bundle.getInt("val");
        String pass = Generate(len);
        textView = findViewById(R.id.txtPassword);
        textView.setText(pass);
        copyBtn = findViewById(R.id.copyBtn);

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("EditText",textView.getText().toString());
                clipboard.setPrimaryClip(clipData);
                Toast.makeText(Password.this, "Password copied to Clipboard",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String Generate(int len){
        String allchar = "ABCDEFGHIKLMNOPQRSTVXYZabcdefghiklmnopqrstvxyz!@#$%^&*()";
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i=0;i<len;i++){
            int index = random.nextInt(allchar.length());
            sb.append(allchar.charAt(index));
        }
        return sb.toString();
    }
}