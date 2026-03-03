<!DOCTYPE html>

<html>
    <head>
        <title>Login Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div class="container">
            <div class="login-form">
                <h1>Login</h1>
                <form action="loginServlet" method="POST">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required><br><br>
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required><br><br>
                    <input type="submit" value="Login">
                </form>
                <% if (request.getAttribute("msg") != null) { %>
                    <p class="error-message"><%= request.getAttribute("msg") %></p>
                <% } %>
            </div>
        </div>
    </body>
</html>

