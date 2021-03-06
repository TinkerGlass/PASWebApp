package com.jaba.webapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jaba.webapp.breadcrumbs.annotation.Breadcrumb;
import com.jaba.webapp.controller.dto.AccountTypeRequest;
import com.jaba.webapp.controller.dto.UserSearchRequest;
import com.jaba.webapp.controller.jsonviews.JSONViews;
import com.jaba.webapp.converter.StringToAccountTypeConverter;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.service.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScope
@Controller
public class UserController {

    private UserManager userService;
    private ResourceBundleMessageSource messageSource;

    @Autowired
    public UserController(UserManager userService){
        this.userService = userService;
    }

    @Autowired
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Breadcrumb(label="users.title", depth=0, family = {"user", "userModify"})
    @RequestMapping(value = "/users")
    public String showUsers(@RequestParam(name="username", defaultValue = "") String usernameQuery, Model model) {
        model.addAttribute("allUsers", userService.findUsersByUsername(usernameQuery));
        return "users";
    }

    @RequestMapping(value = "/users-ajax", method = RequestMethod.POST)
    @ResponseBody
    @JsonView(JSONViews.UserListView.class)
    public List<User> showUsersAjax(@RequestBody UserSearchRequest searchRequest) {
        return userService.findUsersByUsername(searchRequest.getUsername());
    }

    @RequestMapping(value = "/account-type-ajax", method = RequestMethod.POST)
    @ResponseBody
    public String getAccountTypeDescription(@RequestBody AccountTypeRequest type) {
        User.AccountType typeConverted = new StringToAccountTypeConverter().convert(type.getType());
        return messageSource.getMessage(typeConverted.getDescription(), null, LocaleContextHolder.getLocale());
    }

    @Breadcrumb(label="users.add.title", depth=1, family = {"user"})
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allAccountTypes", Arrays.asList(User.AccountType.values()));
        return "addUser";
    }

    @Breadcrumb(label="users.add.title", depth=1, family = {"user"})
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute User user,
                          final BindingResult bindingResult,
                          @ModelAttribute("errors") ArrayList<ApplicationException> errors, Model model, RedirectAttributes ra){
        model.addAttribute("allAccountTypes", Arrays.asList(User.AccountType.values()));
        if(bindingResult.hasErrors())
            return "addUser";
        try {
            userService.addUser(user);
        } catch(ApplicationException e) {
            errors.add(e);
            if(e.getErrorCode() == ApplicationException.ErrorCode.USERNAME_NOT_UNIQUE.ordinal())
                bindingResult.addError(new FieldError("user", "username",""));
            return "addUser";
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String shutUser(@PathVariable Long id, RedirectAttributes ra) {
        try {
            userService.blockUser(id);
        } catch (ApplicationException e) {

        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/upUser/{id}", method = RequestMethod.GET)
    public String upUser(@PathVariable Long id, RedirectAttributes ra) {
        try {
            userService.unblockUser(id);
        } catch (ApplicationException e) {

        }
        return "redirect:/users";
    }

    @Breadcrumb(label="users.modify.title", depth=1, family = {"userModify"})
    @RequestMapping(value = "/modifyUser/{id}", method = RequestMethod.GET)
    public String modifyUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allAccountTypes", Arrays.asList(User.AccountType.values()));
        return "modifyUser";
    }


    @Breadcrumb(label="users.modify.title", depth=1, family = {"userModify"})
    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public String modifyUserForm(@Valid @ModelAttribute User user,
                                 final BindingResult bindingResult,
                                 Model model, RedirectAttributes ra) {
        model.addAttribute("allAccountTypes", Arrays.asList(User.AccountType.values()));
        if(bindingResult.hasErrors())
            return "modifyUser";
        try {
            userService.updateUser(user);
        } catch(ApplicationException e) {
            ArrayList<ApplicationException> errors = new ArrayList<>();
            model.addAttribute("errors", errors);
            errors.add(e);
            if(e.getErrorCode() == ApplicationException.ErrorCode.USERNAME_NOT_UNIQUE.ordinal())
                bindingResult.addError(new FieldError("user", "username",""));
            return "modifyUser";
        }
        return "redirect:/users";
    }
}
