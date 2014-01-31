<?php require_once("utilities.php");
$utils=new utilities();
$merch_obj=new merchant_functions();
session_start();
if(isset($_SESSION['merchant']))$muser=$_SESSION['merchant'];
else header("location:../index.php");
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Ezee Pay | Merchant</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/jquery-ui/jquery-ui-1.10.4.custom.min.css">
<link href="../css/user.css" rel="stylesheet">
<script src="../js/jquery.js"></script>
<script src="../js/jquery-ui.js"></script>
<script src="../js/user.js"></script>
</head>
<body>
<fieldset id="content">
	<legend>Welcome, <?php $merch_obj->getMName($muser);?></legend>
    <table class="table table-condensed table-striped" id="userTable">
 		<?php $merch_obj->showMerchantDetails($muser);?>
    </table>
</fieldset>
<div id='referurlPart'>
<fieldset id="referurl">
	<legend>Enter your Referal URL:</legend>
    <form action="referurlupdate.php" method="post">
   	<input type="text" name="referalurl" /><br/>
    <input type="submit" value="Submit"/>
    </form>
</fieldset>
</div>
<button id="referurlbtn" class="btn btn-large btn-success">Add Referal URL</button>
<a href="?page=logout" id="logout"><button class="btn btn-large btn-danger">Logout</button></a>
<?php if(isset($_GET['page'])){
	session_destroy();
	header("location:../index.php");
}
?>
</body>
</html>
