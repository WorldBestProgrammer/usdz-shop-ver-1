package shop.usdzshop.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.usdzshop.domain.Content;
import shop.usdzshop.repository.ContentRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    @Transactional
    public Long join(Content content) {
        validateDuplicateMember(content);
        contentRepository.save(content);
        return content.getId();
    }

    private void validateDuplicateMember(Content content) {
        List<Content> findContent = contentRepository.findByName(content.getName());
        if (!findContent.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 컨텐츠입니다.");
        }
    }

    public Content findOne(Long id) {
        return contentRepository.findOne(id);
    }

    public List<Content> findContents() {
        return contentRepository.findAll();
    }

}
