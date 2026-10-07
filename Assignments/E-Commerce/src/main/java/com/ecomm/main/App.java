package com.ecomm.main;


import com.ecomm.Config.AppConfig;
import com.ecomm.Model.Product;
import com.ecomm.Service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService= context.getBean(ProductService.class);
        Product product = new Product();

        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("---------E-Commerce Application-------");
            System.out.println("1.Insert");
            System.out.println("2.Find by Id");
            System.out.println("3.Update Stock");
            System.out.println("4.Aggregate");
            System.out.println("0.Exit");
            System.out.println("---------------------------------------");
            int input=sc.nextInt();
            if(input==0){
                System.out.println("Exiting...");
                break;
            }
            switch(input){
                case 1-> {


                    System.out.println("Enter product ID");
                    product.setId(sc.nextInt());

                    sc.nextLine(); // consume leftover newline

                    System.out.println("Enter product name");
                    product.setName(sc.nextLine());

                    System.out.println("Enter product price");
                    product.setPrice(sc.nextDouble());

                    System.out.println("Enter stock quantity");
                    product.setStockQuantity(sc.nextInt());

                    System.out.println("Enter category ID");
                    product.setCategory_id(sc.nextInt());

                    System.out.println("Enter vendor ID");
                    product.setVendor_id(sc.nextInt());

                    try {
                        productService.save(product);
                        System.out.println("Product inserted");
                    } catch (Exception e) {
                        System.out.println("Insertion failed."+e.getMessage());

                    }
                    break;
                }

                case 2-> {
                    System.out.println("Enter product ID to search");
                    int id=sc.nextInt();
                    try{
                        List<Product> productList=productService.findById(id);
                        productList.forEach(System.out :: println);
                    } catch (Exception e) {
                        System.out.println("Product not found."+e.getMessage());

                    }
                    break;
                }
                case 3-> {
                    System.out.println("Enter the Product ID to update stock:");
                    int id=sc.nextInt();
                    System.out.println("Enter the stock amount to update:");
                    int updatedStock=sc.nextInt();
                    try {
                        productService.updateStock(id, updatedStock);
                        System.out.println("Product stock updated!");
                    } catch (Exception e) {
                        System.out.println("Can't Update."+e.getMessage());
                    }
                    break;
                }
                case 4-> {
                    try {
                        Map<String, Integer> result = productService.countProductsByVendor();
                        for (Map.Entry<String, Integer> entry : result.entrySet()) {
                            System.out.println("Vendor:"+entry.getKey()+"\nProducts:"+entry.getValue());
                        }
                    }
                    catch (Exception e) {
                    System.out.println("Aggregate query failed:"+e.getMessage());
                    }
                    break;
                }
                default -> {
                    System.out.println("Invalid option");
                    break;
                }
            }
        }
    }
}
