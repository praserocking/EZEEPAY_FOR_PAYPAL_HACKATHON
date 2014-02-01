<?php
$merchantID=$_POST['merchantID'];
$userID=$_POST['userID'];
$postfields=array("userID"=>$userID,"merchantID"=>$merchantID);
$ch = curl_init();
curl_setopt($ch,CURLOPT_POST,true);
curl_setopt($ch,CURLOPT_URL,"http://localhost/HACKATHON-PayPal/php/mobilenotify.php" );
curl_setopt($ch,CURLOPT_POSTFIELDS,$postfields );
$response = curl_exec($ch);
curl_close($ch);
echo $response;
?>