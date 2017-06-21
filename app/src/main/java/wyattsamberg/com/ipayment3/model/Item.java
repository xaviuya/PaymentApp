package wyattsamberg.com.ipayment3.model;


public class Item {
    public static final String TAG = Item.class.getSimpleName();

    public String lblPrice;
    public String lblTitle;
    public String lblDescription;
    public String lblCount;

    public Item(String lblTitle, String lblDescription, String lblPrice) {
        this.lblTitle = lblTitle;
        this.lblDescription = lblDescription;
        this.lblPrice = lblPrice;
    }

    public Item(String lblTitle, String lblDescription, String lblPrice, String lblCount) {
        this.lblTitle = lblTitle;
        this.lblDescription = lblDescription;
        this.lblPrice = lblPrice;
        this.lblCount = lblCount;
    }

    public String getLblPrice() {
        return lblPrice;
    }

    public void setLblPrice(String lblPrice) {
        this.lblPrice = lblPrice;
    }

    public String getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(String lblTitle) {
        this.lblTitle = lblTitle;
    }

    public String getLblDescription() {
        return lblDescription;
    }

    public void setLblDescription(String lblDescription) {
        this.lblDescription = lblDescription;
    }

    public String getLblCount() {
        return lblCount;
    }

    public void setLblCount(String lblCount) {
        this.lblCount = lblCount;
    }
}
