<!DOCTYPE html>
<html>
  <head>
    <title>Ajouter client</title>
    
  <style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    padding: 20px;
  }
  
  form {
    background-color: #ffffff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    width: 300px;
    margin: 0 auto;
  }
  
  label {
    display: block;
    margin-bottom: 10px;
    font-weight: bold;
  }
  
  input[type="text"],
  input[type="number"],
  input[type="email"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    margin-bottom: 20px;
  }
  
  input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  input[type="submit"]:hover {
    background-color: #45a049;
  }
  h3{
      padding-left: 160px;
  }
  .container{
      padding-left:  320px ;
      padding-top: 10px;
  }
</style>

    
  </head>
  <body>
    
      <div class ="container">
          <h3>Ajouter un nouveau client : </h3>
          <form action="AjouterClient" method="get">
        
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom"><br>
        
        <label for="telephone">Telephone :</label>
        <input type="number" id="telephone" name="telephone"><br>
        
        <label for="email">Email :</label>
        <input type="email" id="email" name="email"><br>
        <input type="submit">
    
      </form>
      </div>
      
    
  </body>
</html>
