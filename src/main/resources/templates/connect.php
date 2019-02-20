<?php

$link = mysqli_connect("localhost", "root","","sspr");
mysqli_set_charset($link, 'utf8');
if (mysqli_connect_errno()) {
    echo 'Ошибка подключения (' . mysqli_connect_errno() . '): ' . mysqli_connect_error();
    exit();
}
?>