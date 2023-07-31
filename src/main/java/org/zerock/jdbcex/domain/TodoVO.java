package org.zerock.jdbcex.domain;

import lombok.*;

import java.time.LocalDate;

@Getter    // getter 자동 생성
@Builder  //모든 필드를 인수로 사용하여 생성자가 생성, 빌더 패턴 사용, @Builder를 이용해서 TodoVO.builder().build()와 같은 형태로 객체를 생성할 수 있다.
@ToString  //toString()메서드 자동 생성
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {

    private long tno;

    private String title;

    private LocalDate dueDate;

    private boolean finished;


}