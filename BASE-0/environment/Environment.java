package environment;

public interface Environment<T> {

    Environment<T> beginScope();

    Environment<T> endScope();

    void assoc(String id, T bind);

    T find(String id);
}
