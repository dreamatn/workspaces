package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCPSW {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String CPN_F_RANDOM_VALUE = "SC-RANDOM-VALUE     ";
    String CPN_ENCRYPT_PASSWORD = "SC-ENCRYPT-PASSWORD ";
    BPZFCPSW_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZFCPSW_WS_TEMP_VARIABLE();
    short WS_PSW_LEN = 0;
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    SCCRAND SCCRAND = new SCCRAND();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCPASS SCCPASS = new SCCPASS();
    BPRTLT BPRTLT = new BPRTLT();
    SCCGWA SCCGWA;
    BPCFCPSW BPCFCPSW;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFCPSW BPCFCPSW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFCPSW = BPCFCPSW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCPSW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFCPSW.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_PSW_LEN = 20;
        CEP.TRC(SCCGWA, WS_PSW_LEN);
        B010_COMMON_CHECK();
        if (pgmRtn) return;
        if (BPCFCPSW.OPT == 'C') {
            B020_CHECK_PASSWORD();
            if (pgmRtn) return;
        } else if (BPCFCPSW.OPT == 'R') {
            B030_RESET_PASSWORD();
            if (pgmRtn) return;
        } else if (BPCFCPSW.OPT == 'M') {
            B040_MODIFY_PASSWORD();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFCPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B050_REWRITE_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_COMMON_CHECK() throws IOException,SQLException,Exception {
        if (BPCFCPSW.OPT == 'C'
            || BPCFCPSW.OPT == 'R'
            || BPCFCPSW.OPT == 'M') {
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFCPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTM.INFO.FUNC = 'Q';
        BPRTLT.KEY.TLR = BPCFCPSW.TLR;
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLRM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_BANK_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCFCPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_PASSWORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPASS);
        SCCPASS.LEN = WS_PSW_LEN;
        SCCPASS.KEY = BPCFCPSW.TLR;
        SCCPASS.DATA = BPCFCPSW.OLD_PSW;
        S000_CALL_BPZPASS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCPASS.DATA);
        if (!SCCPASS.DATA.equalsIgnoreCase(BPRTLT.TLR_CRD_PSW)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSW_NOT_CORRECT, BPCFCPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_RESET_PASSWORD() throws IOException,SQLException,Exception {
        WS_TEMP_VARIABLE.WS_TEMP_PSW = " ";
        IBS.init(SCCGWA, SCCRAND);
        S000_CALL_SCZRAND();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_TEMP_PSW = SCCRAND.VALUE;
    }
    public void B040_MODIFY_PASSWORD() throws IOException,SQLException,Exception {
    }
    public void B050_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        B050_01_ENCRYPT_PASSWORD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTM.INFO.FUNC = 'R';
        BPRTLT.KEY.TLR = BPCFCPSW.TLR;
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLRM();
        if (pgmRtn) return;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        BPCRTLTM.INFO.FUNC = 'M';
        S000_CALL_BPZRTLRM();
        if (pgmRtn) return;
        if (BPCRTLTM.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCFCPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_01_ENCRYPT_PASSWORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPASS);
        SCCPASS.LEN = WS_PSW_LEN;
        SCCPASS.KEY = BPCFCPSW.TLR;
        if (BPCFCPSW.OPT == 'R') {
            SCCPASS.DATA = WS_TEMP_VARIABLE.WS_TEMP_PSW;
            SCCPASS.FUNC = '2';
        } else {
            SCCPASS.DATA = BPCFCPSW.NEW_PSW;
        }
        S000_CALL_BPZPASS();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCZRAND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_RANDOM_VALUE, SCCRAND);
        if (SCCRAND.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCRAND.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCRTLTM.RC.RC_CODE+"", BPCFCPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCCENPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ENCRYPT_PASSWORD, SCCENPSW);
        CEP.TRC(SCCGWA, SCCENPSW.RC.RC_CODE);
        if (SCCENPSW.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCENPSW.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPASS() throws IOException,SQLException,Exception {
        SCZPASS SCZPASS = new SCZPASS();
        SCZPASS.MP(SCCGWA, SCCPASS);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (BPCFCPSW.OPT == 'R'
            || BPCFCPSW.OPT == 'M') {
            BPRTLT.PSW_RETRY = 0;
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            JIBS_tmp_str[0] = "" + 0;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPRTLT.TLR_STSW = BPRTLT.TLR_STSW.substring(0, 12 - 1) + JIBS_tmp_str[0] + BPRTLT.TLR_STSW.substring(12 + 1 - 1);
            if (SCCPASS.DATA == null) SCCPASS.DATA = "";
            JIBS_tmp_int = SCCPASS.DATA.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) SCCPASS.DATA += " ";
            BPRTLT.TLR_CRD_PSW = SCCPASS.DATA.substring(0, WS_PSW_LEN);
            BPRTLT.LAST_JRN = SCCGWA.COMM_AREA.JRN_NO;
            BPRTLT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTLT.CRD_PSW_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTLT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else if (BPCFCPSW.OPT == 'C') {
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFCPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFCPSW.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFCPSW = ");
            CEP.TRC(SCCGWA, BPCFCPSW);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
