package net.controller;

import com.json.JSONArray;
import com.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.dao.simulacion;

public class servletSimulacion extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String action = request.getParameter("action");
        simulacion datos = new simulacion();
        
        if(action.equals("getSimulacion")){
            JSONObject objJson = datos.getSimulacion(); 
            PrintWriter out = response.getWriter();
            out.print(objJson);
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
