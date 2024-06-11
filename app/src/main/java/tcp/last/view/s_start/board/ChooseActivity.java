package tcp.last.view.s_start.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import tcp.last.R;
import tcp.last.view.u_user.LoginActivity;


public class ChooseActivity extends AppCompatActivity {
    Button nuevo, viejo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_activity_choose);
        nuevo = findViewById(R.id.buttonStartNew);
        viejo = findViewById(R.id.buttonStartOld);

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this, BoardActivity.class));
                finish();
            }
        });
        viejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}