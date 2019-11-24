package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "daily_reports")
public class DailyReport implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "earnings")
    private Long earnings;

    @Column(name = "soldCars")
    private Long soldCars;

    @Column(name = "lastReportId")
    private Long lastReportId;

    public DailyReport() {

    }

    public DailyReport(Long earnings, Long soldCars) {
        this.earnings = earnings;
        this.soldCars = soldCars;
    }

    public DailyReport(Long earnings, Long soldCars, Long lastReportId) {
        this.earnings = earnings;
        this.soldCars = soldCars;
        this.lastReportId = lastReportId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings = earnings;
    }

    public Long getSoldCars() {
        return soldCars;
    }

    public void setSoldCars(Long soldCars) {
        this.soldCars = soldCars;
    }

    public Long getLastReportId() {
        return lastReportId;
    }

    public void setLastReportId(Long lastReportId) {
        this.lastReportId = lastReportId;
    }
}