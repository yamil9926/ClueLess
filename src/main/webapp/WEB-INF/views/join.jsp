<html>
    <head>
    </head>
    <body>
        <h2>Join Game</h2>
	<div>
		<form id="joinForm" method="post" action="">
		<br />
		Game Name: <input type="text" name="name" value="${name}">
		Password: <input type="password" name="password" value="${password}"><br /><br />
        <input id="type" type="hidden" name="type"  value="">
		</form>
		${errorMessage}<br />
	</div>
        <button onClick="document.getElementById('type').value='private';document.getElementById('joinForm').submit();return false;">Join Private Game</button>
        <button onClick="location.href='home';return false;">Home</button>
        <button onClick="document.getElementById('type').value='public';document.getElementById('joinForm').submit();return false;">Join Public Game</button>
    </body>
</html>