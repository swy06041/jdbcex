package org.zerock.jdbcex.service;


import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.util.MapperUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Log4j2
//enum(열거형) 타입으로 클래스를 작성하는 경우 가장 큰 장점은 정해진 수만큼만 객체를 생성할 수 있다는 점
public enum TodoService {

    INSTANCE;  // 이 부분이 객체의 개수를 결정하는 부분으로 한 개만 지정되어 있으므로 하나의 객체만을 생성헤서 사용하게 됩니다.
    // 이 경우 객체를 사용할 때는 TodoService.INSTANCE와 같이 간단하게 사용 가능하다.
    // TodoService.INSTANCE는 항상 하나의 객체만을 가리키게 되는데 이처럼 하나만 생성해서 사용하는 패턴을 싱글톤(singletone pattern)이라고 함
    //여러 컨트롤러들이 TodoService 객체를 통해서 원하는 데이터를 주고받는 구조로 구성하기 때문에 TodoService 객체를 하나만 생성하도록 합니다.

    private TodoDAO todoDAO;
    private final ModelMapper modelMapper;


    TodoService() {

        todoDAO = new TodoDAO();
        modelMapper= MapperUtil.INSTANCE.get();

    }

    // todoDTO 객체를 받아서 확인하는 것이 목표
    public void register(TodoDTO todoDTO) throws Exception {

        System.out.println("들어온 todoDTO(데이터 전송을 위한) 객체" + todoDTO);

        TodoVO todoVO=modelMapper.map(todoDTO, TodoVO.class);

        System.out.println("todoDTO(데이터 전송을 위한) 객체가 TodoVO 객체로 매핑됨 " + todoVO);

        todoDAO.insert(todoVO);

    }


    public List<TodoDTO> listAll() throws  Exception{
        List<TodoVO> voList = todoDAO.selectAll();
        log.info("voList객체" + voList);
//        vo를 dto로 변환
         List<TodoDTO> dtoList= voList.stream().map( vo -> modelMapper.map(vo,TodoDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

    //10개의 TodoDTO 객체를 만들어서 반환
    public List<TodoDTO> getList(){
        //스트림 이용

        List<TodoDTO> todoDTOS =IntStream.range(0,10).mapToObj( i -> {

            TodoDTO dto=new  TodoDTO();
            dto.setTno((long) i);
            dto.setTitle("Todo..." + i);
            dto.setDueDate(LocalDate.now());

            return dto;
        }).collect(Collectors.toList());  ;


        return  todoDTOS;
    }

    public TodoDTO get(Long tno){
        TodoDTO todo=new TodoDTO();
        todo.setTno(tno);
        todo.setTitle("Sample Todo");
        todo.setDueDate(LocalDate.now());
        todo.setFinished(true);
        return todo;
    }


    public void remove(Long tno)throws Exception {

        log.info("tno: " + tno);
        todoDAO.deleteOne(tno);
    }

    public void modify(TodoDTO todoDTO)throws Exception {

        log.info("todoDTO: " + todoDTO );

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        todoDAO.updateOne(todoVO);

    }

}