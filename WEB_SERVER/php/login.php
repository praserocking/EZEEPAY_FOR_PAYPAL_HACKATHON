<?php
session_start();
require_once "utilities.php";
$user=$_POST['user'];
$pass=$_POST['pass'];
$usertype="null";
$final_pass=md5($pass."@ezeepay");
$util=new utilities();
$conn=$util->connect_db();
$result=$conn->query("select pass from master_user where username='$user'");
if($result->num_rows==1)$usertype="user";
else{
	$result=$conn->query("select pass from master_merchant where username='$user'");
	if($result->num_rows==1)$usertype="merchant";
}
switch($usertype){
	case "null":{
		echo "<script>alert('Wrong User Name or Password');history.back();</script>";
	}
	case "user":{
		$temp_array=$result->fetch_array();
		if($temp_array[0]==$final_pass){
			$_SESSION['user']=$user;
			header("location:user.php");
		}
	}
	case "merchant":{
		$temp_array=$result->fetch_array();
		if($temp_array[0]==$final_pass){
			$_SESSION['merchant']=$user;
			header("location:merchant.php");
		}
	}
}
?>
