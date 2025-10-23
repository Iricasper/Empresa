package com.empresa.controller;

import com.empresa.dao.EmpleadoDAO;
import com.empresa.model.Empleado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(description = "Administra peticiones para la tabla empleados", urlPatterns = {"/empleados"})
public class EmpleadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpleadoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String opcion = request.getParameter("opcion");

        if (opcion.equals("listar")) {

            EmpleadoDAO empDAO = new EmpleadoDAO();
            List<Empleado> lista;
            try {
                lista = empDAO.obtenerEmpleados();
                for (Empleado empleado : lista) {
                    System.out.println(empleado);
                }

                request.setAttribute("lista", lista);
                RequestDispatcher rd = request.getRequestDispatcher("/views/listar.jsp");
                rd.forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Listando empleados");
        } else if (opcion.equals("buscar")) {

            EmpleadoDAO empDAO = new EmpleadoDAO();
            try {
                RequestDispatcher rd = request.getRequestDispatcher("/views/buscar.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Mostrando búsqueda de empleados");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        if (opcion.equals("buscar")) {

            String dni;
            EmpleadoDAO empDAO = new EmpleadoDAO();
            try {
                dni = request.getParameter("dni");
                request.setAttribute("dni", dni);
                Map sueldoEmpleado = empDAO.obtenerSueldoEmpleado(dni);
                RequestDispatcher rd = request.getRequestDispatcher("/views/buscar.jsp");

                rd.forward(request, response);
            } catch  (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Mostrando empleado");
        }
    }
}
