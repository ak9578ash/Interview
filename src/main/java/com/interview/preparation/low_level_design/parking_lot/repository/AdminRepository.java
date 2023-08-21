package com.interview.preparation.low_level_design.parking_lot.repository;

import com.interview.preparation.low_level_design.parking_lot.model.account.Admin;

import java.util.*;

public class AdminRepository {
    public static Map<String, Admin> adminMap = new HashMap<>();
    public static List<Admin> admins = new ArrayList<>();

    public Admin addAdmin(Admin admin){
        adminMap.putIfAbsent(admin.getId() , admin);
        admins.add(admin);
        return admin;
    }

    public Admin getAdminByEmailId(String emailId){
        Optional<Admin> admin = admins
                .stream()
                .filter(adm -> adm.getEmail().equalsIgnoreCase(emailId))
                .findFirst();
        return admin.orElse(null);
    }

    public Admin getAdminById(String adminId){
        return adminMap.get(adminId);
    }
}
