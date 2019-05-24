package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCEXC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_CHK_UPD_AUTH = "BP-CHK-EXR-UPD-AUTH";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_R_EXRD = "BP-R-EXRD-B       ";
    String CPN_R_EXRM = "BP-R-EXRD-M       ";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String K_FWD_TENOR = "FWDT ";
    int K_MAX_CNT = 20;
    int K_MAX_DATE = 99991231;
    int K_MAX_TIME = 235959;
    short WS_I = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_FWD_TENOR = " ";
    double WS_QTP_NEW = 0;
    double WS_QTP_OLD = 0;
    short WS_READ_CNT = 0;
    String WS_ERR_MSG = " ";
    char WS_STOP = ' ';
    short WS_ERROR_FIELD_NO = 0;
    String WS_ERROR_FIELD = " ";
    String WS_MSG_TXT = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCREXRS BPCREXRS = new BPCREXRS();
    BPCCEUA BPCCEUA = new BPCCEUA();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCEXCR BPCEXCR = new BPCEXCR();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPREXMSG BPREXMSG = new BPREXMSG();
    BPCREMSG BPCREMSG = new BPCREMSG();
    SCCIMSG SCCIMSG = new SCCIMSG();
    BPREXRD BPREXRD = new BPREXRD();
    SCCGWA SCCGWA;
    BPCEXCHK BPCEXCHK;
    public void MP(SCCGWA SCCGWA, BPCEXCHK BPCEXCHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXCHK = BPCEXCHK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZCEXC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXCHK.RC);
        if (BPCEXCHK.EXCEL_DATA == null) BPCEXCHK.EXCEL_DATA = "";
        JIBS_tmp_int = BPCEXCHK.EXCEL_DATA.length();
        for (int i=0;i<1000-JIBS_tmp_int;i++) BPCEXCHK.EXCEL_DATA += " ";
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_DATA.substring(0, 50));
        if (BPCEXCHK.EXCEL_DATA == null) BPCEXCHK.EXCEL_DATA = "";
        JIBS_tmp_int = BPCEXCHK.EXCEL_DATA.length();
        for (int i=0;i<1000-JIBS_tmp_int;i++) BPCEXCHK.EXCEL_DATA += " ";
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_DATA.substring(51 - 1, 51 + 50 - 1));
        IBS.CPY2CLS(SCCGWA, BPCEXCHK.EXCEL_DATA, BPCEXCR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXMSG);
        IBS.init(SCCGWA, BPCREMSG);
        BPREXMSG.KEY.BATNO = BPCEXCHK.EXCEL_BATNO;
        BPREXMSG.KEY.SEQ = BPCEXCHK.EXCEL_SEQ;
        BPCREMSG.FUNC = 'Q';
        BPCREMSG.OPT = 'S';
        BPCREMSG.REC_LEN = 197;
        BPCREMSG.REC_PT = BPREXMSG;
        S000_CALL_BPZRXMSG();
        BPCREMSG.FUNC = 'Q';
        BPCREMSG.OPT = 'R';
        S000_CALL_BPZRXMSG();
        WS_STOP = 'N';
        while (WS_STOP != 'Y') {
            CEP.TRC(SCCGWA, "READ NEXT RECORD");
            BPCREMSG.FUNC = 'D';
            S000_CALL_BPZRXMSG();
            BPCREMSG.FUNC = 'Q';
            BPCREMSG.OPT = 'R';
            S000_CALL_BPZRXMSG();
        }
        BPCREMSG.FUNC = 'Q';
        BPCREMSG.OPT = 'E';
        S000_CALL_BPZRXMSG();
        WS_ERROR_FIELD_NO = 0;
        CEP.TRC(SCCGWA, BPCEXCR.EXR_TYP);
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPCEXCR.EXR_TYP;
        BPCPRMR.DAT_PTR = BPREXRT;
        S000_CALL_BPZPRMR();
        WS_CCY = BPCEXCR.CCY;
        R000_CHECK_CCY();
        if (BPREXRT.DAT_TXT.FWD_IND == '1' 
            && BPCEXCR.FWD_TENOR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_FWD_TENOR, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            WS_ERROR_FIELD = "TENOR";
            S000_TRAN_DATA();
        }
        if (BPREXRT.DAT_TXT.FWD_IND == '0' 
            && BPCEXCR.FWD_TENOR.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONEED_FWD_TENOR, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            WS_ERROR_FIELD = "TENOR";
            S000_TRAN_DATA();
        }
        if (BPCEXCR.FWD_TENOR.trim().length() > 0) {
            WS_FWD_TENOR = BPCEXCR.FWD_TENOR;
            R000_CHECK_FWD_TENOR();
        }
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = BPCEXCR.EXR_TYP;
        BPREXRD.KEY.FWD_TENOR = BPCEXCR.FWD_TENOR;
        BPREXRD.KEY.CCY = BPCEXCR.CCY;
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        if (BPCEXCR.LOC_MID == 0 
            && BPCEXCR.FX_BUY == 0 
            && BPCEXCR.FX_SELL == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INP_RATE, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            WS_ERROR_FIELD = "RATE";
            S000_TRAN_DATA();
        }
        if (BPCEXCR.EFF_DT == 0) {
            BPCEXCR.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCEXCR.EFF_TM == 0) {
            BPCEXCR.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
        }
        CEP.TRC(SCCGWA, BPCEXCR.EFF_DT);
        CEP.TRC(SCCGWA, BPCEXCR.EFF_TM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPCEXCR.EFF_DT;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            JIBS_tmp_str[0] = "SC";
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
            JIBS_tmp_str[1] = "" + SCCCKDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[0] = JIBS_tmp_str[0].substring(0, 3 - 1) + JIBS_tmp_str[1] + JIBS_tmp_str[0].substring(3 + JIBS_tmp_str[1].length() - 1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            WS_ERROR_FIELD = "EFFECTIVE DATE        ";
            S000_TRAN_DATA();
        } else {
            if (BPCEXCR.EFF_DT < SCCGWA.COMM_AREA.AC_DATE 
                || (BPCEXCR.EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                && BPCEXCR.EFF_TM < SCCGWA.COMM_AREA.TR_TIME)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFFDT_LT_ACDT, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                WS_ERROR_FIELD = "EFFECTIVE DATE OR TIME";
                S000_TRAN_DATA();
            }
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            WS_ERROR_FIELD = "CURRENCY";
            S000_TRAN_DATA();
        }
    }
    public void R000_CHECK_FWD_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_FWD_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = WS_FWD_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_CODE, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            WS_ERROR_FIELD = "TENOR";
            S000_TRAN_DATA();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            WS_ERROR_FIELD = "EXRATE GROUP";
            S000_TRAN_DATA();
        }
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, CPN_R_EXRM, BPCTEXRM);
        if (BPCTEXRM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTEXRM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            WS_ERROR_FIELD = "EXRATE GROUP/CURRENCY/TENOR";
            S000_TRAN_DATA();
        }
    }
    public void S000_CALL_BPZRXMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-EXL-MSG", BPCREMSG);
        if (BPCREMSG.RETURN_INFO == 'N') {
            WS_STOP = 'Y';
        }
    }
    public void S000_TRAN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCREMSG);
        IBS.init(SCCGWA, BPREXMSG);
        WS_ERROR_FIELD_NO += 1;
        BPREXMSG.KEY.BATNO = BPCEXCHK.EXCEL_BATNO;
        BPREXMSG.KEY.SEQ = BPCEXCHK.EXCEL_SEQ;
        BPREXMSG.KEY.NO = WS_ERROR_FIELD_NO;
        BPREXMSG.ERROR_FIELD = WS_ERROR_FIELD;
        BPREXMSG.MSG_TXT = WS_MSG_TXT;
        BPCREMSG.FUNC = 'A';
        BPCREMSG.REC_LEN = 197;
        BPCREMSG.REC_PT = BPREXMSG;
        S000_CALL_BPZRXMSG();
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_BATNO);
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_SEQ);
    }
    public void S000_GET_ERRORMSG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCIMSG);
        SCCIMSG.ID = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
        S000_CALL_SCZIMSG();
        if (WS_MSG_TXT == null) WS_MSG_TXT = "";
        JIBS_tmp_int = WS_MSG_TXT.length();
        for (int i=0;i<88-JIBS_tmp_int;i++) WS_MSG_TXT += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
        WS_MSG_TXT = JIBS_tmp_str[0] + WS_MSG_TXT.substring(8);
        if (WS_MSG_TXT == null) WS_MSG_TXT = "";
        JIBS_tmp_int = WS_MSG_TXT.length();
        for (int i=0;i<88-JIBS_tmp_int;i++) WS_MSG_TXT += " ";
        if (SCCIMSG.TXT == null) SCCIMSG.TXT = "";
        JIBS_tmp_int = SCCIMSG.TXT.length();
        for (int i=0;i<88-JIBS_tmp_int;i++) SCCIMSG.TXT += " ";
        WS_MSG_TXT = WS_MSG_TXT.substring(0, 9 - 1) + SCCIMSG.TXT + WS_MSG_TXT.substring(9 + SCCIMSG.TXT.length() - 1);
    }
    public void S000_CALL_SCZIMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-MSG-INQ", SCCIMSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCEXCHK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCEXCHK  = ");
            CEP.TRC(SCCGWA, BPCEXCHK);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
