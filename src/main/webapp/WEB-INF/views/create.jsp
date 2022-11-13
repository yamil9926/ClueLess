<html>
    <head>
    </head>
    <body>
        <h2>Create Game</h2>
	<div>
		<form id="createForm" method="post" action="">
		Game Security: <select name="type"><option value="public" <c:if test="${type=='public'}"></c:if>>Public</option><option value="private" <c:if test="${type=='private'}"></c:if>>Private</option></select><br />
		<br />
		Game Name: <input type="text" name="name" value="${name}">
		Password: <input type="password" name="password" value=""><br /><br />
		</form>
		${errorMessage}<br />
		<button onClick="document.getElementById('createForm').submit();return false;">Create Game</button>
		<button onClick="location.href='home';return false;">Main Menu</button>
	</div>
    </body>
</html>