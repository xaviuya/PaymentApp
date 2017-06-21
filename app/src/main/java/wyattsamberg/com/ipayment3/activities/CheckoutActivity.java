package wyattsamberg.com.ipayment3.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import wyattsamberg.com.ipayment3.R;
import wyattsamberg.com.ipayment3.constants.Key;
import wyattsamberg.com.ipayment3.fragments.CheckoutFragment;

public class CheckoutActivity extends AppCompatActivity {
    public static final String TAG = CheckoutActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();

            double mTotal = bundle.getDouble(Key.TOTAL);
            int mValueBanana = bundle.getInt(Key.VALUE_BANANA);
            int mValueChicken = bundle.getInt(Key.VALUE_CHICKEN);
            int mValueIceCream = bundle.getInt(Key.VALUE_ICE_CREAM);
            int mValueCoffee = bundle.getInt(Key.VALUE_COFFEE);

            getSupportFragmentManager().beginTransaction().replace(R.id.container_checkout,
                    CheckoutFragment.newInstance(mTotal, mValueBanana, mValueChicken,
                            mValueIceCream, mValueCoffee), null).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
