package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pais;
import service.PaisService;

@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String acao = request.getParameter("acao");
		
		
				
		//instanciar o service
		PaisService ps = new PaisService();
		RequestDispatcher dispatcher = null;
		
		switch(acao) {
		case "Incluir":
			String pNome = request.getParameter("nome");
			Long pPopulacao = Long.parseLong(request.getParameter("populacao"));
			Double pArea = Double.parseDouble(request.getParameter("area"));
			
			//instanciar o javabean
			Pais pais = new Pais();
			pais.setNome(pNome);
			pais.setPopulacao(pPopulacao);
			pais.setArea(pArea);
			
			ps.criar(pais);
			pais = ps.carregar(pais.getId());
			
			request.setAttribute("pais", pais);
			dispatcher = request.getRequestDispatcher("Pais.jsp");
			break;
		case "Listar":
			ArrayList<Pais> paises = ps.listarTodos();
			request.setAttribute("paises", paises);
			dispatcher = request.getRequestDispatcher("ListaDePaises.jsp");
		}
		
		dispatcher.forward(request, response);
	}
}