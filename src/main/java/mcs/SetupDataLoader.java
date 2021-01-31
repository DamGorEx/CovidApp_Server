package mcs;

import lombok.SneakyThrows;
import mcs.Model.*;
import mcs.Model.Repositories.ItemGroupRepository;
import mcs.Model.Repositories.OrderRepository;
import mcs.Model.Repositories.ProductRepository;
import mcs.Model.Repositories.UserRepository;
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
    @PostConstruct
    private void setUpUserRep() {
        userRepository = applicationContext.getBean(UserRepository.class);
        orderRepostiory = applicationContext.getBean(OrderRepository.class);
        itemGroupRepository = applicationContext.getBean(ItemGroupRepository.class);
        productRepository = applicationContext.getBean(ProductRepository.class);
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
        Product pr = new Product("product1", "Product 1 jest najlepszy", 3d);
        Product pr1 = new Product("product12", "Product 2 to arcydzielo sztuki", 5d);
        Product pr2 = new Product("product13", "Pro 3 to nic specjalnego", 7d);
        Product pr3 = new Product("product11sd3", "unikatowy w konlekcji", 7d);
        byte[] fileToTest = new byte[0];
        try {
            fileToTest = Files.readAllBytes(Paths.get("/Users/DamianGoraj/Documents/CovidApp/src/test/java/panda.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        var img1 = new Image("Image1", "image/jpeg", ImageService.comprese(fileToTest));
        pr.setImage(img1);
        pr1.setImage(img1);
        pr2.setImage(img1);
        pr3.setImage(img1);
        productRepository.saveAll(Arrays.asList(pr, pr1, pr2, pr3));
        productRepository.flush();
        return Arrays.asList(pr, pr1, pr2);
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
