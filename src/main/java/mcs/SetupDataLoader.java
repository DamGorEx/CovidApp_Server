package mcs;

import lombok.SneakyThrows;
import mcs.Model.*;
import mcs.Model.Repositories.*;
import mcs.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@EnableJpaRepositories("mvs.Model.Repository")
public class SetupDataLoader {

    private boolean alreadySetup = false;

    @Autowired
    private WebApplicationContext applicationContext;
    private UserRepository userRepository;
    private OrderRepository orderRepostiory;
    private ItemGroupRepository itemGroupRepository;
    private ProductRepository productRepository;
    private NewsRepository newsRepository;
    @PostConstruct
    private void setUpUserRep() {
        userRepository = applicationContext.getBean(UserRepository.class);
        orderRepostiory = applicationContext.getBean(OrderRepository.class);
        itemGroupRepository = applicationContext.getBean(ItemGroupRepository.class);
        productRepository = applicationContext.getBean(ProductRepository.class);
        newsRepository  = applicationContext.getBean(NewsRepository.class);
    }

    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {

        if (alreadySetup)
            return;
        List<User> testUsers = setUpTestUsers();
        List<Product> testProducts = setUpProduct();
        ItemGroup testBasket = setUpTestBasket(testProducts);
        ItemGroup testBasket1 = setUpTestBasket(testProducts);
        ItemGroup testBasket2 = setUpTestBasket(testProducts);
        User testUser = testUsers.get(0);
        User testUser1 = testUsers.get(1);
        setUpOrders(testUser, testBasket);
        setUpOrders(testUser, testBasket2);
        setUpOrders(testUser1, testBasket1);

        alreadySetup = true;
    }

