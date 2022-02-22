package com.kazu.carp.app.jpa.specification;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author akifova
 * 02.06.2021
 */
@Getter @Setter
public class SpecCreator<T> {
    Specification<T> spec;

    public SpecCreator(String sp) {
        Node rootNode = new RSQLParser().parse(sp);
        spec = rootNode.accept(new CustomRsqlVisitor<T>());
    }
}
