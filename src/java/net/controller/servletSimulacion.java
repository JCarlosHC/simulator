package net.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        String strfechainicio = request.getParameter("fechainit");
        String strfechafin = request.getParameter("fechaend");
        Date fechainicio, fechafin;
        
        try {
            fechainicio = formato.parse(strfechainicio);
            fechafin = formato.parse(strfechafin);
            simulacion datos = new simulacion(fechainicio, fechafin);
            datos.getSimulacion();
            
            request.setAttribute("listaA", datos.getListA());
            request.setAttribute("listaB", datos.getListB());
            request.setAttribute("fresas", datos.getFresas());
            request.setAttribute("tornos", datos.getTornos());
            
            request.setAttribute("success", true);
        } catch (ParseException ex) {
            Logger.getLogger(servletSimulacion.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("succes", false);
        }
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
