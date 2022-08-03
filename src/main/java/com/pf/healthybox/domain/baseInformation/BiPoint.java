package com.pf.healthybox.domain.baseInformation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_point")
public class BiPoint { // 포인트 Entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter @Column(nullable = false, length = 50) private String content; // 포인트 발생 사유
    @Setter @Column(nullable = false) private int occurPoint; // 발생 포인트 금액
    @CreatedDate @Column(nullable = false, updatable = false) private LocalDateTime occurDate; // 포인트 발생 일자
    @Setter @Column(nullable = false, length = 50) private String userId; // 포인트 발생 사용자 아이디

    public BiPoint() {}

    public BiPoint(String content, int occurPoint, String userId) {
        this.content = content;
        this.occurPoint = occurPoint;
        this.userId = userId;
    }

    public static BiPoint of(String content, int occurPoint, String userId) {
        return new BiPoint(content, occurPoint, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiPoint biPoint = (BiPoint) o;
        return idx == biPoint.idx && occurPoint == biPoint.occurPoint && content.equals(biPoint.content) && userId.equals(biPoint.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, content, occurPoint, userId);
    }
}
