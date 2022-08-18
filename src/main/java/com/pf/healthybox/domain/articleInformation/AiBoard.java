package com.pf.healthybox.domain.articleInformation;

import com.pf.healthybox.domain.AuditingFields;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_board")
public class AiBoard extends AuditingFields {

    @EmbeddedId AiBoardPk aiBoardPk;

    @Setter @Column(nullable = false, length = 100) private String title;
    @Setter @Column(nullable = false, length = 50) private String writer;
    @Setter @Column(nullable = false, columnDefinition = "MEDIUMBLOB") private String content;
    @Setter @Column(length = 8) private String sellerCode;
    @Setter @Column(length = 8) private String productCode;

    public AiBoard() {
    }

    public AiBoard(AiBoardPk aiBoardPk, String title, String writer, String content, String sellerCode, String productCode) {
        this.aiBoardPk = aiBoardPk;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.sellerCode = sellerCode;
        this.productCode = productCode;
    }

    public static AiBoard of(AiBoardPk aiBoardPk, String title, String writer, String content, String sellerCode, String productCode) {
        return new AiBoard(aiBoardPk, title, writer, content, sellerCode, productCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AiBoard aiBoard = (AiBoard) o;
        return aiBoardPk.equals(aiBoard.aiBoardPk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aiBoardPk);
    }
}
