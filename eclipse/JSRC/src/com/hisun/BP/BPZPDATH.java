package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPDATH {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_TATH_CNT = 0;
    int WS_GRPL_CNT = 0;
    int WS_I = 0;
    char WS_STSW_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRTLT BPRTLT = new BPRTLT();
    BPRTXIF BPRTXIF = new BPRTXIF();
    BPRTATH BPRTATH = new BPRTATH();
    BPRGRPL BPRGRPL = new BPRGRPL();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    BPCRTXIF BPCRTXIF = new BPCRTXIF();
    BPCRTATH BPCRTATH = new BPCRTATH();
    BPCRGRPL BPCRGRPL = new BPCRGRPL();
    SCCGWA SCCGWA;
    BPCPDATH BPCPDATH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCPDATH BPCPDATH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPDATH = BPCPDATH;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPDATH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        BPRTLT.KEY.TLR = BPCPDATH.TLR_NO;
        BPCRTLTM.INFO.FUNC = 'Q';
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRBANK.TLR_COND);
        if (BPCRBANK.TLR_COND == '0' 
            || BPCRBANK.TLR_COND == '1') {
            B010_DISPOST_GRPL();
            if (pgmRtn) return;
        }
        if (BPCRBANK.TLR_COND == '0' 
            || BPCRBANK.TLR_COND == '2') {
            B020_DISPOST_TATH();
            if (pgmRtn) return;
        }
    }
    public void B010_DISPOST_GRPL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRPL);
        IBS.init(SCCGWA, BPCRGRPL);
        BPRGRPL.KEY.ASS_ID = BPRTLT.KEY.TLR;
        BPRGRPL.KEY.ASS_TYP = 'T';
        BPRGRPL.KEY.ATH_TYP = '0';
        BPCRGRPL.INFO.FUNC = 'Q';
        BPCRGRPL.INFO.POINTER = BPRGRPL;
        BPCRGRPL.INFO.LEN = 52;
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
        if (BPCRGRPL.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, BPRGRPL.ROLE_CNT);
            WS_GRPL_CNT = 0;
            for (WS_I = 1; WS_I <= BPRGRPL.ROLE_CNT; WS_I += 1) {
                if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EXP_DT > SCCGWA.COMM_AREA.AC_DATE 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '0' 
                    || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '2' 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '1' 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT < SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM < SCCGWA.COMM_AREA.TR_TIME)) 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT > SCCGWA.COMM_AREA.AC_DATE) 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '3' 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                    || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT < SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM < SCCGWA.COMM_AREA.TR_TIME)))) {
                    WS_GRPL_CNT = WS_GRPL_CNT + 1;
                    BPCPDATH.ROLE_INFO.ROLE_DATA[WS_GRPL_CNT-1].ROLE_CD = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD;
                }
            }
        }
    }
    public void B020_DISPOST_TATH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTATH);
        IBS.init(SCCGWA, BPCRTATH);
        BPRTATH.KEY.ASS_ID = BPRTLT.KEY.TLR;
        BPRTATH.KEY.ASS_TYP = 'T';
        BPRTATH.KEY.ATH_TYP = '0';
        BPCRTATH.INFO.FUNC = 'Q';
        BPCRTATH.INFO.POINTER = BPRTATH;
        BPCRTATH.INFO.LEN = 52;
        S000_CALL_BPZRTATH();
        if (pgmRtn) return;
        if (BPCRTATH.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, BPRTATH.TXIF_CNT);
            WS_TATH_CNT = 0;
            for (WS_I = 1; WS_I <= BPRTATH.TXIF_CNT; WS_I += 1) {
                if (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EXP_DT > SCCGWA.COMM_AREA.AC_DATE 
                    && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '0' 
                    || BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '2' 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '1' 
                    && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT < SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_TM < SCCGWA.COMM_AREA.TR_TIME)) 
                    && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT > SCCGWA.COMM_AREA.AC_DATE) 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '3' 
                    && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                    || BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT < SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_TM < SCCGWA.COMM_AREA.TR_TIME)))) {
                    IBS.init(SCCGWA, BPRTXIF);
                    IBS.init(SCCGWA, BPCRTXIF);
                    WS_STSW_FLG = ' ';
                    BPRTXIF.KEY.IN_FLG = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG;
                    BPRTXIF.KEY.SYS_MMO = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO;
                    BPRTXIF.KEY.TX_CD = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD;
                    BPCRTXIF.INFO.FUNC = 'Q';
                    BPCRTXIF.INFO.POINTER = BPRTXIF;
                    BPCRTXIF.INFO.LEN = 106;
                    S000_CALL_BPZRTXIF();
                    if (pgmRtn) return;
                    if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
                    JIBS_tmp_int = BPRTXIF.STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
                    if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
                    JIBS_tmp_int = BPRTLT.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
                    if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
                    JIBS_tmp_int = BPRTXIF.STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
                    if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
                    JIBS_tmp_int = BPRTLT.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
                    if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
                    JIBS_tmp_int = BPRTXIF.STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
                    if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
                    JIBS_tmp_int = BPRTLT.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
                    if ((BPRTXIF.STSW.substring(0, 1).equalsIgnoreCase("1") 
                        && BPRTLT.TLR_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("0")) 
                        || (BPRTXIF.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                        && BPRTLT.TLR_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("0")) 
                        || (BPRTXIF.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                        && BPRTLT.TLR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("0"))) {
                        WS_STSW_FLG = 'N';
                    } else {
                        WS_STSW_FLG = 'Y';
                    }
                    if (BPRTLT.TMP_TX_LVL >= BPRTXIF.TX_LVL 
                        && WS_STSW_FLG == 'Y') {
                        WS_TATH_CNT = WS_TATH_CNT + 1;
                        BPCPDATH.TATH_INFO.TATH_DATA[WS_TATH_CNT-1].IN_FLG = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG;
                        BPCPDATH.TATH_INFO.TATH_DATA[WS_TATH_CNT-1].SYS_MMO = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO;
                        BPCPDATH.TATH_INFO.TATH_DATA[WS_TATH_CNT-1].TX_CD = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD;
                    }
                }
            }
        }
    }
    public void S000_CALL_BPZRTLTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-TLR-MAINTAIN   ", BPCRTLTM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPDATH.RC);
            pgmRtn = true;
            return;
        }
    }
    public void S000_CALL_BPZRTATH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TATH       ", BPCRTATH);
        if (BPCRTATH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPDATH.RC);
            pgmRtn = true;
            return;
        }
    }
    public void S000_CALL_BPZRGRPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GRPL       ", BPCRGRPL);
        if (BPCRGRPL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRGRPL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPDATH.RC);
            pgmRtn = true;
            return;
        }
    }
    public void S000_CALL_BPZRTXIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TXIF       ", BPCRTXIF);
        if (BPCRTXIF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPDATH.RC);
            pgmRtn = true;
            return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPDATH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPDATH = ");
            CEP.TRC(SCCGWA, BPCPDATH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
