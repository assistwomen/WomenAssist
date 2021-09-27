package com.orange.womenassist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.orange.womenassist.models.User;
import com.orange.womenassist.utilities.FireBaseUtils;
import com.orange.womenassist.utilities.SecurityUtility;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ConnexionActivity extends AppCompatActivity {

    private Button btnConnexion;
    private EditText edtPseudo, edtPassword;
    private static FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        //get UI Object
        btnConnexion = findViewById(R.id.btnConnexion);
        edtPseudo = findViewById(R.id.edtPseudo);
        edtPassword = findViewById(R.id.edtPassword);

        //Action button

        /* fonction de connexion */
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = FireBaseUtils.signInAnonymously();
                    if( currentUser != null)
                    {
                        try {
                            FireBaseUtils.addUser(new User(edtPseudo.getText().toString(),
                                           SecurityUtility.generateStorngPasswordHash(edtPassword.getText().toString())),
                                    currentUser.getUid());
                        } catch (NoSuchAlgorithmException e) {
                            Log.w("Exception", "Algorithme introuvable");
                        } catch (InvalidKeySpecException e) {
                            Log.w("Exception", "Erreur de la generation du mot de passe");
                        }
                        Log.w("user", currentUser.getUid());
                    }
                    else{
                        Log.w("Exception", "Impossible ce créer l'utilisateur");
                    }
            }
        });
    }
}