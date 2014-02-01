<?php
require_once("utilities.php");
$userObj=new user_functions();
$merchantID=$_POST['merchantID'];
$userID=$_POST['userID'];
$userDevID=$userObj->getDevID($userID);
$bankdetails=$userObj->getBankDetails($userID);
$bankscount=$bankdetails->num_rows;
$i=0;
$msg=array("count"=>$bankscount);
while($temp=$bankdetails->fetch_array()){
	$i++;
	$msg=array("bank".$i =>$temp[0]);
	$msg=array("cardnum".$i =>$temp[1]);
}
$msg=array("user"=>$userID);
$msg=array("merchant"=>$merchantID);
$response=sendGCMNotification(array($userDevID),$msg);
echo $response;
?>
