package com.orange.womenassist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.orange.womenassist.utilities.FireBaseUtils;
import com.orange.womenassist.utilities.GpsTracker;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView messageUrgence, appelUrgence;
    private GpsTracker gpsTracker;
    CardView cdvAssociation, cdvFicheConseil;
    private static final int ACTION_CALL_PERMISSION_CODE = 100;
    private static final int SEND_SMS_PERMISSION_CODE = 101;
    private static final int FINE_LOCATION_PERMISSION_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //initialyse varibles
        messageUrgence = findViewById(R.id.imgmessageUrgence);
        appelUrgence = findViewById(R.id.imgappelUrgence);
        Toolbar toolbar = findViewById(R.id.homeToolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        cdvFicheConseil = findViewById(R.id.cdvFicheConseil);
        cdvAssociation = findViewById(R.id.cdvAssociation);

        setSupportActionBar(toolbar);
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()  {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.forum:
                        startActivity( new Intent( HomeActivity.this , ListeForum.class));
                        break;
                    case R.id.contact:
                        startActivity( new Intent( HomeActivity.this , ContactConfianceActivity.class));
                        break;
                    case R.id.holder:
                        break;
                    case R.id.article:
                        startActivity( new Intent( HomeActivity.this , ListeArticleActivity.class));
                        break;
                    case R.id.parametres:
                        startActivity( new Intent( HomeActivity.this , Parametre.class));
                        break;
                }
                return true;
            }
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.forum:
                        startActivity( new Intent( HomeActivity.this , ListeForum.class));
                        break;
                    case R.id.contact:
                        startActivity( new Intent( HomeActivity.this , ContactConfianceActivity.class));
                        break;
                    case R.id.holder:
                        break;
                    case R.id.article:
                        startActivity( new Intent( HomeActivity.this , ListeArticle.class));
                        break;
                    case R.id.parametres:
                        startActivity( new Intent( HomeActivity.this , MonCompte.class));
                        break;

                }
            }
        });

        //authetification anonyme
        if (FireBaseUtils.signInAnonymously() == null) {
            Toast.makeText(this, "V??rifiez votre connexion internet", Toast.LENGTH_LONG).show();
        }

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_PERMISSION_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Une exception s'est produite", Toast.LENGTH_LONG).show();
        }

        //ecouteur pour appel
        appelUrgence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(Manifest.permission.CALL_PHONE, ACTION_CALL_PERMISSION_CODE);
            }
        });

        //ecouteur pour message
        messageUrgence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(Manifest.permission.SEND_SMS, SEND_SMS_PERMISSION_CODE);
            }
        });

        //ecouteur association
        cdvAssociation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListeAssociationActivity.class);
                startActivity(intent);
            }
        });

        //ecouteur fiche conseil
        cdvFicheConseil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FicheConseilActivity.class);
                startActivity(intent);
            }
        });

    }

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(HomeActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{permission}, requestCode);
        } else {
            if (requestCode == ACTION_CALL_PERMISSION_CODE)
                makeCall();
            if (requestCode == SEND_SMS_PERMISSION_CODE)
                sendSMD();
        }
    }


    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == ACTION_CALL_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this, "Gestion des appels autoris??e", Toast.LENGTH_SHORT).show();
                makeCall();
            } else {
                Toast.makeText(HomeActivity.this, "Gestion des appels refus??e", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == SEND_SMS_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this, "Envoie des message autoris??", Toast.LENGTH_SHORT).show();
                sendSMD();
            } else {
                Toast.makeText(HomeActivity.this, "Envoie des message refus??", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == FINE_LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(HomeActivity.this, "Impossible de continuer sans la localisation", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    /*Demarrer l'appel des ugences*/
    public void makeCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + 117));//change the number
        startActivity(callIntent);
    }

    public void sendSMD() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+237693615121", null, "position ", null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
    }

    public void getLocation() {
        gpsTracker = new GpsTracker(HomeActivity.this);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
        } else {
            gpsTracker.showSettingsAlert();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.connexion) {
            Intent intent = new Intent(this, ConnexionActivity.class);
            startActivity(intent);
        } else if (id == R.id.register) {
            Intent intent = new Intent(this, RegisterMemberActivity.class);
            startActivity(intent);
        } else if (id == R.id.registerAssociation) {
            Intent intent = new Intent(this, CompteAssociation.class);
            startActivity(intent);
        }
        return true;
    }
}