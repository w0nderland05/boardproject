package org.boardpj.models.board;

import lombok.RequiredArgsConstructor;
import org.boardpj.commons.MemberUtil;
import org.boardpj.entities.BoardData;
import org.boardpj.models.board.config.BoardConfigInfoService;
import org.boardpj.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDataInfoService {

    private final BoardDataRepository boardDataRepository;
    private final BoardConfigInfoService configInfoService;
    private final MemberUtil memberUtil;

    public BoardData get(Long id) {
        return get(id, "view");
    }

    public BoardData get(Long id, String location) {

        BoardData boardData = boardDataRepository.findById(id).orElseThrow(BoardDataNotExistsException::new);

        // 게시판 설정 조회 + 접근 권한체크
        configInfoService.get(boardData.getBoard().getBId(), location);

        // 게시글 삭제 여부 체크(소프트 삭제)
        if (!memberUtil.isAdmin() && boardData.getDeletedAt() != null) {
            throw new BoardDataNotExistsException();
        }

        return boardData;
    }
}
