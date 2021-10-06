package com.orange.womenassist;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.AuthResult;
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
import java.util.ArrayList;

public class RegisterMemberActivity extends AppCompatActivity {

    TextView tevConnexion, tevAide;
    private FirebaseAuth mAuth;
    ImageView backBtn;
    Button btnInscrire;
    EditText edtPseudo, edtConfirmPassword, edtPassword;
    CheckBox ckIsMembre;
    CircularProgressIndicator indicator;
    private static final String USER_COLLECTION = "Utilisateurs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_member);

        backBtn = findViewById(R.id.backBtn);
        tevConnexion = findViewById(R.id.tevConnexion);
        tevAide = findViewById(R.id.tevAide);
        btnInscrire = findViewById(R.id.btnInscrire);
        edtPseudo = findViewById(R.id.edtPseudo);
        edtPassword = findViewById(R.id.edtPassword);
        btnInscrire = findViewById(R.id.btnInscrire);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        ckIsMembre = findViewById(R.id.ckIsMembre);
        indicator = findViewById(R.id.indicator);
        mAuth = FirebaseAuth.getInstance();

        tevAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterMemberActivity.this, Aide.class));
            }
        });

        tevConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterMemberActivity.this, ConnexionActivity.class));
                finish();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterMemberActivity.this.onBackPressed();
                finish();
            }
        });

        btnInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterMemberActivity.this, ConnexionActivity.class));
                finish();
            }
        });

        /* fonction de creation de compte */
        btnInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator.setVisibility(View.VISIBLE);
                if ((edtConfirmPassword.getText().toString()).isEmpty() || (edtPassword.getText().toString()).isEmpty()) {
                    AlertUtility.simpleAlert(RegisterMemberActivity.this, "Erreur", "Tous les champs sont obligatoires.").show();
                    indicator.setVisibility(View.INVISIBLE);
                    return;
                }

                if (!(edtConfirmPassword.getText().toString().equals(edtPassword.getText().toString()))) {
                    AlertUtility.simpleAlert(RegisterMemberActivity.this, "Erreur", "Le mot de passe est différent de la confirmation.").show();
                    indicator.setVisibility(View.INVISIBLE);
                    return;
                }


                FireBaseUtils.getFireBaseAuth().signInAnonymously()
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FireBaseUtils.getFireBaseReference(USER_COLLECTION)
                                            .whereEqualTo("pseudo", edtPseudo.getText().toString())
                                            .whereEqualTo("is_exist", true)
                                            .limit(1)
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        if (!task.getResult().isEmpty()) {
                                                            AlertUtility.simpleAlert(RegisterMemberActivity.this, "OUPS", "Un compte avec ce pseudonyme existe déjà.").show();
                                                            indicator.setVisibility(View.INVISIBLE);
                                                            return;
                                                        }
                                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                                        FireBaseUtils.getFireBaseReference(USER_COLLECTION).document(currentUser.getUid())
                                                                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                        if (task.isSuccessful()) {
                                                                            DocumentSnapshot document = task.getResult();
                                                                            if (document.exists()) {
                                                                                AlertUtility.simpleAlert(RegisterMemberActivity.this, "OUPS", "Il semble qu'un utilisateur avec votre identifiant existe déjà, effacez les données de l'application puis recommencez.").show();
                                                                                Log.d("failttogetuser", "get failed with ", task.getException());
                                                                                indicator.setVisibility(View.INVISIBLE);
                                                                                return;
                                                                            } else {

                                                                                try {
                                                                                    User user = new User();
                                                                                    user.setAvatar("");
                                                                                    user.setMembre_association(new ArrayList<String>());
                                                                                    user.setNom_association("");
                                                                                    user.setRole_association("");
                                                                                    user.setPseudo(edtPseudo.getText().toString());
                                                                                    user.setPassword(SecurityUtility.generateStorngPasswordHash(edtPassword.getText().toString()));
                                                                                    user.setRole(User.getUserRoleMembre());

                                                                                    FireBaseUtils.getFireBaseReference(USER_COLLECTION).document(currentUser.getUid())
                                                                                            .set(user)
                                                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                @Override
                                                                                                public void onSuccess(Void unused) {
                                                                                                    if(ckIsMembre.isChecked()) {
                                                                                                        Intent intent = new Intent(RegisterMemberActivity.this, CompteAssociation.class);
                                                                                                        intent.putExtra(USER_COLLECTION,user);
                                                                                                        startActivity(intent);
                                                                                                        indicator.setVisibility(View.INVISIBLE);
                                                                                                        finish();
                                                                                                    } else {
                                                                                                        startActivity(new Intent(RegisterMemberActivity.this, ConnexionActivity.class));
                                                                                                        Toast.makeText(RegisterMemberActivity.this, "Votre compte a été créer avec succès", Toast.LENGTH_SHORT).show();
                                                                                                        indicator.setVisibility(View.INVISIBLE);
                                                                                                        finish();
                                                                                                    }
                                                                                                }
                                                                                            })
                                                                                            .addOnFailureListener(new OnFailureListener() {
                                                                                                @Override
                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                    Log.w("Exception", "Algorithme introuvable");
                                                                                                    AlertUtility.simpleAlert(RegisterMemberActivity.this, "OUPS", "Une erreur s'est produite lors de l'ajout du compte.").show();
                                                                                                    indicator.setVisibility(View.INVISIBLE);
                                                                                                    return;
                                                                                                }
                                                                                            });


                                                                                } catch (NoSuchAlgorithmException e) {
                                                                                    Log.w("Exception", "Algorithme introuvable");
                                                                                    AlertUtility.simpleAlert(RegisterMemberActivity.this, "OUPS", "Une exception sur l'algorithme du mot de passe s'est produite.").show();
                                                                                    indicator.setVisibility(View.INVISIBLE);
                                                                                    return;
                                                                                } catch (InvalidKeySpecException e) {
                                                                                    Log.w("Exception", "Erreur de la generation du mot de passe");
                                                                                    AlertUtility.simpleAlert(RegisterMemberActivity.this, "OUPS", "Une erreur sur le clé de chiffrement s'est produite.").show();
                                                                                    indicator.setVisibility(View.INVISIBLE);
                                                                                    return;
                                                                                } catch (Exception exception) {
                                                                                    AlertUtility.simpleAlert(RegisterMemberActivity.this, "OUPS", "Une exception s'est produite.").show();
                                                                                    indicator.setVisibility(View.INVISIBLE);
                                                                                    return;
                                                                                }
                                                                            }
                                                                        } else {
                                                                            AlertUtility.simpleAlert(RegisterMemberActivity.this, "OUPS", "Une erreur s'est produite. Nous ne parvenons pas à vérifier vos informations. vérifiez votre connexion internet puis réessayer.").show();
                                                                            Log.d("failttogetuser", "get failed with ", task.getException());
                                                                            indicator.setVisibility(View.INVISIBLE);
                                                                            return;
                                                                        }
                                                                    }
                                                                });
                                                    } else {
                                                        AlertUtility.simpleAlert(RegisterMemberActivity.this, "OUPS", "Une erreur s'est produite. Nous ne parvenons pas à vérifier vos informations. vérifiez votre connexion internet puis réessayer.").show();
                                                        Log.d("failttogetuser", "get failed with ", task.getException());
                                                        indicator.setVisibility(View.INVISIBLE);
                                                        return;
                                                    }
                                                }
                                            });
                                } else {
                                    AlertUtility.simpleAlert(RegisterMemberActivity.this, "OUPS", "Une erreur s'est produite. vérifiez votre connexion internet puis réessayer.").show();
                                    indicator.setVisibility(View.INVISIBLE);
                                    indicator.setVisibility(View.INVISIBLE);
                                    Log.w("FailedAuthentication", "signInAnonymously:failure", task.getException());
                                    return;
                                }
                            }
                        });


            }

        });
    }
}