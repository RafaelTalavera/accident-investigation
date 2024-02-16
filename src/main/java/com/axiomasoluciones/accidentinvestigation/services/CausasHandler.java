package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.services.cases.*;
import org.springframework.stereotype.Service;

@Service
public class CausasHandler {

    public String evaluarCausas(Event event) {

        Case1 caseInstance1 = new Case1();
        Case2 caseInstance2 = new Case2();
        Case3 caseInstance3 = new Case3();
        Case4 caseInstance4 = new Case4();
        Case5 caseInstance5 = new Case5();
        Case6 caseInstance6 = new Case6();
        Case7 caseInstance7 = new Case7();


        String case1 = caseInstance1.case1(event);
        String case2 = caseInstance2.case2(event);
        String case3 = caseInstance3.case3(event);
        String case4 = caseInstance4.case4(event);
        String case5 = caseInstance5.case5(event);
        String case6 = caseInstance6.case6(event);
        String case7 = caseInstance7.case7(event);

        if (!case1.equals("caso1")) {return case1;}
        if (!case2.equals("caso2")) {return case2;}
        if (!case3.equals("caso3")) {return case3;}
        if (!case4.equals("caso4")) {return case4;}
        if (!case5.equals("caso5")) {return case5;}
        if (!case6.equals("caso6")) {return case6;}
        if (!case7.equals("caso7")) {return case7;}

        return "No corresponde a ni un caso evaluado";
    }
}
