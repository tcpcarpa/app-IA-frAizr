package tcp.last.view.m_menu.frag;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import tcp.last.R;
import tcp.last.view.m_menu.MenuActivity;
import tcp.last.view.s_start.main.MainActivity;

public class LogOutFragment extends Fragment {
    Button logoutButton, logoutCancel;
    public LogOutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m_fragment_logout, container, false);
        logoutButton = v.findViewById(R.id.logoutButton);
        logoutCancel = v.findViewById(R.id.buttonLogOutCancel);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Esperamos.. \n volver a verte\n Pronto", Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(requireContext(), MainActivity.class));

            }
        });
        logoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Uuuuuuuishhhh!!!!!....\n CasiiiII", Toast.LENGTH_LONG).show();
                startActivity(new Intent(requireContext(), MenuActivity.class));

            }
        });
        return v;
    }
}