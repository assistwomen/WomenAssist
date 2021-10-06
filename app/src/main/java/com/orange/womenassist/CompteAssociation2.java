package com.orange.womenassist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orange.womenassist.models.Association;
import com.orange.womenassist.models.User;
import com.orange.womenassist.utilities.AlertUtility;
import com.orange.womenassist.utilities.FireBaseUtils;

import java.util.ArrayList;

public class CompteAssociation2 extends AppCompatActivity {

    private static final String USER_COLLECTION = "Utilisateurs";
    private static boolean result = true;
    private static final String ASSOCIATION_COLLECTION = "Association";
    private static final String USER_CREDENTIAL = "com.orange.womenassist";
    private EditText edtObjectif,edtDescription;
    private Button btnTerminer;
    private View vwPhoto;
    private CircularProgressIndicator indicator;
    private User user;
    private Association association;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_association2);

        vwPhoto = findViewById(R.id.vwPhoto);
        btnTerminer = findViewById(R.id.btnTerminer);
        edtDescription = findViewById(R.id.edtDescription);
        edtObjectif = findViewById(R.id.edtObjectif);
        indicator = findViewById(R.id.indicator);
        btnBack = findViewById(R.id.btnBack);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Intent intent = getIntent();
        user = intent.getParcelableExtra(CompteAssociation2.USER_COLLECTION);
        association = intent.getParcelableExtra(CompteAssociation2.ASSOCIATION_COLLECTION);

        vwPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CompteAssociation2.this, "Ce champ est actuellement indisponible", Toast.LENGTH_LONG).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompteAssociation2.this.onBackPressed();
            }
        });

        btnTerminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator.setVisibility(View.VISIBLE);
                if(checkUser()){
                    indicator.setVisibility(View.INVISIBLE);
                    return;
                }
                if((edtDescription.getText().toString()).isEmpty() || (edtObjectif.getText().toString()).isEmpty())
                {
                    AlertUtility.simpleAlert(CompteAssociation2.this, "Erreur","Tous les champs sont obligatoirs.").show();
                    indicator.setVisibility(View.INVISIBLE);
                    return;
                }

                association.setDescription(edtDescription.getText().toString());
                association.setObjectif(edtObjectif.getText().toString());
                association.setAdministrateur(user.getPseudo());
                ArrayList<String> membre = new ArrayList<String>();
                membre.add(user.getPseudo());
                association.setMembre(membre);
                association.setResponsable(membre);

                user.setMembre_association(membre);
                user.setRole_association(User.getUserRoleAdministrateur());
                user.setNom_association(association.getAbreviation());

                FireBaseUtils.getFireBaseReference(USER_COLLECTION).document(currentUser.getUid())
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                CollectionReference collection = db.collection(ASSOCIATION_COLLECTION);
                                collection.add(association)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                startActivity(new Intent(CompteAssociation2.this, ConnexionActivity.class));
                                                Toast.makeText(CompteAssociation2.this, "Votre compte a été créer avec succès", Toast.LENGTH_SHORT).show();
                                                indicator.setVisibility(View.INVISIBLE);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                AlertUtility.simpleAlert(CompteAssociation2.this, "OUPS","Une erreur s'est produite lors de l'ajout de l'association.").show();
                                                indicator.setVisibility(View.INVISIBLE);
                                                return;
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                AlertUtility.simpleAlert(CompteAssociation2.this, "OUPS","Une erreur s'est produite lors de l'ajout du compte.").show();
                                indicator.setVisibility(View.INVISIBLE);
                                return;
                            }
                        });
            }
        });

    }


    private boolean checkUser()
    {

        if(user == null)
        {
            SharedPreferences sharedPreferences = getSharedPreferences(USER_CREDENTIAL, Context.MODE_PRIVATE);
            String userId = sharedPreferences.getString(User.USER_ID, "RAS");

            if(userId.equals("RAS"))
            {
                AlertUtility.simpleAlert(CompteAssociation2.this, "INFO","Pour créer une nouvelle association vous devrez avoir un compte sur l'application.").show();
                return result;
            }
            Log.w("testemptyuser", userId);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference collection = db.collection(USER_COLLECTION);
            collection.document(userId)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d("seccessgetdocument", "DocumentSnapshot data: " + document.getData());
                                    user =  new User();
                                    user.setPseudo(document.getString("pseudo"));
                                    result = false;
                                } else {
                                    AlertUtility.simpleAlert(CompteAssociation2.this, "INFO","Pour créer une nouvelle association vous devrez avoir un compte sur l'application.").show();
                                }
                            } else {
                                AlertUtility.simpleAlert(CompteAssociation2.this, "OUPS","Une erreur s'est produite lors de la récupération de vos informations de compte.").show();
                            }

                        }
                    });
            return result;
        }
        return result;
    }
}