package lk.ijse.webpossystembackend.dao;

import lk.ijse.webpossystembackend.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.webpossystembackend.dao.custom.impl.ItemDAOImpl;
import lk.ijse.webpossystembackend.dao.custom.impl.OrderDAOImpl;
import lk.ijse.webpossystembackend.dao.custom.impl.OrderDetailsDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,ITEM,ORDER,ORDER_DETAILS
    }

    public SuperDAO getDAO(DAOTypes daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }
}
