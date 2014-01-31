<?php
session_destroy();
require_once("utilities.php");
$util=new utilities();
$conn=$util->connect_db();
$merch_obj=new merchant_functions();
$name=$_POST['Mname'];
$mail=$_POST['Mmail'];
$user=$_POST['user'];
$pass=$_POST['pass'];
$repass=$_POST['repass'];
$notset="undefined";
if($merch_obj->isAlreadyRegisteredMerchant($user)){
	echo"<script>alert('Username Already Taken!');history.back();</script>";
}
if($pass!=$repass){
	echo"<script>alert('Passwords doesnot match!');history.back();</script>";
}
else{
	$final_pass=md5($pass."@ezeepay");
	$uid=strrev($final_pass);
	$query="insert into master_merchant values('$name','$mail','$user','$final_pass','$uid','$notset')";
	$conn->query($query);
	session_start();
	$_SESSION['merchant']=$user;
	header("location:merchant.php");
}
?>