package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9960 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 20;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_CI_NM = " ";
    String WS_PHONE = " ";
    String CIOT9960_FILLER5 = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    CIB9960_AWA_9960 CIB9960_AWA_9960;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9960 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9960_AWA_9960>");
        CIB9960_AWA_9960 = (CIB9960_AWA_9960) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_MOVE_DATA_TO_AGENT_AREA();
        if (pgmRtn) return;
        B020_TRANS_DATA_TO_COMMAREA();
        if (pgmRtn) return;
    }
    public void B010_MOVE_DATA_TO_AGENT_AREA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "WRITE DATA");
        CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.ID_TYP);
        CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.ID_NO);
        CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.CI_NM);
        CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.PHONE);
        WS_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        WS_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        WS_CI_NM = CICGAGA_AGENT_AREA.CI_NM;
        WS_PHONE = CICGAGA_AGENT_AREA.PHONE;
        CEP.TRC(SCCGWA, "AAAAA");
        CEP.TRC(SCCGWA, "BBBBB");
    }
    public void B020_TRANS_DATA_TO_COMMAREA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        CEP.TRC(SCCGWA, "DDDDDDDDDDDDDD");
        IBS.init(SCCGWA, CICSAGEN);
        CEP.TRC(SCCGWA, "999999999");
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = "80000000010";
        CICSAGEN.OUT_AC = "546464";
        CEP.TRC(SCCGWA, "1");
        CICSAGEN.ID_TYP = WS_ID_TYP;
        CICSAGEN.ID_NO = WS_ID_NO;
        CEP.TRC(SCCGWA, "2");
        CICSAGEN.AGENT_TP = "03";
        CICSAGEN.CI_NAME = WS_CI_NM;
        CEP.TRC(SCCGWA, "3");
        CICSAGEN.PHONE = WS_PHONE;
        CEP.TRC(SCCGWA, "AGENT TEST");
        CICSAGEN.FUNC = 'A';
        S000_CALL_TEST_CPN();
        if (pgmRtn) return;
    }
    public void S000_CALL_TEST_CPN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL CIZSAGEN");
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
