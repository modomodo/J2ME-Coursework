<?php
include ('conn.php');
$first_name = $_GET['first_name'];
//$middle_name = $_GET['middle_name'];
$last_name = $_GET['last_name'];
$id_no = $_GET['id_no'];


//register query
$registerQuery = mysql_query("SELECT *  FROM `users` WHERE `first_name` LIKE '$first_name' AND `last_name` LIKE '$last_name' AND `id_no` LIKE '$id_no';");

//check rows returned
$rows_num = mysql_num_rows($registerQuery);
if($rows_num>0){
echo 1;
}else{
echo 0;
}

?>