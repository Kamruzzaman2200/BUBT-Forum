package com.example.bubtforun;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bubtforun.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        binding.login.setOnClickListener(view -> {
            String email = binding.email.getText().toString().trim();
            String password = binding.password.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()) {
                binding.email.setText("Please fill all the fields");
            } else {
                binding.email.setText("");
                binding.password.setText("");
            }
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("In progress");
            progressDialog.show();

            FirebaseAuth
                    .getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        public void onSuccess(AuthResult authResult) {
                            progressDialog.cancel();
                            FirebaseFirestore
                                    .getInstance()
                                    .collection("Users")
                                    .document(FirebaseAuth.getInstance().getUid())
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                                            UserModel userModel = documentSnapshot.toObject(UserModel.class);
                                            new MySharedPreferences(LoginActivity.this).setMyData(userModel.getUserNumber());

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        public void onFailure(@NonNull Exception e) {
                                            progressDialog.cancel();
                                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.cancel();
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, NewsFeedActivity.class));
            finish();
        }
    }
}