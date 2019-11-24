package servlet;

import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProducerServlet extends HttpServlet {

    private CarService service = CarService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status = HttpServletResponse.SC_FORBIDDEN;
        Car car = transformFromRequest(request);

        if (service.countByBrand(car.getBrand()) < 10
                && !service.hasByLicensePlate(car.getLicensePlate())) {
            if (service.add(car)) {
                status = HttpServletResponse.SC_OK;
            }
        }

        response.setStatus(status);
    }

    private Car transformFromRequest(HttpServletRequest request) {
        String brand = request.getParameter("brand");
        String licensePlate = request.getParameter("licensePlate");
        String model = request.getParameter("model");
        Long price = Long.valueOf(request.getParameter("price"));

        return new Car(brand, model, licensePlate, price);
    }
}