package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "city")

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="city_id")
    private int id;

    @Column (name ="city_name", length = 100)
    private String cityName;
// mappedBy указывает  поле в объекте, который является владельцем отношения
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set <Employee> employees;

    public City(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + id +
                ", cityName='" + cityName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cityName);
    }
}
