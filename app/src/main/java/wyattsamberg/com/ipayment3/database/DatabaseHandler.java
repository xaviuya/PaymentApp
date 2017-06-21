package wyattsamberg.com.ipayment3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import wyattsamberg.com.ipayment3.model.TransactionItem;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String TAG = DatabaseHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "transactionsManager";
    private static final String TABLE_TRANSACTIONS = "transactions";

    private static final String KEY_TRANSACTION_ID = "transactionId";
    private static final String KEY_AUTHORIZED_AMOUNT = "authorizedAmount";
    private static final String KEY_GATEWAY_RESPONSE = "gatewayResponse";
    private static final String KEY_TIP_AMOUNT = "tipAmount";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE " + TABLE_TRANSACTIONS + "(" +
                KEY_TRANSACTION_ID + " STRING PRIMARY KEY," + KEY_AUTHORIZED_AMOUNT + "TEXT," +
                KEY_GATEWAY_RESPONSE + "TEXT," + KEY_TIP_AMOUNT + "TEXT" + ")";
        db.execSQL(CREATE_TRANSACTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);

        onCreate(db);
    }

    public void addTransaction(TransactionItem transactionItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
    }
}
