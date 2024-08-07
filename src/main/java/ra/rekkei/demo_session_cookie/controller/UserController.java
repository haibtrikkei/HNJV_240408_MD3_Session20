package ra.rekkei.demo_session_cookie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.rekkei.demo_session_cookie.model.dto.UserDTO;
import ra.rekkei.demo_session_cookie.model.entity.Users;
import ra.rekkei.demo_session_cookie.service.UserService;
import sun.jvm.hotspot.gc.z.ZForwardingEntry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/","/login"})
    public String login(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user",userDTO);
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(@Valid @ModelAttribute("user")UserDTO user, BindingResult result, @RequestParam(value = "save",defaultValue = "")String save, Model model, HttpSession session, HttpServletResponse response){
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "login";
        }else{
            Users userLogin = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if(userLogin!=null){
                //xu ly save password
                Cookie cusername;
                Cookie cpassword;
                Cookie csave;

                if(save!=null && !save.isEmpty()){
                    cusername = new Cookie("username",user.getUsername());
                    cpassword = new Cookie("password",user.getPassword());
                    csave = new Cookie("save","checked");

                }else{
                    cusername = new Cookie("username","");
                    cpassword = new Cookie("password","");
                    csave = new Cookie("save","");
                }

                cusername.setMaxAge(7*24*60*60);
                cpassword.setMaxAge(7*24*60*60);
                csave.setMaxAge(7*24*60*60);

                //su dung response de mang cookie di
                response.addCookie(cusername);
                response.addCookie(cpassword);
                response.addCookie(csave);

                user.setPassword("");
                session.setAttribute("userLogin",userLogin);
                return "home";
            }else{
                model.addAttribute("error","Login failed!");
                model.addAttribute("user",user);
                return "login";
            }
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping("/doLoginAuto")
    public String doLoginAuto(HttpServletRequest request, HttpSession session){
        Cookie[] cookies = request.getCookies();
        String username = "", password = "";
        for(Cookie c : cookies){
            if(c.getName().equals("username"))
                username = c.getValue();
            if(c.getName().equals("password"))
                password = c.getValue();
        }
        Users userLogin = userService.findByUsernameAndPassword(username, password);
        if(userLogin!=null){
            userLogin.setPassword("");
            session.setAttribute("userLogin",userLogin);
            return "home";
        }else{
            return "redirect:/login";
        }
    }
}
