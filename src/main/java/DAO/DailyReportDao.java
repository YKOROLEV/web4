package DAO;

import model.DailyReport;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class DailyReportDao extends AbstractDao {

    public DailyReportDao(Session session) {
        super(session);
    }

    public DailyReport get(long id) {
        return runInSession(session ->
                (DailyReport) session.get(DailyReport.class, id)
        );
    }

    @SuppressWarnings("unchecked")
    public List<DailyReport> getAllDailyReport() {
        return runInSession(session ->
                (List<DailyReport>) session.createQuery("from DailyReport").list()
        );
    }

    public DailyReport getLastReport() {
        return runInSession(session -> {
            DailyReport report = (DailyReport) session.createQuery("from DailyReport ORDER BY id DESC")
                    .setMaxResults(1)
                    .uniqueResult();

            return (DailyReport) session.get(DailyReport.class, report.getLastReportId());
        });
    }

    public DailyReport getCurrentReport() {
        return runInSession(session ->
                (DailyReport) session.createQuery("from DailyReport ORDER BY id DESC")
                        .setMaxResults(1)
                        .uniqueResult()
        );
    }

    public DailyReport createReport() {
        return runInSession(session -> {
            Long id = (Long) session.save(new DailyReport(0L, 0L));
            return (DailyReport) session.get(DailyReport.class, id);
        });
    }

    public void newReport() {
        runInSession(session -> {
            Long id = (Long) session.createQuery("select id from DailyReport ORDER BY id DESC")
                    .setMaxResults(1)
                    .uniqueResult();

            session.save(new DailyReport(0L, 0L, id));
        });
    }

    public void update(DailyReport report) {
        runInSession(session -> {
            session.saveOrUpdate(report);
        });
    }

    public void deleteAll() {
        runInSession(session -> {
            Query query = session.createQuery("delete from DailyReport");
            query.executeUpdate();
        });
    }
}