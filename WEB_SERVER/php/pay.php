<?php
require_once("utilities.php");
$userObj=new user_functions();
$merchantID=$_POST['merchantID'];
$userID=$_POST['userID'];
$refererURL=$_SERVER['HTTP_REFERER'];
$postfields="?userID=$userID&merchantID=$merchantID";
$ch = curl_init();
curl_setopt($ch,CURLOPT_POST,true);
curl_setopt($ch,CURLOPT_URL,"mobilenotify.php" );
curl_setopt($ch,CURLOPT_RETURNTRANSFER,true );
curl_setopt($ch,CURLOPT_POSTFIELDS,$postfields );
$response = curl_exec($ch);
curl_close($ch);
echo $response;
?>