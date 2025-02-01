package com.assignment.resturentmanagementsystem.bo;

import com.assignment.resturentmanagementsystem.bo.custom.impl.MenuBoImpl;
import com.assignment.resturentmanagementsystem.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory serviceFactory;

    private BoFactory() {

    }

    public static BoFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new BoFactory();
        }
        return serviceFactory;
    }

    public SuperBo getSuperService(ServiceType serviceType) {
        switch (serviceType){
            case LOGIN -> {
                return new LoginServiceImpl();
            }
            case BRANCH -> {
                return new BranchBoImpl();
            }
            case CUSTOMER -> {
                return new CustomerBoImpl();
            }
            case EMPLOYEE -> {
                return new EmployeeBoImpl();
            }
            case CATEGORY -> {
                return new CatagoryBoImpl();
            }
            case FEEDBACK -> {
                return new FeedbackBoImpl();
                }
            case INVENTORY -> {
                return new InventoryBoImpl();
            }
            case MENU -> {
                return new MenuBoImpl();
            }
            case RESERVATION -> {
                return new ReservationBoImpl();
            }
            case ROLE -> {
                return new RoleBoImpl();
            }
            case SALESREPORT -> {
                return new SalesReportBoImpl();
            }
            case SUPPLIER -> {
                return new SupplierBoImpl();
            }
            case ORDER -> {
                return new OrderBoImpl();
            }
                case ORDERVIEW -> {
                    return new OrderViewBoimpl();
            }
            default -> {
                return null;
            }
        }
    }
    public enum ServiceType {
    LOGIN,BRANCH,CUSTOMER,EMPLOYEE,CATEGORY,FEEDBACK,INVENTORY,MENU,RESERVATION,ROLE,SALESREPORT,SUPPLIER,ORDER,ORDERVIEW
    }
}
