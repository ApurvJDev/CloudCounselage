package com.example.cloudcounselageconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton btnLogout;
    FirebaseAuth mAuth;
    EditText edtSendMsg;
    ImageButton imgbtnSend;
    RecyclerView recyclerView;
    ArrayList<msgModel> msgModelArrayList;
    boolean isWaitingForResponse;
    msgAdapter msgsAdapter;

    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btnLogout = findViewById(R.id.btnLogout);
        edtSendMsg = findViewById(R.id.edtSendMsg);
        imgbtnSend = findViewById(R.id.imgbtnSend);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true); // to display messages from bottom
        recyclerView.setLayoutManager(linearLayoutManager);

        msgModelArrayList = new ArrayList<>();

        msgsAdapter = new msgAdapter(MainActivity.this, msgModelArrayList);
        recyclerView.setAdapter(msgsAdapter);

        imgbtnSend.setOnClickListener(v -> {
            String query = edtSendMsg.getText().toString();
            if(query.isEmpty()) {
                Toast.makeText(MainActivity.this, "Enter your query", Toast.LENGTH_SHORT).show();
            }
            edtSendMsg.setText("");
            //model class
            msgModel msg = new msgModel(query, true); // mark as sender
            msgModelArrayList.add(msg);
            msgsAdapter.notifyDataSetChanged();
            isWaitingForResponse = true;

            new Handler().postDelayed(() -> {
                String botReply = BotResponse.getBotReply(query);

                //add bot reply
                msgModel botMsg = new msgModel(botReply, false); // mark as receiver
                msgModelArrayList.add(botMsg);

                //notify adapter data has changed
                msgsAdapter.notifyDataSetChanged();
                isWaitingForResponse = false;

                //scroll rv to newly added message
                recyclerView.smoothScrollToPosition(msgModelArrayList.size() - 1);
            }, 2000);
        });
        //LOGOUT
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this,AuthActivity.class);
            startActivity(intent);
            finish();
        });
    }
}