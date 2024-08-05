package model;

/**
 * The Bill class represents a bill in the system.
 * It is a record class, immutable and consists of three fields: id, idClient, and price.
 *
 * @param id       The unique identifier of the bill.
 * @param idClient The unique identifier of the client associated with the bill.
 * @param price    The price of the bill.
 */
public record Bill(int id, int idClient, int price) {

}
