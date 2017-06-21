package wyattsamberg.com.ipayment3.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TransactionItem {
    public static final String TAG = TransactionItem.class.getSimpleName();

    public String lblTransactionId;
    public String lblAuthorizedAmount;
    public String lblGatewayResponse;
    public String lblTipAmount;

    public TransactionItem() {

    }

    public TransactionItem(JSONObject object) {
        try {
            this.lblTransactionId = object.getString("GatewayTransactionId");
            this.lblAuthorizedAmount = object.getString("AuthorizedAmount");
            this.lblGatewayResponse = object.getString("GatewayResponse");
            this.lblTipAmount = object.getString("TipAmount");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public TransactionItem(String lblTransactionId, String lblAuthorizedAmount,
                           String lblGatewayResponse, String lblTipAmount) {
        this.lblTransactionId = lblTransactionId;
        this.lblAuthorizedAmount = lblAuthorizedAmount;
        this.lblGatewayResponse = lblGatewayResponse;
        this.lblTipAmount = lblTipAmount;
    }

    public void addTransaction(TransactionItem transactionItem) {}

    public String getLblTransactionId() {
        return lblTransactionId;
    }

    public void setLblTransactionId(String lblTransactionId) {
        this.lblTransactionId = lblTransactionId;
    }

    public String getLblAuthorizedAmount() {
        return lblAuthorizedAmount;
    }

    public void setLblAuthorizedAmount(String lblAuthorizedAmount) {
        this.lblAuthorizedAmount = lblAuthorizedAmount;
    }

    public String getLblGatewayResponse() {
        return lblGatewayResponse;
    }

    public void setLblGatewayResponse(String lblGatewayResponse) {
        this.lblGatewayResponse = lblGatewayResponse;
    }

    public String getLblTipAmount() {
        return lblTipAmount;
    }

    public void setLblTipAmount(String lblTipAmount) {
        this.lblTipAmount = lblTipAmount;
    }
}
