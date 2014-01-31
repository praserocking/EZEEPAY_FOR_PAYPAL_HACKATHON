<?php
require_once "utilities.php";
$user=$_POST['username'];
$pass=$_POST['password'];
$devid=$_POST['devid'];
$model=$_POST['model'];
$util=new utilities();
$user_obj=new user_functions();
if($user_obj->isAlreadyRegisteredUser($user)){
	$cmppass=$util->getUserPass($user);
	$finalpass=md5($pass."@ezeepay");
	if($cmppass==$finalpass){
		$query="update master_user set devid='$devid',devname='$model' where username='$user' and pass='$finalpass'";
		$conn=$util->connect_db();
		$conn->query($query);
		echo "success";
	}else{
		echo "Wrong password!";
	}
}else{
	echo "Wrong Username And Password!";
}
?>