package service;

import DAO.DailyReportDao;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public DailyReport get(long id) {
        return new DailyReportDao(sessionFactory.openSession()).get(id);
    }

    public List<DailyReport> getAllDailyReports() {
        return new DailyReportDao(sessionFactory.openSession()).getAllDailyReport();
    }

    public DailyReport getCurrentReport() {
        return new DailyReportDao(sessionFactory.openSession()).getCurrentReport();
    }

    public DailyReport getLastReport() {
        return new DailyReportDao(sessionFactory.openSession()).getLastReport();
    }

    public void update(DailyReport report) {
        new DailyReportDao(sessionFactory.openSession()).update(report);
    }

    public DailyReport createReport() {
        return new DailyReportDao(sessionFactory.openSession()).createReport();
    }

    public void newReport() {
        new DailyReportDao(sessionFactory.openSession()).newReport();
    }

    public void deleteAll() {
        new DailyReportDao(sessionFactory.openSession()).deleteAll();
    }
}
