<?php
include ('conn.php');
$email_address = $_GET['email_address'];
$password = $_GET['password'];
$phone_number = $_GET['phone_number'];

//create a query to add a new user
$adduser = mysql_query("INSERT INTO `project`.`users` (`email_address`, `password`, `phone_number`) VALUES ('$email_address', '$password', '$phone_number');");


if($adduser){
	echo "1";
	}else{
	echo "0";
	}

?>