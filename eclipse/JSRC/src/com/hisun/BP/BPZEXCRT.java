package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZEXCRT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_RATEID = "RRTID";
    String K_BASE_TYPE = "RBASE";
    String K_TENOR = "RTENO";
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    String WS_ERR_MSG = " ";
    BPZEXCRT_WS_UPLOAD_DATA WS_UPLOAD_DATA = new BPZEXCRT_WS_UPLOAD_DATA();
    char WS_STOP = ' ';
    short WS_ERROR_FIELD_NO = 0;
    String WS_ERROR_FIELD = " ";
    String WS_MSG_TXT = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCOQRTD BPCOQRTD = new BPCOQRTD();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    BPCIQCIT BPCIQCIT = new BPCIQCIT();
    BPREXMSG BPREXMSG = new BPREXMSG();
    BPCREMSG BPCREMSG = new BPCREMSG();
    SCCIMSG SCCIMSG = new SCCIMSG();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    SCCGWA SCCGWA;
    BPCEXCHK BPCEXCHK;
    public void MP(SCCGWA SCCGWA, BPCEXCHK BPCEXCHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXCHK = BPCEXCHK;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZEXCRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXCHK.RC);
        BPCEXCHK.DATA_FLG = ' ';
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCEXCHK.DATA_FLG = '1';
        B100_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B200_MAP_INPUT_DATA();
        if (pgmRtn) return;
        B300_CHK_DATA_VALID();
        if (pgmRtn) return;
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN CHECK INTEREST RATE");
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_TYPE);
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_DATA);
        if (BPCEXCHK.EXCEL_DATA.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_DATA_EMPTY, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAP_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCEXCHK.EXCEL_DATA, WS_UPLOAD_DATA);
        BPCEXCHK.EXCEL_DATA = " ";
    }
    public void B300_CHK_DATA_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXMSG);
        IBS.init(SCCGWA, BPCREMSG);
        BPREXMSG.KEY.BATNO = BPCEXCHK.EXCEL_BATNO;
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_BATNO);
        BPREXMSG.KEY.SEQ = BPCEXCHK.EXCEL_SEQ;
        BPCREMSG.FUNC = 'Q';
        BPCREMSG.OPT = 'S';
        BPCREMSG.REC_LEN = 197;
        BPCREMSG.REC_PT = BPREXMSG;
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        BPCREMSG.FUNC = 'Q';
        BPCREMSG.OPT = 'R';
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        WS_STOP = 'N';
        while (WS_STOP != 'Y') {
            CEP.TRC(SCCGWA, "READ NEXT RECORD");
            BPCREMSG.FUNC = 'D';
            S000_CALL_BPZRXMSG();
            if (pgmRtn) return;
            BPCREMSG.FUNC = 'Q';
            BPCREMSG.OPT = 'R';
            S000_CALL_BPZRXMSG();
            if (pgmRtn) return;
        }
        BPCREMSG.FUNC = 'Q';
        BPCREMSG.OPT = 'E';
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        WS_ERROR_FIELD_NO = 0;
        CEP.TRC(SCCGWA, "HERE");
        if (WS_UPLOAD_DATA.WS_RATE_TYPE.trim().length() == 0 
            || WS_UPLOAD_DATA.WS_TENOR.trim().length() == 0 
            || WS_UPLOAD_DATA.WS_EFF_DATE == 0 
            || WS_UPLOAD_DATA.WS_RATE == 0 
            || WS_UPLOAD_DATA.WS_CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_ALL, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "ALL FIELD";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_UPLOAD_DATA.WS_RATE_TYPE);
        if (WS_UPLOAD_DATA.WS_RATE_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
            BPCOQPCD.INPUT_DATA.CODE = WS_UPLOAD_DATA.WS_RATE_TYPE;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            if (BPCOQPCD.RC.RC_CODE > 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "INTEREST RATE TYPE";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_UPLOAD_DATA.WS_TENOR);
        if (WS_UPLOAD_DATA.WS_TENOR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
            BPCOQPCD.INPUT_DATA.CODE = WS_UPLOAD_DATA.WS_TENOR;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            if (BPCOQPCD.RC.RC_CODE > 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_TENOR_NO_VALID, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "INTEREST RATE TENOR";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            }
        }
        if (WS_UPLOAD_DATA.WS_RATE_TYPE.trim().length() > 0 
            && WS_UPLOAD_DATA.WS_TENOR.trim().length() > 0 
            && WS_UPLOAD_DATA.WS_CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CHECK CCY-TYPE-TENOR");
            IBS.init(SCCGWA, BPRIRPD);
            BPRIRPD.KEY.CCY = WS_UPLOAD_DATA.WS_CCY;
            BPRIRPD.KEY.BASE_TYP = WS_UPLOAD_DATA.WS_RATE_TYPE;
            BPRIRPD.KEY.TENOR = WS_UPLOAD_DATA.WS_TENOR;
            IBS.init(SCCGWA, BPCRIPDM);
            BPCRIPDM.INFO.FUNC = 'Q';
            BPCRIPDM.INFO.OPT_2 = 'O';
            S000_CALL_BPZRIPDM();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_UPLOAD_DATA.WS_EFF_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "EFFECTIVE DATE";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(WS_UPLOAD_DATA.WS_RATE+"")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_DATA_ERR, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "RATE";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_GET_ERRORMSG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCIMSG);
        SCCIMSG.ID = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
        S000_CALL_SCZIMSG();
        if (pgmRtn) return;
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
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_BATNO);
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_SEQ);
    }
    public void S000_CALL_BPZRXMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-EXL-MSG", BPCREMSG);
        if (BPCREMSG.RETURN_INFO == 'N') {
            WS_STOP = 'Y';
        }
    }
    public void B300_01_CHK_RATEID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQRTD);
        BPCOQRTD.DATA.CCY = WS_UPLOAD_DATA.WS_CCY;
        BPCOQRTD.DATA.BASE_TYP = WS_UPLOAD_DATA.WS_RATE_TYPE;
        BPCOQRTD.DATA.TENOR = WS_UPLOAD_DATA.WS_TENOR;
        BPCOQRTD.INQ_FLG = 'C';
        S000_CALL_BPZPQRTD();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRIPDM() throws IOException,SQLException,Exception {
        BPCRIPDM.INFO.POINTER = BPRIRPD;
        IBS.CALLCPN(SCCGWA, CPN_R_BK_MT, BPCRIPDM);
        if (BPCRIPDM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRIPDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
        }
        if (BPCRIPDM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCEXCHK.RC);
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-RTID", BPCOQRTD);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        CEP.TRC(SCCGWA, BPCOQPCD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCEXCHK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCEXCHK = ");
            CEP.TRC(SCCGWA, BPCEXCHK);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
