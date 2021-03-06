package wyattsamberg.com.ipayment3.api;

import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import wyattsamberg.com.ipayment3.constants.NetworkUrl;
import wyattsamberg.com.ipayment3.constants.User;
import wyattsamberg.com.ipayment3.listeners.NetworkCallListener;

public class SaleApi extends AsyncTask<String, String, String> {
    public static final String TAG = SaleApi.class.getSimpleName();

    private HttpURLConnection mUrlConnection;
    private NetworkCallListener mNetworkCallListener;
    private double mTotal;
    private double mTipAmount;
    private String mCardNumber;

    public SaleApi(NetworkCallListener networkCallListener, double total, double tipAmount,
                   String cardNumber) {
        mNetworkCallListener = networkCallListener;
        mTotal = total;
        mTipAmount = tipAmount;
        mCardNumber = cardNumber;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected String doInBackground(String... params) {
        String url = NetworkUrl.BASE_URL + "/sale";
        StringBuilder stringBuilder = new StringBuilder();
        String response = "";

        try {
            URL request = new URL(url);
            String authorization = User.merchantId + ":" + User.merchantKey;

            mUrlConnection = (HttpURLConnection) request.openConnection();
            mUrlConnection.setRequestProperty("Authorization", authorization);
            mUrlConnection.setRequestProperty("Content-Type", "application/json");
            mUrlConnection.setRequestMethod("POST");
            mUrlConnection.setDoInput(true);
            mUrlConnection.setDoOutput(true);

            JSONObject jsonObject = new JSONObject();
            JSONObject card = new JSONObject();

            try {
                jsonObject.put("TransactionKey", User.transactionKey);
                jsonObject.put("LocationId", User.LOCATION_ID);
                jsonObject.put("TerminalId", User.TERMINAL_ID);
                jsonObject.put("PosTransactionId", User.POS_TRANSACTION_ID);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
                String fldPosTimeStamp = simpleDateFormat.format(new Date());

                jsonObject.put("PosTimestamp", fldPosTimeStamp);

                jsonObject.put("TransactionAmount", mTotal);
                jsonObject.put("TipAmount", mTipAmount);

                card.put("Track2", mCardNumber);
                card.put("Debit", false);
                card.put("EntryMethod", User.ENTRY_METHOD);

                jsonObject.put("Card", card);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(mUrlConnection.getOutputStream());
            outputStreamWriter.write(jsonObject.toString());
            outputStreamWriter.flush();

            int httpResult = mUrlConnection.getResponseCode();

            if (httpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader
                        (new InputStreamReader(mUrlConnection.getInputStream(), "utf-8"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                }

                bufferedReader.close();

                response = stringBuilder.toString();
                Log.d(TAG, response);
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
