package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSACOB {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    brParm BPTACOBL_BR = new brParm();
    DBParm BPTACOBL_RD;
    DBParm BPTSEQ_RD;
    boolean pgmRtn = false;
    String K_CMP_CALL_SCZKDGAC = "SC-AC-DIGIT    ";
    String K_OUTPUT_FMT = "BPA04";
    short K_PAGE_ROWS = 15;
    String K_AC_TIT = "95508";
    String CPN_CI_CIZACCU_CN = "CI-INQ-ACCU";
    String CPN_CI_CIZCUST_CN = "CI-INQ-CUST";
    String WS_ERR_MSG = " ";
    short WS_INP_NUM = 0;
    BPZSACOB_WS_AC_NO_ALL WS_AC_NO_ALL = new BPZSACOB_WS_AC_NO_ALL();
    String WS_SEQ_TYPE = "ACSEQ";
    short WS_I = 0;
    BPZSACOB_WS_AC_NO_CODE WS_AC_NO_CODE = new BPZSACOB_WS_AC_NO_CODE();
    BPZSACOB_WS_SEQ_NAME WS_SEQ_NAME = new BPZSACOB_WS_SEQ_NAME();
    int WS_AC_SEQ_OLD = 0;
    char WS_BROWSE_COND_FLG = ' ';
    char WS_BROWSE_CONTINUE = ' ';
    BPZSACOB_WS_DATA WS_DATA = new BPZSACOB_WS_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRACOBL BPRACOBL = new BPRACOBL();
    SCCKDGAC SCCKDGAC = new SCCKDGAC();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    BPROBL BPROBL = new BPROBL();
    BPRSEQ BPRSEQ = new BPRSEQ();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCRAOBL BPCRAOBL = new BPCRAOBL();
    BPCO065 BPCO065 = new BPCO065();
    char WS_AC_FLG = ' ';
    char WS_AC_TYPE = ' ';
    String WS_AC_NO = " ";
    String WS_CI_NO = " ";
    long WS_ACNO_SEQ = 0;
    char WS_USED_FLG = ' ';
    int WS_MAKE_BR = 0;
    String WS_MAKER_ID = " ";
    String WS_CHECKER_ID = " ";
    String WS_REMARK = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCSACOB BPCSACOB;
    public void MP(SCCGWA SCCGWA, BPCSACOB BPCSACOB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSACOB = BPCSACOB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSACOB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPCRAOBL.PTR = BPROBL;
        BPCRAOBL.LEN = 121;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSACOB.RC);
        CEP.TRC(SCCGWA, BPCSACOB.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSACOB.FUNC == 'B') {
            B030_BROWSE_OBL_AC();
            if (pgmRtn) return;
        } else if (BPCSACOB.FUNC == 'A') {
            B050_CREATE_OBL_AC();
            if (pgmRtn) return;
            R000_TRANS_TABLE_DATA1();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT1();
            if (pgmRtn) return;
        } else if (BPCSACOB.FUNC == 'R') {
            B070_READ_OBL_AC();
            if (pgmRtn) return;
            R000_TRANS_TABLE_DATA();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSACOB.FUNC == 'D') {
            B090_DELETE_OBL_AC();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSACOB.FUNC == 'F') {
            B100_BROWSE_FMT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSACOB.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, BPCSACOB.RC);
    }
    public void B050_CREATE_OBL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.CI_AC_FLG = BPCSACOB.DATA.AC_FLG;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_FLG);
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_AC_FLG);
        BPCCGAC.DATA.CI_AC_TYPE = BPCSACOB.DATA.AC_TYPE;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_TYPE);
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_AC_TYPE);
        JIBS_tmp_str[0] = "" + BPCSACOB.DATA.AC_NO_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<10-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCCGAC.DATA.CI_RANDOM = JIBS_tmp_str[0].substring(0, 2);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_NO_SEQ);
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_RANDOM);
        BPCCGAC.DATA.CI_SEQ = (int) BPCSACOB.DATA.AC_NO_SEQ;
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_SEQ);
        BPCCGAC.DATA.AC_KIND = '1';
        S000_CALL_BPZGACNO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_AC);
        BPCSACOB.DATA.AC_NO = BPCCGAC.DATA.CI_AC;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = BPCSACOB.DATA.AC_NO;
        S000_CALL_CIZACCU_CN();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSACOB.RC);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OBL_ACNO_IS_USED, BPCSACOB.RC);
        }
        S000_CHECK_RETURN_CODE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRACOBL);
        BPRACOBL.KEY.AC_FLG = BPCSACOB.DATA.AC_FLG;
        BPRACOBL.KEY.AC_TYPE = BPCSACOB.DATA.AC_TYPE;
        BPRACOBL.KEY.AC_NO_SEQ = BPCSACOB.DATA.AC_NO_SEQ;
        BPRACOBL.AC_NO = BPCSACOB.DATA.AC_NO;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_FLG);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_TYPE);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_NO_SEQ);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_FLG);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_TYPE);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_NO_SEQ);
        T000_READ_BPTACOBL1();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AAA");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "BBB");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OBL_ACNO_IS_USED, BPCSACOB.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSACOB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CCC");
        IBS.init(SCCGWA, BPRACOBL);
        R000_TRANS_DATA_FOR_CREATE();
        if (pgmRtn) return;
        T000_WRITE_BPTACOBL();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRAOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-ADD-OBL", BPCRAOBL);
        if (BPCRAOBL.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCSACOB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRAOBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRAOBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSACOB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZKDGAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_CALL_SCZKDGAC, SCCKDGAC);
        CEP.TRC(SCCGWA, SCCKDGAC.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCKDGAC.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSACOB.RC);
        S000_CHECK_RETURN_CODE();
        if (pgmRtn) return;
    }
    public void S000_CHECK_RETURN_CODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSACOB.RC);
        if (BPCSACOB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSACOB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSACOB.RC);
        }
    }
    public void B070_READ_OBL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRACOBL);
        BPRACOBL.KEY.AC_FLG = BPCSACOB.DATA.AC_FLG;
        BPRACOBL.KEY.AC_TYPE = BPCSACOB.DATA.AC_TYPE;
        BPRACOBL.KEY.AC_NO_SEQ = BPCSACOB.DATA.AC_NO_SEQ;
        T000_READ_BPTACOBL1();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B090_DELETE_OBL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRACOBL);
        BPRACOBL.KEY.AC_FLG = BPCSACOB.DATA.AC_FLG;
        BPRACOBL.KEY.AC_TYPE = BPCSACOB.DATA.AC_TYPE;
        BPRACOBL.KEY.AC_NO_SEQ = BPCSACOB.DATA.AC_NO_SEQ;
        T000_READUPDATE_BPTACOBL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRACOBL.USED_FLG);
        if (BPRACOBL.USED_FLG == 'Y') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DEL1_1063);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPRACOBL.CREATE_BR);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPRACOBL.CREATE_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DEL2_1063);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            R000_TRANS_TABLE_DATA();
            if (pgmRtn) return;
            T000_DELETE_BPTACOBL();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCSACOB.DATA;
        SCCFMT.DATA_LEN = 180;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_OUTPUT1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_DATA;
        SCCFMT.DATA_LEN = 125;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSACOB.FUNC == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IN_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSACOB.FUNC == 'B') {
            WS_INP_NUM = 0;
            if (BPCSACOB.DATA.AC_TYPE != ' ' 
                && BPCSACOB.DATA.AC_FLG != ' ') {
                WS_INP_NUM += 1;
                WS_BROWSE_COND_FLG = 'T';
            }
            if (BPCSACOB.DATA.AC_NO.trim().length() > 0) {
                WS_INP_NUM += 1;
                WS_BROWSE_COND_FLG = 'A';
            }
            if (BPCSACOB.DATA.AC_NO_SEQ != 0) {
                WS_INP_NUM += 1;
                WS_BROWSE_COND_FLG = 'Q';
            }
            if (BPCSACOB.DATA.CI_NO.trim().length() > 0) {
                WS_INP_NUM += 1;
                WS_BROWSE_COND_FLG = 'C';
            }
            if (WS_INP_NUM > 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MORE_THAN_ONE_COND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_INP_NUM < 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NONE_COND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSACOB.FUNC == 'A') {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCSACOB.DATA.CI_NO;
            CEP.TRC(SCCGWA, BPCSACOB.DATA.CI_NO);
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST_CN();
            if (pgmRtn) return;
            S000_CHECK_RETURN_CODE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
            CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_TYPE);
            if ((CICCUST.O_DATA.O_CI_TYP == '1' 
                && BPCSACOB.DATA.AC_TYPE == '8') 
                || (CICCUST.O_DATA.O_CI_TYP == '2' 
                && BPCSACOB.DATA.AC_TYPE == '9')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_E_AC_TYPE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("100900")) {
            JIBS_tmp_str[0] = "" + BPCSACOB.DATA.AC_NO_SEQ;
            JIBS_f0 = "";
            for (int i=0;i<10-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCSACOB.DATA.AC_NO_SEQ;
            JIBS_NumStr = JIBS_NumStr.substring(0, 2 - 1) + "8" + JIBS_NumStr.substring(2 + 1 - 1);
            BPCSACOB.DATA.AC_NO_SEQ = Long.parseLong(JIBS_NumStr);
        } else {
            JIBS_tmp_str[0] = "" + BPCSACOB.DATA.AC_NO_SEQ;
            JIBS_f0 = "";
            for (int i=0;i<10-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCSACOB.DATA.AC_NO_SEQ;
            JIBS_NumStr = JIBS_NumStr.substring(0, 2 - 1) + "9" + JIBS_NumStr.substring(2 + 1 - 1);
            BPCSACOB.DATA.AC_NO_SEQ = Long.parseLong(JIBS_NumStr);
        }
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_NO_SEQ);
    }
    public void B030_BROWSE_OBL_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSACOB);
        R000_WRITE_PAGE_TITLE();
        if (pgmRtn) return;
        WS_AC_FLG = BPCSACOB.DATA.AC_FLG;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_FLG);
        CEP.TRC(SCCGWA, WS_AC_FLG);
        WS_AC_TYPE = BPCSACOB.DATA.AC_TYPE;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_TYPE);
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        WS_CI_NO = BPCSACOB.DATA.CI_NO;
        WS_AC_NO = BPCSACOB.DATA.AC_NO;
        WS_ACNO_SEQ = BPCSACOB.DATA.AC_NO_SEQ;
        WS_USED_FLG = BPCSACOB.DATA.USED_FLG;
        CEP.TRC(SCCGWA, WS_AC_FLG);
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, WS_AC_NO);
        CEP.TRC(SCCGWA, WS_ACNO_SEQ);
        CEP.TRC(SCCGWA, WS_USED_FLG);
        CEP.TRC(SCCGWA, WS_BROWSE_COND_FLG);
        if (WS_BROWSE_COND_FLG == 'A') {
            WS_BROWSE_CONTINUE = 'N';
            T000_READ_AC_NO();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                R000_TRANS_TABLE_DATA();
                if (pgmRtn) return;
                R000_WRITE_PAGE_RECORD();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (WS_BROWSE_COND_FLG == 'Q') {
            WS_BROWSE_CONTINUE = 'Y';
            T000_STARTBR_ACNO_SEQ();
            if (pgmRtn) return;
        } else if (WS_BROWSE_COND_FLG == 'C') {
            WS_BROWSE_CONTINUE = 'Y';
            T000_STARTBR_CI_NO();
            if (pgmRtn) return;
        } else if (WS_BROWSE_COND_FLG == 'T') {
            WS_BROWSE_CONTINUE = 'Y';
            T000_STARTBR_AC_TYPE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID COND(" + WS_BROWSE_COND_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (WS_BROWSE_CONTINUE == 'Y') {
            T000_READNEXT_BPTACOBL();
            if (pgmRtn) return;
            while (WS_BROWSE_CONTINUE != 'N' 
                && SCCMPAG.FUNC != 'E') {
                R000_TRANS_TABLE_DATA();
                if (pgmRtn) return;
                R000_WRITE_PAGE_RECORD();
                if (pgmRtn) return;
                T000_READNEXT_BPTACOBL();
                if (pgmRtn) return;
            }
            T000_ENDBR_BPTACOBL();
            if (pgmRtn) return;
        }
    }
    public void B100_BROWSE_FMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_FLG);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_TYPE);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.CI_NO);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_NO);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_NO_SEQ);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.USED_FLG);
        IBS.init(SCCGWA, BPRACOBL);
        BPRACOBL.KEY.AC_FLG = BPCSACOB.DATA.AC_FLG;
        BPRACOBL.KEY.AC_TYPE = BPCSACOB.DATA.AC_TYPE;
        BPRACOBL.CI_NO = BPCSACOB.DATA.CI_NO;
        BPRACOBL.AC_NO = BPCSACOB.DATA.AC_NO;
        BPRACOBL.KEY.AC_NO_SEQ = BPCSACOB.DATA.AC_NO_SEQ;
        BPRACOBL.USED_FLG = BPCSACOB.DATA.USED_FLG;
        IBS.init(SCCGWA, BPCO065);
        T000_STARTBR_FMT();
        if (pgmRtn) return;
        T000_READNEXT_BPTACOBL();
        if (pgmRtn) return;
        if (WS_BROWSE_CONTINUE == 'N') {
            IBS.init(SCCGWA, BPRSEQ);
            CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_FLG);
            CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_TYPE);
            CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_NO_SEQ);
            IBS.CPY2CLS(SCCGWA, BPCSACOB.DATA.AC_NO_SEQ+"", WS_AC_NO_CODE);
            CEP.TRC(SCCGWA, WS_AC_NO_CODE);
            CEP.TRC(SCCGWA, WS_AC_NO_CODE.WS_AC_RAND);
            WS_SEQ_NAME.WS_SEQ_AC_FLG = BPCSACOB.DATA.AC_FLG;
            WS_SEQ_NAME.WS_SEQ_AC_TYP = BPCSACOB.DATA.AC_TYPE;
            WS_SEQ_NAME.WS_SEQ_AC_RAND = WS_AC_NO_CODE.WS_AC_RAND;
            BPRSEQ.KEY.TYPE = "ACSEQ";
            BPRSEQ.KEY.NAME = IBS.CLS2CPY(SCCGWA, WS_SEQ_NAME);
            CEP.TRC(SCCGWA, BPRSEQ.KEY.TYPE);
            CEP.TRC(SCCGWA, BPRSEQ.KEY.NAME);
            T000_READ_BPTSEQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
            CEP.TRC(SCCGWA, WS_AC_NO_CODE.WS_AC_SEQ);
            WS_AC_SEQ_OLD = (int) BPRSEQ.SEQ_NO;
            CEP.TRC(SCCGWA, WS_AC_SEQ_OLD);
            if (WS_AC_NO_CODE.WS_AC_SEQ <= WS_AC_SEQ_OLD) {
                CEP.TRC(SCCGWA, "M-BP-SEQ-HAS-USED");
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_SEQ_HAS_USED);
            }
        }
        for (WS_I = 1; WS_BROWSE_CONTINUE != 'N' 
            && WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            R000_OUTPUT_DATA_FMT();
            if (pgmRtn) return;
            T000_READNEXT_BPTACOBL();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_I);
        if (( WS_I - 1 ) != 0) {
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = "BPB04";
            SCCFMT.DATA_PTR = BPCO065;
            SCCFMT.DATA_LEN = 640;
            CEP.TRC(SCCGWA, SCCFMT.DATA_PTR);
            CEP.TRC(SCCGWA, BPCO065);
            IBS.FMT(SCCGWA, SCCFMT);
            CEP.TRC(SCCGWA, "FMT-OUTPUT");
        }
        T000_ENDBR_BPTACOBL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "ENDBR");
    }
    public void T000_STARTBR_FMT() throws IOException,SQLException,Exception {
        BPTACOBL_BR.rp = new DBParm();
        BPTACOBL_BR.rp.TableName = "BPTACOBL";
        BPTACOBL_BR.rp.where = "( :BPRACOBL.KEY.AC_FLG = ' ' "
            + "OR AC_FLG = :BPRACOBL.KEY.AC_FLG ) "
            + "AND ( :BPRACOBL.KEY.AC_TYPE = ' ' "
            + "OR AC_TYPE = :BPRACOBL.KEY.AC_TYPE ) "
            + "AND ( :BPRACOBL.CI_NO = ' ' "
            + "OR CI_NO = :BPRACOBL.CI_NO ) "
            + "AND ( :BPRACOBL.AC_NO = ' ' "
            + "OR AC_NO = :BPRACOBL.AC_NO ) "
            + "AND ( :BPRACOBL.KEY.AC_NO_SEQ = 0 "
            + "OR AC_NO_SEQ = :BPRACOBL.KEY.AC_NO_SEQ ) "
            + "AND ( :BPRACOBL.USED_FLG = ' ' "
            + "OR USED_FLG = :BPRACOBL.USED_FLG )";
        IBS.STARTBR(SCCGWA, BPRACOBL, this, BPTACOBL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void R000_OUTPUT_DATA_FMT() throws IOException,SQLException,Exception {
        BPCO065.AC_DATA[WS_I-1].AC_TYPE = BPRACOBL.KEY.AC_TYPE;
        BPCO065.AC_DATA[WS_I-1].CI_NO = BPRACOBL.CI_NO;
        BPCO065.AC_DATA[WS_I-1].ACNO_SEQ = BPRACOBL.KEY.AC_NO_SEQ;
        BPCO065.AC_DATA[WS_I-1].ACNO_AC = BPRACOBL.AC_NO;
        BPCO065.AC_DATA[WS_I-1].USED_FLG = BPRACOBL.USED_FLG;
        BPCO065.AC_DATA[WS_I-1].USED_DATE = BPRACOBL.USED_DATE;
        CEP.TRC(SCCGWA, BPCO065.AC_DATA[WS_I-1].AC_TYPE);
        CEP.TRC(SCCGWA, BPCO065.AC_DATA[WS_I-1].CI_NO);
        CEP.TRC(SCCGWA, BPCO065.AC_DATA[WS_I-1].ACNO_SEQ);
        CEP.TRC(SCCGWA, BPCO065.AC_DATA[WS_I-1].ACNO_AC);
        CEP.TRC(SCCGWA, BPCO065.AC_DATA[WS_I-1].USED_FLG);
        CEP.TRC(SCCGWA, BPCO065.AC_DATA[WS_I-1].USED_DATE);
    }
    public void R000_WRITE_PAGE_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 180;
        SCCMPAG.SCR_ROW_CNT = K_PAGE_ROWS;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_AC_TYPE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BROWSE_BPTACOBL");
        if (WS_USED_FLG != ' ') {
            BPTACOBL_BR.rp = new DBParm();
            BPTACOBL_BR.rp.TableName = "BPTACOBL";
            BPTACOBL_BR.rp.where = "AC_FLG = :WS_AC_FLG "
                + "AND AC_TYPE = :WS_AC_TYPE "
                + "AND USED_FLG = :WS_USED_FLG";
            IBS.STARTBR(SCCGWA, BPRACOBL, this, BPTACOBL_BR);
        } else {
            BPTACOBL_BR.rp = new DBParm();
            BPTACOBL_BR.rp.TableName = "BPTACOBL";
            BPTACOBL_BR.rp.where = "AC_FLG = :WS_AC_FLG "
                + "AND AC_TYPE = :WS_AC_TYPE";
            IBS.STARTBR(SCCGWA, BPRACOBL, this, BPTACOBL_BR);
        }
    }
    public void T000_STARTBR_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BROWSE_BPTACOBL");
        if (WS_USED_FLG != ' ') {
            BPTACOBL_BR.rp = new DBParm();
            BPTACOBL_BR.rp.TableName = "BPTACOBL";
            BPTACOBL_BR.rp.where = "CI_NO = :WS_CI_NO "
                + "AND USED_FLG = :WS_USED_FLG";
            IBS.STARTBR(SCCGWA, BPRACOBL, this, BPTACOBL_BR);
        } else {
            BPTACOBL_BR.rp = new DBParm();
            BPTACOBL_BR.rp.TableName = "BPTACOBL";
            BPTACOBL_BR.rp.where = "CI_NO = :WS_CI_NO";
            IBS.STARTBR(SCCGWA, BPRACOBL, this, BPTACOBL_BR);
        }
    }
    public void T000_STARTBR_ACNO_SEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BROWSE_BPTACOBL");
        if (WS_USED_FLG != ' ') {
            BPTACOBL_BR.rp = new DBParm();
            BPTACOBL_BR.rp.TableName = "BPTACOBL";
            BPTACOBL_BR.rp.where = "AC_NO_SEQ = :WS_ACNO_SEQ "
                + "AND USED_FLG = :WS_USED_FLG";
            IBS.STARTBR(SCCGWA, BPRACOBL, this, BPTACOBL_BR);
        } else {
            BPTACOBL_BR.rp = new DBParm();
            BPTACOBL_BR.rp.TableName = "BPTACOBL";
            BPTACOBL_BR.rp.where = "AC_NO_SEQ = :WS_ACNO_SEQ";
            IBS.STARTBR(SCCGWA, BPRACOBL, this, BPTACOBL_BR);
        }
    }
    public void T000_READ_AC_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BROWSE_BPTACOBL");
        T000_READ_BPTACOBL();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTACOBL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_TYPE);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_NO_SEQ);
        BPTACOBL_RD = new DBParm();
        BPTACOBL_RD.TableName = "BPTACOBL";
        BPTACOBL_RD.where = "AC_NO = :WS_AC_NO";
        IBS.READ(SCCGWA, BPRACOBL, this, BPTACOBL_RD);
    }
    public void T000_READ_BPTACOBL1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_FLG);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_TYPE);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_NO_SEQ);
        BPTACOBL_RD = new DBParm();
        BPTACOBL_RD.TableName = "BPTACOBL";
        BPTACOBL_RD.where = "AC_FLG = :BPRACOBL.KEY.AC_FLG "
            + "AND AC_TYPE = :BPRACOBL.KEY.AC_TYPE "
            + "AND AC_NO_SEQ = :BPRACOBL.KEY.AC_NO_SEQ";
        IBS.READ(SCCGWA, BPRACOBL, this, BPTACOBL_RD);
    }
    public void T000_DELETE_BPTACOBL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_TYPE);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_NO_SEQ);
        BPTACOBL_RD = new DBParm();
        BPTACOBL_RD.TableName = "BPTACOBL";
        IBS.DELETE(SCCGWA, BPRACOBL, BPTACOBL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCSACOB.RC);
        }
    }
    public void T000_WRITE_BPTACOBL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_TYPE);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_NO_SEQ);
        BPTACOBL_RD = new DBParm();
        BPTACOBL_RD.TableName = "BPTACOBL";
        BPTACOBL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRACOBL, BPTACOBL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY, BPCSACOB.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTACOBL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void t000_READNEXT_BPTACOBL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READNEXT_BPTACOBL");
        IBS.READNEXT(SCCGWA, BPRACOBL, this, BPTACOBL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BROWSE_CONTINUE = 'N';
        } else {
            WS_BROWSE_CONTINUE = 'N';
        }
    }
    public void T000_READUPDATE_BPTACOBL() throws IOException,SQLException,Exception {
        BPTACOBL_RD = new DBParm();
        BPTACOBL_RD.TableName = "BPTACOBL";
        BPTACOBL_RD.upd = true;
        IBS.READ(SCCGWA, BPRACOBL, BPTACOBL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCSACOB.RC);
        } else {
        }
    }
    public void T000_ENDBR_BPTACOBL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ENDBR_BPTACOBL");
        IBS.ENDBR(SCCGWA, BPTACOBL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_BPTSEQ() throws IOException,SQLException,Exception {
        BPTSEQ_RD = new DBParm();
        BPTSEQ_RD.TableName = "BPTSEQ";
        BPTSEQ_RD.where = "TYPE = :BPRSEQ.KEY.TYPE "
            + "AND NAME = :BPRSEQ.KEY.NAME";
        BPTSEQ_RD.fst = true;
        IBS.READ(SCCGWA, BPRSEQ, this, BPTSEQ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void R000_TRANS_TABLE_DATA() throws IOException,SQLException,Exception {
        BPCSACOB.DATA.AC_FLG = BPRACOBL.KEY.AC_FLG;
        BPCSACOB.DATA.AC_TYPE = BPRACOBL.KEY.AC_TYPE;
        BPCSACOB.DATA.AC_NO = BPRACOBL.AC_NO;
        BPCSACOB.DATA.CI_NO = BPRACOBL.CI_NO;
        BPCSACOB.DATA.AC_NO_SEQ = BPRACOBL.KEY.AC_NO_SEQ;
        BPCSACOB.DATA.USED_FLG = BPRACOBL.USED_FLG;
        BPCSACOB.DATA.USED_DATE = BPRACOBL.USED_DATE;
        BPCSACOB.DATA.CREATE_BR = BPRACOBL.CREATE_BR;
        BPCSACOB.DATA.MAKER_ID = BPRACOBL.CREATE_TLR;
        BPCSACOB.DATA.CHECKER_ID = BPRACOBL.CREATE_SUP;
        BPCSACOB.DATA.LAST_MAKER = BPRACOBL.LAST_TLR;
        BPCSACOB.DATA.LAST_CHECKER = BPRACOBL.LAST_SUP;
        BPCSACOB.DATA.REMARK = BPRACOBL.REMARK;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.REMARK);
        BPCSACOB.DATA.EFF_DATE = BPRACOBL.EFF_DATE;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.EFF_DATE);
        BPCSACOB.DATA.EXP_DATE = BPRACOBL.EXP_DATE;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.EXP_DATE);
    }
    public void R000_TRANS_TABLE_DATA1() throws IOException,SQLException,Exception {
        WS_DATA.WS1_AC_FLG = BPRACOBL.KEY.AC_FLG;
        WS_DATA.WS1_AC_TYPE = BPRACOBL.KEY.AC_TYPE;
        WS_DATA.WS1_AC_NO = BPRACOBL.AC_NO;
        WS_DATA.WS1_CI_NO = BPRACOBL.CI_NO;
        WS_DATA.WS1_AC_NO_SEQ = BPRACOBL.KEY.AC_NO_SEQ;
        WS_DATA.WS1_USED_FLG = BPRACOBL.USED_FLG;
        WS_DATA.WS1_USED_DATE = BPRACOBL.USED_DATE;
        WS_DATA.WS1_REMARK = BPRACOBL.REMARK;
    }
    public void R000_TRANS_DATA_FOR_CREATE() throws IOException,SQLException,Exception {
        BPRACOBL.KEY.AC_FLG = BPCSACOB.DATA.AC_FLG;
        BPRACOBL.KEY.AC_TYPE = BPCSACOB.DATA.AC_TYPE;
        BPRACOBL.AC_NO = BPCSACOB.DATA.AC_NO;
        BPRACOBL.CI_NO = BPCSACOB.DATA.CI_NO;
        BPRACOBL.KEY.AC_NO_SEQ = BPCSACOB.DATA.AC_NO_SEQ;
        BPRACOBL.USED_FLG = 'N';
        if (BPCSACOB.DATA.CREATE_BR != 0) {
            BPRACOBL.CREATE_BR = BPCSACOB.DATA.CREATE_BR;
        } else {
            BPRACOBL.CREATE_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        BPRACOBL.CREATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRACOBL.CREATE_SUP = SCCGMSG.SUP1_ID;
        BPRACOBL.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRACOBL.LAST_SUP = SCCGMSG.SUP1_ID;
        BPRACOBL.REMARK = BPCSACOB.DATA.REMARK;
        BPRACOBL.EFF_DATE = BPCSACOB.DATA.EFF_DATE;
        BPRACOBL.EXP_DATE = BPCSACOB.DATA.EXP_DATE;
    }
    public void R000_WRITE_PAGE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCSACOB.DATA);
        SCCMPAG.DATA_LEN = 180;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZACCU_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CI_CIZACCU_CN, CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSACOB.RC);
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        CEP.TRC(SCCGWA, BPCCGAC.RC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CI_CIZCUST_CN, CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSACOB.RC);
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
