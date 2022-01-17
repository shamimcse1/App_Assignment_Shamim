package codercamp.com.app_assignment_shamim.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import codercamp.com.app_assignment_shamim.R;

public class LoginActivity extends AppCompatActivity {
    private RadioButton user, waiter;
    private MaterialButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.user);
        waiter = findViewById(R.id.waiter);
        login =findViewById(R.id.login_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.isChecked()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("key","user");
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "User", Toast.LENGTH_SHORT).show();

                }
                else if (waiter.isChecked()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("key","waiter");
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Select Waiter", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Please Select User Type", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}