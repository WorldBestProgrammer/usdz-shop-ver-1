package shop.usdzshop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Entity
@Getter
public class Content {

    public Content() {

    }

    public Content(String name, String image, @NotEmpty byte[] file) {
        this.name = name;
        this.image = image;
        this.file = file;
    }

    @Id
    @GeneratedValue
    @Column(name = "content_id")
    private Long id;

    @NotEmpty
    private String name;

    @Lob
    @Column(name = "thumbnail_image")
    private String image;

    @Lob
    @NotEmpty
    @Column(name = "usdz_file")
    private byte[] file;
}
