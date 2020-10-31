package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void etsiNimi() {
        assertEquals("Semenko", stats.search("Semenko").getName());
    }
    
    @Test
    public void etsiNimiväärin() {
        assertEquals(null, stats.search("Niko"));
    }
    
    @Test
    public void etsiTiiminPelaajatKokoOikea() {
        ArrayList<Player> pelaajat = new ArrayList<Player>();
        pelaajat.add(new Player("Semenko", "EDM", 4, 12));
        pelaajat.add(new Player("Kurri",   "EDM", 37, 53));
        pelaajat.add(new Player("Gretzky", "EDM", 35, 89));        
        
        assertEquals(3, stats.team("EDM").size());
    }
    
    @Test
    public void etsiScoretListanPituus() {
        assertEquals(3, stats.topScorers(2).size());
    }
    
    @Test
    public void playerMaalit() {
        assertEquals(4, stats.search("Semenko").getGoals());
    }   
    
    @Test
    public void playerAssists() {
        assertEquals(12, stats.search("Semenko").getAssists());
    }
    @Test
    public void playerTiimi() {
        assertEquals("EDM", stats.search("Semenko").getTeam());
    }
    @Test
    public void playerVaihdaTiimi() {
        stats.search("Semenko").setTeam("VAIHTO");
        assertEquals("VAIHTO", stats.search("Semenko").getTeam());
    } 
    @Test
    public void playerVaihdaNimi() {
        stats.search("Semenko").setName("VAIHTO");
        assertEquals("VAIHTO", stats.search("VAIHTO").getName());
    }  
    @Test
    public void playerVaihdaMaalit() {
        stats.search("Semenko").setGoals(30);
        assertEquals(30, stats.search("Semenko").getGoals());
    }      
    @Test
    public void playerVaihdaAvustukset() {
        stats.search("Semenko").setAssists(30);
        assertEquals(30, stats.search("Semenko").getAssists());
    }    
}
