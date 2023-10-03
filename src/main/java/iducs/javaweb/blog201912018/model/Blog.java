package iducs.javaweb.blog201912018.model;


import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode

public class Blog {

    private long id;
    private String title;
    private String content;
    private String author;
    private String email;
    private String regDate;

    public Blog() {
        // 현재 시간을 가져와서 "yyyy-MM-dd HH:mm:ss" 형식으로 변환
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.regDate = currentDateTime.format(formatter);
    }

}
