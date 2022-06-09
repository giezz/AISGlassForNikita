package glass.aisglass.db;

public enum Queries {
    DELIVERY_INSERT("INSERT INTO delivery VALUES (DEFAULT, ?, ?, ?)"),
    DELIVERY_GET_ALL("SELECT * FROM delivery ORDER BY number_of_delivery DESC"),
    DELIVERY_DELETE("DELETE FROM delivery WHERE number_of_delivery = ?"),
    DELIVERY_UPDATE("UPDATE delivery SET " +
            "price_of_delivery = ?, " +
            "address_of_delivery = ?, " +
            "description_of_delivery = ? " +
            "WHERE number_of_delivery = ?"),
    DELIVERY_GET_BY_ID("SELECT * FROM delivery WHERE number_of_delivery = ?"), ;

    String QUERY;

    Queries(String QUERY) {
        this.QUERY = QUERY;
    }
}
