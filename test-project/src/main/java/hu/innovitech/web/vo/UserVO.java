package hu.innovitech.web.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private String username;
    private String hashedPass;
    private String name;
    private String role;
    private List<AddressVO> addresses;

}
