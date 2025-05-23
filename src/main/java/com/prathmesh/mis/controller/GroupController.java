package com.prathmesh.mis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prathmesh.mis.entity.Group;
import com.prathmesh.mis.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<Group> create(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(groupService.addGroup(body.get("groupName")));
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAll() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> update(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(groupService.updateGroup(id, body.get("groupName")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        groupService.softDeleteGroup(id);
        return ResponseEntity.ok().build();
    }
}

