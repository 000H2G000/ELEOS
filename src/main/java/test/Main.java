package test;
<<<<<<< HEAD
import models.Activite;

import service.ActiviteService;


import java.time.LocalDateTime;
=======

import models.Visite.Visite;
import service.VisiteService.VisiteService;
>>>>>>> 90781c80dac62ee0115c83aef63989fd27e397da

public class Main {
    public static void main(String[] args) {

<<<<<<< HEAD
    }

    private static void displayAllSoins(int service) {
=======
        VisiteService v = new VisiteService();
        Visite visit = new Visite(1, 2, "2002-07-16", "en_attente", "ordinaire");
        //v.add(visit);
        v.delete(15);

        //visit.setId(5);


        //v.update(visit);
        System.out.println(v.getAll());
        System.out.println(v.getOne(9));
>>>>>>> 90781c80dac62ee0115c83aef63989fd27e397da

    }
}