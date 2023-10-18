package com.example.cloudcounselageconnect;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Objects;


public class SignupFragment extends Fragment {
    Button signup;
//    ImageView ivFacebookRegister, ivTwitterRegister, ivMicrosoftRegister;
    EditText edtUsername, edtSignupEmail,edtSignupPass,edtSignupConfirmPass;
    CallbackManager mCallbackManager;
    AuthActivity authActivity;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore db  = FirebaseFirestore.getInstance();
    public static final String USER = "Users";


    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_signup, container, false);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        authActivity = (AuthActivity) getActivity();
        // to avoid unexpected callback manager error
        mCallbackManager = CallbackManager.Factory.create();
//        authActivity.passActivityResultToFragment(reque);

        signup = root.findViewById(R.id.btnSignup);
        edtUsername = root.findViewById(R.id.edtUsername);
        edtSignupEmail = root.findViewById(R.id.edtSignupEmail);
        edtSignupPass = root.findViewById(R.id.edtSignupPass);
        edtSignupConfirmPass = root.findViewById(R.id.edtSignupConfirmPass);
//        ivFacebookRegister = root.findViewById(R.id.ivFacebookRegister);

        // facebook login
//        ivFacebookRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginManager.getInstance().logInWithReadPermissions(getActivity() , Arrays.asList("email", "username"));
//                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
//                        handleFacebookAccessToken(loginResult.getAccessToken());
//                    }
//                    @Override
//                    public void onCancel() {
//                        Log.d(TAG, "facebook:onCancel");
//                    }
//                    @Override
//                    public void onError(@NonNull FacebookException error) {
//                        Log.d(TAG, "facebook:onError", error);
//                    }
//                });
//            }
//        });


        AuthActivity authActivity = (AuthActivity) getActivity();
        assert authActivity != null;
        TabLayout tabLayout = authActivity.findViewById(R.id.tabLayout);

        signup.setOnClickListener(v -> {
            String email = String.valueOf(edtSignupEmail.getText());
            String password = String.valueOf(edtSignupPass.getText());
            String confirmPass = String.valueOf(edtSignupConfirmPass.getText());
            String username = String.valueOf(edtUsername.getText());

            if(checkEmail(email) && checkPassword(password,confirmPass) && checkUsername(username)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Authentication successful",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getActivity(), "Authentication failed "+ Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(authResult -> {

                            HelperClass usr = new HelperClass();

                            usr.setEmail(email);
                            usr.setUsername(username);
                            usr.setPassword(password);
                            usr.setConfirmPass(confirmPass);

                            db.collection(USER).document(email).set(usr).
                                    addOnSuccessListener(unused -> Toast.makeText(authActivity, "User added!",
                                            Toast.LENGTH_SHORT).show()).
                                    addOnFailureListener(e -> Toast.makeText(authActivity, e.toString(),
                                            Toast.LENGTH_SHORT).show());
                            Log.d("Test", "Sign Up successful");
                            Toast.makeText(authActivity, "Sign Up successful", Toast.LENGTH_SHORT).show();
                        });
                //switches to login if registration is successful
                TabLayout.Tab tab = tabLayout.getTabAt(0);
                if(tab != null) {
                    tab.select();
                }
            }
        });
        return root;
    }
//    private void handleFacebookAccessToken(AccessToken accessToken) {
//        Log.d(TAG, "handleFacebookAccessToken:" + accessToken);
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
//        mAuth.signInWithCredential(credential).addOnCompleteListener(getActivity(), task -> {
//            if (task.isSuccessful()) {
//
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInWithCredential:success");
//
//                        user = mAuth.getCurrentUser();
//                        // adding facebook credentials in firebase database
//                        //String email = user.getEmail();
//                        String username = user.getDisplayName();
//                        String email = username + "@gmail.com";
//
//                        email.toLowerCase().trim();
//
//                        HelperClass usr = new HelperClass();
//                        db = FirebaseFirestore.getInstance();
//
//                        usr.setEmail(email);
//                        usr.setUsername(username);
//
//                        db.collection(USER).document(email).set(usr).
//                                addOnSuccessListener(unused -> Toast.makeText(getActivity(), "User added!",
//                                        Toast.LENGTH_SHORT).show()).
//                                addOnFailureListener(e -> Toast.makeText(getActivity(), e.toString(),
//                                        Toast.LENGTH_SHORT).show());
//                        Log.d("Test", "Sign Up successful");
//                        Toast.makeText(getActivity(), "Sign Up successful", Toast.LENGTH_SHORT).show();
//
//                        // changing activity
//
//                        if(user != null) {
//                            Intent intent = new Intent(getActivity(), MainActivity.class);
//                            startActivity(intent);
//                        }
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithCredential:failure", task.getException());
//                        Toast.makeText(getActivity(), "Authentication failed.",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }


    public boolean checkEmail(String email) {
        //email field empty
        if(email.equals("")) {
            edtUsername.setError("Username cannot be empty");
            return false;
        }
        return true;
    }
    public boolean checkPassword(String password, String confirmPass) {
        //password field empty
        if(password.equals("")) {
            edtSignupPass.setError("Password cannot be empty");
            return false;
        }
        // password and confirm pass not matching
        else if(!confirmPass.equals(password)) {
            edtSignupConfirmPass.setError("Password is not matching");
            return false;
        }
        else {
            return true;
        }
    }
    public boolean checkUsername(String username) {
        //username field empty
        if(username.equals("")) {
            edtUsername.setError("Username cannot be empty");
            return false;
        }
        return true;
    }
}

