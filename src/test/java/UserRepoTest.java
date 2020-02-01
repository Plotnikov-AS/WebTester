import com.webtester.entity.UserDAO;
import com.webtester.entity.User;
import com.webtester.repo.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class UserRepoTest {
    private EmbeddedDatabase embeddedDatabase;

    private JdbcTemplate jdbcTemplate;

    private UserRepository userRepository;

    @Before
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();

        jdbcTemplate = new JdbcTemplate(embeddedDatabase);

        userRepository = new UserDAO(jdbcTemplate);
    }

    @After
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    public void testListAll() {
        Assert.assertNotNull(userRepository.listAllUsers());
    }

    @Test
    public void testFindUser() {
        Assert.assertNotNull(userRepository.findUser(1));
        Assert.assertNull(userRepository.findUser(-1));
    }

    @Test
    public void testSave() {
        User user = userRepository.save(new User(1000, "FirstName", "LastName"));

        Assert.assertNotNull(user);
        Assert.assertNotEquals(user.getId(), 0);
        Assert.assertEquals("FirstName", user.getFirstName());
        Assert.assertEquals("LastName", user.getLastName());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveInvalid() {
        userRepository.save(new User());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveConflict(){
        userRepository.save(new User(1, "Jack", "Daniels"));
    }

    @Test
    public void testDelete(){
        Assert.assertEquals(1, userRepository.delete(1));
        Assert.assertEquals(1, userRepository.delete(userRepository.findUser(2)));
        Assert.assertEquals(0, userRepository.delete(new User()));
    }
}
