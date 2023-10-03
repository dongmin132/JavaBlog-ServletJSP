package iducs.javaweb.blog201912018.model;

// 1. model 패키지를 추가
// 2. MemberDTO 클래스 정의
public class MemberDTO {
    private String name;
    private String email;

    public String getName() { // boiler-plate code : 상용구, 관용구
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