    @SneakyThrows
    private List<Product> setUpProduct() {
        Product pr = new Product("Maska 1", "Dla wielbicieli kotow", 5d);
        Product pr1 = new Product("Maska 2", "Dla wielbicieli psow", 5d);
        Product pr2 = new Product("Maska 3", "Dla wielbicieli innych psow", 5d);
        Product pr3 = new Product("Maska 4", "Dla wielbicieli orlow", 7d);
        Product pr4 = new Product("Maska 5", "Dla wielbicieli goryli", 7d);
        Product pr5 = new Product("Maska 6", "Dla wielbicieli innych goryli", 7d);
        Product pr6 = new Product("Maska 7", "Dla wielbicieli malpek", 7d);
        Product pr7 = new Product("Zel 1", "Zel antybakteryjny zapachowy piesek", 15d);
        Product pr8 = new Product("Zel 2", "Zel antybakteryjny zapachowy panda", 15d);
        Product pr9 = new Product("Zel 3", "Zel antybakteryjny zapachowy tygrysek", 15d);
        byte[] fileToTest = new byte[0];
        byte[] fileToTest2 = new byte[0];
        byte[] fileToTest3 = new byte[0];
        byte[] fileToTest4 = new byte[0];
        byte[] fileToTest5 = new byte[0];
        byte[] fileToTest6 = new byte[0];
        byte[] fileToTest7 = new byte[0];
        byte[] fileToTest8 = new byte[0];
        byte[] fileToTest9 = new byte[0];
        byte[] fileToTest10 = new byte[0];
        try {
            fileToTest = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/images/cat.jpg"));
            fileToTest2 = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/images/dog.jpg"));
            fileToTest3 = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/images/dog2.jpg"));
            fileToTest4 = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/images/eagle.jpg"));
            fileToTest5 = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/images/gorilla.jpg"));
            fileToTest6 = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/images/gorilla_man.jpg"));
            fileToTest7 = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/images/monkey.jpg"));
            fileToTest8 = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/images/zel.jpg"));
            fileToTest9 = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/images/zel2.jpg"));
            fileToTest10 = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/images/zel3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        var img1 = new Image("Image1", "image/jpeg", ImageService.comprese(fileToTest));
        var img2 = new Image("Image2", "image/jpeg", ImageService.comprese(fileToTest2));
        var img3 = new Image("Image3", "image/jpeg", ImageService.comprese(fileToTest3));
        var img4 = new Image("Image4", "image/jpeg", ImageService.comprese(fileToTest4));
        var img5 = new Image("Image5", "image/jpeg", ImageService.comprese(fileToTest5));
        var img6 = new Image("Image6", "image/jpeg", ImageService.comprese(fileToTest6));
        var img7 = new Image("Image7", "image/jpeg", ImageService.comprese(fileToTest7));
        var img8 = new Image("Image8", "image/jpeg", ImageService.comprese(fileToTest8));
        var img9 = new Image("Image9", "image/jpeg", ImageService.comprese(fileToTest9));
        var img10 = new Image("Image10", "image/jpeg", ImageService.comprese(fileToTest10));
        pr.setImage(img1);
        pr.addCategories(Product.Category.mask);
        pr1.setImage(img2);
        pr1.addCategories(Product.Category.mask);
        pr2.setImage(img3);
        pr2.addCategories(Product.Category.mask);
        pr3.setImage(img4);
        pr3.addCategories(Product.Category.mask);
        pr4.setImage(img5);
        pr4.addCategories(Product.Category.mask);
        pr5.setImage(img6);
        pr5.addCategories(Product.Category.mask);
        pr6.setImage(img7);
        pr6.addCategories(Product.Category.mask);
        pr7.setImage(img8);
        pr7.addCategories(Product.Category.hygiene_products);
        pr8.setImage(img9);
        pr8.addCategories(Product.Category.hygiene_products);
        pr9.setImage(img10);
        pr9.addCategories(Product.Category.hygiene_products);
        productRepository.saveAll(Arrays.asList(pr, pr1, pr2, pr3, pr4, pr5, pr6, pr7,pr8,pr9));
        productRepository.flush();
        News news = new News();
        news.addImg(img1);
        news.addParagraph("dasfsdfasfadsf");
        news.addParagraph("2");
        news.addParagraph("3");
        news.addParagraph("4");
        news.addParagraph("5");
        news.addParagraph("6");
        news.addParagraph("7");
        news.addParagraph("8");
        news.addParagraph("9");
        news.addParagraph("10");
        news.addParagraph("11");

        newsRepository.saveAndFlush(news);
        return Arrays.asList(pr, pr1, pr2, pr4, pr5, pr6,pr7,pr8,pr9);
    }

    private ItemGroup setUpTestBasket(List<Product> products) {
        ItemGroup itemGroup = new ItemGroup();
        Random random = new Random();
        products.stream().forEach(p -> {
            itemGroup.addItem(p, Math.abs(random.nextInt(20)));
        });
        itemGroupRepository.saveAndFlush(itemGroup);
        return itemGroup;
    }

    private List<ShopOrder>  setUpOrders(User user, ItemGroup basket) {
        ShopOrder shopOrder = new ShopOrder(user, basket);
        orderRepostiory.saveAndFlush(shopOrder);
        return Arrays.asList(shopOrder);
    }

    private List<User> setUpTestUsers() {
        User.Role.Privilege readPrivilege = new User.Role.Privilege("READ_PRIVILEGE");
        User.Role.Privilege writePrivilege = new User.Role.Privilege("WRITE_PRIVILEGE");

        List<User.Role.Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        var role = new User.Role("ROLE_ADMIN");
        role.addPrivilege(readPrivilege);
        role.addPrivilege(writePrivilege);
        var role1 = new User.Role("ROLE_USER");
        role1.addPrivilege(readPrivilege);

        User user = new User("Admin", "Admin", "Admin", "123", "admin@test.com", "test");
        User user1 = new User("User", "User", "User", "123", "user@test.com", "test");
        user.setRoles(Arrays.asList(role));
        user1.setRoles(Arrays.asList(role1));

        userRepository.saveAll(Arrays.asList(user, user1));
        userRepository.flush();
        return  Arrays.asList(user, user1);
    }
}
