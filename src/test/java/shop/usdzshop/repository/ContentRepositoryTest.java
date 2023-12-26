package shop.usdzshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import shop.usdzshop.domain.Content;
import shop.usdzshop.service.ContentService;

@SpringBootTest
@Transactional
class ContentRepositoryTest {

    @Autowired
    ContentService contentService;

    @Autowired
    ContentRepository contentRepository;

    @Test
    @Rollback(value = false)
    public void 컨텐트_업로드() throws Exception {
        //given
        Content content = new Content("lee", "image", null);

        //when
        Long contentId = contentService.join(content);

        //then
        assertEquals(content, contentRepository.findOne(contentId));
    }

}