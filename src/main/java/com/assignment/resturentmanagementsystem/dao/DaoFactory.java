package com.assignment.resturentmanagementsystem.dao;

import com.assignment.resturentmanagementsystem.bo.custom.impl.FeedbackBoImpl;
import com.assignment.resturentmanagementsystem.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {

    }
    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();

        }
        return daoFactory;
    }

    public enum ServiceType {
    LOGIN,BRANCH,CUSTOMER,EMPLOYEE,CATEGORY,FEEDBACK,INVENTORY,MENU,RESERVATION,ROLE,SALESREPORT,SUPPLIER,ORDER,ORDERVIEW
    }

    public SuperDao getDao(ServiceType serviceType) {
        switch (serviceType) {
            case LOGIN:
                return new LoginDaoImpl();
                case BRANCH:
                    return  new BranchDaoImpl();
            case CUSTOMER:
                 return new CustomerDaoImpl();
            case EMPLOYEE:
                return new EmplyeeDaoImpl();
            case CATEGORY:
               return new CatagoryDaoImpl();
            case FEEDBACK:
                return new FeedbackDaoImpl();
            case INVENTORY:
                return new InventoryDaoImpl();
            case MENU:
                return new MenuDaoImpl();
            case RESERVATION:
                return new ReservationDaoImpl();
            case ROLE:
                return new RoleDaoImpl();
            case SALESREPORT:
                return new SalesReportDaoImpl();
            case SUPPLIER:
                return new SupplierDaoImpl();
            case ORDER:
                return new OrderDaoImpl();
            case ORDERVIEW:
                return new OrderVIewDaoImpl();
                default:
                    return null;
            }
        }
    }

