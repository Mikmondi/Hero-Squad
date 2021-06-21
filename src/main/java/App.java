import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import models.Hero;
import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        staticFileLocation("/public");

//        String connectionString = "jdbc:postgresql://localhost:4567/hero_squad";
//        Sql2o sql2o = new Sql2o(connectionString, "moringa", "newpsqlpass");
        String connectionString = "jdbc:postgresql://ec2-34-231-56-78.compute-1.amazonaws.com:5432/deq00sm90atkqn";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //get: show all heroes in all squads and show all squads
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads", allSquads);
            List<Hero> heroes = heroDao.getAll();
            System.out.println(heroes);
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to create a new squad
        get("/squads/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "squad-form.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //post: process a form to create a new squad
        post("/squads", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            Squad newSquad = new Squad(name);
            squadDao.add(newSquad);
            model.put("squads", squadDao.getAll());
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all squads and all heroes
        get("/squads/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            squadDao.clearAllSquads();
            heroDao.clearAllHeroes();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        // delete an individual squad
        get("/squads/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete = Integer.parseInt(req.params("id"));
            squadDao.deleteById(idOfSquadToDelete);
            model.put("squads", squadDao.getAll());
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes.
        get("/heroes/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            heroDao.clearAllHeroes();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get a specific squad (and the heroes it contains)
        get("/squads/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfSquadToFind = Integer.parseInt(request.params(":id"));
            Squad foundSquad =  squadDao.findById(idOfSquadToFind);
            model.put("squad", foundSquad);
            List<Hero> allHeroesBySquad = squadDao.getAllHeroesBySquad(idOfSquadToFind);
            model.put("heroes", allHeroesBySquad);
            model.put("squads", squadDao.getAll());
            return new ModelAndView(model, "squad-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a squad
        get("/squads/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<String,Object>();
            model.put("updateSquad",true);
            Squad squad = squadDao.findById(Integer.parseInt(req.params("id")));
            model.put("squad", squad);
            model.put("squads", squadDao.getAll());
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process form to update a squad
        post("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfSquadToUpdate = Integer.parseInt(req.params("id"));
            String newName = req.queryParams("newSquadName");
            squadDao.update(idOfSquadToUpdate, newName);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // delete an individual hero
        get("/heroes/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("id"));
            heroDao.deleteById(idOfHeroToDelete);
            model.put("heroes",heroDao.getAll());
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual hero - in a squad.
        get("/squads/:squad_id/heroes/:hero_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("hero_id"));
            heroDao.deleteById(idOfHeroToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new hero form
        get("/heroes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a new hero form.
        post("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> allSquads = squadDao.getAll(); //new
            model.put("squads", allSquads); // new
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            int squadId = Integer.parseInt(request.queryParams("squadId"));
            Hero newHero = new Hero(name,age, power, weakness, squadId);
            heroDao.add(newHero);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual hero that is nested within a squad.
        get("/squads/:squad_id/heroes/:hero_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params("squad_id"));
            int idOfHeroToFind = Integer.parseInt(req.params("hero_id"));
            Hero foundHero = heroDao.findById(idOfHeroToFind);
            model.put("hero", foundHero);
            model.put("squad",squadDao.findById(idOfSquadToFind));
            model.put("squads", squadDao.getAll());
            model.put("heroes", heroDao.getAll());
            return new ModelAndView(model, "hero-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a hero
        get("/heroes/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads", allSquads);
            Hero hero = heroDao.findById(Integer.parseInt(req.params("id")));
            model.put("hero", hero);
            model.put("editHero", true);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a hero.
        post("/heroes/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToUpdate = Integer.parseInt(req.params("id"));
            String newName = req.queryParams("name");
            int newAge = Integer.parseInt(req.queryParams("age"));
            String newPower = req.queryParams("power");
            String newWeakness = req.queryParams("weakness");
            int newSquadId = Integer.parseInt(req.queryParams("squadId"));
            heroDao.update(idOfHeroToUpdate, newName, newAge, newPower, newWeakness, newSquadId);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
