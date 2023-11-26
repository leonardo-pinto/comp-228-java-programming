 <html>
     <head>
        <title>My first JSP View</title>
     </head>
     <body>
     <div class="container">
         <form method="post">
             <h1>Welcome to the login page ${name}!</h1>
             <h3>${errorMessage}</h3>
             <label>Name</label>
             <input type="text" name="name"/>
             <label>Password</label>
             <input type="password" name="password"/>
             <button type="submit">Submit</input>
          </form>
     </div>

     </body>
 </html>