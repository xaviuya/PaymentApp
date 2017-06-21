package wyattsamberg.com.ipayment3.dialog;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

import wyattsamberg.com.ipayment3.R;
import wyattsamberg.com.ipayment3.adapters.CartItemAdapter;
import wyattsamberg.com.ipayment3.model.Item;

public class ViewCartDialog extends DialogFragment {
    public static final String TAG = ViewCartDialog.class.getSimpleName();

    private double mTotal;
    private int mValueBanana;
    private int mValueChicken;
    private int mValueIceCream;
    private int mValueCoffee;

    public ViewCartDialog(double total, int valueBanana, int valueChicken,
                          int valueIceCream, int valueCoffee) {
        mTotal = total;
        mValueBanana = valueBanana;
        mValueChicken = valueChicken;
        mValueIceCream = valueIceCream;
        mValueCoffee = valueCoffee;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_dialog, container, false);

        String total = "$ " + String.format(Locale.getDefault(), "%.2f", mTotal);

        TextView lblTotalCartValue = (TextView) view.findViewById(R.id.lbl_cart_total_value);
        lblTotalCartValue.setText(total);

        LinearLayout btnCloseDialog = (LinearLayout)view.findViewById(R.id.btn_close_dialog);
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Item[] items = new Item[]{
                new Item("Banana", "A tropical plant of the genus Musa.", "1.00",
                        String.valueOf(mValueBanana)),
                new Item("Chicken", "A domestic fowl kept for its eggs or meat.", "5.00",
                        String.valueOf(mValueChicken)),
                new Item("Ice Cream",
                        "A soft frozen food.", "10.00", String.valueOf(mValueIceCream)),
                new Item("Coffee", "A drink made from the roasted seeds",
                        "20.00", String.valueOf(mValueCoffee))
        };

        CartItemAdapter itemAdapter = new CartItemAdapter(getContext(),
                R.layout.listview_cart_row, items);

        ListView listView = (ListView) view.findViewById(R.id.list_cart);
        listView.setAdapter(itemAdapter);

        return view;
    }
}
