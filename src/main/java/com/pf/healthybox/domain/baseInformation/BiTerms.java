package com.pf.healthybox.domain.baseInformation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_terms")
public class BiTerms {

    @Id
    @Column(length = 3)
    private String code;

    @Setter @Column(nullable = false, unique = true, length = 30) private String termsName;
    @Setter @Column(nullable = false, columnDefinition = "TEXT") private String content;

    public BiTerms() {}

    public BiTerms(String code, String termsName, String content) {
        this.code = code;
        this.termsName = termsName;
        this.content = content;
    }

    public static BiTerms of(String code, String termsName, String content) {
        return new BiTerms(code, termsName, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiTerms biTerms = (BiTerms) o;
        return code.equals(biTerms.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
