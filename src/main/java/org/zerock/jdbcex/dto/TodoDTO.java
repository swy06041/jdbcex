package org.zerock.jdbcex.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
@Builder
@Data
//모든 필드에 대한 게터, 유용한 toString 메서드 및 hashCode를 생성하고 모든 비일시적 필드를 확인하는 구현과 동일합니다. 최종이 아닌 모든 필드와 생성자에 대한 세터도 생성합니다. //@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode와 같습니다.
@NoArgsConstructor
@AllArgsConstructor //Generates an all-args constructor.
public class TodoDTO {

    private long tno;

    private String title;

    private LocalDate dueDate;

    private boolean finished;


}