<?php
require_once("utilities.php");
$userobj=new user_functions();
$cipher=new cipher();
$util=new utilities();
$merchobj=new merchant_functions();
$bank=$_POST['bank'];
$cardnum=$_POST['cardnum'];
$user=$_POST['user'];
$merchant=$_POST['merchant'];
$referurl=$merchobj->getReferURL($merchant);
$conn=$util->connect_db();
$query="select exp_dt,ccv,passwd,pin from db_cards where db_card_no='$cardnum'";
$result=$conn->query($query);
$temp_array=$result->fetch_array();
$exp_dt=$cipher->decrypt($temp_array[0]);
$ccv=$cipher->decrypt($temp_array[1]);
$passwd=$cipher->decrypt($temp_array[2]);
$pin=$cipher->decrypt($temp_array[3]);
$msg=array(
	"bank"=>$bank,
	"cardnum"=>$cardnum,
	"exp_date"=>$exp_dt,
	"ccv"=>$ccv,
	"password"=>$passwd,
	"pin"=>$pin,
	"customerUID"=>$user
	);
$ch=curl_init();
$headers = array("Content-Type:"."application/json","Provider:"."EZEEPAY");
curl_setopt( $ch, CURLOPT_HTTPHEADER,$headers ); 
curl_setopt( $ch, CURLOPT_URL,$referurl);
curl_setopt( $ch, CURLOPT_SSL_VERIFYHOST,1);
curl_setopt( $ch, CURLOPT_SSL_VERIFYPEER,1);
curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );
curl_setopt( $ch, CURLOPT_POSTFIELDS,$msg);
$response=curl_exec($ch);
curl_close($ch);
if($response!=FALSE)echo "success";
?>
