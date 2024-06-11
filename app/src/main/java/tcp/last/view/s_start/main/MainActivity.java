package tcp.last.view.s_start.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

import tcp.last.R;
import tcp.last.utils.Constants;
import tcp.last.view.m_menu.MenuActivity;
import tcp.last.view.s_start.board.ChooseActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    startActivity(new Intent(getApplicationContext(), ChooseActivity.class));

                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                }
            }
        }, Constants.SPLASH_TIMER);
    }
}
