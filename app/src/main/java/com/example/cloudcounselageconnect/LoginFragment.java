package com.example.cloudcounselageconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginFragment extends Fragment {
    EditText edtLoginUsername,edtLoginPass,edtLoginEmail;
    Button btnLogin;
    FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        edtLoginEmail = root.findViewById(R.id.edtLoginEmail);
        edtLoginUsername = root.findViewById(R.id.edtLoginUsername);
        edtLoginPass = root.findViewById(R.id.edtLoginPass);
        btnLogin = root.findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();



        btnLogin.setOnClickListener(v -> {

            String email = edtLoginEmail.getText().toString();
            String username = edtLoginUsername.getText().toString();
            String password = edtLoginPass.getText().toString();

            if(checkEmail(email) && checkUsername(username) && checkPassword(password)) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getActivity(), "Login successful",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getActivity(),MainActivity.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getActivity(), "Login fail" + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        return root;
    }
    public boolean checkEmail(String email) {
        //email field cannot be empty
        if(email.equals("")) {
            edtLoginEmail.setError("Email field cannot empty");
            return false;
        }
        return true;
    }
    public boolean checkUsername(String username) {
        //username field cannot be empty
        if(username.equals("")) {
            edtLoginUsername.setError("Username cannot be empty");
            return false;
        }
        return true;
    }
    public boolean checkPassword(String password) {
        //password field cannot be empty
        if(password.equals("")) {
            edtLoginPass.setError("Password cannot be empty");
            return false;
        }
        return true;
    }
}