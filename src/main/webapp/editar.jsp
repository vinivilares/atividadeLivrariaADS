<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Editar</title>
</head>

<body>
	<h1>Editar Livro</h1>
	<form name=formLivro action="update">
		<input type="number" name="codlivro" readonly="readonly"
			value="<%out.print(request.getAttribute("codlivro"));%>" /> <input
			type="text" name="titulo" required
			value="<%out.print(request.getAttribute("titulo"));%>" /> <input
			type="text" name="autor" required
			value="<%out.print(request.getAttribute("autor"));%>" /> <input
			type="text" name="categoria" required
			value="<%out.print(request.getAttribute("categoria"));%>" /> <input
			type="number" name="valor" required
			value="<%out.print(request.getAttribute("valor"));%>" /> 
			<button type="submit" >Salvar</button>
	</form>
</body>
</html>