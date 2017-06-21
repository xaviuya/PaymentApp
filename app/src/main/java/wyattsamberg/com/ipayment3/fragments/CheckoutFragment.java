package wyattsamberg.com.ipayment3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import wyattsamberg.com.ipayment3.R;
import wyattsamberg.com.ipayment3.api.SaleApi;
import wyattsamberg.com.ipayment3.dialog.ViewCartDialog;
import wyattsamberg.com.ipayment3.listeners.NetworkCallListener;

public class CheckoutFragment extends Fragment implements NetworkCallListener {
    public static final String TAG = CheckoutFragment.class.getSimpleName();

    private double mTotal;
    private double mTipAmount;

    private int mValueBanana;
    private int mValueChicken;
    private int mValueIceCream;
    private int mValueCoffee;

    private EditText mFldCardNumber;
    private EditText mFldTipAmount;

    private String mCardNumber;

    public CheckoutFragment() {
        // Required empty public constructor
    }

    public static CheckoutFragment newInstance(double total,
                                               int valueBanana, int valueChicken,
                                               int valueIceCream, int valueCoffee) {
        CheckoutFragment fragment = new CheckoutFragment();
        fragment.mTotal = total;
        fragment.mValueBanana = valueBanana;
        fragment.mValueChicken = valueChicken;
        fragment.mValueIceCream = valueIceCream;
        fragment.mValueCoffee = valueCoffee;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        TextView lblTotalPay = (TextView) view.findViewById(R.id.lbl_total_pay);

        mFldCardNumber = (EditText) view.findViewById(R.id.fld_card_number);
        mFldTipAmount = (EditText) view.findViewById(R.id.fld_tip_amount);

        String total = "$ " + String.format(Locale.US, "%.2f", mTotal);
        lblTotalPay.setText(String.valueOf(total));

        LinearLayout btnPay = (LinearLayout) view.findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mFldCardNumber.getText().toString()) ||
                        TextUtils.isEmpty(mFldTipAmount.getText().toString())) {
                    Toast.makeText(getContext(), "Invalid Fields", Toast.LENGTH_SHORT).show();
                } else {
                    mCardNumber = mFldCardNumber.getText().toString();
                    mTipAmount = Double.valueOf(mFldTipAmount.getText().toString());

                    saleRequest();
                }
            }
        });

        LinearLayout btnViewCart = (LinearLayout) view.findViewById(R.id.container_view_cart);
        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCartDialog dialog = new ViewCartDialog(mTotal, mValueBanana, mValueChicken,
                        mValueIceCream, mValueCoffee);
                dialog.show(getFragmentManager(), null);
            }
        });

        return view;
    }

    private void saleRequest() {
        SaleApi saleApi = new SaleApi(this, mTotal, mTipAmount, mCardNumber);
        saleApi.execute();
    }

    @Override
    public void onNetworkCallCompleted(String response) {
        Log.d(TAG, response);
    }
}
