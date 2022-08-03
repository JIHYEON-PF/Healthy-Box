package com.pf.healthybox.domain.articleInformation;

import com.pf.healthybox.domain.config.AuditingFields;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_reply")
public class AiReply extends AuditingFields {

    @EmbeddedId private AiReplyPk aiReplyPk;

    @Setter @Column(nullable = false, length = 50) private String writer;
    @Setter @Column(nullable = false, columnDefinition = "TEXT") private String content;

    public AiReply() {
    }

    public AiReply(AiReplyPk aiReplyPk, String writer, String content) {
        this.aiReplyPk = aiReplyPk;
        this.writer = writer;
        this.content = content;
    }

    public static AiReply of(AiReplyPk aiReplyPk, String writer, String content) {
        return new AiReply(aiReplyPk, writer, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AiReply aiReply = (AiReply) o;
        return aiReplyPk.equals(aiReply.aiReplyPk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aiReplyPk);
    }
}

@Getter
@RequiredArgsConstructor
@Embeddable
class AiReplyPk implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column private Long boardIdx;
    @Column(length = 3) private String boardGroup;
    @Column(length = 3) private String boardCategory;

}
