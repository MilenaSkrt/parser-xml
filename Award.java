public class Award {
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return name != null ? name : "Значение пусто";
    }
    public String toXml() {
        return "<award>" + (name != null ? name : "") + "</award>";
    }

}
