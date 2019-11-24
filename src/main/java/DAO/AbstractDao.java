package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

public class AbstractDao {

    private Session session;

    public AbstractDao(Session session) {
        this.session = session;
    }

    protected void runInSession(Consumer<Session> consumer) {
        Transaction transaction = session.beginTransaction();

        try {
            consumer.accept(session);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        session.close();
    }

    protected <R> R runInSession(Function<Session, R> function) {
        Transaction transaction = session.beginTransaction();
        R result = null;

        try {
            result = function.apply(session);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        session.close();
        return result;
    }
}