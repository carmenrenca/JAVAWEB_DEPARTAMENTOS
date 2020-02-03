package com.ecodeup.articulo.controller;
 
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Dep.DAOFactory;
import Dep.Departamento;
import Dep.DepartamentoDAO;
import Dep.NeodatisDAOFactory;
import Dep.SqlDbDAOFactory;



 
/**
 * Servlet implementation class AdminArticulo
 */
@WebServlet("/")
public class AdminArticulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	   
	   DAOFactory factory = new SqlDbDAOFactory();
       DepartamentoDAO dpto = factory.getDepartamentoDAO();

   
	   public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			 
			SqlDbDAOFactory.crearConexion();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminArticulo() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "register":
				System.out.println("entro");
				registrar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "showedit":
				showEditar(request, response);
				break;	
			case "editar":
				editar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");


		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//mostrar(request, response);
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
 
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Departamento d = new Departamento(Integer.parseInt(request.getParameter("codigo")) , request.getParameter("nombre"), request.getParameter("localidad"));
		System.out.println(d.getDnombre());
        dpto.InsertarDep(d);
        

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		System.out.println("%%%%%%%%%%%%");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/register.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
		List<Departamento> listadepartamento= dpto.MostrarDepartamentos();
for(int i=0; i<listadepartamento.size(); i++) {
	System.out.println(listadepartamento.get(i).getDnombre());
}
		request.setAttribute("lista", listadepartamento);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		Departamento d = dpto.ConsultarDep(Integer.parseInt(request.getParameter("id")));
	System.out.println(d.getDnombre());
		request.setAttribute("departamento", d);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/editar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		System.out.println("ENTRA EN EDITAR");
		Departamento d= new Departamento(Integer.parseInt(request.getParameter("codigo")),request.getParameter("nombre"),request.getParameter("localidad"));
String codigo=request.getParameter("codigo");
		int id= Integer.parseInt(codigo); 

System.out.println(id+"idd");
try {

	dpto.ModificarDep( id, d);

	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	dispatcher.forward(request, response);

}catch( java.util.IllegalFormatConversionException e) {


	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	dispatcher.forward(request, response);
}
	
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		 dpto.EliminarDep(Integer.parseInt(request.getParameter("id")));
		 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}
}