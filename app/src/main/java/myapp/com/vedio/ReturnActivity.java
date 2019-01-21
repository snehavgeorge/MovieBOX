package myapp.com.vedio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ReturnActivity extends AppCompatActivity {
    EditText mobileNo;
    RadioGroup movies;
    RadioButton regular, children, newReleases;
    DBHelper mydb;
    Button submit;
    String movieType;
    String mobile;
    String returnDate;
    double totalPrice;
    SimpleDateFormat myFormat;
    long diff,difference;
    double moviePrice;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);
        mobileNo = findViewById(R.id.mobileNo);
        movies = findViewById(R.id.movies);
        regular = findViewById(R.id.regular);
        children = findViewById(R.id.children);
        newReleases= findViewById(R.id.newReleases);
        submit = findViewById(R.id.submit);
        mydb = new DBHelper(this);
        myFormat = new SimpleDateFormat("yyyy-MM-dd");


        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                mobile = mobileNo.getText().toString();
                movieType =
                        ((RadioButton)findViewById(movies.getCheckedRadioButtonId()))
                                .getText().toString();
                Calendar c = Calendar.getInstance();

                returnDate = c.get(Calendar.YEAR) + "-"
                        + c.get(Calendar.MONTH)
                        + "-" + c.get(Calendar.DAY_OF_MONTH);

                mydb.updateCustomer(mobile,returnDate);
                getdiff();
                mydb.getMoviePrice(movieType);
                if(diff == 0){
                    difference= diff + 1;
                }
                else {
                    difference= diff ;
                }

                moviePrice = parseDouble(String.valueOf(mydb.getMoviePrice(movieType)));
                    if (movieType.equals("regular")) {
                        if (difference > 2) {
                            totalPrice = (moviePrice * 2.0) + ((difference - 2.0) * 1.5);
                        } else {
                            totalPrice = (moviePrice * difference);
                        }

                    } else {
                        totalPrice = moviePrice * difference;
                    }


                Intent i = new Intent(ReturnActivity.this,PriceActivity.class);
                bundle.putString("totalPrice", String.valueOf(totalPrice));
                i.putExtras(bundle);
                startActivity(i);


            }
        });




    }

    public void getdiff() {
        String returnDate = String.valueOf(mydb.getreturnDte(mobile, movieType));
        String receiveDate = String.valueOf(mydb.getreceivedDate(mobile,movieType));
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(receiveDate);
            d2 = format.parse(returnDate);

            long diff = d2.getTime() - d1.getTime();

            long diffDays = diff / (24 * 60 * 60 * 1000);
            Log.e("diffDays", String.valueOf(diffDays));

        } catch (Exception e) {
            e.printStackTrace();
        }



    }


}