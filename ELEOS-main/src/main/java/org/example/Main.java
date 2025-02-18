package org.example;

import models.Stock.Product;
import models.Stock.Provider;
import service.StockService.ProductService;
import service.StockService.ProviderService;

import java.math.BigDecimal;
import java.util.Date;

public class    Main {
    public static void main(String[] args) {
        ProviderService providerService = new ProviderService();

        // Create a Provider object with all required fields
        Provider provider = new Provider(
                "Provider Name",
                "Contact Info",
                "Provider Address",
                "Payment Terms"
        );

        // Add the provider to the database
        providerService.add(provider);

        // Retrieve and print all providers
        System.out.println("Providers before update:");
        System.out.println(providerService.display());

        // Update the provider details
        provider.setName("Updated Provider Name");
        provider.setContact("Updated Contact Info");
        providerService.update(provider);

        // Retrieve and print all providers after update
        System.out.println("Providers after update:");
        System.out.println(providerService.display());

        // Delete the provider from the database
//        providerService.delete(1);

        // Retrieve and print all providers after deletion
        System.out.println("Providers after deletion:");
        System.out.println(providerService.display());

    }
    }
