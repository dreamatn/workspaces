package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPORLB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_ORG_RGL = "BP-R-BRW-ORG-RGL    ";
    String CPN_R_BRW_ORG_RGI = "BP-R-BRW-ORG-RGI    ";
    int K_MAX_CNT = 500;
    int K_MAX_COL = 99;
    int K_MAX_ROW = 10;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_ACO_AC = " ";
    BPZPORLB_WS_PORLB_BRO WS_PORLB_BRO = new BPZPORLB_WS_PORLB_BRO();
    char WS_OUT = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRORGL BPRORGL = new BPRORGL();
    BPRORGI BPRORGI = new BPRORGI();
    BPCRORLB BPCRORLB = new BPCRORLB();
    BPCRORGI BPCRORGI = new BPCRORGI();
    BPCRORGL BPCRORGL = new BPCRORGL();
    CICQACAC CICQACAC = new CICQACAC();
    BPCRORIB BPCRORIB = new BPCRORIB();
    SCCGWA SCCGWA;
    BPCPORLB BPCPORLB;
    public void MP(SCCGWA SCCGWA, BPCPORLB BPCPORLB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPORLB = BPCPORLB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPORLB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPORLB.FUNC);
        CEP.TRC(SCCGWA, BPCPORLB.AC_DT);
        CEP.TRC(SCCGWA, BPCPORLB.MN_JRN);
        CEP.TRC(SCCGWA, BPCPORLB.TX_TOOL);
        CEP.TRC(SCCGWA, BPCPORLB.ORGI_TYP);
        if (BPCPORLB.FUNC == 'R') {
            B010_BROWSE_ORGI_RECORD();
            if (pgmRtn) return;
        }
        if (BPCPORLB.FUNC == 'B') {
            B020_BACKOUT_ORGI_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_ORGI_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRORIB);
        IBS.init(SCCGWA, BPRORGI);
        BPRORGI.INCO_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGI.ORGI_FLG = '0';
        BPCRORIB.INFO.POINTER = BPRORGI;
        BPCRORIB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORIB.RC);
        BPCRORIB.FUNC = 'S';
        S000_CALL_BPZRORIB();
        if (pgmRtn) return;
        B021_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRORIB.RC.RC_CODE == 0 
            && WS_CNT < K_MAX_CNT 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            BPCRORIB.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCRORIB.RC);
            BPCRORIB.FUNC = 'R';
            S000_CALL_BPZRORIB();
            if (pgmRtn) return;
            if (BPCRORIB.RC.RC_CODE == 0) {
                CEP.TRC(SCCGWA, BPRORGI.AC_ORGI_TYP);
                if (BPCPORLB.ORGI_TYP != ' ') {
                    if (BPCPORLB.ORGI_TYP == BPRORGI.ORGI_TYP) {
                        WS_OUT = 'Y';
                    } else {
                        WS_OUT = 'N';
                    }
                } else {
                    WS_OUT = 'Y';
                }
                if (WS_OUT == 'Y') {
                    if (BPCPORLB.TX_TOOL.trim().length() > 0) {
                        if (!BPCPORLB.TX_TOOL.equalsIgnoreCase(BPRORGI.TX_TOOL)) {
                            WS_OUT = 'N';
                        }
                    }
                }
                if (WS_OUT == 'Y') {
                    if (BPCPORLB.AC_DT != 0) {
                        if (BPCPORLB.AC_DT != BPRORGI.KEY.AC_DT) {
                            WS_OUT = 'N';
                        }
                    }
                }
                if (WS_OUT == 'Y') {
                    if (BPCPORLB.MN_JRN != 0) {
                        if (BPCPORLB.MN_JRN != BPRORGI.KEY.MN_JRN) {
                            WS_OUT = 'N';
                        }
                    }
                }
                CEP.TRC(SCCGWA, WS_OUT);
                if (WS_OUT == 'Y') {
                    B022_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        BPCRORIB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORIB.RC);
        BPCRORIB.FUNC = 'E';
        S000_CALL_BPZRORIB();
        if (pgmRtn) return;
    }
    public void B020_BACKOUT_ORGI_RECORD() throws IOException,SQLException,Exception {
        B030_UPDATE_BPTORGI();
        if (pgmRtn) return;
        B040_UPDATE_BPTORGL();
        if (pgmRtn) return;
    }
    public void B021_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 95;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_PORLB_BRO);
        WS_PORLB_BRO.WS_BRO_ORGI_TYP = BPRORGI.ORGI_TYP;
        WS_PORLB_BRO.WS_BRO_AC_DT = BPRORGI.KEY.AC_DT;
        WS_PORLB_BRO.WS_BRO_INCO_DATE = BPRORGI.INCO_DATE;
        WS_PORLB_BRO.WS_BRO_TX_JRN = BPRORGI.KEY.MN_JRN;
        WS_PORLB_BRO.WS_BRO_TX_TOOL = BPRORGI.TX_TOOL;
        WS_PORLB_BRO.WS_BRO_INCO_OLD_BR = BPRORGI.INCO_OLD_BR;
        WS_PORLB_BRO.WS_BRO_INCO_NEW_BR = BPRORGI.INCO_NEW_BR;
        WS_PORLB_BRO.WS_BRO_AC_ORGI_TYP = BPRORGI.AC_ORGI_TYP;
        WS_PORLB_BRO.WS_BRO_ACO_AC = BPRORGI.ACO_AC;
        CEP.TRC(SCCGWA, WS_PORLB_BRO.WS_BRO_ORGI_TYP);
        CEP.TRC(SCCGWA, WS_PORLB_BRO.WS_BRO_AC_DT);
        CEP.TRC(SCCGWA, WS_PORLB_BRO.WS_BRO_INCO_DATE);
        CEP.TRC(SCCGWA, WS_PORLB_BRO.WS_BRO_TX_JRN);
        CEP.TRC(SCCGWA, WS_PORLB_BRO.WS_BRO_TX_TOOL);
        CEP.TRC(SCCGWA, WS_PORLB_BRO.WS_BRO_INCO_OLD_BR);
        CEP.TRC(SCCGWA, WS_PORLB_BRO.WS_BRO_INCO_NEW_BR);
        CEP.TRC(SCCGWA, WS_PORLB_BRO.WS_BRO_AC_ORGI_TYP);
        CEP.TRC(SCCGWA, WS_PORLB_BRO.WS_BRO_ACO_AC);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_PORLB_BRO);
        SCCMPAG.DATA_LEN = 95;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_BPTORGI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRORGI);
        IBS.init(SCCGWA, BPRORGI);
        BPRORGI.KEY.AC_DT = BPCPORLB.AC_DT;
        BPRORGI.KEY.MN_JRN = BPCPORLB.MN_JRN;
        BPCRORGI.INFO.FUNC = 'R';
        S000_CALL_BPZRORGI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRORGI);
        BPRORGI.ORGI_FLG = '9';
        BPRORGI.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGI.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRORGI.INFO.FUNC = 'U';
        S000_CALL_BPZRORGI();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_BPTORGL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRORLB);
        IBS.init(SCCGWA, BPRORGL);
        BPRORGL.KEY.TX_DATE = BPCPORLB.AC_DT;
        BPRORGL.KEY.TX_JRN = BPCPORLB.MN_JRN;
        BPCRORLB.INFO.POINTER = BPRORGL;
        BPCRORLB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORLB.RC);
        BPCRORLB.FUNC = 'S';
        S000_CALL_BPZRORLB();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRORLB.RC.RC_CODE == 0 
            && WS_CNT < K_MAX_CNT 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            BPCRORLB.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCRORLB.RC);
            BPCRORLB.FUNC = 'R';
            S000_CALL_BPZRORLB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRORLB.RC.RC_CODE);
            if (BPCRORLB.RC.RC_CODE == 0) {
                IBS.init(SCCGWA, BPCRORGL);
                CEP.TRC(SCCGWA, BPRORGL.KEY.TX_DATE);
                CEP.TRC(SCCGWA, BPRORGL.KEY.TX_JRN);
                CEP.TRC(SCCGWA, BPRORGL.KEY.TX_SEQ);
                BPCRORGL.INFO.FUNC = 'R';
                S000_CALL_BPZRORGL();
                if (pgmRtn) return;
                BPRORGL.TX_FLG = 'U';
                BPRORGL.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRORGL.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCRORGL.INFO.FUNC = 'U';
                S000_CALL_BPZRORGL();
                if (pgmRtn) return;
            }
        }
        BPCRORLB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORLB.RC);
        BPCRORLB.FUNC = 'E';
        S000_CALL_BPZRORLB();
        if (pgmRtn) return;
    }
    public void B020_GET_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************************");
        CEP.TRC(SCCGWA, BPRORGI.TX_TOOL);
        CEP.TRC(SCCGWA, BPRORGI.SEQ);
        CEP.TRC(SCCGWA, BPRORGI.CCY);
        CEP.TRC(SCCGWA, BPRORGI.CCY_TYPE);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = BPRORGI.TX_TOOL;
        CICQACAC.DATA.AGR_SEQ = BPRORGI.SEQ;
        CICQACAC.DATA.CCY_ACAC = BPRORGI.CCY;
        CICQACAC.DATA.CR_FLG = BPRORGI.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR2);
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
    }
    public void S000_CALL_BPZRORLB() throws IOException,SQLException,Exception {
        BPCRORLB.INFO.LEN = 245;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_ORG_RGL, BPCRORLB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRORLB.RC);
        if (BPCRORLB.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRORLB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRORIB() throws IOException,SQLException,Exception {
        BPCRORIB.INFO.LEN = 190;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_ORG_RGI, BPCRORIB);
        CEP.TRC(SCCGWA, BPCRORIB.RC.RC_CODE);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRORIB.RC);
        if (BPCRORIB.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRORIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRORGI() throws IOException,SQLException,Exception {
        BPCRORGI.INFO.POINTER = BPRORGI;
        BPCRORGI.INFO.LEN = 190;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGI", BPCRORGI);
        if (BPCRORGI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRORGI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRORGL() throws IOException,SQLException,Exception {
        BPCRORGL.INFO.POINTER = BPRORGL;
        BPCRORGL.INFO.LEN = 245;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGL", BPCRORGL);
        if (BPCRORGL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRORGL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
