package shop.usdzshop.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.usdzshop.domain.Content;

@Repository
@RequiredArgsConstructor
public class ContentRepository {

    private final EntityManager em;

    public void save(Content content) {
        em.persist(content);
    }

    public Content findOne(Long id) {
        return em.find(Content.class, id);
    }

    public List<Content> findAll() {
        return em.createQuery("select c from Content c", Content.class)
                .getResultList();
    }

    public List<Content> findByName(String name) {
        return em.createQuery("select c from Content c where c.name = :name", Content.class)
                .setParameter("name", name)
                .getResultList();
    }
}
