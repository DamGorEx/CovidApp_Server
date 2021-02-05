package mcs.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "password")
@Entity
public @Data class User {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String Id;
    private String  fName, lName, address, phNumber;
    private @Column(unique = true) String email;
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @OneToMany
    @Cascade(CascadeType.ALL)
    @JsonIgnore
    private List<ShopOrder> shopOrders;
    @JsonIgnore
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<Role> roles;

    public User(String fName, String lName, String address, String phNumber, String email, String password) {
        this();
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.phNumber = phNumber;
        this.email = email;
        setPassword(password);
    }

    public User() {
        ArrayList<ShopOrder> orders = new ArrayList<>();
        this.shopOrders = orders;
        this.roles = new ArrayList<>();
    }

    public void setPassword (String pass) {
         this.password = PASSWORD_ENCODER.encode(pass);
    }


    public void addRole(Role role) {
    roles.add(role);
    }

    public void addOrder(ShopOrder shopOrder) {
        this.shopOrders.add(shopOrder);
    }

    @Entity
    public @Data static class Role {
        @Id @GeneratedValue(generator="system-uuid")
        @GenericGenerator(name="system-uuid", strategy = "uuid")
        private String id;
        private String name;
        @Cascade(CascadeType.ALL)
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "roles_privileges",
                joinColumns = @JoinColumn(
                        name = "role_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "privilege_id", referencedColumnName = "id"))
        private List<Privilege> privileges;

        public Role(String name) {
            this.name = name;
            this.privileges = new ArrayList<>();
        }

        public Role() {

        }

        public void addPrivilege(Privilege privilege) {
            this.privileges.add(privilege);
            privilege.addRole(this);
        }


        @Entity
        public @Data static class Privilege {
            @Id @GeneratedValue(generator="system-uuid")
            @GenericGenerator(name="system-uuid", strategy = "uuid")
            private String id;
            private String name;
            @JsonIgnore
            @ManyToMany (mappedBy = "privileges")
            private List<Role> roles;


            public Privilege(String name) {
                this.roles = new ArrayList<>();
                this.name = name;
            }
            public Privilege (){};

            private void addRole(Role r) {
                roles.add(r);
            }
        }
    }
}

