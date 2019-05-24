package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTAXR {
    DBParm BPTTAXR_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "02H71";
    String K_OUTPUT_FMT1 = "CIX01";
    String K_BPRTAXR = "BPRTAXR ";
    String K_HIS_ADD_RMKS = "ADD TAX RATE PARAMETER";
    String K_HIS_MOD_RMKS = "CHANGE TAX RATE PARAMETER";
    String K_HIS_DEL_RMKS = "DELETE TAX RATE PARAMETER";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    int K_MAX_DT = 99999999;
    String WS_ERR_MSG = " ";
    String WS_REC = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";
    short WS_REC_LEN = 0;
    String WS_TABLE_NAME = " ";
    BPZSTAXR_WS_RTN_DATA_A WS_RTN_DATA_A = new BPZSTAXR_WS_RTN_DATA_A();
    String WS_OLD_TRCHNL = " ";
    double WS_OLD_B_AMT = 0;
    double WS_OLD_S_AMT = 0;
    BPZSTAXR_WS_REF_DATA WS_REF_DATA = new BPZSTAXR_WS_REF_DATA();
    String WS_OUT_CODE = " ";
    char WS_SEL_FLAG = ' ';
    char WS_TABLE_REC = ' ';
    char WS_READ_REC = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRTAXR BPRTAXR = new BPRTAXR();
    BPCRTAXR BPCRTAXR = new BPCRTAXR();
    BPCITAXR BPCITAXR = new BPCITAXR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRTAXR BPRFHISO = new BPRTAXR();
    BPRTAXR BPRFHISN = new BPRTAXR();
    BPCOTAXR BPCOTAXR = new BPCOTAXR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSTAXR BPCSTAXR;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA, BPCSTAXR BPCSTAXR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTAXR = BPCSTAXR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTAXR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTAXR.FUNC);
        CEP.TRC(SCCGWA, BPCSTAXR);
        if (BPCSTAXR.FUNC == 'I'
            || BPCSTAXR.FUNC == 'Q') {
            WS_OUT_CODE = "BP089";
            WS_SEL_FLAG = 'Y';
            B001_INQ_BPTTAXR_PROC();
            if (pgmRtn) return;
        } else if (BPCSTAXR.FUNC == 'A') {
            B002_ADD_BPTTAXR_PROC();
            if (pgmRtn) return;
            B210_SET_WF_AP_INFO();
            if (pgmRtn) return;
            WS_OUT_CODE = "BP089";
            WS_SEL_FLAG = 'N';
            B031_TRAN_DATA_PROC();
            if (pgmRtn) return;
        } else if (BPCSTAXR.FUNC == 'U') {
            B003_UPD_BPTTAXR_PROC();
            if (pgmRtn) return;
            B210_SET_WF_AP_INFO();
            if (pgmRtn) return;
            WS_OUT_CODE = "BP090";
            WS_SEL_FLAG = 'N';
            B031_TRAN_DATA_PROC();
            if (pgmRtn) return;
        } else if (BPCSTAXR.FUNC == 'D') {
            B004_DEL_BPTTAXR_PROC();
            if (pgmRtn) return;
            B210_SET_WF_AP_INFO();
            if (pgmRtn) return;
            WS_OUT_CODE = "BP091";
            WS_SEL_FLAG = 'N';
            B031_TRAN_DATA_PROC();
            if (pgmRtn) return;
        } else if (BPCSTAXR.FUNC == 'B') {
            B005_BRW_BPTTAXR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSTAXR.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B001_INQ_BPTTAXR_PROC() throws IOException,SQLException,Exception {
        B020_CHK_RECORD_PROC();
        if (pgmRtn) return;
        if (BPCITAXR.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "INQ NOTFND");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else if (BPCITAXR.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "INQ NORMAL");
            B031_TRAN_DATA_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "INQ OTHER");
            BPCITAXR.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B002_ADD_BPTTAXR_PROC() throws IOException,SQLException,Exception {
        B020_CHK_RECORD_PROC();
        if (pgmRtn) return;
        if (BPCITAXR.RETURN_INFO == 'N') {
        } else if (BPCITAXR.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRTAXR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCRTAXR);
        IBS.init(SCCGWA, BPRTAXR);
        BPCRTAXR.FUNC = 'A';
        BPRTAXR.KEY.CCY = BPCSTAXR.KEY.CCY;
        BPRTAXR.KEY.BR = BPCSTAXR.KEY.BR;
        BPRTAXR.KEY.TAX_TYP = BPCSTAXR.KEY.TAX_TYP;
        BPRTAXR.KEY.VAL_TYP = BPCSTAXR.KEY.VAL_TYP;
        BPRTAXR.KEY.RESIDENT = BPCSTAXR.KEY.RESIDENT;
        BPRTAXR.TAX_NAME = BPCSTAXR.TAX_NAME;
        BPRTAXR.KEY.EFF_DT = BPCSTAXR.KEY.EFF_DT;
        BPRTAXR.EXP_DT = BPCSTAXR.EXP_DT;
        BPRTAXR.TAX_VAL = BPCSTAXR.TAX_VAL;
        BPRTAXR.CREATE_DATE = BPCSTAXR.CREATE_DATE;
        BPRTAXR.CREATE_TELL = BPCSTAXR.CREATE_TELL;
        BPRTAXR.UPDTBL_DATE = BPCSTAXR.UPDTBL_DATE;
        BPRTAXR.LAST_TELL = BPCSTAXR.LAST_TELL;
        BPRTAXR.SUP_TEL1 = BPCSTAXR.SUP_TEL1;
        BPRTAXR.SUP_TEL2 = BPCSTAXR.SUP_TEL2;
        BPCRTAXR.REC_PTR = BPRTAXR;
        BPCRTAXR.REC_LEN = 181;
        S000_CALL_BPZRTAXR();
        if (pgmRtn) return;
        if (BPCRTAXR.RETURN_INFO == 'F') {
        } else if (BPCRTAXR.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRTAXR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_CHK_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCITAXR);
        BPCITAXR.KEY.CCY = BPCSTAXR.KEY.CCY;
        BPCITAXR.KEY.BR = BPCSTAXR.KEY.BR;
        BPCITAXR.KEY.EFF_DT = BPCSTAXR.KEY.EFF_DT;
        BPCITAXR.KEY.TAX_TYP = BPCSTAXR.KEY.TAX_TYP;
        BPCITAXR.KEY.VAL_TYP = BPCSTAXR.KEY.VAL_TYP;
        BPCITAXR.KEY.RESIDENT = BPCSTAXR.KEY.RESIDENT;
        CEP.TRC(SCCGWA, BPCITAXR.KEY.CCY);
        CEP.TRC(SCCGWA, BPCITAXR.KEY.BR);
        CEP.TRC(SCCGWA, BPCITAXR.KEY.EFF_DT);
        CEP.TRC(SCCGWA, BPCITAXR.KEY.TAX_TYP);
        CEP.TRC(SCCGWA, BPCITAXR.KEY.VAL_TYP);
        CEP.TRC(SCCGWA, BPCITAXR.KEY.RESIDENT);
        S000_CALL_BPZITAXR();
        if (pgmRtn) return;
    }
    public void B003_UPD_BPTTAXR_PROC() throws IOException,SQLException,Exception {
        B040_READUPD_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AAA");
        if (BPCRTAXR.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "BBB");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else if (BPCRTAXR.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "CCC");
        } else {
            CEP.TRC(SCCGWA, "DDD");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRTAXR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "EEE");
        B050_UPDATE_PROC();
        if (pgmRtn) return;
        if (BPCRTAXR.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRTAXR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B040_READUPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTAXR);
        IBS.init(SCCGWA, BPCRTAXR);
        BPRTAXR.KEY.CCY = BPCSTAXR.KEY.CCY;
        BPRTAXR.KEY.BR = BPCSTAXR.KEY.BR;
        BPRTAXR.KEY.EFF_DT = BPCSTAXR.KEY.EFF_DT;
        BPRTAXR.KEY.TAX_TYP = BPCSTAXR.KEY.TAX_TYP;
        BPRTAXR.KEY.VAL_TYP = BPCSTAXR.KEY.VAL_TYP;
        BPRTAXR.KEY.RESIDENT = BPCSTAXR.KEY.RESIDENT;
        CEP.TRC(SCCGWA, BPRTAXR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.BR);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.EFF_DT);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.TAX_TYP);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.VAL_TYP);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.RESIDENT);
        T000_READ_REC_UPD_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTAXR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTAXR.RETURN_INFO = 'N';
        } else {
            BPCRTAXR.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRTAXR.EXP_DT);
    }
    public void T000_READ_REC_UPD_PROC() throws IOException,SQLException,Exception {
        BPTTAXR_RD = new DBParm();
        BPTTAXR_RD.TableName = "BPTTAXR";
        BPTTAXR_RD.upd = true;
        IBS.READ(SCCGWA, BPRTAXR, BPTTAXR_RD);
    }
    public void B050_UPDATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTAXR);
        BPRTAXR.EXP_DT = BPCSTAXR.EXP_DT;
        BPRTAXR.TAX_NAME = BPCSTAXR.TAX_NAME;
        BPRTAXR.TAX_VAL = BPCSTAXR.TAX_VAL;
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTAXR.RETURN_INFO = 'F';
        } else {
            BPCRTAXR.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        BPTTAXR_RD = new DBParm();
        BPTTAXR_RD.TableName = "BPTTAXR";
        IBS.REWRITE(SCCGWA, BPRTAXR, BPTTAXR_RD);
    }
    public void B004_DEL_BPTTAXR_PROC() throws IOException,SQLException,Exception {
        B040_READUPD_PROC();
        if (pgmRtn) return;
        if (BPCRTAXR.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "AAA");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else if (BPCRTAXR.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "BBB");
        } else {
            CEP.TRC(SCCGWA, "CCC");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRTAXR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        B080_DELETE_PROC();
        if (pgmRtn) return;
        if (BPCRTAXR.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_BPRTAXR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B080_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTAXR);
        IBS.init(SCCGWA, BPCRTAXR);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.BR);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.EFF_DT);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.TAX_TYP);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.VAL_TYP);
        CEP.TRC(SCCGWA, BPRTAXR.KEY.RESIDENT);
        T000_DELETE_REC_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTAXR.RETURN_INFO = 'F';
        } else {
            BPCRTAXR.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        BPTTAXR_RD = new DBParm();
        BPTTAXR_RD.TableName = "BPTTAXR";
        IBS.DELETE(SCCGWA, BPRTAXR, BPTTAXR_RD);
    }
    public void B005_BRW_BPTTAXR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 5241;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B010_STABR_BPTTAXR_PROC();
        if (pgmRtn) return;
        if (WS_READ_REC == '0') {
            B010_READ_NEXT_TAXR_PROC();
            if (pgmRtn) return;
        } else {
            B010_READ_NEXT_TAXR_PROC_1();
            if (pgmRtn) return;
        }
        while (BPCRTAXR.RETURN_INFO != 'E' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_REL_MPAGE_OUTPUT();
            if (pgmRtn) return;
            if (WS_READ_REC == '0') {
                B010_READ_NEXT_TAXR_PROC();
                if (pgmRtn) return;
            } else {
                B010_READ_NEXT_TAXR_PROC_1();
                if (pgmRtn) return;
            }
        }
        B010_END_BROWSE_TAXR_PROC();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_RTN_DATA_A);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 106;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_REL_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        WS_REC = " ";
        WS_RTN_DATA_A.WS_R_TAX_TYP = BPRTAXR.KEY.TAX_TYP;
        WS_RTN_DATA_A.WS_R_VAL_TYP = BPRTAXR.KEY.VAL_TYP;
        WS_RTN_DATA_A.WS_R_TAX_NAME = BPRTAXR.TAX_NAME;
        WS_RTN_DATA_A.WS_R_BR = BPRTAXR.KEY.BR;
        WS_RTN_DATA_A.WS_R_CCY = BPRTAXR.KEY.CCY;
        WS_RTN_DATA_A.WS_R_TAX_VAL = BPRTAXR.TAX_VAL;
        WS_RTN_DATA_A.WS_R_EFF_DT = BPRTAXR.KEY.EFF_DT;
        WS_RTN_DATA_A.WS_R_EXP_DT = BPRTAXR.EXP_DT;
        WS_RTN_DATA_A.WS_R_RESIDENT = BPRTAXR.KEY.RESIDENT;
        WS_REC = IBS.CLS2CPY(SCCGWA, WS_RTN_DATA_A);
        WS_REC_LEN = 106;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void B010_STABR_BPTTAXR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTAXR);
        IBS.init(SCCGWA, BPRTAXR);
        BPCRTAXR.FUNC = 'B';
        BPCRTAXR.OPT = 'C';
        if (BPCSTAXR.KEY.TAX_TYP.trim().length() > 0) {
            CEP.TRC(SCCGWA, "1&&&&&");
            BPCRTAXR.STR_TAX_TYP = BPCSTAXR.KEY.TAX_TYP;
            BPCRTAXR.END_TAX_TYP = BPCSTAXR.KEY.TAX_TYP;
            CEP.TRC(SCCGWA, BPCSTAXR.KEY.TAX_TYP);
        } else {
            CEP.TRC(SCCGWA, "2&&&&&");
            BPCRTAXR.STR_TAX_TYP = "" + 0X00;
            JIBS_tmp_int = BPCRTAXR.STR_TAX_TYP.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) BPCRTAXR.STR_TAX_TYP = "0" + BPCRTAXR.STR_TAX_TYP;
            BPCRTAXR.END_TAX_TYP = "" + 0XFF;
            JIBS_tmp_int = BPCRTAXR.END_TAX_TYP.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) BPCRTAXR.END_TAX_TYP = "0" + BPCRTAXR.END_TAX_TYP;
        }
        if (BPCSTAXR.KEY.CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, "3&&&&&");
            BPCRTAXR.STR_CCY = BPCSTAXR.KEY.CCY;
            BPCRTAXR.END_CCY = BPCSTAXR.KEY.CCY;
            CEP.TRC(SCCGWA, BPCSTAXR.KEY.CCY);
        } else {
            CEP.TRC(SCCGWA, "4&&&&&");
            BPCRTAXR.STR_CCY = "" + 0X00;
            JIBS_tmp_int = BPCRTAXR.STR_CCY.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) BPCRTAXR.STR_CCY = "0" + BPCRTAXR.STR_CCY;
            BPCRTAXR.END_CCY = "" + 0XFF;
            JIBS_tmp_int = BPCRTAXR.END_CCY.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) BPCRTAXR.END_CCY = "0" + BPCRTAXR.END_CCY;
        }
        if (BPCSTAXR.KEY.BR != 0) {
            CEP.TRC(SCCGWA, "5&&&&&");
            BPCRTAXR.STR_BR = BPCSTAXR.KEY.BR;
            BPCRTAXR.END_BR = BPCSTAXR.KEY.BR;
            CEP.TRC(SCCGWA, BPCSTAXR.KEY.BR);
        } else {
            CEP.TRC(SCCGWA, "6&&&&&");
            BPCRTAXR.STR_BR = 0;
            BPCRTAXR.END_BR = 999999;
        }
        if (BPCSTAXR.KEY.EFF_DT != 0) {
            BPCRTAXR.OPT = 'D';
            WS_READ_REC = '1';
            BPCRTAXR.STR_EFF_DT = BPCSTAXR.KEY.EFF_DT;
            BPCRTAXR.END_EFF_DT = BPCSTAXR.KEY.EFF_DT;
            CEP.TRC(SCCGWA, BPCSTAXR.KEY.EFF_DT);
        } else {
            BPCRTAXR.OPT = 'C';
            WS_READ_REC = '0';
            BPCRTAXR.STR_EFF_DT = 19000101;
            BPCRTAXR.END_EFF_DT = 20991231;
        }
        BPCRTAXR.VAL_TYP = BPCSTAXR.KEY.VAL_TYP;
        BPCRTAXR.REC_PTR = BPRTAXR;
        BPCRTAXR.REC_LEN = 181;
        S000_CALL_BPZRTAXR();
        if (pgmRtn) return;
    }
    public void B010_READ_NEXT_TAXR_PROC() throws IOException,SQLException,Exception {
        BPCRTAXR.FUNC = 'B';
        BPCRTAXR.OPT = 'R';
        S000_CALL_BPZRTAXR();
        if (pgmRtn) return;
    }
    public void B010_READ_NEXT_TAXR_PROC_1() throws IOException,SQLException,Exception {
        BPCRTAXR.FUNC = 'B';
        BPCRTAXR.OPT = 'N';
        S000_CALL_BPZRTAXR();
        if (pgmRtn) return;
    }
    public void B010_END_BROWSE_TAXR_PROC() throws IOException,SQLException,Exception {
        BPCRTAXR.FUNC = 'B';
        BPCRTAXR.OPT = 'E';
        S000_CALL_BPZRTAXR();
        if (pgmRtn) return;
    }
    public void B210_SET_WF_AP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_REF_DATA);
        WS_REF_DATA.WS_REF_CCY = BPCSTAXR.KEY.CCY;
    }
    public void B031_TRAN_DATA_PROC() throws IOException,SQLException,Exception {
        B031_OUTPUT_FORMAT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = WS_OUT_CODE;
        SCCFMT.DATA_PTR = BPCOTAXR;
        SCCFMT.DATA_LEN = 106;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B031_OUTPUT_FORMAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTAXR);
        CEP.TRC(SCCGWA, BPCSTAXR);
        if (WS_SEL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, BPCITAXR.TAX_NAME);
            CEP.TRC(SCCGWA, BPCITAXR.TAX_VAL);
            CEP.TRC(SCCGWA, BPCITAXR.EXP_DT);
            BPCOTAXR.TAX_NAME = BPCITAXR.TAX_NAME;
            BPCOTAXR.TAX_VAL = BPCITAXR.TAX_VAL;
        } else {
            BPCOTAXR.TAX_NAME = BPCSTAXR.TAX_NAME;
            BPCOTAXR.TAX_VAL = BPCSTAXR.TAX_VAL;
        }
        BPCOTAXR.EFF_DT = BPCSTAXR.KEY.EFF_DT;
        BPCOTAXR.EXP_DT = BPCSTAXR.EXP_DT;
        BPCOTAXR.BR = BPCSTAXR.KEY.BR;
        BPCOTAXR.CCY = BPCSTAXR.KEY.CCY;
        BPCOTAXR.TAX_TYP = BPCSTAXR.KEY.TAX_TYP;
        BPCOTAXR.VAL_TYP = BPCSTAXR.KEY.VAL_TYP;
        BPCOTAXR.RESIDENT = BPCSTAXR.KEY.RESIDENT;
        CEP.TRC(SCCGWA, BPCOTAXR.RESIDENT);
    }
    public void S000_CALL_BPZITAXR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-I-MAINT-TAXR", BPCITAXR);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZRTAXR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAIN-TAXR", BPCRTAXR);
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
