package org.boardpj.repositories;

import org.boardpj.entities.Board;
import org.boardpj.entities.BoardData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardDataRepository extends JpaRepository<BoardData, String>, QuerydslPredicateExecutor<Board> {

}