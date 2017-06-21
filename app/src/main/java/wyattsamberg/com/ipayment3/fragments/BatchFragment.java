package wyattsamberg.com.ipayment3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import wyattsamberg.com.ipayment3.R;
import wyattsamberg.com.ipayment3.api.CloseBatchApi;
import wyattsamberg.com.ipayment3.api.OpenBatchApi;
import wyattsamberg.com.ipayment3.listeners.NetworkCallListener;

public class BatchFragment extends Fragment implements NetworkCallListener {
    public static final String TAG = BatchFragment.class.getSimpleName();

    public BatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_batch, container, false);

        Button btnOpenBatch = (Button) view.findViewById(R.id.btn_open_batch);
        Button btnCloseBatch = (Button) view.findViewById(R.id.btn_close_batch);

        btnOpenBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBatch();
            }
        });

        btnCloseBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeBatch();
            }
        });

        return view;
    }

    public void openBatch() {
        OpenBatchApi openBatchApi = new OpenBatchApi(this);
        openBatchApi.execute();
    }

    public void closeBatch() {
        CloseBatchApi closeBatchApi = new CloseBatchApi(this);
        closeBatchApi.execute();
    }

    @Override
    public void onNetworkCallCompleted(String response) {
        Log.d(TAG, response);
    }
}
