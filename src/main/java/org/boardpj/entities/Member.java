package org.boardpj.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.boardpj.commons.constants.Role;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name="idx_member_userNm",columnList = "userNm"),
        @Index(name="idx_member_email", columnList = "email")
})
public class Member extends BaseEntity {

    @Id @GeneratedValue
    private Long userNo; //회원번호

    @Column(length = 40, nullable = false, unique = true)
    private String userId;//회원 아이디
    @Column(length = 65, nullable = false)
    private String userPw;//회원비밀번호

    @Column(length = 40, nullable = false)
    private String userNm;//회원명

    @Column(length = 100, nullable = false)
    private String email;//이메일

    @Column(length = 11)
    private String mobile;//휴대전화

    @Lob
    private String termsAgree;//약관동의 내역(추후 JSON형태로)

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role roles = Role.USER;
}
