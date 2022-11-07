package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/adicionar", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans livro = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			livros(request, response);
		} else if (action.equals("/adicionar")) {
			novoLivro(request, response);
		} else if (action.equals("/select")) {
			listarLivro(request, response);
		} else if (action.equals("/update")) {
			editarLivro(request, response);
		} else if (action.equals("/delete")) {
			removerLivro(request, response);
		}

		else {
			response.sendRedirect("index.html");
		}
	}

	// Listar
	protected void livros(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listar();
		request.setAttribute("livros", lista);
		RequestDispatcher rd = request.getRequestDispatcher("livros.jsp");
		rd.forward(request, response);

	}

	// Adicionar
	protected void novoLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		livro.setCodlivro(Integer.parseInt(request.getParameter("codlivro")));
		livro.setTitulo(request.getParameter("titulo"));
		livro.setAutor(request.getParameter("autor"));
		livro.setCategoria(request.getParameter("categoria"));
		livro.setValor(Double.parseDouble(request.getParameter("valor")));

		dao.adicionar(livro);
		response.sendRedirect("main");
	}

	// Editar
	protected void listarLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int codlivro = Integer.parseInt(request.getParameter("codlivro"));
		livro.setCodlivro(codlivro);
		dao.selecionarLivro(livro);

		request.setAttribute("codlivro", livro.getCodlivro());
		request.setAttribute("titulo", livro.getTitulo());
		request.setAttribute("autor", livro.getAutor());
		request.setAttribute("categoria", livro.getCategoria());
		request.setAttribute("valor", livro.getValor());

		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	protected void editarLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		livro.setCodlivro(Integer.parseInt(request.getParameter("codlivro")));
		livro.setTitulo(request.getParameter("titulo"));
		livro.setAutor(request.getParameter("autor"));
		livro.setCategoria(request.getParameter("categoria"));
		livro.setValor(Double.parseDouble(request.getParameter("valor")));

		dao.alterarLivro(livro);

		response.sendRedirect("main");
	}

	// Deletar
	protected void removerLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codlivro = Integer.parseInt(request.getParameter("codlivro"));

		livro.setCodlivro(codlivro);
		dao.deletarLivro(livro);

		response.sendRedirect("main");
	}
}
