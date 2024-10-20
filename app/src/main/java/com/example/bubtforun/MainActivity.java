package com.example.bubtforun;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bubtforun.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        binding.goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });


        binding.createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.name.getText().toString();
                String number = binding.number.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();
                if (name.isEmpty() || number.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    binding.email.setText("Please fill all the fields");
                } else {
                    binding.email.setText("");
                    binding.name.setText("");
                    binding.number.setText("");
                    binding.email.setText("");
                    binding.password.setText("");
                }

                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Please wait...");
                progressDialog.setMessage("Creating Account");
                progressDialog.show();

                FirebaseAuth
                        .getInstance()
                        .createUserWithEmailAndPassword(email.trim(), password.trim())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            public void onSuccess(AuthResult authResult) {
                                UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();
                                FirebaseAuth.getInstance().getCurrentUser().updateProfile(userProfileChangeRequest);
                                new MySharedPreferences(MainActivity.this).setMyData(number);
                                UserModel userModel = new UserModel();
                                userModel.setUsername(name);
                                userModel.setUserNumber(number);
                                userModel.setUserEmail(email);

                                FirebaseFirestore
                                        .getInstance()
                                        .collection("Users")
                                        .document(FirebaseAuth.getInstance().getUid())
                                        .set(userModel);
                                reset();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            public void onFailure(Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }
    public void reset() {
        progressDialog.cancel();
        Toast.makeText(this, "Account Created Login Please", Toast.LENGTH_SHORT).show();

    }
}