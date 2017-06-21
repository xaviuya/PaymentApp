package wyattsamberg.com.ipayment3.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import wyattsamberg.com.ipayment3.R;
import wyattsamberg.com.ipayment3.api.TransactionKeyApi;
import wyattsamberg.com.ipayment3.constants.User;
import wyattsamberg.com.ipayment3.fragments.BatchFragment;
import wyattsamberg.com.ipayment3.fragments.CartFragment;
import wyattsamberg.com.ipayment3.fragments.TransactionsFragment;
import wyattsamberg.com.ipayment3.listeners.NetworkCallListener;

public class HomeActivity extends AppCompatActivity implements NetworkCallListener {
    public static final String TAG = HomeActivity.class.getSimpleName();

    private FragmentManager mFragmentManager;
    private Fragment mFragment;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation_view);

        if (savedInstanceState == null) {
            bottomNavigationView.getMenu().getItem(1).setChecked(true);
            setTitle("Cart");
            mFragment = new CartFragment();
            mId = "Cart";
            showFragment();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.batch) {
                            if (!(mId.equals("Batch"))) {
                                mFragment = new BatchFragment();
                                mId = "Batch";
                                setTitle("Batch");
                            }

                        } else if (id == R.id.cart) {
                            if (!(mId.equals("Cart"))) {
                                mFragment = new CartFragment();
                                mId = "Cart";
                                setTitle("Cart");
                            }

                        } else if (id == R.id.transactions) {
                            if (!(mId.equals("Transactions"))) {
                                mFragment = new TransactionsFragment();
                                mId = "Transactions";
                                setTitle("Transactions");
                            }
                        }

                        showFragment();

                        return true;
                    }
                });
    }

    private void showFragment() {
        mFragmentManager.beginTransaction().replace(R.id.main_container, mFragment, mId)
                .addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_options_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh_transaction_key) {
            TransactionKeyApi transactionKeyApi = new TransactionKeyApi(HomeActivity.this,
                    User.merchantId, User.merchantKey);
            transactionKeyApi.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNetworkCallCompleted(String response) {
        Log.d(TAG, response);
    }
}
