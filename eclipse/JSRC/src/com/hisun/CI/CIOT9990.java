package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9990 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 20;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String CPN_SCSSCKDT = "SCSSCKDT";
    CIOT9990_WS_RC WS_RC = new CIOT9990_WS_RC();
    int WS_I = 0;
    String WS_CI_NO = " ";
    char WS_ENTY_TYP = ' ';
    String WS_FRM_APP = " ";
    double WS_A = 0;
    int WS_B = 0;
    String WS_C = " ";
    String WS_D = " ";
    short WS_DATA_LEN = 0;
    CIOT9990_WS_DATA[] WS_DATA = new CIOT9990_WS_DATA[200];
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICSFEA CICSFEA = new CICSFEA();
    CICTMSG CICTMSG = new CICTMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    CICSACR CICSACR = new CICSACR();
    CICSGRS CICSGRS = new CICSGRS();
    CICSPLC CICSPLC = new CICSPLC();
    CICMLRG CICMLRG = new CICMLRG();
    DCCSARQC DCCSARQC = new DCCSARQC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9990_AWA_9990 CIB9990_AWA_9990;
    CIRMMSG5 CIRMMSG5;
    public CIOT9990() {
        for (int i=0;i<200;i++) WS_DATA[i] = new CIOT9990_WS_DATA();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9990 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9990_AWA_9990>");
        CIB9990_AWA_9990 = (CIB9990_AWA_9990) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_TRANS_DATA_TO_COMMAREA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_TRANS_DATA_TO_COMMAREA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSARQC);
        DCCSARQC.CARD_NO = "6235553010000158093";
        DCCSARQC.CARD_SEQ = 0;
        DCCSARQC.CARD_ARQC = "AAAAAAAAAAAA";
        DCCSARQC.CARD_ARQC_DATA = "XXXXXXXXXXXXXXXXXX";
        DCCSARQC.ISSUE_DATA = "99999999999";
        DCCSARQC.VERIFY_RLT = "VVVVVVV";
        S000_CALL_DCZSARQC();
        if (pgmRtn) return;
    }
    public void S000_CALL_TEST_CPN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CAL-FLRG", CICMLRG);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMLRG.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_RC);
        CEP.TRC(SCCGWA, CICMLRG.RC);
        if (WS_RC.WS_RC_CODE != 0) {
            CEP.ERR(SCCGWA, WS_RC);
        }
    }
    public void S000_CALL_DCZSARQC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-ARQC-CHECK", DCCSARQC);
    }
    public void B030_OUTPUT_COMMAREA_FMT() throws IOException,SQLException,Exception {
        for (WS_I = 1; CICSGRS.OUT_DATA[WS_I-1].GRPS_NO.trim().length() != 0 
            && WS_I <= 99; WS_I += 1) {
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = "CIX01";
            SCCFMT.DATA_PTR = CICSGRS.OUT_DATA[WS_I-1];
            SCCFMT.DATA_LEN = 9306;
            IBS.FMT(SCCGWA, SCCFMT);
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
