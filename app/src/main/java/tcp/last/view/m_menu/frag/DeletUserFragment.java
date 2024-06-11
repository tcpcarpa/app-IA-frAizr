package tcp.last.view.m_menu.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import tcp.last.R;
import tcp.last.view.m_menu.MenuActivity;
import tcp.last.view.s_start.main.MainActivity;


public class DeletUserFragment extends Fragment {
    Button bt_deleteOk, bt_deleteCancel;

    public DeletUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m_fragment_delet_user, container, false);
        bt_deleteCancel = v.findViewById(R.id.buttonDeleteUserCancel);
        bt_deleteOk = v.findViewById(R.id.buttonDeleteUserOk);
        bt_deleteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Bien! \n Sigues con Nosotros", Toast.LENGTH_LONG).show();
                startActivity(new Intent(requireContext(), MenuActivity.class));
            }
        });
        bt_deleteOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Lo intentamos oTra vez?", Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().getCurrentUser().delete();
                startActivity(new Intent(requireContext(), MainActivity.class));

            }
        });

        return v;
    }
}