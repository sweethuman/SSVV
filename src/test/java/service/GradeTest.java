package service;

import domain.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.*;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class GradeTest {

    private Service service;

    @Before
    public void setUp(){
        Validator<Student> validator = new StudentValidator();
        Validator<Tema> validator1 = new TemaValidator();
        Validator<Nota> validator2 = new NotaValidator();
        StudentXMLRepository studentFileRepository = new StudentXMLRepository( validator,"studenti_test.xml");
        TemaXMLRepository temaFileRepository = new TemaXMLRepository(validator1, "teme_test.xml");
        NotaXMLRepository notaFileRepository = new NotaXMLRepository(validator2, "note_test.xml");

        service = new Service(studentFileRepository, temaFileRepository, notaFileRepository);
    }

    @Test
    public void addGrade(){
        Assert.assertEquals(service.saveNota("1", "1", 10, 8, "aaaa"), 0);
    }

    @Test
    public void addStudent() {
        Assert.assertEquals(service.saveStudent("1", "aaa", 935), 0);
    }

    @Test
    public void addAssignment(){
        Assert.assertEquals(service.saveTema("1", "description", 9, 7), 0);}

    @Test
    public void integrationAssignment(){
        addStudent();
        addAssignment();
    }

    @Test
    public void integrationGrade(){
        addStudent();
        addAssignment();
        Assert.assertEquals(service.saveNota("1", "1", 10, 8, "aaa"), 0);
    }

    @Test
    public void integrationTesting(){
        addStudent();
        addAssignment();
        addGrade();
    }

}