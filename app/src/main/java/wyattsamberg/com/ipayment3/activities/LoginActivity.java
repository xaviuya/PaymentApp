package wyattsamberg.com.ipayment3.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import wyattsamberg.com.ipayment3.R;
import wyattsamberg.com.ipayment3.api.TransactionKeyApi;
import wyattsamberg.com.ipayment3.constants.User;
import wyattsamberg.com.ipayment3.listeners.NetworkCallListener;

public class LoginActivity extends AppCompatActivity implements NetworkCallListener {
    public static final String TAG = LoginActivity.class.getSimpleName();

    private EditText mEtMerchantId;
    private EditText mEtMerchantKey;
    private Button mBtnLogin;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtMerchantId = (EditText) findViewById(R.id.etMerchantId);
        mEtMerchantKey = (EditText) findViewById(R.id.etMerchantKey);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);



        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtMerchantId.getText()) ||
                        TextUtils.isEmpty(mEtMerchantKey.getText())) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid fields.",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 16);
                    toast.show();
                } else {
                    User.merchantId = mEtMerchantId.getText().toString();
                    User.merchantKey = mEtMerchantKey.getText().toString();
                    sendRequest();
                }
            }
        });
    }

    private void sendRequest() {
        TransactionKeyApi loginApi = new TransactionKeyApi(this, User.merchantId, User.merchantKey);
        loginApi.execute();
    }

    @Override
    public void onNetworkCallCompleted(String response) {
        Log.d(TAG, response);
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
