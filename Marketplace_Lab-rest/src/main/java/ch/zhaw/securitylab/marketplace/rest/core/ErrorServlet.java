package ch.zhaw.securitylab.marketplace.rest.core;

import java.io.IOException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ch.zhaw.securitylab.marketplace.common.model.RestErrorDto;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int status = response.getStatus();
        RestErrorDto error = new RestErrorDto();
        switch(status) {
            case 401:
                error.setError("Authentication required");
                break;
            case 403:
                error.setError("Access denied");
                break;
            case 404:
                error.setError("Not found");
                break;
            case 405:
                error.setError("Method not allowed");
                break;
            default:
                error.setError("Unknown error");
        }
        Jsonb jsonb = JsonbBuilder.create();
        String errorJson = jsonb.toJson(error);
        response.getWriter().write(errorJson);
    }
}
