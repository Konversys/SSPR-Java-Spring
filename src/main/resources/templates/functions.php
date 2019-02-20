<?php

function QueryExecute(mysqli $connection, string $query)
{
    $result = mysqli_query($connection, $query);
    $data = mysqli_fetch_all($result, MYSQLI_ASSOC);
    return $data;
}

function GetWagons(mysqli $connection)
{
    $query = sprintf("SELECT * from wagon");
    return QueryExecute($connection, $query);
}

function ClearWagon(mysqli $connection, int $wagon)
{
    $query = sprintf("delete from stock where wagon = %d", $wagon);
    return QueryExecute($connection, $query);
}

function ChangeCountProductToWagon(mysqli $connection, int $wagon, int $product, int $count)
{
    $query = sprintf("UPDATE stock SET selled = selled + %d where wagon = %d and product = %d",
     $count, $wagon, $product);
    return QueryExecute($connection, $query);
}

function UpdateStock(mysqli $connection, int $wagon, int $product, int $count)
{
    $query = sprintf("INSERT INTO stock(wagon, product, count, selled) 
    VALUES(%d, %d, %d, 0) 
    ON DUPLICATE KEY UPDATE count = count + %d",
     $wagon, $product, $count, $count);
    return QueryExecute($connection, $query);
}

function GetWagon(mysqli $connection, int $id)
{
    $query = sprintf("SELECT stock.wagon, stock.product, product.title, product.price, stock.count, stock.selled 
    from stock, product
    where stock.product = product.id and stock.wagon = %d", $id);
    return QueryExecute($connection, $query);
}

function GetProducts(mysqli $connection)
{
    $query = sprintf("select product.id, product.title, tag.title as tag, product.price, product.unit, product.img_path 
    from product, tag
    where product.tag = tag.id");
    return QueryExecute($connection, $query);
}

function InsertProduct(mysqli $connection, string $title, int $tag, string $img_path, float $price, string $unit)
{
    $query = sprintf("INSERT INTO product (title, price, tag, img_path, unit)
    VALUES('%s', %f, %d, '%s', '%s')",
     $title, $price, $tag, $img_path, $unit);
    return QueryExecute($connection, $query);
}

function UpdateProduct(mysqli $connection, int $id, string $title, int $tag, string $img_path, float $price, string $unit)
{
    $query = sprintf("UPDATE product SET
     title = '%s', tag = %d, img_path = '%s', price = %f, unit = '%s' where id = %d",
     $title, $tag, $img_path, $price, $unit, $id);
    return QueryExecute($connection, $query);
}

function GetProduct(mysqli $connection, int $id)
{
    $query = sprintf("select * from product where id = %d", $id);
    return QueryExecute($connection, $query);
}

function GetTags(mysqli $connection)
{
    $query = sprintf("select * from tag");
    return QueryExecute($connection, $query);
}


function DeleteProduct(mysqli $connection, int $id)
{
    $query = sprintf("delete from product where id = %d", $id);
    return QueryExecute($connection, $query);
}
?>

