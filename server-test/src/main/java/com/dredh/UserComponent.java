package com.dredh;

import com.dredh.handler.user.IUser;
import com.dredh.handler.user.IUserResponse;
import com.dredh.handler.user.LoginRequest;
import com.dredh.handler.user.UserHandlerComponent;
import com.dredh.model.ResponseType;
import org.springframework.stereotype.Component;

@Component
public class UserComponent implements UserHandlerComponent {
    @Override
    public IUserResponse login(LoginRequest request) {
        return new IUserResponse() {
            @Override
            public IUser getUser() {
                return new User(1L, "zhangsan");
            }

            @Override
            public int getResultCode() {
                return ResponseType.SUCCESS.getResultCode();
            }
        };
    }

}
