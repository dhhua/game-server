package com.dredh.handler.user;

import com.dredh.common.annotation.Command;
import com.dredh.common.annotation.CommandHandler;
import com.dredh.model.ResponseType;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.net.InetSocketAddress;


@CommandHandler(name = "user")
@ConditionalOnBean(UserHandlerComponent.class)
public class UserHandler {

    @Autowired
    private UserHandlerComponent component;

    @Autowired
    private UserManager userManager;

    @Command
    public ResponseType login(LoginRequest request, Channel channel) {
        IUserResponse response = component.login(request);
        InetSocketAddress socketAddress = (InetSocketAddress) channel.remoteAddress();
        Session session = new Session(response.getUser(), channel, socketAddress.getAddress().getHostAddress());
        userManager.putSession(response.getUser().getUserId(), session);
        return new ResponseType(response.getResultCode());
    }
}
