package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.EmployeeView;
import constants.MessageConst;
import services.EmployeeService;


public class EmployeeValidator {

    public static List<String> validate(
                EmployeeService service, EmployeeView ev,Boolean codeDuplicateCheckFlag,Boolean passwordCheckFlag){
        List<String> errors = new ArrayList<String>();

        String codeError = validateCode(service,ev.getCode(),codeDuplicateCheckFlag);
        if(!codeError.equals("")) {
            errors.add(codeError);
        }

        String nameError = validateName(ev.getName());
        if(!nameError.equals("")) {
            errors.add(nameError);
        }

        String passError = validatePassword(ev.getPassword(),passwordCheckFlag);
            if(!passError.equals("")) {
                errors.add(passError);
            }
            return errors;
    }

    private static String validateCode(EmployeeService service,String code,Boolean codeDuplicateCheckFlag) {
        if(code == null || code.equals("")) {
            return MessageConst.E_NOEMP_CODE.getMessage();
        }

        if(codeDuplicateCheckFlag) {
            long employeeConst = isDuplicateEmployee(service,code);

            if(employeeConst > 0) {
                return MessageConst.E_EMP_CODE_EXIST.getMessage();
            }
        }

        return "";
    }

    private static long idDuplicateEmployee(EmployeeService service,String code) {
        long employeeConst = service.countByCode(code);
        return employeeConst;
    }

    private static validateName(String name) {
        if(name == null || name.equals("")) {
            return MessageConst.E_NONAME.getMessage();
        }
        return "";
    }

    private static String validatePassword(String password,Boolean passwordCheckFlag) {
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return MessageConst.E_NONAME.getMessage();
        }

        return "";
    }

}
