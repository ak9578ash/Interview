package com.interview.preparation.low_level_design.parking_lot.service;

import com.interview.preparation.low_level_design.parking_lot.model.account.Admin;
import com.interview.preparation.low_level_design.parking_lot.repository.AdminRepository;

public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public Admin addAdmin(Admin admin){
        return adminRepository.addAdmin(admin);
    }

    public Admin getAdminByEmailId(String emailId){
        return adminRepository.getAdminByEmailId(emailId);
    }

    public Admin getAdminById(String adminId){
        return adminRepository.getAdminById(adminId);
    }

}
