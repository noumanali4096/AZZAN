package com.example.nouman.azzan;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class UserHomeScreen extends AppCompatActivity {

    private static final String TAG = "UserHomeScreen";

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    public static final int REQUEST_LOCATION_CODE = 99;
    //private PagerAdapter adapter;
    private ViewPager viewPager;
    private int[] imageResId = {
            R.drawable.mosque,
            R.drawable.qibla,
            R.drawable.alarm,
    R.drawable.calendar};
    Toolbar toolbar;
    String third;
    DatabaseReference databaseMosqueSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_screen);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        databaseMosqueSub= FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/mosquesubscriber");
        setSupportActionBar(toolbar);
        Intent intent2 = getIntent();
        third = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().toString();
       // adapter=new PagerAdapter(getSupportFragmentManager());


        if(isServicesOK()){
            viewPager=(ViewPager) findViewById(R.id.viewpager);
            viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),UserHomeScreen.this));
            setupviewpager(viewPager);
            TabLayout tabLayout=(TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            for (int i = 0; i < imageResId.length; i++) {
                tabLayout.getTabAt(i).setIcon(imageResId[i]);
            }
        }

    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(UserHomeScreen.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(UserHomeScreen.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private  void setupviewpager(ViewPager viewPager){
        MyPagerAdapter pagerAdapter=new MyPagerAdapter(getSupportFragmentManager(),UserHomeScreen.this);
        pagerAdapter.addFragment(new MosqueFragmentSolution(),"Mosque");
        pagerAdapter.addFragment(new QiblaCompassFragmentUpdated(),"Qibla");
        pagerAdapter.addFragment(new NamazAlarmFragment(),"Alarm");
        pagerAdapter.addFragment(new HijriCalenderFragment(),"Calender");
        viewPager.setAdapter(pagerAdapter);
        if(getIntent().getAction()!=null) {
            if (getIntent().getAction().equals("namaztimechangged")) {
                viewPager.setCurrentItem(2);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id=item.getItemId();
        if(res_id==R.id.action_nikkah)
        {
            Query fireBaseQueryMsubscriber = databaseMosqueSub.orderByChild("userPhone").equalTo(third);

            fireBaseQueryMsubscriber.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        for (DataSnapshot mosqueSubSnapshot : dataSnapshot.getChildren()) {
                            MosqueSubcribe obj = mosqueSubSnapshot.getValue(MosqueSubcribe.class);
                            String mPhone = obj.getMosquePhone();
                            if (!mPhone.isEmpty()) {

                                Intent intent=new Intent(UserHomeScreen.this,NikkahActivity.class);
                                intent.putExtra("UserPhone3",third);
                                intent.putExtra("mPhone",mPhone);
                                startActivityForResult(intent,4);
                            }
                        }
                    }
                    else{
                            Intent intent=new Intent(UserHomeScreen.this,NikkahActivity.class);
                            intent.putExtra("UserPhone3",third);
                            intent.putExtra("mPhone","");
                            startActivityForResult(intent,4);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(res_id==R.id.action_Ittekaaf)
        {
            Query fireBaseQueryMsubscriber = databaseMosqueSub.orderByChild("userPhone").equalTo(third);

            fireBaseQueryMsubscriber.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        for (DataSnapshot mosqueSubSnapshot : dataSnapshot.getChildren()) {
                            MosqueSubcribe obj = mosqueSubSnapshot.getValue(MosqueSubcribe.class);
                            String mPhone = obj.getMosquePhone();
                            if (!mPhone.isEmpty()) {

                                Intent intent=new Intent(UserHomeScreen.this,IteekaafActivity.class);
                                intent.putExtra("UserPhone4",third);
                                intent.putExtra("mPhone",mPhone);
                                startActivityForResult(intent,4);
                            }
                        }
                    }
                    else{
                        Intent intent=new Intent(UserHomeScreen.this,IteekaafActivity.class);
                        intent.putExtra("UserPhone4",third);
                        intent.putExtra("mPhone","");
                        startActivityForResult(intent,4);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(res_id==R.id.action_shop)
        {
            Intent intent=new Intent(UserHomeScreen.this,AllProductsActivity.class);
            startActivity(intent);
        }
        if(res_id==R.id.action_logout)
        {
            Query fireBaseQueryMsubscriber = databaseMosqueSub.orderByChild("userPhone").equalTo(third);

            fireBaseQueryMsubscriber.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        for (DataSnapshot mosqueSubSnapshot : dataSnapshot.getChildren()) {
                            MosqueSubcribe obj = mosqueSubSnapshot.getValue(MosqueSubcribe.class);
                            String mPhone = obj.getMosquePhone();
                            if (!mPhone.isEmpty()) {
                                String topic = "mosqueTiming" + mPhone;
                                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                                topic = "Nikkah"+mPhone+third.substring(1,third.length());
                                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                                topic = "Ittekaaf"+mPhone+third.substring(1,third.length());
                                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(UserHomeScreen.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                    }
                    else{
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(UserHomeScreen.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        return super.onOptionsItemSelected(item);
    }


}
