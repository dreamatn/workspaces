package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.PN.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSUATP {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    String CDD_M_AUTO_TD_PLAN = "DC-M-AUTO-TD-PLAN";
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    char K_PRIVATE_CUSTOM = '1';
    char K_PUBLIC_CUSTOM = '2';
    String K_PRDPR_TYPE = "PRDPR";
    char K_CARD_F_AC = '2';
    char K_AC_NO_INPUT = '0';
    String WS_MSG_ID = "      ";
    char WS_AC_STS = ' ';
    String WS_AC_STS_WORD = " ";
    String WS_CI_NO_1 = " ";
    String WS_CI_NO_2 = " ";
    int WS_START_DT = 0;
    int WS_END_DT = 0;
    String WS_CARD_NO = "                   ";
    String WS_AC_NO = "                                ";
    String WS_TRC_AC_NO = "                                ";
    short WS_CNT = 0;
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUMPRM DCCUMPRM = new DCCUMPRM();
    CICACCU CICACCU = new CICACCU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCRIRPRD DCRIRPRD = new DCRIRPRD();
    DCCUMATP DCCUMATP = new DCCUMATP();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DCCSUATP DCCSUATP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DCCSUATP DCCSUATP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSUATP = DCCSUATP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCZSUATP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        WS_TBL_FLAG = 'Y';
        IBS.init(SCCGWA, DCCSUATP.O_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_UNIT_CPN();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_AC_NO = DCCSUATP.IO_AREA.AGR_NO;
        WS_TRC_AC_NO = DCCSUATP.IO_AREA.TR_AGRNO;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_AC_NO;
        S000_CALL_CIZACCU();
        WS_CI_NO_1 = CICACCU.DATA.CI_NO;
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CUSTOM_FORBID;
            S000_ERR_MSG_PROC();
        }
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CUSTOM_CLOSE;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCSUATP.IO_AREA.PROD_CDE;
        S000_CALL_BPZPQPRD();
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_PRD_PARM_NUL, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DCCUMPRM);
        IBS.init(SCCGWA, DCRIRPRD);
        DCCUMPRM.FUNC = 'I';
        DCCUMPRM.DATA.KEY.PROD_CODE = BPCPQPRD.PARM_CODE;
        S000_CALL_DCZUMPRM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUMPRM.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRIRPRD);
        if (DCRIRPRD.EFFDAT.trim().length() == 0) WS_START_DT = 0;
        else WS_START_DT = Integer.parseInt(DCRIRPRD.EFFDAT);
        if (DCRIRPRD.EXPDAT.trim().length() == 0) WS_END_DT = 0;
        else WS_END_DT = Integer.parseInt(DCRIRPRD.EXPDAT);
        if (DCCSUATP.IO_AREA.PROCL_DT > DCCSUATP.IO_AREA.PROCS_DT) {
        } else {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_EXP_DT_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DCCSUATP.IO_AREA.TRIG_MTH == '1') {
            if ((DCCSUATP.IO_AREA.TRC_AMT != 0) 
                && DCCSUATP.IO_AREA.MRM_AMT == 0 
                && DCCSUATP.IO_AREA.TRPCT_S == 0) {
            } else {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_FIX_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCCSUATP.IO_AREA.TRIG_MTH == '2') {
            if (DCCSUATP.IO_AREA.TRC_AMT == 0 
                && (DCCSUATP.IO_AREA.MRM_AMT != 0) 
                && DCCSUATP.IO_AREA.TRPCT_S == 0) {
            } else {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_SUB_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCCSUATP.IO_AREA.TRIG_MTH == '3') {
            if (DCCSUATP.IO_AREA.TRC_AMT == 0 
                && DCCSUATP.IO_AREA.MRM_AMT == 0 
                && (DCCSUATP.IO_AREA.TRPCT_S != 0)) {
            } else {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PCNT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCCSUATP.IO_AREA.TRM_AMT != 0) {
            if (DCCSUATP.IO_AREA.MRM_AMT <= DCCSUATP.IO_AREA.TRM_AMT 
                && DCCSUATP.IO_AREA.TRC_AMT <= DCCSUATP.IO_AREA.TRM_AMT) {
            } else {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_TRC_MRM_MUST_LT_TRM;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCCSUATP.IO_AREA.PROC_STS == '4') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PROC_STS_INV;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CALL_UNIT_CPN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMATP);
        DCCUMATP.IO_AREA.FUNC_M = '2';
        DCCUMATP.IO_AREA.AGR_NO = DCCSUATP.IO_AREA.AGR_NO;
        DCCUMATP.IO_AREA.CI_NAME = DCCSUATP.IO_AREA.CI_NAME;
        DCCUMATP.IO_AREA.OVR_NO = DCCSUATP.IO_AREA.OVR_NO;
        DCCUMATP.IO_AREA.PROD_CDE = DCCSUATP.IO_AREA.PROD_CDE;
        DCCUMATP.IO_AREA.CCY = DCCSUATP.IO_AREA.CCY;
        DCCUMATP.IO_AREA.CCY_TYP = DCCSUATP.IO_AREA.CCY_TYP;
        DCCUMATP.IO_AREA.PROCS_DT = DCCSUATP.IO_AREA.PROCS_DT;
        DCCUMATP.IO_AREA.PROCL_DT = DCCSUATP.IO_AREA.PROCL_DT;
        DCCUMATP.IO_AREA.SMR = DCCSUATP.IO_AREA.SMR;
        DCCUMATP.IO_AREA.PROC_TYP = DCCSUATP.IO_AREA.PROC_TYP;
        if (DCCSUATP.IO_AREA.PROC_TYP.equalsIgnoreCase("O")) {
            DCCUMATP.IO_AREA.PERM_KND = DCCSUATP.IO_AREA.PERM_KND;
            DCCUMATP.IO_AREA.PERD = DCCSUATP.IO_AREA.PERD;
            DCCUMATP.IO_AREA.TRM_AMT = DCCSUATP.IO_AREA.TRM_AMT;
            DCCUMATP.IO_AREA.TRIG_MTH = DCCSUATP.IO_AREA.TRIG_MTH;
            DCCUMATP.IO_AREA.TRPCT_S = DCCSUATP.IO_AREA.TRPCT_S;
            DCCUMATP.IO_AREA.MRM_AMT = DCCSUATP.IO_AREA.MRM_AMT;
            DCCUMATP.IO_AREA.TRC_AMT = DCCSUATP.IO_AREA.TRC_AMT;
            DCCUMATP.IO_AREA.TD_TERM = DCCSUATP.IO_AREA.DEP_TERM;
            DCCUMATP.IO_AREA.TRIG_MD = DCCSUATP.IO_AREA.TRIG_MD;
            DCCUMATP.IO_AREA.TRIG_TMS = DCCSUATP.IO_AREA.TRIG_TMS;
            DCCUMATP.IO_AREA.INT_MTH = DCCSUATP.IO_AREA.INT_MTH;
            CEP.TRC(SCCGWA, DCCSUATP.IO_AREA.TR_AGRNO);
            DCCUMATP.IO_AREA.TR_AGRNO = DCCSUATP.IO_AREA.TR_AGRNO;
        }
        if (DCCSUATP.IO_AREA.PROC_TYP.equalsIgnoreCase("I")) {
            DCCUMATP.IO_AREA.DRAW_FLG = DCCSUATP.IO_AREA.DRAW_FLG;
            DCCUMATP.IO_AREA.DRAW_USE = DCCSUATP.IO_AREA.DRAW_USE;
            DCCUMATP.IO_AREA.DRAW_MAX = DCCSUATP.IO_AREA.DRAW_MAX;
            DCCUMATP.IO_AREA.DRAW_MIN = DCCSUATP.IO_AREA.DRAW_MIN;
            DCCUMATP.IO_AREA.DRAW_AMT = DCCSUATP.IO_AREA.DRAW_AMT;
            DCCUMATP.IO_AREA.LIMT_AMT = DCCSUATP.IO_AREA.LIMT_AMT;
            for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
                CEP.TRC(SCCGWA, DCCSUATP.IO_AREA.LNK_INFO[WS_CNT-1].LNK_ACNO);
                DCCUMATP.IO_AREA.LNK_INFO[WS_CNT-1].LNK_ACNO = DCCSUATP.IO_AREA.LNK_INFO[WS_CNT-1].LNK_ACNO;
            }
            if (DCCSUATP.IO_AREA.PROD_CDE.equalsIgnoreCase("9510000006") 
                && DCCSUATP.IO_AREA.LNK_INFO[1-1].LNK_ACNO.trim().length() == 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_LNK_AC_M_INPUT;
                CEP.ERR(SCCGWA, WS_MSG_ID);
            }
        }
        S000_CALL_DCZUMATP();
    }
    public void B300_REC_HISTRY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = WS_AC_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSUATP.IO_AREA.DR_CARD;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = " ";
        BPCPNHIS.INFO.NEW_DAT_PT = DCCSUATP;
        BPCPNHIS.INFO.FMT_ID = "DCZSUATP";
        BPCPNHIS.INFO.FMT_ID_LEN = 1070;
        S000_CALL_BPZPNHIS();
    }
    public void B101_INQCARDAC_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = K_CARD_F_AC;
        DCCPACTY.INPUT.AC = WS_CARD_NO;
        S000_CALL_DCZPACTY();
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        if (DCCPACTY.RC.RC_CODE != 0 
            || DCCPACTY.OUTPUT.STD_AC.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_NOT_FOUND_OR_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD);
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUMATP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_M_AUTO_TD_PLAN, DCCUMATP);
        if (DCCSUATP.O_AREA.RC_CODE == 0) {
        } else {
            WS_MSG_ID = DCCSUATP.O_AREA.MSG_ID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        DCCSUATP.O_AREA.RC_CODE = 08;
        DCCSUATP.O_AREA.MSG_ID = WS_MSG_ID;
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF ", DCCPACTY);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUMPRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-IRDD-PARM", DCCUMPRM);
        CEP.TRC(SCCGWA, DCCUMPRM.RC);
        if (DCCUMPRM.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DCCUMPRM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
