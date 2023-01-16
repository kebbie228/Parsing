package com.company.product;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductsParser {
    private static Document document;
    private static final String url = "https://calorizator.ru/product/all";
    //*[@id="main-content"]/div/div[1]/table[2]/tbody/tr[1]/td[2]/a
  //  $x("//div//div//table//tbody//tr//td//a")
    private static final String selector = "div>div>table>tbody>tr"; //Добавить >td>a
    private final String selectorContent = "div>div>table>tbody>tr>td"; //Добавить :eq(3)
    //*[@id="main-content"]/div/div[1]/table[2]/tbody/tr[1]/td[3]

    private static List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        connect(" ");
        parse();
    }

    public static boolean connect(String sPage) {
        try {
            document = Jsoup.connect(url + sPage).get();
           // System.out.println(document.html());
        } catch (IOException e) {
            System.err.println("Check HTTP connection to the url " + url);
            return false;
        }
        return true;
    }
    static void parse(){
        Elements names =
                document.select
                        (selector);
        for (Element element : names) {
            String name = element.select(">td>a").text().trim();

       if (!name.isEmpty()){
            //String sProtein =
            element.select
                    (":eq(3)").text().trim();
            try {
                double protein = Double.parseDouble(element.select(":eq(2)").text().trim());
                double fat = Double.parseDouble(element.select(":eq(3)").text().trim());
                double carbohydrate = Double.parseDouble(element.select(":eq(4)").text().trim());
                double kcal = Double.parseDouble(element.select(":eq(5)").text().trim());
                Product product = new Product(name, kcal, protein, fat, carbohydrate);
                products.add(product);
//System.out.println(name);
            }
            catch (Exception e){};
        }
       for(Product product:products)
           System.out.println(product);

    }


}
}

