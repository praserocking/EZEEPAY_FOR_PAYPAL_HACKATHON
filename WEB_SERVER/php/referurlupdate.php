<?php
require_once("utilities.php");
$util=new utilities();
session_start();
$user=$_SESSION['merchant'];
$url=$_POST['referalurl'];
$conn=$util->connect_db();
$query="update master_merchant set referalurl='$url' where username='$user'";
$conn->query($query);
header("location:merchant.php");
?>
