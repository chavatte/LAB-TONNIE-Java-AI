<?php
$servername = "mysql-connection";
$username = "devchavatte";
$password = "WK0o0IxI00z0mMqCs00cBdVXh0ev0WY0";
$database = "feedback_app_db";

$link = new mysqli($servername, $username, $password, $database);

if (mysqli_connect_errno()) {
    printf("Connect failed: %s\n", mysqli_connect_error());
    exit();
}

?>
