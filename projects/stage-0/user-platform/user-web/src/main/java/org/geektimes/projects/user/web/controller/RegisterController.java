package org.geektimes.projects.user.web.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

@Path("/user")
public class RegisterController implements PageController {



    private UserService userService;

//    public RegisterController() {
//        this.userService = new UserServiceImpl();
//    }
//
//    public RegisterController(UserService userService) {
//        this.userService = userService;
//    }

    @POST
    @Override
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("断点1.....email: " + email + " , password: " + password);

        if (StringUtils.isBlank(email) || StringUtils.isBlank("password"))
            throw new IllegalArgumentException("必填项缺失");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(email);
        user.setPhoneNumber("13"+RandomStringUtils.randomNumeric(9));
        if (Objects.isNull(userService)) {
            userService = new UserServiceImpl();
        }
        userService.register(user);
        return "index.jsp";
    }
}
