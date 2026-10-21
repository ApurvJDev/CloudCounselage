package com.example.cloudcounselageconnect;

import static android.content.ContentValues.TAG;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;


public class AuthActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore db;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;
    ImageView ivGoogleRegister, ivTwitterRegister;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_GOOGLE_SIGN_IN = 9001;
    public static final String USER = "Users";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();

        // below line gets done automatically
        //FacebookSdk.sdkInitialize(getApplicationContext());

        // twitter
        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");
        // Localize to French.
        provider.addCustomParameter("lang", "en");

        ivGoogleRegister = findViewById(R.id.ivGoogleRegister);
        ivTwitterRegister = findViewById(R.id.ivTwitterRegister);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // twitter login
        ivTwitterRegister.setOnClickListener(v -> {
            Task<AuthResult> pendingResultTask = mAuth.getPendingAuthResult();
            if (pendingResultTask != null) {
                // There's something already here! Finish the sign-in for your user.
                pendingResultTask
                        .addOnSuccessListener(
                                authResult -> {
                                    // User is signed in.
                                    // IdP data available in
                                    // authResult.getAdditionalUserInfo().getProfile().
                                    // The OAuth access token can also be retrieved:
                                    // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                                    // The OAuth secret can be retrieved by calling:
                                    // ((OAuthCredential)authResult.getCredential()).getSecret().
                                    Toast.makeText(AuthActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

                                    user = mAuth.getCurrentUser();
                                    assert user != null;
                                    String email = user.getEmail();
                                    String username = user.getDisplayName();

                                    HelperClass usr = new HelperClass();
                                    db = FirebaseFirestore.getInstance();
                                    usr.setUsername(username);
                                    usr.setEmail(email);
                                    assert email != null;
                                    db.collection(USER).document(email).set(usr)
                                            .addOnSuccessListener(unused -> Toast.makeText(AuthActivity.this,"User added",Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(AuthActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show());

                                    Intent intent = new Intent(AuthActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                })
                        .addOnFailureListener(
                                e -> {
                                    // Handle failure.
                                    Toast.makeText(AuthActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                });
            } else {
                // There's no pending result so you need to start the sign-in flow.
                // See below.
                mAuth
                        .startActivityForSignInWithProvider(AuthActivity.this, provider.build())
                        .addOnSuccessListener(
                                authResult -> {
                                    Toast.makeText(AuthActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AuthActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                })
                        .addOnFailureListener(
                                e -> {
                                    // Handle failure.
                                    Toast.makeText(AuthActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                });
            }
        });




        ivGoogleRegister.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
        });
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPagerAdapter = new ViewPagerAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        user = mAuth.getCurrentUser();
        if(user != null) {
            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        SignupFragment signupFragment = (SignupFragment) getSupportFragmentManager().findFragmentById(R.id.SignupFragment);

        // Pass the activity result back to the Facebook SDK
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                handleGoogleAccessToken(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }    }

    private void handleGoogleAccessToken(GoogleSignInAccount account) {
        Log.d(TAG, "handleGoogleAccessToken:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        user = mAuth.getCurrentUser();
                        // adding facebook credentials in firebase database
                        //assert user != null;
                        //String email = user.getEmail();
                        //Log.d("email","THIS IS MY EMAIL" + email);
                        assert user != null;
                        String username = user.getDisplayName();
                        String email = username + "@gmail.com";

                        HelperClass usr = new HelperClass();
                        db = FirebaseFirestore.getInstance();

                        usr.setEmail(email);
                        usr.setUsername(username);

                        db.collection(USER).document(email).set(usr).
                                addOnSuccessListener(unused -> Toast.makeText(AuthActivity.this, "User added!",
                                        Toast.LENGTH_SHORT).show()).
                                addOnFailureListener(e -> Toast.makeText(AuthActivity.this, e.toString(),
                                        Toast.LENGTH_SHORT).show());
                        Log.d("Test", "Sign Up successful");
                        Toast.makeText(AuthActivity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();

                        // changing activity

                        if(user != null) {
                            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(AuthActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
