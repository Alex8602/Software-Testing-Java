import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CentruComercialTest {
    private CentruComercial centruComercial;
    private ArrayList<Magazin> magazine;

    @BeforeEach
    public void setUp() throws ExceptieMagazin {
        magazine = new ArrayList<>();
        magazine.add(new Magazin("Magazin1", 10, ETipMagazin.HAINE, 240));
        magazine.add(new Magazin("Magazin2", 8, ETipMagazin.COSMETICE, 150));
        magazine.add(new Magazin("Magazin3", 12, ETipMagazin.HAINE, 200));
        centruComercial = new CentruComercial("CentruComercial", 10000, magazine);
    }

    @AfterEach
    public void tearDown() {
        magazine = null;
        centruComercial = null;
    }

    @Test
    public void testCelMaiMareMagazinRight() throws ExceptieMagazin {
        assertEquals("Magazin1", centruComercial.getCelMaiMareMagazin(ETipMagazin.HAINE));
    }

    @Test
    public void testCelMaiMareMagazinException()  {
        assertThrows(ExceptieMagazin.class, () -> centruComercial.getCelMaiMareMagazin(ETipMagazin.DECORATIUNI));
    }

    @Test
    public void testCelMaiMareMagazinExistence() {
        ETipMagazin tipMagazin = ETipMagazin.HAINE;
        assertDoesNotThrow(() -> centruComercial.getCelMaiMareMagazin(tipMagazin));
    }

    @Test
    public void testCelMaiMareMagazinExistenceArgument()  {
        assertThrows(ExceptieMagazin.class, () -> centruComercial.getCelMaiMareMagazin(null));
    }

    @Test
    public void testCalculTarifAsigurareRight() throws ExceptieCalculAsigurare {
        IAutoritate autoritateMock = mock(IAutoritate.class);
        when(autoritateMock.esteAsigurat(centruComercial)).thenReturn(true);
        when(autoritateMock.tarifAsigurareMp()).thenReturn(10.0f);

        float tarif = centruComercial.calculTarifAsigurare(autoritateMock);
        assertEquals(5900, tarif);
    }

    @Test
    public void testCalculTarifAsigurareException() {
        IAutoritate autoritateMock = mock(IAutoritate.class);
        when(autoritateMock.esteAsigurat(centruComercial)).thenReturn(false);

        assertThrows(ExceptieCalculAsigurare.class, () -> centruComercial.calculTarifAsigurare(autoritateMock));
    }

    @Test
    public void testCalculTarifAsigurareExistence() {
        assertDoesNotThrow(() -> {
            IAutoritate autoritateMock = mock(IAutoritate.class);
            when(autoritateMock.esteAsigurat(centruComercial)).thenReturn(true);

            float tarif = centruComercial.calculTarifAsigurare(autoritateMock);
            assertEquals(0, tarif);
        });
    }

    @Test
    public void testCalculTarifAsigurareExistenceArgument() {
        assertThrows(ExceptieCalculAsigurare.class,  () -> centruComercial.calculTarifAsigurare(null));
    }

    @Test
    @Tag("UnitTest2")
    public void testCalculTarifAsigurareCrossCheck() throws ExceptieCalculAsigurare {
        IAutoritate autoritateMock = mock(IAutoritate.class);
        when(autoritateMock.esteAsigurat(centruComercial)).thenReturn(true);
        when(autoritateMock.tarifAsigurareMp()).thenReturn(10.0f);

        float tarif = centruComercial.calculTarifAsigurare(autoritateMock);
        int suprafata = 0;
        for (int i = 0; i < magazine.size(); i++) {
            suprafata += magazine.get(i).getSuprafataMp();
        }
        float tarif_real = suprafata * 10.0f;

        assertEquals(tarif_real, tarif);
    }

    @Test
    @Tag("UnitTest2")
    public void testCalculTarifAsigurareBoundary() throws ExceptieMagazin {
        ArrayList<Magazin> magazine = new ArrayList<>();
        magazine.add(new Magazin("Magazin", 10, ETipMagazin.HAINE, 100));
        CentruComercial centruComercial = new CentruComercial("CentruTest", 100, magazine);

        IAutoritate autoritateMock = mock(IAutoritate.class);
        when(autoritateMock.esteAsigurat(centruComercial)).thenReturn(true);
        when(autoritateMock.tarifAsigurareMp()).thenReturn(Float.MAX_VALUE);

        assertThrows(ExceptieCalculAsigurare.class, () -> centruComercial.calculTarifAsigurare(autoritateMock));
    }

    @Test
    @Timeout(10)
    @Tag("UnitTest2")
    public void testGetCelMaiMareMagazinPerformance() {
        assertTimeout(Duration.ofMillis(10), () -> {
            ArrayList<Magazin> magazine = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                magazine.add(new Magazin("Magazin" + (i + 1), 10 + i, ETipMagazin.HAINE, 100 + (i * 10)));
            }
            CentruComercial centruComercial = new CentruComercial("CentruTest", 1000, magazine);
            assertEquals("Magazin10", centruComercial.getCelMaiMareMagazin(ETipMagazin.HAINE));
        });
    }
}