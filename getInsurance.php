<?php
//Import the script with connection to database and server
include('conn.php');

//Specify reg_no as parameter for running PHP script using HTTP GET
$reg_no = $_GET['reg_no'];

//select insurance query
$getInsurance = mysql_query("SELECT * FROM `insurance` WHERE `reg_no` LIKE '$reg_no'");

//Fetch associated rows with the query and echo out. Split with # for separating entries and putting in array
while($row = mysql_fetch_assoc($getInsurance)){
	echo $row['policy_no'] . '#' . $row['issued_by'] . '#' .  $row['start_date'] . '#' . $row['expiry_date'];
}
