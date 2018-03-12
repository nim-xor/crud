package com.crud.crud;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping(value = "/crud")
public class CrudController {

    private List< Crud > Cruds = new ArrayList();


    CrudController() {
        this.Cruds = buildCruds();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Crud> getCruds() {
        return this.Cruds;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Crud getCrud(@PathVariable("id") Long id) {
        return this.Cruds.stream().filter(Crud -> Crud.getId() == id).findFirst().orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Crud saveCrud(@RequestBody Crud Crud) {
        Long nextId = 0L;
        if (this.Cruds.size() != 0) {
            Crud lastCrud = this.Cruds.stream().skip(this.Cruds.size() - 1).findFirst().orElse(null);
            nextId = lastCrud.getId() + 1;
        }

        Crud.setId(nextId);
        this.Cruds.add(Crud);
        return Crud;

    }

    @RequestMapping(method = RequestMethod.PUT)
    public Crud updateCrud(@RequestBody Crud Crud) {
        Crud modifiedCrud = this.Cruds.stream().filter(u -> u.getId() == Crud.getId()).findFirst().orElse(null);
        modifiedCrud.setName(Crud.getName());
        modifiedCrud.setContent(Crud.getContent());
        return modifiedCrud;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteCrud(@PathVariable Long id) {
        Crud deleteCrud = this.Cruds.stream().filter(Crud -> Crud.getId() == id).findFirst().orElse(null);
        if (deleteCrud != null) {
            this.Cruds.remove(deleteCrud);
            return true;
        } else  {
            return false;
        }


    }

    List<Crud> buildCruds() {
        List<Crud> Cruds = new ArrayList<>();

        Crud Crud1 = buildCrud(1L, "John", "Doe");
        Crud Crud2 = buildCrud(2L, "Jon", "Smith");
        Crud Crud3 = buildCrud(3L, "Will", "Craig");
        Crud Crud4 = buildCrud(4L, "Sam", "Lernorad");
        Crud Crud5 = buildCrud(5L, "Ross", "Doe");
        Cruds.add(Crud1);
        Cruds.add(Crud2);
        Cruds.add(Crud3);
        Cruds.add(Crud4);
        Cruds.add(Crud5);

        return Cruds;

    }

    Crud buildCrud(Long id, String name,String content) {
        Crud Crud = new Crud();
        Crud.setId(id);
        Crud.setName(name);
        Crud.setContent(content);
        return Crud;
    }
    
}
