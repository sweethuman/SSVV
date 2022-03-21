package service;

import domain.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import validation.*;
import repository.*;

public class ServiceTest {

    public Service service;

    @Before
    public void setUp() throws Exception {
        Validator<Student> validator = new StudentValidator();
        Validator<Tema> validator1 = new TemaValidator();
        Validator<Nota> validator2 = new NotaValidator();
        StudentXMLRepository studentFileRepository = new StudentXMLRepository(validator, "studenti_test.xml");
        TemaXMLRepository temaFileRepository = new TemaXMLRepository(validator1, "teme_test.xml");
        NotaXMLRepository notaFileRepository = new NotaXMLRepository(validator2, "note_test.xml");

        service = new Service(studentFileRepository, temaFileRepository, notaFileRepository);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void saveTema_nullId() {
        Assert.assertEquals(service.saveTema(null, "aaaa", 6, 4), 1);
    }

    @Test
    public void saveTema_invalidId() {
        Assert.assertEquals(service.saveTema("", "aaaa", 6, 4), 1);
    }

    @Test
    public void saveTema_nullDescription() {
        Assert.assertEquals(service.saveTema("1", null, 6, 4), 1);
    }

    @Test
    public void saveTema_invalidDescription() {
        Assert.assertEquals(service.saveTema("1", "", 6, 4), 1);
    }

    @Test
    public void saveTema_deadlineSmaller() {
        Assert.assertEquals(service.saveTema("1", "description", 0, 4), 1);
    }

    @Test
    public void saveTema_deadlineBigger() {
        Assert.assertEquals(service.saveTema("1", "description", 15, 4), 1);
    }

    @Test
    public void saveTema_TC7() {
        Assert.assertEquals(service.saveTema("1", "description", 7, 9), 1);
    }

    @Test
    public void saveTema_startlineSmaller() {
        Assert.assertEquals(service.saveTema("1", "description", 4, 0), 1);
    }

    @Test
    public void saveTema_startlineBigger() {
        Assert.assertEquals(service.saveTema("1", "description", 4, 15), 1);
    }

    @Test
    public void addValidAssignment() {
        Assert.assertEquals(service.saveTema("11", "description", 9, 7), 1);
    }

    @Test
    public void addDuplicatedAssignment() {
        Assert.assertEquals(service.saveTema("12", "description", 9, 7), 1);
        Assert.assertEquals(service.saveTema("12", "description", 9, 7), 0);
    }
}