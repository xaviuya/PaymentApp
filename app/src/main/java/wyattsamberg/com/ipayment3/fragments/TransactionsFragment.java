package wyattsamberg.com.ipayment3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import wyattsamberg.com.ipayment3.R;
import wyattsamberg.com.ipayment3.adapters.TransactionItemAdapter;
import wyattsamberg.com.ipayment3.listeners.NetworkCallListener;
import wyattsamberg.com.ipayment3.model.TransactionItem;

public class TransactionsFragment extends Fragment implements NetworkCallListener {


    public TransactionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        TransactionItem[] transactionItems = new TransactionItem[] {


        };

        TransactionItemAdapter adapter = new TransactionItemAdapter(getContext(),
                R.layout.listview_transaction_item_row, transactionItems);

        ListView listView = (ListView) view.findViewById(R.id.list_transactions);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onNetworkCallCompleted(String response) {

    }
}
