package myapp.com.vedio;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_splash_screen);
        mydb = new DBHelper(this);
        mydb.createMovieTable();

            mydb. insertMovie( "regular","2");
            mydb. insertMovie( "children","1.5");
            mydb. insertMovie( "newReleases","3");


        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);

    }

}
