package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.VT.WFCCAPV;
import com.hisun.WF.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class FXZSLLMT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "02H71";
    String K_OUTPUT_FMT1 = "CIX01";
    String K_FXRLLMT = "FXRLLMT ";
    String K_HIS_ADD_RMKS = "ADD LARGE AMOUNT LIMIT";
    String K_HIS_MOD_RMKS = "CHANGE LARGE AMOUNT LIMIT";
    String K_HIS_DEL_RMKS = "DELETE LARGE AMOUNT LIMIT";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    int K_MAX_DT = 99999999;
    String WS_ERR_MSG = " ";
    String WS_REC = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";
    short WS_REC_LEN = 0;
    String WS_TABLE_NAME = " ";
    FXZSLLMT_WS_RTN_DATA_A WS_RTN_DATA_A = new FXZSLLMT_WS_RTN_DATA_A();
    String WS_OLD_TRCHNL = " ";
    double WS_OLD_B_AMT = 0;
    double WS_OLD_S_AMT = 0;
    int WS_OLD_CRE_DT = 0;
    String WS_OLD_CRT_TLR = " ";
    FXZSLLMT_WS_REF_DATA WS_REF_DATA = new FXZSLLMT_WS_REF_DATA();
    char WS_TABLE_REC = ' ';
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    FXRLLMT FXRLLMT = new FXRLLMT();
    FXCRLLMT FXCRLLMT = new FXCRLLMT();
    FXCILLMT FXCILLMT = new FXCILLMT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    FXRLLMT FXRFHISO = new FXRLLMT();
    FXRLLMT FXRFHISN = new FXRLLMT();
    WFCCAPV WFCCAPV = new WFCCAPV();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    FXCSLLMT FXCSLLMT;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA, FXCSLLMT FXCSLLMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FXCSLLMT = FXCSLLMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FXZSLLMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LALALA");
        CEP.TRC(SCCGWA, "MAMAMA");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, "BUBUBU");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXCSLLMT.FUNC);
        CEP.TRC(SCCGWA, "DEV001");
        if (FXCSLLMT.FUNC == 'Q') {
            B001_INQ_EXTLLMT_PROC();
            if (pgmRtn) return;
        } else if (FXCSLLMT.FUNC == 'A') {
            B002_ADD_FXTLLMT_PROC();
            if (pgmRtn) return;
            B210_SET_WF_AP_INFO();
            if (pgmRtn) return;
        } else if (FXCSLLMT.FUNC == 'U') {
            B003_UPD_EXTLLMT_PROC();
            if (pgmRtn) return;
            B210_SET_WF_AP_INFO();
            if (pgmRtn) return;
        } else if (FXCSLLMT.FUNC == 'D') {
            B004_DEL_EXTLLMT_PROC();
            if (pgmRtn) return;
            B210_SET_WF_AP_INFO();
            if (pgmRtn) return;
        } else if (FXCSLLMT.FUNC == 'B') {
            B005_BRW_EXTLLMT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + FXCSLLMT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B001_INQ_EXTLLMT_PROC() throws IOException,SQLException,Exception {
        B010_CHK_CCY_PROC();
        if (pgmRtn) return;
        B020_CHK_RECORD_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, FXCILLMT.RETURN_INFO);
        if (FXCILLMT.RETURN_INFO == 'N') {
            FXCSLLMT.RC.RC_CODE = 3;
        } else if (FXCILLMT.RETURN_INFO == 'F') {
            FXCSLLMT.B_AMT = FXCILLMT.BAMT;
            FXCSLLMT.S_AMT = FXCILLMT.SAMT;
        }
    }
    public void B002_ADD_FXTLLMT_PROC() throws IOException,SQLException,Exception {
        B010_CHK_CCY_PROC();
        if (pgmRtn) return;
        B020_CHK_RECORD_PROC();
        if (pgmRtn) return;
        if (FXCILLMT.RETURN_INFO == 'N') {
        } else if (FXCILLMT.RETURN_INFO == 'F') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_FXRLLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, FXCRLLMT);
        IBS.init(SCCGWA, FXRLLMT);
        FXCRLLMT.FUNC = 'A';
        FXRLLMT.KEY.CCY = FXCSLLMT.KEY.CCY;
        FXRLLMT.KEY.TRCHNL = FXCSLLMT.KEY.TRCHNL;
        FXRLLMT.BLIMIT_AMT = FXCSLLMT.B_AMT;
        FXRLLMT.SLIMIT_AMT = FXCSLLMT.S_AMT;
        FXRLLMT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        FXRLLMT.CRE_DT = SCCGWA.COMM_AREA.TR_DATE;
        FXCRLLMT.REC_PTR = FXRLLMT;
        FXCRLLMT.REC_LEN = 98;
        S000_CALL_FXZRLLMT();
        if (pgmRtn) return;
        if (FXCRLLMT.RETURN_INFO == 'F') {
        } else if (FXCRLLMT.RETURN_INFO == 'D') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_FXRLLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B010_CHK_CCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXCSLLMT.KEY.CCY);
        if (FXCSLLMT.KEY.CCY.trim().length() > 0 
            && FXCSLLMT.KEY.CCY.charAt(0) != 0X00) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = FXCSLLMT.KEY.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_NOTFND);
        }
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHK_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXCSLLMT.KEY.CCY);
        CEP.TRC(SCCGWA, FXCSLLMT.KEY.TRCHNL);
        IBS.init(SCCGWA, FXCILLMT);
        FXCILLMT.KEY.CCY = FXCSLLMT.KEY.CCY;
        FXCILLMT.KEY.TRCHNL = FXCSLLMT.KEY.TRCHNL;
        S000_CALL_FXZILLMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, FXCILLMT.BAMT);
        CEP.TRC(SCCGWA, FXCILLMT.SAMT);
    }
    public void B003_UPD_EXTLLMT_PROC() throws IOException,SQLException,Exception {
        B010_CHK_CCY_PROC();
        if (pgmRtn) return;
        B040_READUPD_PROC();
        if (pgmRtn) return;
        if (FXCRLLMT.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_NOTFND);
        } else if (FXCRLLMT.RETURN_INFO == 'F') {
            WS_OLD_B_AMT = FXRLLMT.BLIMIT_AMT;
            WS_OLD_S_AMT = FXRLLMT.SLIMIT_AMT;
            WS_OLD_CRE_DT = FXRLLMT.CRE_DT;
            WS_OLD_CRT_TLR = FXRLLMT.CRT_TLR;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_FXRLLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        B050_UPDATE_PROC();
        if (pgmRtn) return;
        if (FXCRLLMT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_FXRLLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B040_READUPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRLLMT);
        IBS.init(SCCGWA, FXCRLLMT);
        FXRLLMT.KEY.CCY = FXCSLLMT.KEY.CCY;
        FXRLLMT.KEY.TRCHNL = FXCSLLMT.KEY.TRCHNL;
        FXCRLLMT.FUNC = 'R';
        FXCRLLMT.REC_PTR = FXRLLMT;
        FXCRLLMT.REC_LEN = 98;
        S000_CALL_FXZRLLMT();
        if (pgmRtn) return;
    }
    public void B050_UPDATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRLLMT);
        IBS.init(SCCGWA, FXCRLLMT);
        FXRLLMT.KEY.CCY = FXCSLLMT.KEY.CCY;
        FXRLLMT.KEY.TRCHNL = FXCSLLMT.KEY.TRCHNL;
        FXRLLMT.BLIMIT_AMT = FXCSLLMT.B_AMT;
        FXRLLMT.SLIMIT_AMT = FXCSLLMT.S_AMT;
        FXRLLMT.CRE_DT = WS_OLD_CRE_DT;
        FXRLLMT.CRT_TLR = WS_OLD_CRT_TLR;
        FXRLLMT.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        FXRLLMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        FXCRLLMT.FUNC = 'U';
        FXCRLLMT.REC_PTR = FXRLLMT;
        FXCRLLMT.REC_LEN = 98;
        S000_CALL_FXZRLLMT();
        if (pgmRtn) return;
    }
    public void B004_DEL_EXTLLMT_PROC() throws IOException,SQLException,Exception {
        B010_CHK_CCY_PROC();
        if (pgmRtn) return;
        B040_READUPD_PROC();
        if (pgmRtn) return;
        if (FXCRLLMT.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_NOTFND);
        } else if (FXCRLLMT.RETURN_INFO == 'F') {
            WS_OLD_B_AMT = FXRLLMT.BLIMIT_AMT;
            WS_OLD_S_AMT = FXRLLMT.SLIMIT_AMT;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_FXRLLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        B080_DELETE_PROC();
        if (pgmRtn) return;
        if (FXCRLLMT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_FXRLLMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B080_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRLLMT);
        IBS.init(SCCGWA, FXCRLLMT);
        FXRLLMT.KEY.CCY = FXCSLLMT.KEY.CCY;
        FXRLLMT.KEY.TRCHNL = FXCSLLMT.KEY.TRCHNL;
        FXRLLMT.BLIMIT_AMT = FXCSLLMT.B_AMT;
        FXRLLMT.SLIMIT_AMT = FXCSLLMT.S_AMT;
        FXCRLLMT.FUNC = 'D';
        FXCRLLMT.REC_PTR = FXRLLMT;
        FXCRLLMT.REC_LEN = 98;
        S000_CALL_FXZRLLMT();
        if (pgmRtn) return;
    }
    public void B005_BRW_EXTLLMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 80;
        SCCSUBS.TR_CODE = 1410;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B010_STABR_EXTLLMT_PROC();
        if (pgmRtn) return;
        B010_READ_NEXT_LLMT_PROC();
        if (pgmRtn) return;
        while (FXCRLLMT.RETURN_INFO != 'E' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_REL_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B010_READ_NEXT_LLMT_PROC();
            if (pgmRtn) return;
        }
        B010_END_BROWSE_LLMT_PROC();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_RTN_DATA_A);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 40;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_REL_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        WS_REC = " ";
        WS_RTN_DATA_A.WS_R_CCY = FXRLLMT.KEY.CCY;
        WS_RTN_DATA_A.WS_R_TRCHNL = FXRLLMT.KEY.TRCHNL;
        WS_RTN_DATA_A.WS_R_B_AMT = FXRLLMT.BLIMIT_AMT;
        WS_RTN_DATA_A.WS_R_S_AMT = FXRLLMT.SLIMIT_AMT;
        CEP.TRC(SCCGWA, WS_RTN_DATA_A.WS_R_CCY);
        CEP.TRC(SCCGWA, WS_RTN_DATA_A.WS_R_TRCHNL);
        CEP.TRC(SCCGWA, WS_RTN_DATA_A.WS_R_B_AMT);
        CEP.TRC(SCCGWA, WS_RTN_DATA_A.WS_R_S_AMT);
        WS_REC = IBS.CLS2CPY(SCCGWA, WS_RTN_DATA_A);
        WS_REC_LEN = 40;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void B010_STABR_EXTLLMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCRLLMT);
        IBS.init(SCCGWA, FXRLLMT);
        FXCRLLMT.FUNC = 'B';
        FXCRLLMT.OPT = 'C';
        if (FXCSLLMT.KEY.CCY.trim().length() > 0) {
            FXCRLLMT.STR_CCY = FXCSLLMT.KEY.CCY;
            FXCRLLMT.END_CCY = FXCSLLMT.KEY.CCY;
        } else {
            FXCRLLMT.STR_CCY = "" + 0X00;
            JIBS_tmp_int = FXCRLLMT.STR_CCY.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) FXCRLLMT.STR_CCY = "0" + FXCRLLMT.STR_CCY;
            FXCRLLMT.END_CCY = "" + 0XFF;
            JIBS_tmp_int = FXCRLLMT.END_CCY.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) FXCRLLMT.END_CCY = "0" + FXCRLLMT.END_CCY;
        }
        if (FXCSLLMT.KEY.TRCHNL.trim().length() > 0) {
            FXCRLLMT.STR_TRCHNL = FXCSLLMT.KEY.TRCHNL;
            FXCRLLMT.END_TRCHNL = FXCSLLMT.KEY.TRCHNL;
        } else {
            FXCRLLMT.STR_TRCHNL = "" + 0X00;
            JIBS_tmp_int = FXCRLLMT.STR_TRCHNL.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) FXCRLLMT.STR_TRCHNL = "0" + FXCRLLMT.STR_TRCHNL;
            FXCRLLMT.END_TRCHNL = "" + 0XFF;
            JIBS_tmp_int = FXCRLLMT.END_TRCHNL.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) FXCRLLMT.END_TRCHNL = "0" + FXCRLLMT.END_TRCHNL;
        }
        FXCRLLMT.REC_PTR = FXRLLMT;
        FXCRLLMT.REC_LEN = 98;
        S000_CALL_FXZRLLMT();
        if (pgmRtn) return;
    }
    public void B010_READ_NEXT_LLMT_PROC() throws IOException,SQLException,Exception {
        FXCRLLMT.FUNC = 'B';
        FXCRLLMT.OPT = 'R';
        S000_CALL_FXZRLLMT();
        if (pgmRtn) return;
    }
    public void B010_END_BROWSE_LLMT_PROC() throws IOException,SQLException,Exception {
        FXCRLLMT.FUNC = 'B';
        FXCRLLMT.OPT = 'E';
        S000_CALL_FXZRLLMT();
        if (pgmRtn) return;
    }
    public void B210_SET_WF_AP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WFCCAPV);
        IBS.init(SCCGWA, WS_REF_DATA);
        WFCCAPV.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WFCCAPV.FLG_INFO.FLG = 'C';
        WS_REF_DATA.WS_REF_TRCHNL = FXCSLLMT.KEY.TRCHNL;
        WS_REF_DATA.WS_REF_CCY = FXCSLLMT.KEY.CCY;
        WFCCAPV.AP_REF = IBS.CLS2CPY(SCCGWA, WS_REF_DATA);
        CEP.TRC(SCCGWA, WS_REF_DATA);
    }
    public void S000_CALL_FXZILLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-INQUIRE-CCY", FXCILLMT);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_FXZRLLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-R-MAIN-LLMT", FXCRLLMT);
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
