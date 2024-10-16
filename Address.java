public class Address {
    private String city;
    private String country;

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    @Override
    public String toString() {
        return (city != null ? city : "Значение пусто") + ", " + (country != null ? country : "Значение пусто");
    }
    public String toXml() {
        return "<address>\n" +
                "<city>" + (city != null ? city : "") + "</city>\n" +
                "<country>" + (country != null ? country : "") + "</country>\n" +
                "</address>";
    }

}
