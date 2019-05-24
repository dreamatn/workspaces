package com.hisun.BP;

import com.hisun.SC.*;
import java.text.DecimalFormat;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSPLMT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DecimalFormat df;
    DBParm BPTPOSH_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "02H71";
    String K_OUTPUT_FMT1 = "CIX01";
    String K_BPRPLMT = "BPRPLMT ";
    String K_HIS_ADD_RMKS = "ADD LARGE AMOUNT LIMIT";
    String K_HIS_MOD_RMKS = "CHANGE LARGE AMOUNT LIMIT";
    String K_HIS_DEL_RMKS = "DELETE LARGE AMOUNT LIMIT";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    int K_MAX_DT = 99999999;
    String K_BPRPOSH = "BPRPOSH ";
    String K_CCY_CNY = "156";
    String WS_ERR_MSG = " ";
    String WS_REC = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";
    short WS_REC_LEN = 0;
    String WS_TABLE_NAME = " ";
    BPZSPLMT_WS_RTN_DATA_A WS_RTN_DATA_A = new BPZSPLMT_WS_RTN_DATA_A();
    int WS_OLD_BR = 0;
    String WS_OLD_CCY = " ";
    double WS_OLD_UPP_AMT = 0;
    double WS_OLD_LOW_AMT = 0;
    BPZSPLMT_WS_POSF_DATA WS_POSF_DATA = new BPZSPLMT_WS_POSF_DATA();
    char WS_TABLE_REC = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRPLMT BPRPLMT = new BPRPLMT();
    BPCRPLMT BPCRPLMT = new BPCRPLMT();
    BPCIPLMT BPCIPLMT = new BPCIPLMT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRPLMT BPRFHISO = new BPRPLMT();
    BPRPLMT BPRFHISN = new BPRPLMT();
    SCCBSP SCCBSP = new SCCBSP();
    BPRPOSH BPRPOSH = new BPRPOSH();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSPLMT BPCSPLMT;
    public void MP(SCCGWA SCCGWA, BPCSPLMT BPCSPLMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSPLMT = BPCSPLMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSPLMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSPLMT.FUNC);
        CEP.TRC(SCCGWA, BPCSPLMT);
        if (BPCSPLMT.FUNC == 'I') {
            B001_INQ_BPTPLMT_PROC();
            if (pgmRtn) return;
        } else if (BPCSPLMT.FUNC == 'A') {
            B002_ADD_BPTPLMT_PROC();
            if (pgmRtn) return;
        } else if (BPCSPLMT.FUNC == 'U') {
            B003_UPD_BPTPLMT_PROC();
            if (pgmRtn) return;
        } else if (BPCSPLMT.FUNC == 'D') {
            B004_DEL_BPTPLMT_PROC();
            if (pgmRtn) return;
        } else if (BPCSPLMT.FUNC == 'B') {
            B005_BRW_BPTPLMT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSPLMT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B002_ADD_BPTPLMT_PROC() throws IOException,SQLException,Exception {
        B010_CHK_CCY_PROC();
        if (pgmRtn) return;
        B020_CHK_RECORD_PROC();
        if (pgmRtn) return;
        if (BPCIPLMT.RETURN_INFO == 'N') {
        } else if (BPCIPLMT.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRPLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCRPLMT);
        IBS.init(SCCGWA, BPRPLMT);
        BPCRPLMT.FUNC = 'A';
        BPRPLMT.KEY.BR = BPCSPLMT.KEY.BR;
        BPRPLMT.KEY.CCY = BPCSPLMT.KEY.CCY;
        BPRPLMT.UPP_AMT = BPCSPLMT.UPP_AMT;
        BPRPLMT.LOW_AMT = BPCSPLMT.LOW_AMT;
        BPRPLMT.ACC_CAPITAL = BPCSPLMT.ACC_CAPITAL;
        BPRPLMT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRPLMT.REC_PTR = BPRPLMT;
        BPCRPLMT.REC_LEN = 116;
        S000_CALL_BPZRPLMT();
        if (pgmRtn) return;
        if (BPCRPLMT.RETURN_INFO == 'F') {
        } else if (BPCRPLMT.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRPLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B010_CHK_CCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSPLMT.KEY.CCY);
        if (BPCSPLMT.KEY.CCY.trim().length() > 0 
            && BPCSPLMT.KEY.CCY.charAt(0) != 0X00) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPCSPLMT.KEY.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCQCCY.RC.RTNCODE != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK BPZQCCY,RC-CODE=" + IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHK_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIPLMT);
        BPCIPLMT.KEY.CCY = BPCSPLMT.KEY.CCY;
        BPCIPLMT.KEY.BR = BPCSPLMT.KEY.BR;
        S000_CALL_BPZIPLMT();
        if (pgmRtn) return;
    }
    public void B001_INQ_BPTPLMT_PROC() throws IOException,SQLException,Exception {
        B010_CHK_CCY_PROC();
        if (pgmRtn) return;
        B060_READ_PROC();
        if (pgmRtn) return;
        if (BPCRPLMT.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "&&NOT FOUND&&");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else if (BPCRPLMT.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "&&NORMAL&&");
            BPCSPLMT.KEY.BR = BPRPLMT.KEY.BR;
            BPCSPLMT.KEY.CCY = BPRPLMT.KEY.CCY;
            BPCSPLMT.UPP_AMT = BPRPLMT.UPP_AMT;
            BPCSPLMT.LOW_AMT = BPRPLMT.LOW_AMT;
            BPCSPLMT.ACC_CAPITAL = BPRPLMT.ACC_CAPITAL;
        } else {
            CEP.TRC(SCCGWA, "&&OTHER&&");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRPLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
        }
    }
    public void B003_UPD_BPTPLMT_PROC() throws IOException,SQLException,Exception {
        B010_CHK_CCY_PROC();
        if (pgmRtn) return;
        B040_READUPD_PROC();
        if (pgmRtn) return;
        if (BPCRPLMT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else if (BPCRPLMT.RETURN_INFO == 'F') {
            WS_OLD_BR = BPRPLMT.KEY.BR;
            WS_OLD_CCY = BPRPLMT.KEY.CCY;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRPLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        B050_UPDATE_PROC();
        if (pgmRtn) return;
        if (BPCRPLMT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRPLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B060_READ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPLMT);
        IBS.init(SCCGWA, BPCRPLMT);
        BPRPLMT.KEY.CCY = BPCSPLMT.KEY.CCY;
        BPRPLMT.KEY.BR = BPCSPLMT.KEY.BR;
        BPCRPLMT.FUNC = 'I';
        BPCRPLMT.REC_PTR = BPRPLMT;
        BPCRPLMT.REC_LEN = 116;
        CEP.TRC(SCCGWA, BPCRPLMT.REC_LEN);
        S000_CALL_BPZRPLMT();
        if (pgmRtn) return;
    }
    public void B040_READUPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPLMT);
        IBS.init(SCCGWA, BPCRPLMT);
        BPRPLMT.KEY.CCY = BPCSPLMT.KEY.CCY;
        BPRPLMT.KEY.BR = BPCSPLMT.KEY.BR;
        BPCRPLMT.FUNC = 'R';
        BPCRPLMT.REC_PTR = BPRPLMT;
        BPCRPLMT.REC_LEN = 116;
        S000_CALL_BPZRPLMT();
        if (pgmRtn) return;
    }
    public void B050_UPDATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPLMT);
        IBS.init(SCCGWA, BPCRPLMT);
        BPRPLMT.KEY.CCY = BPCSPLMT.KEY.CCY;
        BPRPLMT.KEY.BR = BPCSPLMT.KEY.BR;
        BPRPLMT.UPP_AMT = BPCSPLMT.UPP_AMT;
        BPRPLMT.LOW_AMT = BPCSPLMT.LOW_AMT;
        BPRPLMT.ACC_CAPITAL = BPCSPLMT.ACC_CAPITAL;
        BPRPLMT.CRT_TLR = BPCSPLMT.CRT_TLR;
        BPRPLMT.LST_UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRPLMT.FUNC = 'U';
        BPCRPLMT.REC_PTR = BPRPLMT;
        BPCRPLMT.REC_LEN = 116;
        S000_CALL_BPZRPLMT();
        if (pgmRtn) return;
    }
    public void B004_DEL_BPTPLMT_PROC() throws IOException,SQLException,Exception {
        B010_CHK_CCY_PROC();
        if (pgmRtn) return;
        B040_READUPD_PROC();
        if (pgmRtn) return;
        if (BPCRPLMT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else if (BPCRPLMT.RETURN_INFO == 'F') {
            WS_OLD_BR = BPRPLMT.KEY.BR;
            WS_OLD_CCY = BPRPLMT.KEY.CCY;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRPLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSPLMT.ACC_CAPITAL);
        CEP.TRC(SCCGWA, BPCSPLMT.KEY.CCY);
        if (BPCSPLMT.ACC_CAPITAL != 0) {
            B092_GET_SEQUENCE_NO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
            if (!BPCSPLMT.KEY.CCY.equalsIgnoreCase(K_CCY_CNY)) {
                CEP.TRC(SCCGWA, "ADD FILE");
                B090_GEN_PLMT_FILE();
                if (pgmRtn) return;
                B091_ADD_POS_HIST();
                if (pgmRtn) return;
            } else {
                B091_ADD_POS_HIST();
                if (pgmRtn) return;
            }
        }
        B080_DELETE_PROC();
        if (pgmRtn) return;
        if (BPCRPLMT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRPLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B080_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPLMT);
        IBS.init(SCCGWA, BPCRPLMT);
        BPRPLMT.KEY.CCY = BPCSPLMT.KEY.CCY;
        BPRPLMT.KEY.BR = BPCSPLMT.KEY.BR;
        BPCRPLMT.FUNC = 'D';
        BPCRPLMT.REC_PTR = BPRPLMT;
        BPCRPLMT.REC_LEN = 116;
        S000_CALL_BPZRPLMT();
        if (pgmRtn) return;
    }
    public void B005_BRW_BPTPLMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 1511;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B010_STABR_BPTPLMT_PROC();
        if (pgmRtn) return;
        B010_READ_NEXT_PLMT_PROC();
        if (pgmRtn) return;
        while (BPCRPLMT.RETURN_INFO != 'E' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_REL_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B010_READ_NEXT_PLMT_PROC();
            if (pgmRtn) return;
        }
        B010_END_BROWSE_PLMT_PROC();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_RTN_DATA_A);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 68;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_REL_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        WS_REC = " ";
        WS_RTN_DATA_A.WS_R_CCY = BPRPLMT.KEY.CCY;
        WS_RTN_DATA_A.WS_R_BR = BPRPLMT.KEY.BR;
        WS_RTN_DATA_A.WS_R_UPP_AMT = BPRPLMT.UPP_AMT;
        WS_RTN_DATA_A.WS_R_LOW_AMT = BPRPLMT.LOW_AMT;
        CEP.TRC(SCCGWA, BPRPLMT.UPP_AMT);
        CEP.TRC(SCCGWA, BPRPLMT.LOW_AMT);
        CEP.TRC(SCCGWA, WS_RTN_DATA_A.WS_R_UPP_AMT);
        CEP.TRC(SCCGWA, WS_RTN_DATA_A.WS_R_LOW_AMT);
        WS_RTN_DATA_A.WS_R_ACC_CAP = BPRPLMT.ACC_CAPITAL;
        WS_RTN_DATA_A.WS_R_CRT_TLR = BPRPLMT.CRT_TLR;
        CEP.TRC(SCCGWA, WS_RTN_DATA_A.WS_R_CCY);
        CEP.TRC(SCCGWA, WS_RTN_DATA_A.WS_R_BR);
        WS_REC = IBS.CLS2CPY(SCCGWA, WS_RTN_DATA_A);
        WS_REC_LEN = 68;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void B010_STABR_BPTPLMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRPLMT);
        IBS.init(SCCGWA, BPRPLMT);
        BPCRPLMT.FUNC = 'B';
        BPCRPLMT.OPT = 'C';
        if (BPCSPLMT.KEY.CCY.trim().length() > 0) {
            BPCRPLMT.STR_CCY = BPCSPLMT.KEY.CCY;
            BPCRPLMT.END_CCY = BPCSPLMT.KEY.CCY;
        } else {
            BPCRPLMT.STR_CCY = "" + 0X00;
            JIBS_tmp_int = BPCRPLMT.STR_CCY.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) BPCRPLMT.STR_CCY = "0" + BPCRPLMT.STR_CCY;
            BPCRPLMT.END_CCY = "" + 0XFF;
            JIBS_tmp_int = BPCRPLMT.END_CCY.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) BPCRPLMT.END_CCY = "0" + BPCRPLMT.END_CCY;
        }
        if (BPCSPLMT.KEY.BR != 0) {
            BPCRPLMT.STR_BR = BPCSPLMT.KEY.BR;
            BPCRPLMT.END_BR = BPCSPLMT.KEY.BR;
        } else {
            BPCRPLMT.STR_BR = 0;
            BPCRPLMT.END_BR = 999999;
        }
        BPCRPLMT.REC_PTR = BPRPLMT;
        BPCRPLMT.REC_LEN = 116;
        S000_CALL_BPZRPLMT();
        if (pgmRtn) return;
    }
    public void B010_READ_NEXT_PLMT_PROC() throws IOException,SQLException,Exception {
        BPCRPLMT.FUNC = 'B';
        BPCRPLMT.OPT = 'R';
        S000_CALL_BPZRPLMT();
        if (pgmRtn) return;
    }
    public void B010_END_BROWSE_PLMT_PROC() throws IOException,SQLException,Exception {
        BPCRPLMT.FUNC = 'B';
        BPCRPLMT.OPT = 'E';
        S000_CALL_BPZRPLMT();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZIPLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-PCCY", BPCIPLMT);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZRPLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAIN-PLMT", BPCRPLMT);
        CEP.TRC(SCCGWA, BPCRPLMT.RETURN_INFO);
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_REC;
        SCCMPAG.DATA_LEN = WS_REC_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B090_GEN_PLMT_FILE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCBSP);
        CEP.TRC(SCCGWA, BPCSPLMT.ACC_CAPITAL);
        SCCBSP.AP_PROC = "BPPONL02";
        WS_POSF_DATA.WS_POSF_PARM = "PARM=";
        WS_POSF_DATA.WS_POSF_JRNNO = BPCSGSEQ.SEQ;
        WS_POSF_DATA.WS_POSF_BR = BPCSPLMT.KEY.BR;
        WS_POSF_DATA.WS_POSF_CCY = BPCSPLMT.KEY.CCY;
        WS_POSF_DATA.WS_POSF_REQPARTY = '1';
        df = new DecimalFormat("000000000000.00");
        WS_POSF_DATA.WS_POSF_ACC_CAPITAL = df.format(BPCSPLMT.ACC_CAPITAL);
        WS_POSF_DATA.WS_POSF_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCBSP.PARM_DA1 = IBS.CLS2CPY(SCCGWA, WS_POSF_DATA);
        CEP.TRC(SCCGWA, WS_POSF_DATA.WS_POSF_ACC_CAPITAL);
        CEP.TRC(SCCGWA, WS_POSF_DATA.WS_POSF_JRNNO);
        CEP.TRC(SCCGWA, SCCBSP.PARM_DA1);
        S000_CALL_SCZOBSP();
        if (pgmRtn) return;
    }
    public void B091_ADD_POS_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPOSH);
        CEP.TRC(SCCGWA, BPCSPLMT.ACC_CAPITAL);
        BPRPOSH.KEY.JRN_NO = BPCSGSEQ.SEQ;
        BPRPOSH.KEY.BR = BPCSPLMT.KEY.BR;
        BPRPOSH.KEY.CCY = BPCSPLMT.KEY.CCY;
        BPRPOSH.KEY.REQPARTY = '1';
        BPRPOSH.KEY.ACC_CAPITAL = BPCSPLMT.ACC_CAPITAL;
        BPRPOSH.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPOSH.POS_FLG = '1';
        CEP.TRC(SCCGWA, BPRPOSH.KEY.ACC_CAPITAL);
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRPOSH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B092_GET_SEQUENCE_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "TIKNO";
        BPCSGSEQ.CODE = "SEQ1";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        BPTPOSH_RD = new DBParm();
        BPTPOSH_RD.TableName = "BPTPOSH";
        IBS.WRITE(SCCGWA, BPRPOSH, BPTPOSH_RD);
    }
    public void S000_CALL_SCZOBSP() throws IOException,SQLException,Exception {
        SCZOBSP SCZOBSP = new SCZOBSP();
        SCZOBSP.MP(SCCGWA, SCCBSP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LINK_BSP_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
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
