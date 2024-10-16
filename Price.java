public class Price {
    private double amount;
    private String currency;

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    @Override
    public String toString() {
        return amount + " " + (currency != null ? currency : "Значение пусто");
    }
    public String toXml() {
        return "<price currency=\"" + (currency != null ? currency : "") + "\">" + amount + "</price>";
    }

}
