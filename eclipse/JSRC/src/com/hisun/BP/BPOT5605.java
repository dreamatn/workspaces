package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5605 {
    int JIBS_tmp_int;
    String CPN_S_SRC_IRATE_INQ = "BP-S-SRC-IRATE-INQ ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String CPN_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_SPACE_NO = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    char WS_COMPLETE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSSRCI BPCSSRCI = new BPCSSRCI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    BPB5205_AWA_5205 BPB5205_AWA_5205;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5605 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5205_AWA_5205>");
        BPB5205_AWA_5205 = (BPB5205_AWA_5205) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B200_SET_NXT_TXN();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5205_AWA_5205.BR == ' ' 
            || BPB5205_AWA_5205.BR == 0) {
            BPB5205_AWA_5205.BR = SCCGWA.COMM_AREA.HQT_BANK;
        }
        CEP.TRC(SCCGWA, BPB5205_AWA_5205.DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPB5205_AWA_5205.DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_DATE_NO_VALID;
            WS_FLD_NO = BPB5205_AWA_5205.DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB5205_AWA_5205.BR == ' ' 
            || BPB5205_AWA_5205.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            WS_FLD_NO = BPB5205_AWA_5205.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            WS_BR = BPB5205_AWA_5205.BR;
            R000_CHECK_BRANCH();
        }
        if (BPB5205_AWA_5205.CCY.trim().length() == 0 
            || BPB5205_AWA_5205.CCY.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_SPACE_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPB5205_AWA_5205.CCY.trim().length() == 0 
            && BPB5205_AWA_5205.BASE_TPE.equalsIgnoreCase("999")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_SPACE_ERR;
            if (BPB5205_AWA_5205.CCY.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB5205_AWA_5205.CCY);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB5205_AWA_5205.CCY);
        if (BPB5205_AWA_5205.CCY.trim().length() > 0) {
            WS_CCY = BPB5205_AWA_5205.CCY;
            R000_CHECK_CCY();
        }
        CEP.TRC(SCCGWA, BPB5205_AWA_5205.BASE_TPE);
        if (BPB5205_AWA_5205.BASE_TPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID;
            WS_FLD_NO = BPB5205_AWA_5205.BASE_TPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            WS_BASE_TYP = BPB5205_AWA_5205.BASE_TPE;
            R000_CHECK_BASE_TYP();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5205_AWA_5205.DT);
        CEP.TRC(SCCGWA, "CGBYWSB200");
        IBS.init(SCCGWA, BPCSSRCI);
        BPCSSRCI.FUNC = 'Q';
        BPCSSRCI.BR = BPB5205_AWA_5205.BR;
        BPCSSRCI.CCY = BPB5205_AWA_5205.CCY;
        BPCSSRCI.BASE_TYP = BPB5205_AWA_5205.BASE_TPE;
        CEP.TRC(SCCGWA, BPB5205_AWA_5205.NAME);
        CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME);
        if (BPB5205_AWA_5205.NAME.trim().length() == 0) {
            if (BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME == null) BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME = "";
            JIBS_tmp_int = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME.length();
            for (int i=0;i<120-JIBS_tmp_int;i++) BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME += " ";
            BPCSSRCI.NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME.substring(0, 30);
        } else {
            BPCSSRCI.NAME = BPB5205_AWA_5205.NAME;
        }
        BPCSSRCI.DT = BPB5205_AWA_5205.DT;
        S000_CALL_BPZSSRCI();
    }
    public void B200_SET_NXT_TXN() throws IOException,SQLException,Exception {
        if (BPB5205_AWA_5205.BASE_TPE.equalsIgnoreCase("999")) {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5642;
            S020_SET_SUBS_TRN();
        } else {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5607;
            S020_SET_SUBS_TRN();
        }
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB5205_AWA_5205.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            WS_FLD_NO = BPB5205_AWA_5205.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_FLD_NO = BPB5205_AWA_5205.BASE_TPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZSSRCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_SRC_IRATE_INQ, BPCSSRCI);
    }
    public void S020_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB5205_AWA_5205.CCY_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
