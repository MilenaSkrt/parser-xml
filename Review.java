public class Review {
    private String user;
    private int rating;
    private String comment;

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() {
        return "Review{" +
                "user='" + (user != null ? user : "Значение пусто") + '\'' +
                ", rating=" + rating +
                ", comment='" + (comment != null ? comment : "Значение пусто") + '\'' +
                '}';
    }
    public String toXml() {
        return "<review>\n" +
                "<user>" + (user != null ? user : "") + "</user>\n" +
                "<rating>" + rating + "</rating>\n" +
                "<comment>" + (comment != null ? comment : "") + "</comment>\n" +
                "</review>";
    }

}
