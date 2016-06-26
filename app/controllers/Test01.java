package controllers;




import com.avaje.ebean.Model;
import models.Person;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.index;

import java.io.File;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by MBS on 24/06/2016.
 */
public class Test01 extends Controller {

    Test01(){

    }

    public Result test(){
        //return render("/persons")
        return ok(views.html.test.render());
    }

    public Result upload() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("Image");
        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();
            return ok("File uploaded");
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

    public Result upload2() {
        File file = request().body().asRaw().asFile();
        return ok("File uploaded");
    }

    public Result addPerson(){
        Person person = Form.form(Person.class).bindFromRequest().get();
        person.save();
        return redirect("/test");
    }

    public Result getPersons(){
        List<Person> persons = new Model.Finder<>(String.class,Person.class).all();
        return  ok(toJson(persons));
    }
}
