package com.example.covid_19;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SendSmsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edSDT,edNoiDung;
    Button btnGui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lauout_send_sms);

        // Kiểm tra quyền SEND_SMS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.SEND_SMS }, 0);
        }
        edNoiDung = (EditText) findViewById(R.id.NoiDung);
        edSDT = (EditText) findViewById(R.id.SDT);
        btnGui = (Button) findViewById(R.id.Gui);

        edSDT.setEnabled(false);
        btnGui.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        sendSMS();
    }
    private void sendSMS() {
        String SDT = edSDT.getText().toString();
        String NoiDung = edNoiDung.getText().toString();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(SDT, null, NoiDung, null, null);
            Toast.makeText(getApplicationContext(), "Gửi thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SendSmsActivity.this, TrangChuActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Gửi thất bại", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}