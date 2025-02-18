package test;

import models.Visite.Visite;
import service.VisiteService.VisiteService;

public class Main {
    public static void main(String[] args) {

        VisiteService v = new VisiteService();
        Visite visit = new Visite(1, 2, "2002-07-16", "en_attente", "ordinaire");
        //v.add(visit);
        v.delete(15);

        //visit.setId(5);


        //v.update(visit);
        System.out.println(v.getAll());
        System.out.println(v.getOne(9));

    }
}