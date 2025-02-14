
package View;

import Services.SellerService;
import models.Platform;
import models.Product;
import Constant.Printer;
import Constant.Constant;
import View.InputScanner;

import java.sql.SQLException;
import java.util.List;
import Services.*;

public class SellerView {
    private static final SellerService sellerService = new SellerService();
    private static final InputScanner input = InputScanner.getInstance();

    private final Platform platform;

    public SellerView(Platform platform){
        this.platform = platform;
    }

    public static void showSellerMenu(String sellerUsername,Platform platform) throws SQLException {
        while (true) {
            Printer.printSellerDashboard(sellerUsername);
            int choice = input.readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addProduct(sellerUsername,platform);
                case 2 -> viewProducts(sellerUsername,platform);
                case 3 -> updateProduct(sellerUsername,platform);
                case 4 -> deleteProduct(sellerUsername,platform);
                case 5 -> showBestSellingProducts(platform);
                case 6 -> showMostLikedProducts(platform);
                case 7 -> {
                    System.out.println("You are logout from sellerMenu");
                    ShowLoginMenu.showLoginMenu();
                }
                case 8 -> {
                    ShowLoginMenu.showLoginMenu();
                }
                case 9 -> {
                    Printer.printExitMessage();
                    System.exit(0);
                    break;
                }
                default -> Printer.printInvalidChoice();
            }
        }

    }

    private static void addProduct(String sellerUsername,Platform platform) throws SQLException {
        Printer.printCategoryMenu();
        int categoryChoice = input.readInt("Enter category number: ");

        String mainCategory = null;
        switch (categoryChoice) {
            case 1:
                mainCategory = Constant.CATEGORY_ELECTRONICS;
                break;
            case 2:
                mainCategory = Constant.CATEGORY_FURNITURE;
                break;
            case 3:
                Printer.printSellerDashboard(sellerUsername);
                return;
            case 4:
                Printer.printExitMessage();
                System.exit(0);
            default:
                Printer.printInvalidChoice();
                return;
        }


        Printer.printSubcategoryMenu(mainCategory);
        int subcategoryChoice = input.readInt("Enter subcategory number: ");
        String subCategory = getSubcategory(mainCategory, subcategoryChoice);
        if (subCategory == null) return;

        Printer.printProductTypeMenu(subCategory);
        int typeChoice = input.readInt("Enter product type number: ");
        String productType = getProductType(subCategory, typeChoice);
        if (productType == null) return;

        String productName = input.readString("Enter product name: ");
        int stock = input.readInt("Enter stock quantity: ");
        double price = input.readDouble("Enter product price: ");


        Product product = new Product(0, productName, mainCategory, subCategory, productType, stock, price,sellerUsername);
        boolean success = sellerService.addProduct(product,platform);

        if (success) System.out.println("\nProduct added successfully!");
        else System.out.println("\nFailed to add product. Please try again.");
    }

    private static void viewProducts(String sellerUsername,Platform platform) throws SQLException {
        List<Product> products = sellerService.getSellerProducts(sellerUsername,platform);
        Printer.printProductList(products);
    }


    private static void updateProduct(String sellerUsername,Platform platform) throws SQLException {
        List<Product> products = sellerService.getSellerProducts(sellerUsername,platform);
        Printer.printProductList(products);

        if (products.isEmpty()) {
            Printer.printNoProductsAvailable();
            return;
        }

        int productId = input.readInt("Enter product ID to update: ");
        Product existingProduct = sellerService.getProductById(productId,platform.getPlatformId());

        if (existingProduct == null) {
            Printer.printProductNotFound();
            return;
        }

        while (true) {
            Printer.printUpdateMenu();
            int choice = input.readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> {
                    String newProductName = input.readString("Enter new product name: ");
                    existingProduct.setProductName(newProductName);
                    printUpdateResult(sellerService.updateProduct(existingProduct,platform), "Product name");
                }
                case 2 -> {
                    double newPrice = input.readDouble("Enter new price: ");
                    existingProduct.setPrice(newPrice);
                    printUpdateResult(sellerService.updateProduct(existingProduct,platform), "Product price");
                }
                case 3 -> {
                    int newStock = input.readInt("Enter new stock quantity: ");
                    existingProduct.setStock(newStock);
                    printUpdateResult(sellerService.updateProduct(existingProduct,platform), "Product stock");
                }
                case 4 -> {
                    String newProductName = input.readString("Enter new product name: ");
                    double newPrice = input.readDouble("Enter new price: ");
                    int newStock = input.readInt("Enter new stock quantity: ");

                    existingProduct.setProductName(newProductName);
                    existingProduct.setPrice(newPrice);
                    existingProduct.setStock(newStock);

                    printUpdateResult(sellerService.updateProduct(existingProduct,platform), "Product");
                }
                case 5 -> {
                    Printer.printSellerDashboard(sellerUsername);
                    return;
                }
                case 6 -> {
                    Printer.printExitMessage();
                    System.exit(0);
                    break;
                }

                default -> Printer.printInvalidChoice();
            }
        }
    }

    private static void printUpdateResult(boolean success, String field) {
        if (success) {
            Printer.printUpdateSuccess(field);

        } else {
            Printer.printUpdateFailed(field);
        }
    }

    private static void deleteProduct(String sellerUsername,Platform platform) throws SQLException {
        List<Product> products = sellerService.getSellerProducts(sellerUsername,platform);
        Printer.printProductList(products);

        if (products.isEmpty()) {
            System.out.println("\nNo products available to delete.");
            return;
        }

        int productId = input.readInt("Enter product ID to delete: ");
        boolean success = sellerService.deleteProduct(productId, sellerUsername,platform);

        if (success) Printer.printProductDeletedSuccess();
        else System.out.println("\nProduct deletion failed.");
    }


    private static String getSubcategory(String mainCategory, int choice) {
        if (mainCategory == null) {
            Printer.printInvalidChoice();
            return null;
        }

        String subCategory = null;
        switch (mainCategory) {
            case Constant.CATEGORY_ELECTRONICS:
                switch (choice) {
                    case 1:
                        subCategory = Constant.SUBCATEGORY_MOBILE;
                        break;
                    case 2:
                        subCategory = Constant.SUBCATEGORY_LAPTOPS;
                        break;
                    case 3:
                        subCategory = Constant.SUBCATEGORY_WEARABLES;
                        break;
                    case 4:
                        subCategory = Constant.SUBCATEGORY_PRINTERS;
                        break;
                    case 5:
                        Printer.printCategoryMenu();
                    case 6:
                        Printer.printExitMessage();
                        System.exit(0);
                        break;
                    default:
                        Printer.printInvalidChoice();
                        return null;
                }
                break;

            case Constant.CATEGORY_FURNITURE:
                switch (choice) {
                    case 1:
                        subCategory = Constant.SUBCATEGORY_ROOM;
                        break;
                    case 2:
                        subCategory = Constant.SUBCATEGORY_OFFICE;
                        break;
                    case 3:
                        subCategory = Constant.SUBCATEGORY_EVENT;
                        break;
                    case 4: {
                        Printer.printCategoryMenu();
                    }
                    case 5:
                        Printer.printExitMessage();
                        System.exit(0);
                        break;
                    default:
                        Printer.printInvalidChoice();
                        return null;
                }
                break;

            default:
                Printer.printInvalidChoice();
        }
        return subCategory;
    }

    private static String getProductType(String subCategory, int choice) {
        if (subCategory == null) {
            Printer.printInvalidChoice();
            return null;
        }

        String productType = null;
        switch (subCategory) {
            case Constant.SUBCATEGORY_MOBILE:
                switch (choice) {
                    case 1:
                        productType = Constant.TYPE_SMARTPHONE_ANDROID;
                        break;
                    case 2:
                        productType = Constant.TYPE_SMARTPHONE_IOS;
                        break;
                    case 3:
                        productType = Constant.TYPE_KEYPAD;
                        break;
                    case 4:
                        Printer.printSubcategoryMenu(subCategory);
                    case 5:
                        Printer.printExitMessage();
                        System.exit(0);
                        break;
                    default:
                        Printer.printInvalidChoice();
                        return null;
                }
                break;

            case Constant.SUBCATEGORY_LAPTOPS:
                switch (choice) {
                    case 1:
                        productType = Constant.TYPE_BUSINESS_LAPTOP;
                        break;
                    case 2:
                        productType = Constant.TYPE_GAMING_LAPTOP;
                        break;
                    case 3:
                        Printer.printSubcategoryMenu(subCategory);
                    case 4:
                        Printer.printExitMessage();
                        System.exit(0);
                        break;
                    default:
                        Printer.printInvalidChoice();
                        return null;
                }
                break;

            case Constant.SUBCATEGORY_WEARABLES:
                switch (choice) {
                    case 1:
                        productType = Constant.TYPE_SMARTWATCHES;
                        break;
                    case 2:
                        productType = Constant.TYPE_FITNESS_TRACKERS;
                        break;
                    case 3:
                        Printer.printSubcategoryMenu(subCategory);
                    case 4:
                        Printer.printExitMessage();
                        System.exit(0);
                        break;
                    default:
                        Printer.printInvalidChoice();
                        return null;
                }
                break;

            case Constant.SUBCATEGORY_PRINTERS:
                switch (choice) {
                    case 1:
                        productType = Constant.TYPE_INKJET_PRINTERS;
                        break;
                    case 2:
                        productType = Constant.TYPE_LASER_PRINTERS;
                        break;
                    case 3:
                        Printer.printSubcategoryMenu(subCategory);
                    case 4:
                        Printer.printExitMessage();
                        System.exit(0);
                        break;
                    default:
                        Printer.printInvalidChoice();
                        return null;
                }
                break;

            case Constant.SUBCATEGORY_ROOM:
                switch (choice) {
                    case 1:
                        productType = Constant.TYPE_SOFA;
                        break;
                    case 2:
                        productType = Constant.TYPE_BED;
                        break;
                    case 3:
                        Printer.printSubcategoryMenu(subCategory);
                    case 4:
                        Printer.printExitMessage();
                        System.exit(0);
                        break;
                    default:
                        Printer.printInvalidChoice();
                        return null;
                }
                break;

            case Constant.SUBCATEGORY_OFFICE:
                switch (choice) {
                    case 1:
                        productType = Constant.TYPE_DESK;
                        break;
                    case 2:
                        productType = Constant.TYPE_CHAIR;
                        break;
                    case 3:
                        Printer.printSubcategoryMenu(subCategory);
                    case 4:
                        Printer.printExitMessage();
                        System.exit(0);
                        break;
                    default:
                        Printer.printInvalidChoice();
                        return null;
                }
                break;

            case Constant.SUBCATEGORY_EVENT:
                switch (choice) {
                    case 1:
                        productType = Constant.TYPE_STAGE;
                        break;
                    case 2:
                        productType = Constant.TYPE_DECORATION;
                        break;
                    case 3:
                        Printer.printSubcategoryMenu(subCategory);
                    case 4:
                        Printer.printExitMessage();
                        System.exit(0);
                        break;
                    default:
                        Printer.printInvalidChoice();
                        return null;
                }
                break;

            default:
                Printer.printInvalidChoice();
        }
        return productType;
    }

    public static void showBestSellingProducts(Platform platform) {
        List<String> bestSellingProducts = sellerService.getBestSellingProducts(platform);
        Printer.printBestSellingProducts(bestSellingProducts);
    }


    public static void showMostLikedProducts(Platform platform) {
        List<String> mostLikedProducts = sellerService.getMostLikedProducts(platform);
        Printer.printMostLikedProducts(mostLikedProducts);
    }
}
