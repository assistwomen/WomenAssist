package com.orange.womenassist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.womenassist.models.User;
import com.orange.womenassist.utilities.AlertUtility;
import com.orange.womenassist.utilities.FireBaseUtils;
import com.orange.womenassist.utilities.SecurityUtility;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ConnexionActivity extends AppCompatActivity {

    private static final String USER_COLLECTION = "Utilisateurs";
    private static final String USER_CREDENTIAL = "com.orange.womenassist";
    private Button btnConnexion;
    ImageView backBtn;
    CircularProgressIndicator indicator;
    private EditText edtPseudo, edtPassword;
    TextView password, tvtregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        //get UI Object
        btnConnexion = findViewById(R.id.btnConnexion);
        edtPseudo = findViewById(R.id.edtPseudo);
        edtPassword = findViewById(R.id.edtPassword);
        password = findViewById(R.id.password);
        tvtregister = findViewById(R.id.tvtregister);
        backBtn = findViewById(R.id.backBtn);
        indicator = findViewById(R.id.indicator);

        //Action button

        /* fonction de connexion */
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator.setVisibility(View.VISIBLE);
                        try {
                            if((edtPseudo.getText().toString()).isEmpty() || (edtPassword.getText().toString()).isEmpty() )
                            {
                                AlertUtility.simpleAlert(ConnexionActivity.this, "Erreur","Tous les champs sont obligatoirs.").show();
                                indicator.setVisibility(View.INVISIBLE);
                                return;
                            }
                            User user =  new User();
                            FireBaseUtils.getFireBaseReference(USER_COLLECTION)
                                    .whereEqualTo("pseudo", edtPseudo.getText().toString())
                                    .whereEqualTo("is_exist", true)
                                    .limit(1)
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                            if (task.isSuccessful()) {
                                                if(task.getResult().isEmpty())
                                                {
                                                    AlertUtility.simpleAlert(ConnexionActivity.this, "OUPS","Pseudo inexistant").show();
                                                    indicator.setVisibility(View.INVISIBLE);
                                                    return;
                                                }

                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    user.setPassword(document.getString("password"));
                                                    user.setPseudo(document.getString("pseudo"));
                                                    user.setRole(document.getString("role"));
                                                    user.setRole_association(document.getString("role_association"));
                                                    user.setNom_association(document.getString("nom_association"));
                                                    user.setCreate_at(document.getString("create_at"));
                                                    user.setId(document.getId());

                                                    try {
                                                        boolean result = SecurityUtility.validatePassword(edtPassword.getText().toString(),user.getPassword());
                                                        if(!result){
                                                            AlertUtility.simpleAlert(ConnexionActivity.this, "OUPS","Mot de passe incorrect").show();
                                                            indicator.setVisibility(View.INVISIBLE);
                                                            return;
                                                        }
                                                        storUserData(user);
                                                        Toast.makeText(ConnexionActivity.this, "Connecter avec succès", Toast.LENGTH_LONG).show();
                                                        startActivity(new Intent(ConnexionActivity.this, HomeActivity.class));
                                                        indicator.setVisibility(View.INVISIBLE);
                                                        return;

                                                    } catch (NoSuchAlgorithmException e) {
                                                        Log.w("Exception", "Algorithme introuvable");
                                                        AlertUtility.simpleAlert(ConnexionActivity.this, "OUPS","Une erreur s'est produite. A").show();
                                                        indicator.setVisibility(View.INVISIBLE);
                                                        return;
                                                    } catch (InvalidKeySpecException e) {
                                                        Log.w("Exception", "Erreur de la generation du mot de passe");
                                                        AlertUtility.simpleAlert(ConnexionActivity.this, "OUPS","Une erreur s'est produite. G").show();
                                                        indicator.setIndeterminate(false);
                                                        return;
                                                    }
                                                }

                                            } else {
                                                Log.d("failttogetuser", "get failed with ", task.getException());
                                                AlertUtility.simpleAlert(ConnexionActivity.this, "OUPS","pseudo inexistant.").show();
                                                indicator.setVisibility(View.INVISIBLE);
                                                return;
                                            }
                                        }
                                    });

                        } catch(Exception ex){
                            AlertUtility.simpleAlert(ConnexionActivity.this, "OUPS","Une erreur s'est produite. E").show();
                            Log.w("gdfsdsdfsdfsddsdfds", ex.toString());
                            indicator.setVisibility(View.INVISIBLE);
                            return;
                        }
                    }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ConnexionActivity.this, "En cours de développement", Toast.LENGTH_LONG).show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnexionActivity.this.onBackPressed();
                finish();
            }
        });

        tvtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConnexionActivity.this, RegisterMemberActivity.class));
                finish();
            }
        });
    }

    private void storUserData(User userDate)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(USER_CREDENTIAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(User.USER_ID, userDate.getId());
        editor.putString(User.USER_ROLE_, userDate.getRole());
        editor.putString(User.USER_ROLE_ASSOCIATION_PREFS, userDate.getRole_association());
        editor.putString(User.USER_NOM_ASSOCIATION, userDate.getNom_association());
        editor.putString(User.USER_PSEUDO, userDate.getPseudo());
        editor.putString(User.USER_CREATE_AT, userDate.getCreate_at());
        editor.commit();
    }

}
