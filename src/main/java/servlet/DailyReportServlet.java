package servlet;

import com.google.gson.Gson;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyReportServlet extends HttpServlet {

    private CarService carService = CarService.getInstance();
    private DailyReportService reportService = DailyReportService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        String reportJson = "";

        if (request.getPathInfo().contains("all")) {
            reportJson = gson.toJson(reportService.getAllDailyReports());
        } else if (request.getPathInfo().contains("last")) {
            reportJson = gson.toJson(reportService.getLastReport());
        }

        response.getWriter().println(reportJson);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reportService.deleteAll();
        carService.deleteAll();
    }
}