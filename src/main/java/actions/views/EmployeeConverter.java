package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Employee;

public class EmployeeConverter {

    public static Employee toModels(EmployeeView ev) {
        return new Employee(
                ev.getId(),
                ev.getCode(),
                ev.getName(),
                ev.getPassword(),
                ev.getAdminFlag() == null
                        ? null
                        : ev.getAdminFlag() == AttributeConst.ROLE_MANAGER.getIntegerValue()
                            ? JpaConst.ROLE_MANAGER
                            : ev.getAdminFlag() == AttributeConst.ROLE_CHIEF.getIntegerValue()
                                ? JpaConst.ROLE_CHIEF
                                : JpaConst.ROLE_GENERAL,
                ev.getCreatedAt(),
                ev.getUpdatedAt(),
                ev.getDeleteFlag() == null
                        ? null
                        : ev.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                ? JpaConst.EMP_DEL_TRUE
                                : JpaConst.EMP_DEL_FALSE);
    }

    public static EmployeeView toView(Employee e) {
        if(e == null) {
            return null;
        }

        return new EmployeeView(
                e.getId(),
                e.getCode(),
                e.getName(),
                e.getPassword(),
                e.getAdminFlag() == null
                        ? null
                        : e.getAdminFlag() == JpaConst.ROLE_MANAGER
                                ? AttributeConst.ROLE_MANAGER.getIntegerValue()
                                : e.getAdminFlag() == JpaConst.ROLE_CHIEF
                                    ? AttributeConst.ROLE_CHIEF.getIntegerValue()
                                    : AttributeConst.ROLE_GENERAL.getIntegerValue(),
                e.getCreatedAt(),
                e.getUpdatedAt(),
                e.getDeleteFlag() == null
                        ? null
                        : e.getDeleteFlag() == JpaConst.EMP_DEL_TRUE
                                ? AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                : AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
    }

    public static List<EmployeeView> toViewList(List<Employee> list){
        List<EmployeeView> evs = new ArrayList<>();

        for(Employee e : list) {
            evs.add(toView(e));
        }
        return evs;
    }

    public static void copyViewToModel(Employee e,EmployeeView ev) {
        e.setId(ev.getId());
        e.setCode(ev.getCode());
        e.setName(ev.getName());
        e.setPassword(ev.getPassword());
        e.setAdminFlag(ev.getAdminFlag());
        e.setCreatedAt(ev.getCreatedAt());
        e.setUpdatedAt(ev.getUpdatedAt());
        e.setDeleteFlag(ev.getDeleteFlag());
    }

}
