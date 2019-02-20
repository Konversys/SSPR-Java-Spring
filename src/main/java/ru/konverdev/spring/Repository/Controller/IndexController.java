package ru.konverdev.spring.Repository.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.konverdev.spring.Domain.Product;
import ru.konverdev.spring.Domain.Special.RestObject;
import ru.konverdev.spring.Repository.ProductRepository;
import ru.konverdev.spring.Repository.Special.Utils;
import ru.konverdev.spring.Repository.StockRepository;
import ru.konverdev.spring.Repository.TagRepository;
import ru.konverdev.spring.Repository.WagonRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


// TODO: Продукты. Запись в бд редактированной записи. Запись новой записи. Удаление записи
// TODO: Вагон. Добавление удаление позиций
// TODO: Вагон. Переключение между вагонами
@Controller
public class IndexController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WagonRepository wagonRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private StockRepository stockRepository;

    @GetMapping("/product")
    public ModelAndView Product() {
        Map<String, Object> model = new HashMap<>();
        model.put("products", Utils.GetRProduct(productRepository.findAll(), tagRepository.findAll()));
        model.put("tags", tagRepository.findAll());
        return new ModelAndView("product", model);
    }

    @GetMapping("/product={id}")
    public ModelAndView ProductId(@PathVariable("id") Integer id) {
        Map<String, Object> model = new HashMap<>();
        Product product = productRepository.findById(id).get();
        model.put("product", product);
        model.put("tags", tagRepository.findAll());
        return new ModelAndView("product_edit", model);
    }

    @GetMapping("/wagon")
    public ModelAndView Wagon() {
        Map<String, Object> model = new HashMap<>();
        Integer id = wagonRepository.findAll().iterator().next().getId();
        return WagonId(id);
    }

    @GetMapping("/wagon={id}")
    public ModelAndView WagonId(@PathVariable("id") Integer id) {
        Map<String, Object> model = new HashMap<>();
        model.put("wagon", Utils.GetRStock(stockRepository.findAllByWagon(id), productRepository.findAll()));
        model.put("wagon_id", id);
        model.put("wagons", wagonRepository.findAll());
        model.put("products", Utils.GetRProduct(productRepository.findAll(), tagRepository.findAll()));
        return new ModelAndView("wagon", model);
    }

    @RequestMapping(value = "/product_edit", method = RequestMethod.POST)
    public ModelAndView ToProductEdit(@Valid @ModelAttribute("restobject") RestObject product,
                                      BindingResult result, ModelMap model) {
        Map<String, Object> data = new HashMap<>();
        data.put("product", productRepository.findById(product.getEdit()).get());
        data.put("tags", tagRepository.findAll());
        return new ModelAndView("product_edit", data);
    }

    @RequestMapping(value = "/product_save", method = RequestMethod.POST)
    public ModelAndView ToProductSave(@Valid @ModelAttribute("restobject") Product product,
                                      BindingResult result, ModelMap model) {
        productRepository.UpdateProduct(product.getId(), product.getTitle(), product.getTag(), product.getImg_path(), product.getPrice(), product.getUnit());
        return Product();
    }

    @RequestMapping(value = "/product_add", method = RequestMethod.POST)
    public ModelAndView ToProductAdd(@Valid @ModelAttribute("restobject") Product product,
                                     BindingResult result, ModelMap model) {
        productRepository.AddProduct(product.getTitle(), product.getPrice(), product.getTag(), product.getImg_path(), product.getUnit());
        return Product();
    }

    @RequestMapping(value = "/product_remove", method = RequestMethod.POST)
    public ModelAndView ToProductRemove(@Valid @ModelAttribute("restobject") RestObject product,
                                        BindingResult result, ModelMap model) {
        productRepository.DeleteProduct(product.getRemove());
        return Product();
    }

    @RequestMapping(value = "/wagon_edit", method = RequestMethod.POST)
    public ModelAndView ToWagonAddProduct(@Valid @ModelAttribute("restobject") RestObject obj,
                                          BindingResult result, ModelMap model) {
        if (obj.getPlus() != null)
            stockRepository.UpdateSelled(obj.getWagon(), obj.getPlus(), 1);
        if (obj.getMinus() != null)
            stockRepository.UpdateSelled(obj.getWagon(), obj.getMinus(), -1);
        return WagonId(obj.getWagon());
    }

    @RequestMapping(value = "/wagon_clear", method = RequestMethod.POST)
    public ModelAndView ClearWagon(@Valid @ModelAttribute("restobject") RestObject obj,
                                   BindingResult result, ModelMap model) {
        if (obj.getRemove() != null)
            stockRepository.ClearStock(obj.getRemove());
        return WagonId(obj.getWagon());
    }

    @RequestMapping(value = "/wagon_add", method = RequestMethod.POST)
    public ModelAndView ToWagonAddProducts(@Valid @ModelAttribute("restobject") RestObject obj,
                                           BindingResult result, ModelMap model) {
        stockRepository.AddProductToWagon(obj.getWagon(), obj.getProduct(), obj.getCount());
        return WagonId(obj.getWagon());
    }
}