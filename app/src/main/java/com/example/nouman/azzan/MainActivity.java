package com.example.nouman.azzan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    TextView t1,t2;
    ImageView i1;
    EditText e1,e2;
    Button b1,b2;
    private FirebaseAuth mAuth;
    // [END declare_auth]
    DatabaseReference databaseMosqueSub;


    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView) findViewById(R.id.textView);
        i1=(ImageView) findViewById(R.id.phoneicon);
        e1=(EditText) findViewById(R.id.phoneNo_editText);
        b1=(Button) findViewById(R.id.send_code_button);
        t2=(TextView) findViewById(R.id.OTP_textView);
        //i1=(ImageView) findViewById(R.id.phoneicon);
        e2=(EditText) findViewById(R.id.OTP_editText);
        b2=(Button) findViewById(R.id.OTP_code_button);
        databaseMosqueSub= FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/mosquesubscriber");
        mAuth = FirebaseAuth.getInstance();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                mVerificationInProgress = false;
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                mVerificationInProgress = false;


                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                    e1.setError("Invalid phone number.");
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {


                }


            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                mVerificationId = verificationId;
                mResendToken = token;
                e1.setVisibility(View.GONE);
                i1.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                b1.setVisibility(View.GONE);
                e2.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);


            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        e1.getText().toString(),        // Phone number to verify
                        60,                 // Timeout duration
                        java.util.concurrent.TimeUnit.SECONDS,   // Unit of timeout
                        MainActivity.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks
                // [END start_phone_auth]

                mVerificationInProgress = true;
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, e2.getText().toString());
                // [END verify_with_code]
                signInWithPhoneAuthCredential(credential);
            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            final String phoneNo=e1.getText().toString().trim();
                            // [START_EXCLUDE]

                            Query fireBaseQueryMsubscriber = databaseMosqueSub.orderByChild("userPhone").equalTo(phoneNo);

                            fireBaseQueryMsubscriber.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    if (dataSnapshot.exists()) {
                                        for (DataSnapshot mosqueSubSnapshot : dataSnapshot.getChildren()) {
                                            MosqueSubcribe obj = mosqueSubSnapshot.getValue(MosqueSubcribe.class);
                                            String mPhone = obj.getMosquePhone();
                                            if (!mPhone.isEmpty()) {
                                                String topic = "mosqueTiming" + mPhone;
                                                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                                                topic = "Nikkah"+mPhone+phoneNo.substring(1,phoneNo.length());
                                                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                                                topic = "Ittekaaf"+mPhone+phoneNo.substring(1,phoneNo.length());
                                                FirebaseMessaging.getInstance().subscribeToTopic(topic);

                                            }
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            Calendar calendar = Calendar.getInstance();
                                calendar.set(
                                        calendar.get(Calendar.YEAR),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DAY_OF_MONTH),
                                        05,
                                        10,
                                        0
                                );

                            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            Intent intent= new Intent(MainActivity.this,SheduleAlarm.class);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                            alarmManager.setRepeating(AlarmManager.RTC,calendar.getTimeInMillis(),
                                    AlarmManager.INTERVAL_DAY,pendingIntent);
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                                    AlarmManager.INTERVAL_DAY,pendingIntent);
                            Toast.makeText(MainActivity.this,"sheduler start",Toast.LENGTH_LONG).show();

                            Intent intent1=new Intent(MainActivity.this,UserVerified.class);
                            intent1.putExtra("UserPhoneNo",phoneNo);
                            startActivityForResult(intent1,1);
                            //startActivity(new Intent(MainActivity.this,UserVerified.class));

                        } else {
                            // Sign in failed, display a message and update the UI
                          //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                // [START_EXCLUDE silent]
                                e2.setError("Invalid code.");
                                // [END_EXCLUDE]
                            }

                        }
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        if(current != null)
        {
            Intent intent = new Intent(this,UserHomeScreen.class);
            intent.putExtra("UserInfo2",current.getPhoneNumber());
            startActivity(intent);
            finish();
        }
    }
}
