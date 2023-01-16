package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
/*
https://www.google.com/search?q=text

$x('//div') - найти все элементы div
$x("//div[@class = 'TbwUpd']") - div с заданным классом
$x("//div[contains(@class, 'Tbw')]") - div с классом, содержащим данную строку
$x("//div[contains(@class, 'TbwUpd') and contains(@class, 'NJjxre')]") - div содержит два класса
$x("//div[contains(@class, 'TbwUpd') and contains(.//cite,'')]")[0].textContent
$x("//div[@class = 'MUFPAc']/div[2]/a")[0].innerText - Найти текст "картинки"
$x("//table[@class = 'AaVjTc']/tbody/tr/td/a/span[not(@class)]")[0].innerText - Найти текст ссылки "Следующая"
$x("//span[@id = 'fsl']/a")[0].innerText Найти слово справка внизу страницы
//*[@id="fsl"]/a[1]
$x("//span[@id = 'xjs']/table/tbody/tr/td[3]")[0].innerText Найти ссылку на 2-ю страницу
$x("//div[@id = '_U9WEYvi5H6OB9u8PkqSA-AQ7']/div/div/div/div[2]/div/a")[0].ariaLabel Вывести информацию об аккаунте
$x("//div[@id = '_lNaEYrybDPf-7_UP7_qlmA45']")[0].ariaLabel Вывести слово "Настройки" (из меню)
*/
public class GoogleDemo {
    private static Document document;
    private static final String url = "https://www.google.com/search?q=text&start=";
    private static final String selector = "div.TbwUpd>cite";
    ////div[@class = 'TbwUpd']
    public static void main(String[] args) {
        for(int i=0;i<3;i++) {
            try {
                document = Jsoup.connect(url+i*10).get();
            } catch (IOException e) {
                System.err.println("Check HTTP connection to the url " + url);
            }
            System.out.println(document.html());

            Elements links = document.select(selector);
            for (Element link : links) {
                String s = link.text();
               if(s.contains("mightytext.net")) System.out.println(i);
                System.out.println(s);
            }
        }
    }
}
