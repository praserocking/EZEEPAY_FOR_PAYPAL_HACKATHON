  <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Ezee Pay | Home</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/jquery-ui/jquery-ui-1.10.4.custom.min.css">
  <link href="css/index.css" rel="stylesheet">
  <script src="js/jquery.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/index.js"></script>
  </head>
  <body>
  <div id=content>
  <center><h1>Ezee Pay<br/><small>Your Ultimate PayMate</small></h1></center>
  <center>
      <button class="btn btn-large" id="registerBtn">Register</button>
      <button class="btn btn-large" id="loginBtn">Login</button>
  </center>
  </div>
  <div id=buildup>
  <center><h3>We Care for All Your Online Payment Needs<br/><small>All Integrated with your Smart Phone!</small></h3></center>
  </div> 
  <div id=regTabs>
  <ul>
      <li><a href="#userRegForm">User Registration</a></li>
      <li><a href="#merchantRegForm">Merchant Registration</a></li>
  </ul>
      <div id=userRegForm>
      <form action="php/userreg.php" method="post">
        <fieldset>
          <legend>User Registration</legend>
          <table class="table table-condensed table-striped">
              <tr>
                  <td><label>Name</label></td>
                  <td><input type="text" placeholder="Your Name" name="name"></td>
              </tr>
              <tr>
                  <td><label>Mail ID</label></td>
                  <td><input type="text" placeholder="Mail ID" name="mail"></td>
              </tr>
              <tr>
                  <td><label>UserName</label></td>
                  <td><input type="text" placeholder="Username" name="user"></td>
              </tr>
              <tr>
                  <td><label>Password</label></td>
                  <td><input type="password" placeholder="Password" name="pass"></td>
              </tr>
              <tr>
                  <td><label>Re-Type Password</label></td>
                  <td><input type="password" placeholder="Re-Type Password" name="repass"></td>
              </tr>
          </table>
          <center>
              <button type="submit" class="btn btn-large">Submit</button>
          </center>
        </fieldset>
      </form>
      </div>
      <div id=merchantRegForm>
      <form action="php/merchreg.php" method="post">
        <fieldset>
          <legend>Merchant Registration</legend>
          <table class="table table-condensed table-striped">
              <tr>
                  <td><label>Company Name</label></td>
                  <td><input type="text" placeholder="Company Name" name="Mname"></td>
              </tr>
              <tr>
                  <td><label>Company Mail ID</label></td>
                  <td><input type="text" placeholder="Company Mail ID" name="Mmail"></td>
              </tr>
              <tr>
                  <td><label>UserName</label></td>
                  <td><input type="text" placeholder="Username" name="user"></td>
              </tr>
              <tr>
                  <td><label>Password</label></td>
                  <td><input type="password" placeholder="Password" name="pass"></td>
              </tr>
              <tr>
                  <td><label>Re-Type Password</label></td>
                  <td><input type="password" placeholder="Re-Type Password" name="repass"></td>
              </tr>
          </table>
          <center>
              <button type="submit" class="btn btn-large">Submit</button>
          </center>
        </fieldset>
      </form>
      </div>
     </div>
     <div id=loginForm>
      <form action="php/login.php" method="post">
        <fieldset>
          <legend>Login to Your Profile</legend>
          <table class="table table-condensed table-striped">
                  <td><label>UserName</label></td>
                  <td><input type="text" placeholder="Username" name="user"></td>
              </tr>
              <tr>
                  <td><label>Password</label></td>
                  <td><input type="password" placeholder="Password" name="pass"></td>
              </tr>
          </table>
          <center>
              <button type="submit" class="btn btn-large">Login</button>
          </center>
        </fieldset>
      </form>
      </div>
      <!-- Clouds Background -->
      <div id="clouds">
      <div class="cloud x1"></div>
      <div class="cloud x2"></div>
      <div class="cloud x3"></div>
      <div class="cloud x4"></div>
      <div class="cloud x5"></div>
  </div>
  </body>
  </html>