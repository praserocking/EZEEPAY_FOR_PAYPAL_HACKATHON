<?php
session_start();
require_once("utilities.php");
$cipher=new cipher();
$util=new utilities();
$user_obj=new user_functions();
$bankname=$_POST['bankname'];
$cardnum=$_POST['cardnum'];
$expdate=$_POST['expdate'];
$ccv=$_POST['ccv'];
$trnspass=$_POST['trnspass'];
$pin=$_POST['pin'];
$user=$_SESSION['user'];
if($user_obj->isAlreadyRegisteredCardNum($cardnum)){
	echo "<script>alert('Card Number is already Registered!');history.back();</script>";
}else{
	$e_expdate=$cipher->encrypt($expdate);
	$e_ccv=$cipher->encrypt($ccv);
	$e_trnspass=$cipher->encrypt($trnspass);
	$e_pin=$cipher->encrypt($pin);
	$uid=$user_obj->getUid($user);
	$query="insert into db_cards values('$bankname','$cardnum','$e_expdate','$e_ccv','$e_trnspass','$uid','$e_pin')";
	$conn=$util->connect_db();
	$conn->query($query);
	echo "<script>alert('Successfully added');</script>";
	header("location:user.php");
}
?>

