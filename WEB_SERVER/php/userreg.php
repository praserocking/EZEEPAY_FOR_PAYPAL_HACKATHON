<?php
session_destroy();
require_once("utilities.php");
$util=new utilities();
$conn=$util->connect_db();
$user_obj=new user_functions();
$name=$_POST['name'];
$mail=$_POST['mail'];
$user=$_POST['user'];
$pass=$_POST['pass'];
$repass=$_POST['repass'];
$notset="undefined";
if($user_obj->isAlreadyRegisteredUser($user)){
	echo"<script>alert('Username Already Taken!');history.back();</script>";
}
if($pass!=$repass){
	echo"<script>alert('Passwords doesnot match!');history.back();</script>";
}
else{
	$final_pass=md5($pass."@ezeepay");
	$uid=strrev($final_pass);
	$query="insert into master_user values('$name','$mail','$user','$final_pass','$uid','$notset','$notset')";
	$conn->query($query);
	session_start();
	$_SESSION['user']=$user;
	header("location:user.php");
}
?>