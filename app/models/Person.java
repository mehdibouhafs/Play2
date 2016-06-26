package models;





import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Constraint;

/**
 * Created by bouhafs on 26/06/2016.
 */


@Entity
public class Person extends Model{


    @Id
    public Long id;

    public String name;







}
