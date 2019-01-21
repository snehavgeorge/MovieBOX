package myapp.com.vedio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PriceActivity extends AppCompatActivity {
    TextView price;
    public Bundle getBundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        price = findViewById(R.id.price);
        getBundle = this.getIntent().getExtras();
        String leadId = getBundle.getString("totalPrice");

        price.setText(leadId);
    }
}
