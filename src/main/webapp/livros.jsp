<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("livros");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Lista de livros</title>
</head>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

a {
	text-decoration: none;
}

table {
	border-collapse: collapse;
}

caption {
	font-size: 1.2em;
	font-weight: bolder;
	padding: 20px;
}

th, td {
	border: 1px solid black;
	padding: 10px;
}

tr:nth-child(even) {
	background-color: #d1d1d1;
}

div#container {
	overflow-x: auto;
}
</style>
<body>
	<h1>Livros</h1>
	<button><a href="novo.html">Novo livro</a></button>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Titulo</th>
				<th>Autor</th>
				<th>Categoria</th>
				<th>Valor</th>
				<th colspan="2">Opções</th>
			</tr>
		</thead>

		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getCodlivro()%></td>
				<td><%=lista.get(i).getTitulo()%></td>
				<td><%=lista.get(i).getAutor()%></td>
				<td><%=lista.get(i).getCategoria()%></td>
				<td><%=lista.get(i).getValor()%></td>
				<td><button>
						<a href="select?codlivro=<%=lista.get(i).getCodlivro()%>">Editar</a>
					</button></td>
				<td><button>
						<a href="delete?codlivro=<%=lista.get(i).getCodlivro()%>">Remover</a>
					</button></td>
			</tr>

			<%
			}
			%>
		</tbody>
	</table>
</body>
</html>