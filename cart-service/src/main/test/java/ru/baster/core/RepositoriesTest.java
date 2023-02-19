package java.ru.baster.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.baster.study.market.core.model.Category;
import ru.baster.study.market.core.repository.CategoryRepository;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void genreRepositoryTest() {
        Category category = new Category();
        category.setTitle("Fiction");
        entityManager.persist(category);
        entityManager.flush();

        List<category> genresList = categoryRepository.findAll();

        Assertions.assertEquals(2, genresList.size());
        Assertions.assertEquals("Fiction", genresList.get(1).getTitle());
    }

    @Test
    public void initDbTest() {
        List<category> genresList = categoryRepository.findAll();
        Assertions.assertEquals(1, genresList.size());
    }
}
