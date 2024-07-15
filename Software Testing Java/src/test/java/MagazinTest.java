import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest1")
public class MagazinTest {

    private Magazin magazin;

    @BeforeEach
    public void setUp() throws ExceptieMagazin {
        this.magazin = new Magazin("Magazin", 10, ETipMagazin.HAINE, 150);
    }

    @AfterEach
    public void tearDown() {
        this.magazin = null;
    }

    @Test
    public void testConstructorRight(){
        assertNotNull(this.magazin);
        assertEquals("Magazin", magazin.getDenumire());
        assertEquals(10, magazin.getNrAngajati());
        assertEquals(ETipMagazin.HAINE, magazin.getTipMagazin());
        assertEquals(150, magazin.getSuprafataMp());
    }

    @Test
    public void testConstructorException() {
        // pentru denumire
        assertThrows(ExceptieMagazin.class, () -> {
            new Magazin("M", 10, ETipMagazin.HAINE, 100);
        });
        assertThrows(ExceptieMagazin.class, () -> {
            new Magazin("MagazinNumeFoarteLung................................................................" +
                    "..................................................", 21, ETipMagazin.HAINE, 100);
        });

        // pentru nrAngajati
        assertThrows(ExceptieMagazin.class, () -> {
                    new Magazin("Magazin", 0, ETipMagazin.HAINE, 100);
        });
        assertThrows(ExceptieMagazin.class, () -> {
            new Magazin("Magazin", 25, ETipMagazin.HAINE, 100);
        });

        // pentru suprafataMp
        assertThrows(ExceptieMagazin.class, () -> {
            new Magazin("Magazin", 10, ETipMagazin.HAINE, 5);
        });
        assertThrows(ExceptieMagazin.class, () -> {
            new Magazin("Magazin", 10, ETipMagazin.HAINE, 300);
        });

    }

    @Test
    public void testConstructorExistence() {
        assertNotNull(this.magazin);
    }

}