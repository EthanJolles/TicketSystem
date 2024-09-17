/* ITMS - A CMSC 495 Project
 * Group 2
 * Controls redirects for login/logout
 */

package com.cmsc495.ticketsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "customLogin";
    }

    @GetMapping("/logout")
    public String logOut() {
        return "redirect:/login";
    }

}
