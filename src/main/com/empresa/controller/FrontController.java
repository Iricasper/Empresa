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
import java.util.List;
import java.util.Map;

@WebServlet(description = "Administra peticiones para la tabla empleados", urlPatterns = {"/empleados"})
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
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
        } else if (opcion.equals("buscarPorDni")) {

            try {
                RequestDispatcher rd = request.getRequestDispatcher("/views/buscarPorDni.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Mostrando búsqueda de empleados");
        } else if (opcion.equals("buscarPorCampo")) {

            try {
                RequestDispatcher rd = request.getRequestDispatcher("/views/buscarPorCampo.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        if (opcion.equals("buscarPorDni")) {

            String dni;
            EmpleadoDAO empDAO = new EmpleadoDAO();
            try {
                dni = request.getParameter("dni");
                Map sueldoEmpleado = empDAO.obtenerSueldoEmpleado(dni);
                if (!sueldoEmpleado.containsKey("error")) {
                    request.setAttribute("nombre", sueldoEmpleado.get("nombre"));
                    request.setAttribute("dni", sueldoEmpleado.get("dni"));
                    request.setAttribute("sueldo", sueldoEmpleado.get("sueldo"));
                } else {
                    request.setAttribute("error", sueldoEmpleado.get("error"));
                }
                RequestDispatcher rd = request.getRequestDispatcher("/views/buscarPorDni.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Mostrando empleado");

        } else if (opcion.equals("buscarPorCampo")) {

            String campo;
            String valor;
            EmpleadoDAO empDAO = new EmpleadoDAO();
            try {
                campo = request.getParameter("buscarPorCampo");
                valor = request.getParameter("valor");
                Map<Integer, Empleado> empleadosMap = empDAO.obtenerEmpleados(campo, valor);
                RequestDispatcher rd = request.getRequestDispatcher("/views/buscarPorCampo.jsp");
                request.setAttribute("empleadosMap", empleadosMap);
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Mostrando empleados");
        }
    }
}
