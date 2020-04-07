package es.devfromt.wildplants.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "use")
public class UseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(name = "PLANT_USE", joinColumns = @JoinColumn(name = "USE_ID"), inverseJoinColumns = @JoinColumn(name = "PLANT_ID"))
    private List<PlantEntity> plants= new ArrayList<PlantEntity>();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addPlant(PlantEntity p){
        plants.add(p);
    }

    @Override
    public String toString() {
        return "Use{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", plants=" + plants +
                '}';
    }
}
