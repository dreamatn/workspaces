package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCGEG {
    int JIBS_tmp_int;
    DBParm DDTCLDD_RD;
    DBParm DDTCCY_RD;
    String CPN_DD_INT_DREG = "DD-SRC-DDZIDREG";
    String K_OTHER_PAYABLES_ITM = "24508";
    String K_NONBUSINESS_INCOM_ITM = "51604";
    String K_OTHER_PAY_CODE = "DDEG3";
    String K_NOBUSINE_INCOM_CODE = "DDEG1";
    String K_ITM_NO1 = " ";
    String K_ITM_NO2 = " ";
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCIDREG DDCIDREG = new DDCIDREG();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQIA AICPQIA = new AICPQIA();
    DDRCLDD DDRCLDD = new DDRCLDD();
    DDRCCY DDRCCY = new DDRCCY();
    AICPUITM AICPUITM = new AICPUITM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    AIRPAI7 AIRPAI7 = new AIRPAI7();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    SCCGWA SCCGWA;
    DDCSCGEG DDCSCGEG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCSCGEG DDCSCGEG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCGEG = DDCSCGEG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSCGEG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        B011_GET_AC_INFO_PROC();
        B020_GET_DREG_INF_PROC();
        B030_GEN_VCH_PROC();
        B040_UPD_DREG_INFO_PROC();
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCGEG.AC);
        CEP.TRC(SCCGWA, DDCSCGEG.CCY);
        CEP.TRC(SCCGWA, DDCSCGEG.CCY_TYPE);
        if (DDCSCGEG.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCGEG.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B011_GET_AC_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSCGEG.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSCGEG.CCY;
        CICQACAC.DATA.CR_FLG = DDCSCGEG.CCY_TYPE;
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
    }
    public void B020_GET_DREG_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.DATA.KEY.AC = DDCSCGEG.AC;
        DDCIDREG.DATA.KEY.CCY = DDCSCGEG.CCY;
        DDCIDREG.DATA.KEY.CCY_TYPE = DDCSCGEG.CCY_TYPE;
        DDCIDREG.OPT = 'R';
        S000_CALL_DDZIDREG();
    }
    public void B030_CALL_AI_UNT_PROC() throws IOException,SQLException,Exception {
        B031_CALL_AI_DR_UNT();
        B033_CALL_AI_CR_UNT();
    }
    public void B030_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        if (DDCSCGEG.BAL > 0) {
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = "CAAC";
            BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
            BPCPOEWA.DATA.EVENT_CODE = "HN";
            BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BR;
            BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BR;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCSCGEG.CCY;
            BPCPOEWA.DATA.AMT_INFO[20-1].AMT = DDCSCGEG.BAL;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            BPCPOEWA.DATA.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            BPCPOEWA.DATA.REF_NO = " ";
            S000_CALL_BPZPOEWA();
        }
    }
    public void B031_CALL_AI_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.CD.AC_TYP = "3";
        AICPQIA.CD.BUSI_KND = K_OTHER_PAY_CODE;
        AICPQIA.BR = DDCSCGEG.BR;
        AICPQIA.CCY = DDCSCGEG.CCY;
        AICPQIA.SIGN = 'C';
        S000_CALL_AIZPQIA();
        if (AICPQIA.AC == null) AICPQIA.AC = "";
        JIBS_tmp_int = AICPQIA.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICPQIA.AC += " ";
        if (K_ITM_NO1 == null) K_ITM_NO1 = "";
        JIBS_tmp_int = K_ITM_NO1.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) K_ITM_NO1 += " ";
        K_ITM_NO1 = AICPQIA.AC.substring(13 - 1, 13 + 8 - 1) + K_ITM_NO1.substring(8);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
        AICUUPIA.DATA.CCY = DDCSCGEG.CCY;
        AICUUPIA.DATA.AMT = DDCSCGEG.BAL;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
    }
    public void B033_CALL_AI_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, AIRPAI7);
        AIRPAI7.KEY.TYP = "PAI07";
        AIRPAI7.KEY.REDEFINES6.AC_TYP = "3";
        AIRPAI7.KEY.REDEFINES6.GL_BOOK = "BK001";
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.BUSI_KND = "DDEG1";
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        BPCPRMR.DAT_PTR = AIRPAI7;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C);
        if (AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C != 0) {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = "3";
            AICPQIA.CD.BUSI_KND = K_NOBUSINE_INCOM_CODE;
            AICPQIA.BR = DDCSCGEG.BR;
            AICPQIA.CCY = DDCSCGEG.CCY;
            AICPQIA.SIGN = 'C';
            S000_CALL_AIZPQIA();
            if (AICPQIA.AC == null) AICPQIA.AC = "";
            JIBS_tmp_int = AICPQIA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQIA.AC += " ";
            if (K_ITM_NO2 == null) K_ITM_NO2 = "";
            JIBS_tmp_int = K_ITM_NO2.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) K_ITM_NO2 += " ";
            K_ITM_NO2 = AICPQIA.AC.substring(13 - 1, 13 + 8 - 1) + K_ITM_NO2.substring(8);
            CEP.TRC(SCCGWA, AICPQIA.AC);
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = AICPQIA.AC;
            AICUUPIA.DATA.CCY = DDCSCGEG.CCY;
            AICUUPIA.DATA.AMT = DDCSCGEG.BAL;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            S000_CALL_AIZUUPIA();
        } else {
            IBS.init(SCCGWA, AICPUITM);
            AICPUITM.DATA.EVENT_CODE = "CR";
            AICPUITM.DATA.BR_OLD = DDCSCGEG.BR;
            AICPUITM.DATA.BR_NEW = DDCSCGEG.BR;
            AICPUITM.DATA.CCY = DDCSCGEG.CCY;
            AICPUITM.DATA.ITM_NO = "63010201";
            AICPUITM.DATA.AMT = DDCSCGEG.BAL;
            AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_AIZPUITM();
        }
    }
    public void B040_UPD_DREG_INFO_PROC() throws IOException,SQLException,Exception {
        DDCIDREG.DATA.STS = '4';
        DDCIDREG.DATA.ITM_NO = K_ITM_NO2;
        DDCIDREG.DATA.SEQ = 2;
        DDCIDREG.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDCIDREG.DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCIDREG.DATA.D_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCIDREG.DATA.D_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCIDREG.DATA.D_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDCIDREG.OPT = 'U';
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.AC);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.CCY);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.STS);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.D_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        S000_CALL_DDZIDREG();
    }
    public void B050_WRITE_DDTCLDD_PROC() throws IOException,SQLException,Exception {
        DDRCLDD.KEY.AC = DDCSCGEG.AC;
        DDRCLDD.KEY.CCY = DDCSCGEG.CCY;
        DDRCLDD.KEY.CCY_TYPE = DDCSCGEG.CCY_TYPE;
        DDRCLDD.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRCLDD.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCLDD.STS = '4';
        DDRCLDD.BAL = DDCSCGEG.BAL;
        DDRCLDD.FLG = 'B';
        DDRCLDD.BR = DDCSCGEG.BR;
        DDRCLDD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCLDD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCLDD_RD = new DBParm();
        DDTCLDD_RD.TableName = "DDTCLDD";
        DDTCLDD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRCLDD, DDTCLDD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CLDD_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        if (AICPQIA.RC.RC_CODE != 0 
            || AICPQIA.AC.trim().length() == 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIDREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_INT_DREG, DDCIDREG);
        if (DDCIDREG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIDREG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_AIZPUITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-ITM", AICPUITM, true);
        if (AICPUITM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPUITM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCPRMR.RC);
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
