package net.javaguides.springbootbackend.user;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY )
      private Integer id;
      @Column(length = 30, nullable = false)
      private String name;
      @Column(length = 30, nullable = false)
      private String dept;

      private boolean enabled;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", dept='" + getDept() + '\'' +
                '}';
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
