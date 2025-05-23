package com.prathmesh.mis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prathmesh.mis.entity.Group;
import com.prathmesh.mis.repository.GroupRepository;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public Group addGroup(String groupName) {
        if (groupRepository.findByGroupName(groupName).isPresent())
            throw new RuntimeException("Group name already exists!");
        Group group = new Group();
        group.setGroupName(groupName);
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAllByIsActiveTrue();
    }

    public Group updateGroup(Long id, String newName) {
        Group group = groupRepository.findById(id).orElseThrow();
        group.setGroupName(newName);
        return groupRepository.save(group);
    }

    public void softDeleteGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow();
        // Check if related to chainId logic here
        group.setActive(false);
        groupRepository.save(group);
    }
}
