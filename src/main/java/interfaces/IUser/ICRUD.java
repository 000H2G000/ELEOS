package interfaces.IUser;

import java.util.List;

public interface ICRUD<T> {
    T findById(Object id);
    List<T> findAll();
    boolean insert(T obj);
    boolean update(T obj);
    boolean delete(Object id);
}
