package es.devfromt.wildplants;

import es.devfromt.wildplants.dao.PlantDao;
import es.devfromt.wildplants.dao.UseDao;
import es.devfromt.wildplants.entities.PlantEntity;
import es.devfromt.wildplants.entities.UseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class WildplantsApplication  implements CommandLineRunner {
private static Logger log= LoggerFactory.getLogger(WildplantsApplication.class);

@Autowired private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(WildplantsApplication.class, args);
    }

    private static void listPlants(List<PlantEntity> plants) {
        for (PlantEntity p: plants) {
                log.info(p.toString());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        PlantDao plantDao = context.getBean(PlantDao.class);
        UseDao useDao= context.getBean(UseDao.class);

        UseEntity use= mockUse("vitamin-c");
        useDao.save(use);

        PlantEntity manzanilla = mockPlant("manzanilla");
        plantDao.save(manzanilla);
        PlantEntity hinojo= mockPlant("hinojo");
        plantDao.save(hinojo);

        manzanilla.addUse(use);
        plantDao.save(manzanilla);
        hinojo.addUse(use);
        plantDao.save(hinojo);

        listPlants(plantDao.findAll());
        listPlants(plantDao.findAllWithUse());
    }

    private UseEntity mockUse(String name){
        UseEntity use= new UseEntity();
        use.setName(name);
        use.setDescription(name + " use");

        return use;
    }
    private PlantEntity mockPlant(String name) {
        PlantEntity p= new PlantEntity();
        p.setName(name);
        p.setCreationDate(new Date(System.currentTimeMillis()));

        return p;
    }
}
