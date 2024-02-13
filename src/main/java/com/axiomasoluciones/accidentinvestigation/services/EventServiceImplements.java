package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IEventDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Event;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplements implements IEventService {

    @Autowired
    private IEventDao eventDao;

    @Autowired
    private AuthenticationService authenticationService;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    @Override
    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return (List<Event>) eventDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Event> findById(String id) {
        return eventDao.findById(id);
    }

    @Override
    @Transactional
    public Event save(Event event) {
        return eventDao.save(event);
    }

    @Override
    @Transactional
    public String extractUserEmailFromToken(String token) {
            try {
                // Remover la palabra "Bearer " del inicio del token
                String jwtToken = token.replace("Bearer ", "");
                Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
                return claims.get("mail", String.class);
            } catch (Exception e) {
                throw new RuntimeException("Error al extraer el correo electrónico del token", e);
            }
    }

    @Override
    public List<Event> findByUserId(String userId) {
        return eventDao.findEventByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Event existingEvent = eventDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        eventDao.deleteById(id);
    }

    @Override
    public Event editEvent(String id, Event editedEvent) {
        Event existEvent = eventDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        existEvent.setDateEvent(editedEvent.getDateEvent());
        existEvent.setSeverity(editedEvent.getSeverity());


        return eventDao.save(existEvent);
    }

    @Override
    public void delete(Event event) {
        eventDao.delete(event);
    }
}




  /*  @Override
    @Transactional(readOnly = true)
    public String getAntiguedadMessageById(String id) {
        Event event = eventDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        return getAntiguedadMessage(event);
    }

    @Override
    @Transactional(readOnly = true)
    public String getCase1ById(String id) {
        Event event = eventDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        return getCase1Message(event);
    }

    private String getCase1Message(Event event) {
        if (event.getWorker() != null && event.getWorker().getEntry() != null) {
            LocalDate entryDate = event.getWorker().getEntry();
            LocalDate currentDate = event.getDateEvent();

            long monthsDifferenceEntry = ChronoUnit.MONTHS.between(entryDate, currentDate);


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
            Case12 caseInstance12 = new Case12();
            Case13 caseInstance13 = new Case13();
            Case14 caseInstance14 = new Case14();
            Case15 caseInstance15 = new Case15();
            Case16 caseInstance16 = new Case16();
            Case17 caseInstance17 = new Case17();
            Case18 caseInstance18 = new Case18();
            Case19 caseInstance19 = new Case19();
            Case20 caseInstance20 = new Case20();
            Case21 caseInstance21 = new Case21();
            Case22 caseInstance22 = new Case22();
            Case23 caseInstance23 = new Case23();
            Case24 caseInstance24 = new Case24();
            Case25 caseInstance25 = new Case25();
            Case26 caseInstance26 = new Case26();
            Case27 caseInstance27 = new Case27();
            Case28 caseInstance28 = new Case28();
            Case29 caseInstance29 = new Case29();
            Case30 caseInstance30 = new Case30();
            Case31 caseInstance31 = new Case31();
            Case32 caseInstance32 = new Case32();
            Case33 caseInstance33 = new Case33();
            Case34 caseInstance34 = new Case34();
            Case35 caseInstance35 = new Case35();
            Case36 caseInstance36 = new Case36();
            Case37 caseInstance37 = new Case37();
            Case38 caseInstance38 = new Case38();
            Case39 caseInstance39 = new Case39();
            Case40 caseInstance40 = new Case40();
            Case41 caseInstance41 = new Case41();
            Case42 caseInstance42 = new Case42();
            Case43 caseInstance43 = new Case43();
            Case44 caseInstance44 = new Case44();
            Case45 caseInstance45 = new Case45();
            Case46 caseInstance46 = new Case46();
            Case47 caseInstance47 = new Case47();
            Case48 caseInstance48 = new Case48();
            Case49 caseInstance49 = new Case49();
            Case50 caseInstance50 = new Case50();
            Case51 caseInstance51 = new Case51();
            Case52 caseInstance52 = new Case52();
            Case53 caseInstance53 = new Case53();
            Case54 caseInstance54 = new Case54();
            Case55 caseInstance55 = new Case55();
            Case56 caseInstance56 = new Case56();
            Case57 caseInstance57 = new Case57();
            Case58 caseInstance58 = new Case58();
            Case59 caseInstance59 = new Case59();
            Case60 caseInstance60 = new Case60();
            Case61 caseInstance61 = new Case61();
            Case62 caseInstance62 = new Case62();
            Case63 caseInstance63 = new Case63();
            Case64 caseInstance64 = new Case64();
            Case65 caseInstance65 = new Case65();
            Case66 caseInstance66 = new Case66();
            Case67 caseInstance67 = new Case67();
            Case68 caseInstance68 = new Case68();
            Case69 caseInstance69 = new Case69();
            Case70 caseInstance70 = new Case70();
            Case71 caseInstance71 = new Case71();
            Case72 caseInstance72 = new Case72();
            Case73 caseInstance73 = new Case73();
            Case74 caseInstance74 = new Case74();
            Case75 caseInstance75 = new Case75();
            Case76 caseInstance76 = new Case76();
            Case77 caseInstance77 = new Case77();
            Case78 caseInstance78 = new Case78();
            Case79 caseInstance79 = new Case79();
            Case80 caseInstance80 = new Case80();








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
            String case12 = caseInstance12.case12(event);
            String case13= caseInstance13.case13(event);
            String case14= caseInstance14.case14(event);
            String case15= caseInstance15.case15(event);
            String case16= caseInstance16.case16(event);
            String case17= caseInstance17.case17(event);
            String case18= caseInstance18.case18(event);
            String case19= caseInstance19.case19(event);
            String case20= caseInstance20.case20(event);
            String case21= caseInstance21.case21(event);
            String case22= caseInstance22.case22(event);
            String case23 = caseInstance23.case23(event);
            String case24 = caseInstance24.case24(event);
            String case25 = caseInstance25.case25(event);
            String case26 = caseInstance26.case26(event);
            String case27 = caseInstance27.case27(event);
            String case28 = caseInstance28.case28(event);
            String case29 = caseInstance29.case29(event);
            String case30 = caseInstance30.case30(event);
            String case31 = caseInstance31.case31(event);
            String case32 = caseInstance32.case32(event);
            String case33 = caseInstance33.case33(event);
            String case34 = caseInstance34.case34(event);
            String case35 = caseInstance35.case35(event);
            String case36 = caseInstance36.case36(event);
            String case37 = caseInstance37.case37(event);
            String case38 = caseInstance38.case38(event);
            String case39 = caseInstance39.case39(event);
            String case40 = caseInstance40.case40(event);
            String case41 = caseInstance41.case41(event);
            String case42 = caseInstance42.case42(event);
            String case43 = caseInstance43.case43(event);
            String case44 = caseInstance44.case44(event);
            String case45 = caseInstance45.case45(event);
            String case46 = caseInstance46.case46(event);
            String case47 = caseInstance47.case47(event);
            String case48 = caseInstance48.case48(event);
            String case49 = caseInstance49.case49(event);
            String case50 = caseInstance50.case50(event);
            String case51 = caseInstance51.case51(event);
            String case52 = caseInstance52.case52(event);
            String case53= caseInstance53.case53(event);
            String case54= caseInstance54.case54(event);
            String case55= caseInstance55.case55(event);
            String case56= caseInstance56.case56(event);
            String case57= caseInstance57.case57(event);
            String case58= caseInstance58.case58(event);
            String case59= caseInstance59.case59(event);
            String case60= caseInstance60.case60(event);
            String case61= caseInstance61.case61(event);
            String case62= caseInstance62.case62(event);
            String case63 = caseInstance63.case63(event);
            String case64 = caseInstance64.case64(event);
            String case65 = caseInstance65.case65(event);
            String case66 = caseInstance66.case66(event);
            String case67 = caseInstance67.case67(event);
            String case68 = caseInstance68.case68(event);
            String case69 = caseInstance69.case69(event);
            String case70 = caseInstance70.case70(event);
            String case71 = caseInstance71.case71(event);
            String case72 = caseInstance72.case72(event);
            String case73 = caseInstance73.case73(event);
            String case74 = caseInstance74.case74(event);
            String case75 = caseInstance75.case75(event);
            String case76 = caseInstance76.case76(event);
            String case77 = caseInstance77.case77(event);
            String case78 = caseInstance78.case78(event);
            String case79 = caseInstance79.case79(event);
            String case80 = caseInstance80.case80(event);





            if (!case1.equals("case1")) {return case1;}
            if (!case2.equals("case2")) {return case2;}
            if (!case3.equals("case3")) {return case3;}
            if (!case4.equals("case4")) {return case4;}
            if (!case5.equals("case5")) {return case5;}
            if (!case6.equals("case6")) {return case6;}
            if (!case7.equals("case7")) {return case7;}
            if (!case8.equals("case8")) {return case8;}
            if (!case9.equals("case9")) {return case9;}
            if (!case10.equals("case10")) {return case10;}
            if (!case11.equals("case11")) {return case11;}
            if (!case12.equals("case12")) {return case12;}
            if (!case13.equals("case13")) {return case13;}
            if (!case14.equals("case14")) {return case14;}
            if (!case15.equals("case15")) {return case15;}
            if (!case16.equals("case16")) {return case16;}
            if (!case17.equals("case17")) {return case17;}
            if (!case18.equals("case18")) {return case18;}
            if (!case19.equals("case19")) {return case19;}
            if (!case20.equals("case20")) {return case20;}
            if (!case21.equals("case21")) {return case21;}
            if (!case22.equals("case22")) {return case22;}
            if (!case23.equals("case23")) {return case23;}
            if (!case24.equals("case24")) {return case24;}
            if (!case25.equals("case25")) {return case25;}
            if (!case26.equals("case26")) {return case26;}
            if (!case27.equals("case27")) {return case27;}
            if (!case28.equals("case28")) {return case28;}
            if (!case29.equals("case29")) {return case29;}
            if (!case30.equals("case30")) {return case30;}
            if (!case31.equals("case31")) {return case31;}
            if (!case32.equals("case32")) {return case32;}
            if (!case33.equals("case33")) {return case33;}
            if (!case34.equals("case34")) {return case34;}
            if (!case35.equals("case35")) {return case35;}
            if (!case36.equals("case36")) {return case36;}
            if (!case37.equals("case37")) {return case37;}
            if (!case38.equals("case38")) {return case38;}
            if (!case39.equals("case39")) {return case39;}
            if (!case40.equals("case40")) {return case40;}
            if (!case41.equals("case41")) {return case41;}
            if (!case42.equals("case42")) {return case42;}
            if (!case43.equals("case43")) {return case43;}
            if (!case44.equals("case44")) {return case44;}
            if (!case45.equals("case45")) {return case45;}
            if (!case46.equals("case46")) {return case46;}
            if (!case47.equals("case47")) {return case47;}
            if (!case48.equals("case48")) {return case48;}
            if (!case49.equals("case49")) {return case49;}
            if (!case50.equals("case50")) {return case50;}
            if (!case51.equals("case51")) {return case51;}
            if (!case52.equals("case52")) {return case52;}
            if (!case53.equals("case53")) {return case53;}
            if (!case54.equals("case54")) {return case54;}
            if (!case55.equals("case55")) {return case55;}
            if (!case56.equals("case56")) {return case56;}
            if (!case57.equals("case57")) {return case57;}
            if (!case58.equals("case58")) {return case58;}
            if (!case59.equals("case59")) {return case59;}
            if (!case60.equals("case60")) {return case60;}
            if (!case61.equals("case61")) {return case61;}
            if (!case62.equals("case62")) {return case62;}
            if (!case63.equals("case63")) {return case63;}
            if (!case64.equals("case64")) {return case64;}
            if (!case65.equals("case65")) {return case65;}
            if (!case66.equals("case66")) {return case66;}
            if (!case67.equals("case67")) {return case67;}
            if (!case68.equals("case68")) {return case68;}
            if (!case69.equals("case69")) {return case69;}
            if (!case70.equals("case70")) {return case70;}
            if (!case71.equals("case71")) {return case71;}
            if (!case72.equals("case72")) {return case72;}
            if (!case73.equals("case73")) {return case73;}
            if (!case74.equals("case74")) {return case74;}
            if (!case75.equals("case75")) {return case75;}
            if (!case76.equals("case76")) {return case76;}
            if (!case77.equals("case77")) {return case77;}
            if (!case78.equals("case78")) {return case78;}
            if (!case79.equals("case79")) {return case79;}
            if (!case80.equals("case80")) {return case80;}







            return "No corresponde a ni un caso evaluado";
        } else {
            return "No ingresó antigüedad para este worker";
        }
    }






   private String getAntiguedadMessage(Event event) {
        if (event.getWorker() != null && event.getWorker().getEntry() != null) {
            LocalDate entryDate = event.getWorker().getEntry();
            LocalDate currentDate = event.getDateEvent();

            long monthsDifference = entryDate.until(currentDate).toTotalMonths();

            if (monthsDifference < 3) {
                return "Antigüedad menor a tres meses";
            } else {
                return "Antigüedad mayor a tres meses";
            }
        } else {
            return "No ingresó antigüedad para este worker";
        }
    }

    public String getAntiguedadMessageForEvent(Event event) {
        return getAntiguedadMessage(event);
    }

}






   */