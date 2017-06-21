package wyattsamberg.com.ipayment3.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import wyattsamberg.com.ipayment3.R;
import wyattsamberg.com.ipayment3.activities.CheckoutActivity;
import wyattsamberg.com.ipayment3.adapters.AdHocItemAdapter;
import wyattsamberg.com.ipayment3.api.AuthApi;
import wyattsamberg.com.ipayment3.constants.Key;
import wyattsamberg.com.ipayment3.listeners.NetworkCallListener;
import wyattsamberg.com.ipayment3.model.Item;

public class CartFragment extends Fragment implements NetworkCallListener {
    public static final String TAG = CartFragment.class.getSimpleName();

    private double mTotal;
    private TextView mLblTotal;
    private int mValueBanana;
    private int mValueChicken;
    private int mValueIceCream;
    private int mValueCoffee;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        mLblTotal = (TextView) view.findViewById(R.id.lbl_total);
        LinearLayout btnCheckout = (LinearLayout) view.findViewById(R.id.btn_checkout);
        LinearLayout btnTotal = (LinearLayout) view.findViewById(R.id.btn_total);

        btnTotal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mTotal != 0.00) {
                    mValueBanana = 0;
                    mValueChicken = 0;
                    mValueIceCream = 0;
                    mValueCoffee = 0;
                    mTotal = 0.00;

                    String total = "$ " + String.format(Locale.getDefault(), "%.2f", mTotal);
                    mLblTotal.setText(String.valueOf(total));

                    return true;
                }

                return false;

            }
        });

        mTotal = 0.00;
        mValueBanana = 0;
        mValueChicken = 0;
        mValueIceCream = 0;
        mValueCoffee = 0;

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTotal == 0.00) {
                    Toast.makeText(getContext(), "Cart is empty.", Toast.LENGTH_SHORT).show();
                } else {
                    sendAuthRequest();
                }
            }
        });


        String total = "$ " + String.format(Locale.getDefault(), "%.2f", mTotal);
        mLblTotal.setText(String.valueOf(total));

        Item[] items = new Item[]{
                new Item("Banana", "A tropical plant of the genus Musa.", "1.00"),
                new Item("Chicken", "A domestic fowl kept for its eggs or meat.", "5.00"),
                new Item("Ice Cream",
                        "A soft frozen food.", "10.00"),
                new Item("Coffee", "A drink made from the roasted seeds",
                        "20.00")
        };

        AdHocItemAdapter itemAdapter = new AdHocItemAdapter(getContext(), R.layout.listview_item_row, items);

        final ListView listView = (ListView) view.findViewById(R.id.list_ad_hoc);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mTotal = mTotal + 1.00;
                    mValueBanana = mValueBanana + 1;

                } else if (position == 1) {
                    mTotal = mTotal + 5.00;
                    mValueChicken = mValueChicken + 1;

                } else if (position == 2) {
                    mTotal = mTotal + 10.00;
                    mValueIceCream = mValueIceCream + 1;

                } else if (position == 3) {
                    mTotal = mTotal + 20.00;
                    mValueCoffee = mValueCoffee + 1;
                }

                String total = "$ " + String.format(Locale.getDefault(), "%.2f", mTotal);
                mLblTotal.setText(String.valueOf(total));
            }
        });

        return view;
    }

    private void sendAuthRequest() {
        AuthApi authApi = new AuthApi(this, mTotal);
        authApi.execute();
    }

    @Override
    public void onNetworkCallCompleted(String response) {
        Log.d(TAG, response);
        Intent intent = new Intent(getContext(), CheckoutActivity.class);
        intent.putExtra(Key.TOTAL, mTotal);
        intent.putExtra(Key.VALUE_BANANA, mValueBanana);
        intent.putExtra(Key.VALUE_CHICKEN, mValueChicken);
        intent.putExtra(Key.VALUE_ICE_CREAM, mValueIceCream);
        intent.putExtra(Key.VALUE_COFFEE, mValueCoffee);
        startActivity(intent);
    }
}
