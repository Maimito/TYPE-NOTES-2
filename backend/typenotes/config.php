<?php

$server = "localhost";
$user = "root";
$password = "";
$nama_database = "typenotes_db";

$db = mysqli_connect($server, $user, $password, $nama_database);

if( !$db ){
    die("Can't connect to database: " . mysqli_connect_error());
}
?>
