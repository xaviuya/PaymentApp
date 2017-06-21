package wyattsamberg.com.ipayment3.api;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import wyattsamberg.com.ipayment3.constants.NetworkUrl;
import wyattsamberg.com.ipayment3.listeners.NetworkCallListener;

public class TransactionKeyApi extends AsyncTask<String, String, String> {
    public static final String TAG = TransactionKeyApi.class.getSimpleName();

    private HttpURLConnection mUrlConnection;
    private String mFldMerchantId;
    private String mFldMerchantKey;
    private NetworkCallListener mNetworkCallListener;

    public TransactionKeyApi(NetworkCallListener networkCallListener, String merchantId,
                             String merchantKey) {
        mNetworkCallListener = networkCallListener;
        mFldMerchantId = merchantId;
        mFldMerchantKey = merchantKey;
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder url = new StringBuilder();
        String response = "";
        url.append(NetworkUrl.BASE_URL);
        url.append("/transactionKey?merchantId=")
                .append(mFldMerchantId).append("&&merchantKey=").append(mFldMerchantKey);

        Log.d(TAG, "Request :: " + url.toString());

        try {
            URL request = new URL(url.toString());
            String authorization = mFldMerchantId + ":" + mFldMerchantKey;
            mUrlConnection = (HttpURLConnection) request.openConnection();

            mUrlConnection.setRequestProperty("Authorization", authorization);
            mUrlConnection.setRequestMethod("GET");
            mUrlConnection.setRequestProperty("Content-Type", "application/json");
            mUrlConnection.setDoInput(true);
            mUrlConnection.connect();

            InputStream inputStream = new BufferedInputStream(mUrlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                response = line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mUrlConnection.disconnect();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        mNetworkCallListener.onNetworkCallCompleted(response);
    }
}

