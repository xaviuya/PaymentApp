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

import java.util.ArrayList;

import wyattsamberg.com.ipayment3.R;
import wyattsamberg.com.ipayment3.model.TransactionItem;

public class TransactionItemAdapter extends ArrayAdapter<TransactionItem> {
    public static final String TAG = TransactionItemAdapter.class.getSimpleName();

    private Context mContext;
    private int mResourceId;
    private TransactionItem[] mTransactionItems = null;

    public TransactionItemAdapter(Context context, int resourceId,
                                  TransactionItem[] transactionItems) {
        super(context, resourceId, transactionItems);
        this.mContext = context;
        this.mResourceId = resourceId;
        this.mTransactionItems = transactionItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        TransactionItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mResourceId, parent, false);

            holder = new TransactionItemHolder();
            holder.lblAuthorizedAmount = (TextView) row.findViewById(R.id.lbl_authorized_amount);
            holder.lblGatewayResponse = (TextView) row.findViewById(R.id.lbl_gateway_response);
            holder.lblTransactionId = (TextView) row.findViewById(R.id.lbl_transaction_id);
            holder.lblTipAmount = (TextView) row.findViewById(R.id.lbl_tip_amount);
        } else {
            holder = (TransactionItemHolder) row.getTag();
        }

        TransactionItem item = mTransactionItems[position];
        holder.lblAuthorizedAmount.setText(item.getLblAuthorizedAmount());
        holder.lblGatewayResponse.setText(item.getLblGatewayResponse());
        holder.lblTransactionId.setText(item.getLblTransactionId());
        holder.lblTipAmount.setText(item.getLblTipAmount());

        return row;
    }

    static class TransactionItemHolder {
        public TextView lblTransactionId;
        public TextView lblAuthorizedAmount;
        public TextView lblGatewayResponse;
        public TextView lblTipAmount;
    }
}
