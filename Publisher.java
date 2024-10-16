public class Publisher {
    private String name;
    private Address address;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + (name != null ? name : "Значение пусто") + '\'' +
                ", address=" + (address != null ? address : "Значение пусто") +
                '}';
    }
    public String toXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<publisher>\n");
        xml.append("<name>").append(name != null ? name : "").append("</name>\n");
        if (address != null) {
            xml.append(address.toXml()).append("\n");
        }
        xml.append("</publisher>");
        return xml.toString();
    }


}
