package com.azureadmanagementapp.azureadmanagementapp.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.azureadmanagementapp.azureadmanagementapp.AddMember;
import com.azureadmanagementapp.azureadmanagementapp.AddOwner;
import com.azureadmanagementapp.azureadmanagementapp.CreateGroup;
import com.azureadmanagementapp.azureadmanagementapp.DeleteGroup;
import com.azureadmanagementapp.azureadmanagementapp.RemoveMember;
import com.azureadmanagementapp.azureadmanagementapp.RemoveOwner;
import com.azureadmanagementapp.azureadmanagementapp.ServiceNow;
import com.azureadmanagementapp.azureadmanagementapp.model.GroupModel;
import com.azureadmanagementapp.azureadmanagementapp.model.MemberModel;

@Controller
public class IndexController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @PostMapping(value = "/create-group")
  public String createGroup(@ModelAttribute GroupModel user) throws IOException {
    CreateGroup create = new CreateGroup();
    create.createIfNew(user.getGroupName());
    ServiceNow serviceNow = new ServiceNow();
    serviceNow.createChangeTicket("Create Group:", user.getGroupName());
    System.out.println(user.getGroupName());
    return "redirect:/";
  }

  @PostMapping(value = "/delete-group")
  public String deleteGroup(@ModelAttribute GroupModel user) throws IOException {
    DeleteGroup delete = new DeleteGroup();
    delete.deleteGroup(user.getGroupName());
    ServiceNow serviceNow = new ServiceNow();
    serviceNow.createChangeTicket("Delete Group:", user.getGroupName());
    System.out.println(user.getGroupName());
    return "redirect:/";
  }

  @PostMapping(value = "/add-member")
  public String addMember(@ModelAttribute MemberModel user) throws IOException {
    AddMember addMember = new AddMember();
    addMember.addMember(user.getGroupName(), user.getMemberId());
    ServiceNow serviceNow = new ServiceNow();
    serviceNow.createChangeTicket("Add Member:", user.getMemberId() + " to " + user.getGroupName());
    System.out.println(user.getGroupName());
    System.out.println(user.getMemberId());
    return "redirect:/";
  }

  @PostMapping(value = "/remove-member")
  public String removeMember(@ModelAttribute MemberModel user) throws IOException {
    RemoveMember remove = new RemoveMember();
    remove.removeMember(user.getGroupName(), user.getMemberId());
    ServiceNow serviceNow = new ServiceNow();
    serviceNow.createChangeTicket("Remove Member:", user.getMemberId() + " from " + user.getGroupName());
    System.out.println(user.getGroupName());
    System.out.println(user.getMemberId());
    return "redirect:/";
  }

  @PostMapping(value = "/add-owner")
  public String addOwner(@ModelAttribute MemberModel user) throws IOException {
    AddOwner addOwner = new AddOwner();
    addOwner.addOwner(user.getGroupName(), user.getMemberId());
    ServiceNow serviceNow = new ServiceNow();
    serviceNow.createChangeTicket("Add Owner:", user.getMemberId() + " to " + user.getGroupName());
    System.out.println(user.getGroupName());
    System.out.println(user.getMemberId());
    return "redirect:/";
  }

  @PostMapping(value = "/remove-owner")
  public String removeOwner(@ModelAttribute MemberModel user) throws IOException {
    RemoveOwner removeOwner = new RemoveOwner();
    removeOwner.removeOwner(user.getGroupName(), user.getMemberId());
    ServiceNow serviceNow = new ServiceNow();
    serviceNow.createChangeTicket("Remove Owner:", user.getMemberId() + " from " + user.getGroupName());
    System.out.println(user.getGroupName());
    System.out.println(user.getMemberId());
    return "redirect:/";
  }
}
