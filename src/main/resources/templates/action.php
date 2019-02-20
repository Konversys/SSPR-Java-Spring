<?php
require_once 'connect.php';
require_once 'functions.php';

$product_link = 'Location: http://konverdev.ru/product';

if ($_GET['action'] == 'save') {
    UpdateProduct($link, $_GET['id'], $_GET['title'], $_GET['tag'], $_GET['img_path'], $_GET['price'], $_GET['unit']);
    header($product_link);
}

if ($_GET['action'] == 'addItem') {
    InsertProduct($link, $_GET['title'], $_GET['tag'], $_GET['img_path'], $_GET['price'], $_GET['unit']);
    header($product_link);
}

if (stristr($_GET['action'], "item_") !== false) {
    $id = substr($_GET['action'], 5);
    $line = sprintf('Location: http://konverdev.ru/item=%d', $id);
    header($line);
}

if (stristr($_GET['action'], "edit") !== false) {
    $id = substr($_GET['action'], 4);
    $line = sprintf('Location: http://konverdev.ru/product=%d', $id);
    header($line);
}

if (stristr($_GET['action'], "remove") !== false) {
    $id = substr($_GET['action'], 6);
    DeleteProduct($link, $id);
    header($product_link);
}

if (stristr($_GET['action'], "add_") !== false) {
    $product = (int)substr($_GET['action'], 4);
    $wagon = (int)$_GET['id'];
    $total = (int)$_GET[sprintf('total_%d', $product)];
    $selled = (int)$_GET[sprintf('selled_%d', $product)];
    $count = (int)$_GET[sprintf('count_%d', $product)];
    if (($total >=$selled + $count) and ($selled + $count >= 0)) {
        ChangeCountProductToWagon($link, $wagon, $product, $count);
    }
    $line = sprintf('Location: http://konverdev.ru/wagon=%d', $wagon);
    header($line);
}

if (stristr($_GET['action'], "sub_") !== false) {
    $product = (int)substr($_GET['action'], 4);
    $wagon = (int)$_GET['id'];
    $total = (int)$_GET[sprintf('total_%d', $product)];
    $selled = (int)$_GET[sprintf('selled_%d', $product)];
    $count = (int)$_GET[sprintf('count_%d', $product)];
    $count = -$count;
    if (($total >=$selled + $count) and ($selled + $count >= 0)) {
        ChangeCountProductToWagon($link, $wagon, $product, $count);
    }
    $line = sprintf('Location: http://konverdev.ru/wagon=%d', $wagon);
    header($line);
}

if (stristr($_GET['action'], "addToWagon") !== false) {
    $wagon = (int)substr($_GET['action'], 10);
    $product = $_GET['product'];
    $count = $_GET['count'];
    UpdateStock($link, $wagon, $product, $count);
    $line = sprintf('Location: http://konverdev.ru/wagon=%d', $wagon);
    header($line);
}

if (stristr($_GET['action'], "ClearWagon") !== false) {
    $wagon = (int)substr($_GET['action'], 10);
    ClearWagon($link, $wagon);
    $line = sprintf('Location: http://konverdev.ru/wagon=%d', $wagon);
    header($line);
}
?>