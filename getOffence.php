<?php
//Import the script with connection to database and server
include('conn.php');

//Specify id_no as parameter for running PHP script using HTTP GET
$id_no = $_GET['id_no'];

//select offence query
$getOffence = mysql_query("SELECT * FROM `offence` WHERE `id_no` LIKE '$id_no'");

//Fetch associated rows with the query and echo out. Split with # for separating entries and putting in array
while($row = mysql_fetch_assoc($getOffence)){
	echo $row['offence_no'] . '#' . $row['reg_no'] . '#' .  $row['offence'];
}
