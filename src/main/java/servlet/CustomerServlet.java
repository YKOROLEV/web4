package servlet;

import com.google.gson.Gson;
import model.Car;
import model.DailyReport;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {

    private CarService carService = CarService.getInstance();
    private DailyReportService reportService = DailyReportService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(carService.getAll());
        response.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String licensePlate = request.getParameter("licensePlate");

        Car car = carService.get(brand, model, licensePlate);
        if (car != null) {
            DailyReport report = reportService.getCurrentReport();

            if (report == null) {
                report = reportService.createReport();
            }

            report.setSoldCars(report.getSoldCars() + 1);
            report.setEarnings(report.getEarnings() + car.getPrice());
            reportService.update(report);

            carService.deleteById(car.getId());
        }
    }
}