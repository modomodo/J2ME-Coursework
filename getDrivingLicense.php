<?php
//Import the script with connection to database and server
include('conn.php');

//Specify id_no as parameter for running PHP script using HTTP GET
$id_no = $_GET['id_no'];

//select driving license query
$getDrivingLicense = mysql_query("SELECT * FROM `driving_license` WHERE `id_no` LIKE '$id_no'");

//Fetch associated rows with the query and echo out. Split with # for separating entries and putting in array
while($row = mysql_fetch_assoc($getDrivingLicense)){
	echo $row['ref_no'] . '#' . $row['id_no'] . '#' .  $row['class'] . '#' . $row['reg_no'];
}
