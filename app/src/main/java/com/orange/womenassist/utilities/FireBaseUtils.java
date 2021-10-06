package com.orange.womenassist.utilities;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.womenassist.models.User;

public class FireBaseUtils {

    private static final String USER_COLLECTION = "Utilisateurs";
    private static CollectionReference refCollection;
    private static User user = null;
    private static boolean result = true;
    private static FirebaseAuth mAuth;

    public static CollectionReference getFireBaseReference(String collectionName){

        if(refCollection == null)
        {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            refCollection = db.collection(collectionName);
            return refCollection;
        }
        return refCollection;
    }

    public static CollectionReference getFireBaseAssociationReference(String collectionName){

        if(refCollection == null)
        {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            refCollection = db.collection(collectionName);
            return refCollection;
        }
        return refCollection;
    }

    public static FirebaseAuth getFireBaseAuth()
    {
        if (mAuth == null)
        {
            mAuth = FirebaseAuth.getInstance();
            return mAuth;
        }
        return mAuth;
    }


    /*permet d'ajouter un nouveau utilisateur*/
    public static boolean addUser(User user, String user_id){
        FireBaseUtils.getFireBaseReference(USER_COLLECTION).document(user_id)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        result = true;
                        Log.d("SuccessAddUser", "addUser:success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        result = false;
                        Log.d("FailedAddUser", "addUser:failed");
                    }
                });
        return result;
    }

    public static User getUserToLoginWithId(String userid)
    {
        FireBaseUtils.getFireBaseReference(USER_COLLECTION).document(userid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d("seccessgetdocument", "DocumentSnapshot data: " + document.getData());
                                user =  new User();
                                user.setPassword(document.getString("password"));
                                user.setPseudo(document.getString("pseudo"));
                            } else {
                                Log.d("userdontexist", "No such document");
                                user = null;
                            }
                        } else {
                            Log.d("failttogetuser", "get failed with ", task.getException());
                            user = null;
                        }

                    }
                });
        return user;
    }


    public static User getUserWithSpseudo(String pseudo)
    {
        FireBaseUtils.getFireBaseReference(USER_COLLECTION)
                .whereEqualTo("pseudo", pseudo)
                .whereEqualTo("is_exist", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Successgetuser", "get un utilisateur retrouvé");
                                user =  new User();
                                user.setPassword(document.getString("password"));
                                user.setPseudo(document.getString("pseudo"));
                                Log.d("Successgetuser", user.getPseudo());
                                user.setId(document.getId());
                            }
                            Log.d("Successgetuser", "get un utilisateur retrouvé");
                        } else {
                            Log.d("failttogetuser", "get failed with ", task.getException());
                            user = null;
                        }
                    }
                });
        return user;
    }

    /*permet de creer un compte anonyme*/
    public static FirebaseUser signInAnonymously (){
        FireBaseUtils.getFireBaseAuth().signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.d("SuccessAuthentication", "signInAnonymously:success");
                        }
                        else
                        {
                            Log.w("FailedAuthentication", "signInAnonymously:failure", task.getException());
                        }
                    }
                });
        return FireBaseUtils.getFireBaseAuth().getCurrentUser();
    }

}







/*

                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
@Override
public void onSuccess(DocumentReference documentReference) {
        Log.d("SUCCESS", "DocumentSnapshot added with ID: " + documentReference.getId());
        }
        })
        .addOnFailureListener(new OnFailureListener() {
@Override
public void onFailure(@NonNull Exception e) {
        Log.w("FAILD", "Error adding document", e);
        }
        });
*/
