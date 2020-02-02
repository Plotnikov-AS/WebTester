import com.webtester.entity.AnswerOptionsDAO;
import com.webtester.entity.Question;
import com.webtester.entity.QuestionDAO;
import com.webtester.repo.AnswerOptionsRepository;
import com.webtester.repo.QuestionRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.ArrayList;
import java.util.List;

public class AnswerOptionsRepoTest {
    EmbeddedDatabase embeddedDatabase;

    JdbcTemplate jdbcTemplate;

    AnswerOptionsRepository answerOptionsRepository;
    QuestionRepository questionRepository;

    @Before
    public void setUp(){
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();

        jdbcTemplate = new JdbcTemplate(embeddedDatabase);

        answerOptionsRepository = new AnswerOptionsDAO(jdbcTemplate);
        questionRepository = new QuestionDAO(jdbcTemplate);
    }

    @After
    public void tearDown(){
        embeddedDatabase.shutdown();
    }

    @Test
    public void testGetQuestionAnswerOptions(){
        List<String> requiredList = answerOptionsRepository.getQuestionAnswerOptions(questionRepository.findQuestion(2));
        List<String> expectedList = new ArrayList<>();
        expectedList.add("первый");
        expectedList.add("Борис Николаевич Ельцин");
        expectedList.add("третий");
        expectedList.add("четвертый");
        Assert.assertNotNull(requiredList);
        Assert.assertEquals(expectedList.get(1), answerOptionsRepository.getQuestionAnswerOptions(questionRepository.findQuestion(2)).get(1));
        Assert.assertNull(answerOptionsRepository.getQuestionAnswerOptions(questionRepository.findQuestion(1)));
    }
}
