package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPSWE {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    boolean pgmRtn = false;
    String CPN_DCZUCINF = "DC-U-CARD-INF";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_HIS_REMARK = "CARD PASSWORD UNLOCK";
    String K_OUTPUT_FMT = "DC114";
    String WS_ERR_MSG = " ";
    char WS_ADSC_TYPE = ' ';
    DCZSPSWE_WS_OUTPUT WS_OUTPUT = new DCZSPSWE_WS_OUTPUT();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCHCGET DCCHCGET = new DCCHCGET();
    DCCHCGET DCCNCGET = new DCCHCGET();
    CICQACRI CICQACRI = new CICQACRI();
    DCCUCDLP DCCUCDLP = new DCCUCDLP();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSPSWE DCCSPSWE;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DCCSPSWE DCCSPSWE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPSWE = DCCSPSWE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPSWE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_CHECK_PASSWD();
        if (pgmRtn) return;
        B020_RELEASE_PASSWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B055_ADD_AGENT_INFO();
            if (pgmRtn) return;
        }
        B025_HISTORY_PROCESS();
        if (pgmRtn) return;
        B030_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSPSWE.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (!DCRCDDAT.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_PIN_STS;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_PIN_STS, DCCSPSWE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_CANCEL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_ACTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            IBS.init(SCCGWA, DCCUCDLP);
            DCCUCDLP.CARD_NO = DCCSPSWE.CARD_NO;
            DCCUCDLP.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_DCZUCDLP();
            if (pgmRtn) return;
        }
    }
    public void B015_CHECK_PASSWD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUPSWM);
        DCCUPSWM.FUNC = 'C';
        DCCUPSWM.PSW_FLG = DCRCDDAT.PSW_TYP;
        DCCUPSWM.OLD_AGR_NO = DCCSPSWE.CARD_NO;
        DCCUPSWM.CARD_PSW_OLD = DCCSPSWE.PASS_WORD;
        S000_CALL_DCZUPSWM();
        if (pgmRtn) return;
        if (DCRCDDAT.PSW_TYP == 'O') {
            if (!DCCUPSWM.O_OLD_PSW.equalsIgnoreCase(DCRCDDAT.TRAN_PIN_DAT)) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PSWD_NOT_MATCH);
            }
        } else {
            if (!DCCUPSWM.O_NEW_PSW.equalsIgnoreCase(DCRCDDAT.TRAN_PIN_DAT)) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PSWD_NOT_MATCH);
            }
        }
    }
    public void B020_RELEASE_PASSWD() throws IOException,SQLException,Exception {
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 9 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(9 + 1 - 1);
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 3 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(3 + 1 - 1);
        DCRCDDAT.PIN_ERR_CNT = 0;
        DCRCDDAT.PIN_LCK_DT = 0;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B025_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, DCCHCGET);
        IBS.init(SCCGWA, DCCNCGET);
        DCCNCGET.CARD_NO = DCCSPSWE.CARD_NO;
        DCCNCGET.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCNCGET.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_CD = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.AC = DCCSPSWE.CARD_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSPSWE.CARD_NO;
        BPCPNHIS.INFO.AC = DCCSPSWE.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = "DCCNCGET";
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 142;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNCGET;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CARD_NO = DCCSPSWE.CARD_NO;
        WS_OUTPUT.WS_HOLDER_IDTYP = DCCSPSWE.ID_TYP;
        WS_OUTPUT.WS_HOLDER_IDNO = DCCSPSWE.ID_NO;
        WS_OUTPUT.WS_HOLDER_NAME = DCCSPSWE.CI_NM;
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 364;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B055_ADD_AGENT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        CICSAGEN.OUT_AC = DCCSPSWE.CARD_NO;
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCINF, DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSPSWE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUPSWM, DCCUPSWM);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSPSWE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DCZUCDLP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHECK-BR", DCCUCDLP);
        if (DCCUCDLP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCDLP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_SEND_MASSAGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCWRMSG);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSPSWE.CARD_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
    }
    public void S000_CALL_SCCWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSPSWE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSPSWE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
        if (DCCSPSWE.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCSPSWE=");
            CEP.TRC(SCCGWA, DCCSPSWE);
        }
    } //FROM #ENDIF
        if (SCCGWA.COMM_AREA.EXCP_FLG != 'Y' 
            && SCCCALL.RET_FLG != 'Y') {
            if ((SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'N' 
                || SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'W')) {
                S000_SEND_MASSAGE_PROC();
                if (pgmRtn) return;
            }
        }
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
