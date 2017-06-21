package wyattsamberg.com.ipayment3.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import wyattsamberg.com.ipayment3.R;
import wyattsamberg.com.ipayment3.model.Item;

public class CartItemAdapter extends ArrayAdapter<Item> {
    public static final String TAG = CartItemAdapter.class.getSimpleName();

    private Context mContext;
    private int mResourceId;
    private Item[] mItems = null;

    public CartItemAdapter(Context context, int resourceId, Item[] items) {
        super(context, resourceId, items);
        this.mContext = context;
        this.mResourceId = resourceId;
        this.mItems = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mResourceId, parent, false);

            holder = new ItemHolder();
            holder.lblTitle = (TextView) row.findViewById(R.id.lbl_title_cart);
            holder.lblPrice = (TextView) row.findViewById(R.id.lbl_price_cart);
            holder.lblDescription = (TextView) row.findViewById(R.id.lbl_description_cart);
            holder.lblCount = (TextView) row.findViewById(R.id.lbl_count_cart);
        } else {
            holder = (ItemHolder) row.getTag();
        }

        Item item = mItems[position];
        holder.lblTitle.setText(item.lblTitle);
        holder.lblDescription.setText(item.lblDescription);
        holder.lblPrice.setText(item.lblPrice);
        holder.lblCount.setText(item.lblCount);

        return row;
    }


    static class ItemHolder {
        public TextView lblPrice;
        public TextView lblTitle;
        public TextView lblDescription;
        public TextView lblCount;
    }
}
