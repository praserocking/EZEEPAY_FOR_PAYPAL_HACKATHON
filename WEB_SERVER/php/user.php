<?php
require_once("utilities.php");
$utils=new user_functions();
session_start();
if(isset($_SESSION['user']))$user=$_SESSION['user'];
else header("location:../index.php");
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Ezee Pay | User</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/jquery-ui/jquery-ui-1.10.4.custom.min.css">
<link href="../css/user.css" rel="stylesheet">
<script src="../js/jquery.js"></script>
<script src="../js/jquery-ui.js"></script>
<script src="../js/user.js"></script>
</head>
<body>
<fieldset id="content">
	<legend>Welcome, <?php $utils->getName($user);?></legend>
    <table class="table table-condensed table-striped" id="userTable">
 		<?php $utils->showUserDetails($user);?>
    </table>
 </fieldset>
<a href="?page=logout" id="logout"><button class="btn btn-large btn-danger">Logout</button></a>
<div id="DB_cardForm">
      <form action="carddetails.php" method="post">
        <fieldset>
          <legend>Enter Debit Card Details</legend>
          <table class="table table-condensed table-striped">
          	  <tr>
                  <td><label>Bank Name</label></td>
                  <td><input type="text" placeholder="Bank Name" name="bankname"></td>
              </tr>
          	  <tr>
                  <td><label>Card Number</label></td>
                  <td><input type="text" placeholder="Card Number" name="cardnum"></td>
              </tr>
              <tr>
                  <td><label>Expiry date:</label></td>
                  <td><input type="text" placeholder="Expiry Date" id="expdate" name="expdate"></td>
              </tr>
              <tr>
                  <td><label>CCV:</label></td>
                  <td><input type="text" placeholder="CCV" name="ccv"></td>
              </tr>
              <tr>
                  <td><label>Online Transaction Password:</label></td>
                  <td><input type="text" placeholder="Password" name="trnspass"></td>
              </tr>
              <tr>
                  <td><label>ATM Pin:</label></td>
                  <td><input type="text" placeholder="PIN" name="pin"></td>
              </tr>
          </table>
           <center>
              <button type="submit" class="btn btn-large">Add Details</button>
          </center>
         </fieldset>
        </form>
       </div>
      <button class="btn btn-large btn-warning" id="details">Add Debit Card Details</button>
      <div id="dbcarddet">
      	<fieldset>
        <legend>Debit Cards Registered</legend>
        	<table class="table table-condensed table-striped" id="userTable">
 		<?php $utils->showUserBanks($user);?>
    		</table>
        </fieldset>
      </div>
<?php if(isset($_GET['page'])){
	session_destroy();
	header("location:../index.php");
}
?>
</body>
</html>
