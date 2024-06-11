package tcp.last.view.m_menu.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tcp.last.R;
import tcp.last.view.p_perfil.PerfilDataActivity;


public class PerfilFragment extends Fragment {
    private Button bt_edit;
    private TextView tname, ttelf, tmail;
    ImageView imageView;

    public PerfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m_fragment_perfil, container, false);

        tname = v.findViewById(R.id.PerfilFragNom);
        tmail = v.findViewById(R.id.perfilFragEdad);
        ttelf = v.findViewById(R.id.perfilFragTelf);
        imageView = v.findViewById(R.id.imagePerfil);
        bt_edit = v.findViewById(R.id.buttonProfileEdit);

        showData();
        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PerfilDataActivity.class));
            }
        });
        return v;
    }


    public void showData() {
        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
        String img = String.valueOf(u.getPhotoUrl());
        tname.setText(u.getDisplayName());
        tmail.setText(u.getEmail());
        //ttelf.setText(u.getPhoneNumber());
        //imageView.setImageResource(Integer.parseInt(img));

    }

}