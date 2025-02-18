package interfaces.IVisite;

import java.util.List;

public interface IVisite<T> {

    public void add(T t);
    public void update(T t);
    public void delete(int id);
    List<T> getAll();
    T getOne(int id);



}
