/* ITMS - A CMSC 495 Project
 * Group 2
 * This class handles requests related to users
 */

package com.cmsc495.ticketsystem.controller.admin.manage_users;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmsc495.ticketsystem.model.AdminUserModel;
import com.cmsc495.ticketsystem.service.AdminUserDetailService;

@Controller
public class ManageUsersController {

    @Autowired
    private AdminUserDetailService adminUserDetailService;

    /*navigates to manage-users page and uses AdminUserDetailService to retrieve all
     * the users from the database
     * and adds them to the AdminUser model, making them available to the manage-users
     * view*/
    @GetMapping("/admin/manage-users")
    public String showManageUsersPage(Model model) {
        List<AdminUserModel> adminUserList = adminUserDetailService.findAllUsers();
        model.addAttribute("adminUserList", adminUserList);
        return "manage-users";
    }

    /* Creates and saves user to database when user created and stays on same page
     * after submit*/
    @PostMapping("/admin/manage-users/submit")
    public String createUser(@RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {

        // Create and save AdminUser object
        AdminUserModel adminUserModel = new AdminUserModel(username, password);
        String userCreationStatus = adminUserDetailService.saveAdminUser(adminUserModel);
        redirectAttributes.addFlashAttribute("userCreationStatus", userCreationStatus);
        return "redirect:/admin/manage-users";
    }

    @PostMapping("/admin/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        adminUserDetailService.deleteUserById(id); // Call service to delete the user
        return "redirect:/admin/manage-users"; // Redirect back to the manage-users page
    }
}
