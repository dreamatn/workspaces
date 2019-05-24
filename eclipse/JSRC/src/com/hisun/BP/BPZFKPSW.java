package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class BPZFKPSW {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZFKPSW";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String CPN_F_RANDOM_VALUE = "SC-RANDOM-VALUE     ";
    String CPN_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_ENCRYPT_PASSWORD = "SC-ENCRYPT-PASSWORD ";
    String CPN_CHECK_TLR_PSW = "BP-F-CHECK-PASSWORD ";
    String CPN_P_QUERY_BK_PSW = "BP-P-QUERY-BKPSW    ";
    String CPN_P_SET_PSW_REPEAT = "BP-P-SET-PSW-RETRY  ";
    String K_OUTPUT_FMT = "BP552";
    BPZFKPSW_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZFKPSW_WS_TEMP_VARIABLE();
    int WS_J = 0;
    int WS_K = 0;
    short WS_PSW_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCRAND SCCRAND = new SCCRAND();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCPASS SCCPASS = new SCCPASS();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPRTLT BPRTLT = new BPRTLT();
    BPCOTLRM BPCOTLRM = new BPCOTLRM();
    BPCFCHPW BPCFCHPW = new BPCFCHPW();
    BPCPQBPW BPCPQBPW = new BPCPQBPW();
    BPCFSPWR BPCFSPWR = new BPCFSPWR();
    SCCGWA SCCGWA;
    BPCFKPSW BPCFKPSW;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLTC;
    public void MP(SCCGWA SCCGWA, BPCFKPSW BPCFKPSW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFKPSW = BPCFKPSW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFKPSW return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTLTC = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        IBS.init(SCCGWA, BPCFCHPW);
        IBS.init(SCCGWA, BPCPQBPW);
        IBS.init(SCCGWA, BPCFSPWR);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFKPSW.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_PSW_LEN = 20;
        CEP.TRC(SCCGWA, WS_PSW_LEN);
        CEP.TRC(SCCGWA, BPCFKPSW.TLR);
        CEP.TRC(SCCGWA, BPCFKPSW.OLD_PSW);
        B010_COMMON_CHECK();
        if (pgmRtn) return;
        if (BPCFKPSW.OPT == 'C') {
            B020_CHECK_PASSWORD();
            if (pgmRtn) return;
        } else if (BPCFKPSW.OPT == 'R') {
            B030_RESET_PASSWORD();
            if (pgmRtn) return;
            B050_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCFKPSW.OPT == 'M') {
            B040_MODIFY_PASSWORD();
            if (pgmRtn) return;
            B050_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCFKPSW.OPT == 'L') {
            B060_RELEASE_PASSWORD();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFKPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFKPSW.OPT == 'R') {
            B090_OUTPUT_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_COMMON_CHECK() throws IOException,SQLException,Exception {
        if (BPCFKPSW.OPT == 'C'
            || BPCFKPSW.OPT == 'R'
            || BPCFKPSW.OPT == 'M'
            || BPCFKPSW.OPT == 'L') {
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFKPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B010_01_TLR_QUERY_PROC();
        if (pgmRtn) return;
        if (BPCFKPSW.OPT == 'M') {
            B020_CHECK_PASSWORD();
            if (pgmRtn) return;
        }
        if (BPCFKPSW.OPT == 'L') {
            B010_03_CHECK_TLR_PSW_RETRY();
            if (pgmRtn) return;
        }
        if (BPCFKPSW.OPT == 'R' 
            || BPCFKPSW.OPT == 'L') {
            if (BPRTLT.SIGN_STS == 'O' 
                && (BPRTLT.SIGN_DT == SCCGWA.COMM_AREA.AC_DATE)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_ALREADY_SINGON, BPCFKPSW.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPRTLT.TLR_STS == 'L') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR, BPCFKPSW.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPRTLT.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                B010_02_ORGM_COMPARE();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_01_TLR_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        CEP.TRC(SCCGWA, BPCFKPSW.TLR);
        BPRTLT.KEY.TLR = BPCFKPSW.TLR;
        BPCRTLTM.INFO.FUNC = 'Q';
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLRM();
        if (pgmRtn) return;
    }
    public void B010_02_ORGM_COMPARE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = BPRTLT.TLR_BR;
        S000_CALL_BPZPRGST();
        if (pgmRtn) return;
        if (BPCPRGST.FLAG != 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_RELATED_BRANCH, BPCFKPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPRGST.LVL_RELATION == 'C') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LVL_RELATION_LOW, BPCFKPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_03_CHECK_TLR_PSW_RETRY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBPW);
        BPCPQBPW.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQBPW.DATA_INFO.PSW_TYP = BPRTLT.PSW_TYP;
        S000_CALL_BPZPQBPW();
        if (pgmRtn) return;
        if (BPRTLT.PSW_RETRY < BPCPQBPW.DATA_INFO.TLR_PMAX) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_PSWD_NOT_LOCKED, BPCFKPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRTLTC.TLR_LVL < BPCPQBPW.DATA_INFO.REL_PSW_LVL) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_LVL_NOT_ALLOW, BPCFKPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_PASSWORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPASS);
        SCCPASS.LEN = WS_PSW_LEN;
        SCCPASS.KEY = BPCFKPSW.TLR;
        SCCPASS.DATA = BPCFKPSW.OLD_PSW;
        S000_CALL_BPZPASS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCPASS.LEN);
        CEP.TRC(SCCGWA, SCCPASS.KEY);
        CEP.TRC(SCCGWA, SCCPASS.DATA);
        if (!SCCPASS.DATA.equalsIgnoreCase(BPRTLT.KB_PSW)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSW_NOT_CORRECT, BPCFKPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_RESET_PASSWORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCRAND.VALUE);
        IBS.init(SCCGWA, BPCPQBPW);
        BPCPQBPW.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        S000_CALL_BPZPQBPW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQBPW.DATA_INFO.RAND_PSW_FLG);
        if (BPCPQBPW.DATA_INFO.RAND_PSW_FLG == 'Y') {
            IBS.init(SCCGWA, SCCRAND);
            S000_CALL_SCZRAND();
            if (pgmRtn) return;
            WS_TEMP_VARIABLE.WS_TEMP_PSW = SCCRAND.VALUE;
        } else {
            WS_TEMP_VARIABLE.WS_TEMP_PSW = BPCPQBPW.DATA_INFO.INIT_PSW;
        }
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TEMP_PSW);
    }
    public void B040_MODIFY_PASSWORD() throws IOException,SQLException,Exception {
        if (BPCFKPSW.NEW_PSW.equalsIgnoreCase(BPCFKPSW.OLD_PSW)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SAME_OLD_PSWD, BPCFKPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!BPCFKPSW.NEW_PSW.equalsIgnoreCase(BPCFKPSW.NEW_PSW2)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_NOT_MATCH, BPCFKPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFCHPW);
        BPCFCHPW.TLR = BPCFKPSW.TLR;
        BPCFCHPW.PSW = BPCFKPSW.NEW_PSW;
        S000_CALL_BPZFCHPW();
        if (pgmRtn) return;
    }
    public void B050_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        B050_01_ENCRYPT_PASSWORD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTM.INFO.FUNC = 'R';
        BPRTLT.KEY.TLR = BPCFKPSW.TLR;
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
    }
    public void B050_01_ENCRYPT_PASSWORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPASS);
        SCCPASS.LEN = WS_PSW_LEN;
        SCCPASS.KEY = BPCFKPSW.TLR;
        if (BPCFKPSW.OPT == 'R') {
            SCCPASS.DATA = WS_TEMP_VARIABLE.WS_TEMP_PSW;
            SCCPASS.FUNC = '2';
        } else {
            SCCPASS.DATA = BPCFKPSW.NEW_PSW;
        }
        S000_CALL_BPZPASS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCPASS.DATA);
    }
    public void B060_RELEASE_PASSWORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFSPWR);
        BPCFSPWR.FUNC = 'R';
        BPCFSPWR.TLR = BPCFKPSW.TLR;
        S000_CALL_BPZFSPWR();
        if (pgmRtn) return;
    }
    public void B090_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTLRM);
        BPCOTLRM.TLR = BPCFKPSW.TLR;
        BPCOTLRM.CREATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOTLRM.NEW_PSW = WS_TEMP_VARIABLE.WS_TEMP_PSW;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTLRM;
        SCCFMT.DATA_LEN = 36;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRTLRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFKPSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZRAND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_RANDOM_VALUE, SCCRAND);
        CEP.TRC(SCCGWA, SCCRAND.RC.RC_CODE);
