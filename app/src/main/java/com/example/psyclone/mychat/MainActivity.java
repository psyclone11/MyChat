package com.example.psyclone.mychat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView)findViewById(R.id.textview);

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mUsersRef = mRootRef.child("users");
        DatabaseReference mMessagesRef = mRootRef.child("messages");

        mUsersRef.child("id-12345").setValue("Jirawatee");

        FriendlyMessage friendlyMessage = new FriendlyMessage("Hello World!", "Jirawatee");
        mMessagesRef.push().setValue(friendlyMessage);

        String key = mMessagesRef.push().getKey();
        HashMap<String, Object> postValues = new HashMap<>();
        postValues.put("username", "Jirawatee");
        postValues.put("text", "Hello World!");

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/messages/" + key, postValues);
        childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

        mRootRef.updateChildren(childUpdates);

        mMessagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text="";
                for(DataSnapshot messages:dataSnapshot.getChildren()){
                    FriendlyMessage message = messages.getValue(FriendlyMessage.class);
                    text += message.getUsername() + "-" + message.getText() + "\n";
                }
                //String value = dataSnapshot.getValue(String.class);
                textview.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textview.setText("Failed: " + databaseError.getMessage());
            }
        });
    }


}
