import com.webtester.entity.Question;
import com.webtester.entity.QuestionDAO;
import com.webtester.entity.UserDAO;
import com.webtester.repo.QuestionRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class QuestionRepoTest {
    EmbeddedDatabase embeddedDatabase;

    JdbcTemplate jdbcTemplate;

    QuestionRepository questionRepository;

    @Before
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();

        jdbcTemplate = new JdbcTemplate(embeddedDatabase);

        questionRepository = new QuestionDAO(jdbcTemplate);
    }

    @After
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    public void testListAllQuestions() {
        Assert.assertNotNull(questionRepository.listAllQuestions());
    }

    @Test
    public void testFindQuestion() {
        Assert.assertEquals("2+2", questionRepository.findQuestion(1).getQuestion());
    }

    @Test
    public void testSave() {
        Question question = new Question(1000, "test?", "test!", false);

        Assert.assertNotNull(question);
        Assert.assertNotNull(questionRepository.save(question));
        Assert.assertEquals("test!", questionRepository.findQuestion(1000).getAnswer());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveInvalid() {
        questionRepository.save(new Question());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveConflict(){
        questionRepository.save(new Question(1, "2+2", "4", false));
    }

    @Test
    public void testDelete(){
        Assert.assertEquals(1, questionRepository.delete(1));
        Assert.assertEquals(0, questionRepository.delete(-1));
    }
}
