package es.devfromt.wildplants.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="plant")
@NamedQueries({@NamedQuery(name = "Plant.findById", query = "select distinct p from Plant p left join fetch p.uses u where p.id= :id"),
               @NamedQuery(name = "Plant.findAllWithUse", query = "select distinct p from Plant p left join fetch p.uses u"),
               @NamedQuery(name = "Plant.findByName", query = "select distinct p from Plant p left join fetch p.uses u where p.name= :name")})
public class PlantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHOTO")
    private byte[] photo;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @ManyToMany
    @JoinTable(name = "PLANT_USE", joinColumns = @JoinColumn(name = "PLANT_ID"), inverseJoinColumns = @JoinColumn(name = "USE_ID"))
    private List<UseEntity> uses= new ArrayList<UseEntity>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void addUse(UseEntity use){
        uses.add(use);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder= new StringBuilder();

        stringBuilder.append("Plant{");
        stringBuilder.append("id=" + id);
        stringBuilder.append(", name='" + name);
        stringBuilder.append(", photo=" + Arrays.toString(photo));
        stringBuilder.append(", creationDate=" + creationDate);

       // if(uses.size()==0) {
        //    stringBuilder.append(", uses=" + uses);
       // }

        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}
