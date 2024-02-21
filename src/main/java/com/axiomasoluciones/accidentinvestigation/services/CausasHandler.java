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
        Case8 caseInstance8 = new Case8();
        Case9 caseInstance9 = new Case9();
        Case10 caseInstance10 = new Case10();
        Case11 caseInstance11 = new Case11();

        String case1 = caseInstance1.case1(event);
        String case2 = caseInstance2.case2(event);
        String case3 = caseInstance3.case3(event);
        String case4 = caseInstance4.case4(event);
        String case5 = caseInstance5.case5(event);
        String case6 = caseInstance6.case6(event);
        String case7 = caseInstance7.case7(event);
        String case8 = caseInstance8.case8(event);
        String case9 = caseInstance9.case9(event);
        String case10 = caseInstance10.case10(event);
        String case11 = caseInstance11.case11(event);
       // String case12 = caseInstance12.case12(event);


        if (!case1.equals("caso1")) {return case1;}
        if (!case2.equals("caso2")) {return case2;}
        if (!case3.equals("caso3")) {return case3;}
        if (!case4.equals("caso4")) {return case4;}
        if (!case5.equals("caso5")) {return case5;}
        if (!case6.equals("caso6")) {return case6;}
        if (!case7.equals("caso7")) {return case7;}
        if (!case8.equals("caso8")) {return case8;}
        if (!case9.equals("caso9")) {return case9;}
        if (!case10.equals("caso10")) {return case10;}
        if (!case11.equals("caso11")) {return case11;}
        //if (!case12.equals("caso12")) {return case12;}




        return "No corresponde a ni un caso evaluado";
    }
}
