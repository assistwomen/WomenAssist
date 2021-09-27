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
import com.google.firebase.firestore.FirebaseFirestore;
import com.orange.womenassist.models.User;

public class FireBaseUtils {

    private static final String USER_COLLECTION = "Utilisateurs";
    private static CollectionReference refCollection;
    private static boolean result;
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
                            Log.w("FaildAuthentication", "signInAnonymously:failure", task.getException());
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
