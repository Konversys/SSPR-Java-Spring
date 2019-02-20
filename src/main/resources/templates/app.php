<?php
require_once __DIR__ . "/../vendor/autoload.php";

$app = new Silex\Application();
$app->register(new Silex\Provider\TwigServiceProvider(), array(
    'twig.path' => __DIR__ . '/../routes',
));

$app->get("/product", function () use ($app, $link) {
    $products = GetProducts($link);
    $tags = GetTags($link);
    return $app['twig']->render("product.html", array('products' => $products, 'tags' => $tags));
});

$app->get("/item={id}", function ($id) use ($app, $link) {
    $product = GetProduct($link, $id)[0];
    return $app['twig']->render("product_item.html", array('product' => $product));
});

$app->get("/product={id}", function ($id) use ($app, $link) {
    $product = GetProduct($link, $id)[0];
    $tags = GetTags($link);
    return $app['twig']->render("product_edit.html", array('product' => $product, 'tags' => $tags));
});

$app->get("/wagon", function () use ($app, $link) {
    $wagons = GetWagons($link);
    $id = $wagons[0]['id'];
    $wagon = GetWagon($link, $id);
    return $app['twig']->render("wagon.html", array('wagon' => $wagon, 'wagons' => $wagons, 'wagon_id' => $id));
});


$app->get("/wagon={id}", function ($id) use ($app, $link) {
    $wagon = GetWagon($link, $id);
    $wagons = GetWagons($link);
    $products = GetProducts($link);
    return $app['twig']->render("wagon.html", array(
        'products' => $products, 'wagon' => $wagon, 'wagons' => $wagons, 'wagon_id' => $id
    ));
});

return $app;
?>