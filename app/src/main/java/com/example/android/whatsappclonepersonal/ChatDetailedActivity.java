package com.example.android.whatsappclonepersonal;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android.whatsappclonepersonal.Adapters.ChatAdapter;
import com.example.android.whatsappclonepersonal.Models.MessageModel;
import com.example.android.whatsappclonepersonal.databinding.ActivityChatDetailedBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailedActivity extends AppCompatActivity {
    ActivityChatDetailedBinding binding;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        final String senderId=mAuth.getUid();
        String receiveId=getIntent().getStringExtra("userId");
        String userName=getIntent().getStringExtra("userName");
        String profilePic=getIntent().getStringExtra("profilePic");
        binding.userNaam.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.avatar).into(binding.profileImage);
        binding.leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(ChatDetailedActivity.this,MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        final ArrayList<MessageModel>messageModels=new ArrayList<>();
        final ChatAdapter chatAdapter =new ChatAdapter(messageModels,this);

        binding.chatRecyclerView.setAdapter(chatAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.chatRecyclerView.setLayoutManager(layoutManager);
        final String senderRoom= senderId+receiveId;
        final String receiverRoom=receiveId+senderId;
        firebaseDatabase.getReference().child("chats")
                        .child(senderRoom)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        messageModels.clear();
                                        for(DataSnapshot snapshot1 :snapshot.getChildren())

                                        {
                                            MessageModel model= snapshot1.getValue(MessageModel.class);
                                            model.setMessageID(snapshot.getKey());
                                            messageModels.add(model);

                                        }
                                        chatAdapter.notifyDataSetChanged();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String message=  binding.etMessage.getText().toString();
               final MessageModel model=new MessageModel(senderId,message);
               model.setTimestamp(new Date().getTime());
               binding.etMessage.setText(" ");
               firebaseDatabase.getReference().child("chats")
                       .child(senderRoom)
                       .push()
                       .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {
                               firebaseDatabase.getReference().child("chats")
                                       .child(receiverRoom)
                                       .push()
                                       .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                           @Override
                                           public void onSuccess(Void aVoid) {

                                           }
                                       });

                           }
                       });

            }
        });




    }
}