package database;

interface DatabaseInteractor<T> {
    String table = null;

    // return true if the object is successfully created in table
    boolean create();

    // return object of type T from table
    T query();

    // return true if the object is successfully updated in table
    boolean update();

    // return true if the object is successfully deleted in table
    boolean delete();
}
