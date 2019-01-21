package myapp.com.vedio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class BuyActivity extends AppCompatActivity {
    EditText customerName, mobileNo;
    RadioGroup movies;
    RadioButton regular, children, newReleases;
    DBHelper mydb;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        customerName = findViewById(R.id.customerName);
        mobileNo = findViewById(R.id.mobileNo);
        movies = findViewById(R.id.movies);
        regular = findViewById(R.id.regular);
        children = findViewById(R.id.children);
        newReleases= findViewById(R.id.newReleases);
        submit = findViewById(R.id.submit);
        mydb = new DBHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String name = customerName.getText().toString();
                String mobile = mobileNo.getText().toString();
                String movieType =
                        ((RadioButton)findViewById(movies.getCheckedRadioButtonId()))
                                .getText().toString();
                Calendar c = Calendar.getInstance();

                String buydate = c.get(Calendar.YEAR) + "-"
                        + c.get(Calendar.MONTH)
                        + "-" + c.get(Calendar.DAY_OF_MONTH);
                mydb.createCustomerTable();
                mydb.insertCustomer(name,mobile,movieType,buydate,null);

                Toast.makeText(BuyActivity.this, "Successfull", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

}