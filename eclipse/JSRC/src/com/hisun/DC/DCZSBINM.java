package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSBINM {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTBINPM_RD;
    brParm DCTBINPM_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC051";
    int K_MAX_COL = 50;
    int K_MAX_ROW = 35;
    int K_COL_CNT = 18;
    String K_HIS_REMARK = "THE CARD BIN PARAMETER MAINTAIN";
    String K_HIS_COPYBOOK = "DCRBINPM";
    String K_TBL_BINPM = "DCTBINPM";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRBINPM DCRBINPM = new DCRBINPM();
    DCRBINPM DCRBINPO = new DCRBINPM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCSBINO DCCSBINO = new DCCSBINO();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSBINM DCCSBINM;
    public void MP(SCCGWA SCCGWA, DCCSBINM DCCSBINM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSBINM = DCCSBINM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSBINM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCRBINPM);
        IBS.init(SCCGWA, DCRBINPM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSBINM);
        if (DCCSBINM.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSBINM.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSBINM.FUNC == 'U') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSBINM.FUNC == 'D') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSBINM.FUNC == 'B') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.FUNC_FALSE, 1);
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCSBINM.VAL.KEY.BIN.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, 9);
        }
        if (DCCSBINM.VAL.CARD_LEN <= 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
        }
        if (DCCSBINM.SEG_NUM != 1 
            && DCCSBINM.SEG_NUM != 2) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
        }
        if (DCCSBINM.SEG_NUM == 2) {
            if ((DCCSBINM.SEG1_LEN <= 0) 
                || (DCCSBINM.SEG1_LEN > 12)) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
            }
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSBINM.VAL.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRBINPM.KEY);
        T000_READ_DCTBINPM();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        CEP.TRC(SCCGWA, "BIN -CHINA:");
        DCRBINPM.KEY.BIN = DCCSBINM.VAL.KEY.BIN;
        CEP.TRC(SCCGWA, DCCSBINM.VAL.KEY.BIN);
        DCRBINPM.UNION_SHORT_NAME = DCCSBINM.VAL.ORG_SNM;
        CEP.TRC(SCCGWA, DCCSBINM.VAL.ORG_SNM);
        DCRBINPM.UNION_NAME = DCCSBINM.VAL.ORG_NM;
        CEP.TRC(SCCGWA, DCCSBINM.VAL.ORG_NM);
        DCRBINPM.CHK_DIG_MTH = DCCSBINM.VAL.DIG_MTH;
        CEP.TRC(SCCGWA, DCCSBINM.VAL.DIG_MTH);
        DCRBINPM.PIN_MTH = DCCSBINM.VAL.PIN_MTH;
        CEP.TRC(SCCGWA, DCCSBINM.VAL.PIN_MTH);
        DCRBINPM.CVV_TYP = DCCSBINM.VAL.CVV_TYP;
        CEP.TRC(SCCGWA, DCCSBINM.VAL.CVV_TYP);
        DCRBINPM.CARD_LEN = DCCSBINM.VAL.CARD_LEN;
        CEP.TRC(SCCGWA, DCCSBINM.VAL.CARD_LEN);
        DCRBINPM.SEG_NUM = DCCSBINM.SEG_NUM;
        CEP.TRC(SCCGWA, DCCSBINM.SEG_NUM);
        DCRBINPM.SEG1_LEN = DCCSBINM.SEG1_LEN;
        DCRBINPM.SEG1_RMK = DCCSBINM.SEG1_RMK;
        CEP.TRC(SCCGWA, DCCSBINM.CEID_EXP);
        DCRBINPM.CEID_EXP = DCCSBINM.CEID_EXP;
        DCRBINPM.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRBINPM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRBINPM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRBINPM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTBINPM();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'D') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSBINM.VAL.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRBINPM.KEY);
        T000_READ_DCTBINPM_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
        IBS.CLONE(SCCGWA, DCRBINPM, DCRBINPO);
        DCRBINPM.KEY.BIN = DCCSBINM.VAL.KEY.BIN;
        DCRBINPM.UNION_SHORT_NAME = DCCSBINM.VAL.ORG_SNM;
        DCRBINPM.UNION_NAME = DCCSBINM.VAL.ORG_NM;
        DCRBINPM.CHK_DIG_MTH = DCCSBINM.VAL.DIG_MTH;
        DCRBINPM.PIN_MTH = DCCSBINM.VAL.PIN_MTH;
        DCRBINPM.CVV_TYP = DCCSBINM.VAL.CVV_TYP;
        DCRBINPM.CARD_LEN = DCCSBINM.VAL.CARD_LEN;
        DCRBINPM.SEG_NUM = DCCSBINM.SEG_NUM;
        DCRBINPM.SEG1_LEN = DCCSBINM.SEG1_LEN;
        DCRBINPM.SEG1_RMK = DCCSBINM.SEG1_RMK;
        DCRBINPM.CEID_EXP = DCCSBINM.CEID_EXP;
        DCRBINPM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRBINPM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTBINPM();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'D') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
        }
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSBINM.VAL.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRBINPM.KEY);
        T000_READ_DCTBINPM_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
        IBS.CLONE(SCCGWA, DCRBINPM, DCRBINPO);
        T000_DELETE_DCTBINPM();
        if (pgmRtn) return;
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        if (DCCSBINM.VAL.KEY.BIN.trim().length() == 0) {
            DCRBINPM.KEY.BIN = "%%%%%%%%";
        } else {
            DCRBINPM.KEY.BIN = DCCSBINM.VAL.KEY.BIN;
        }
        if (DCCSBINM.VAL.ORG_SNM.trim().length() == 0) {
            DCRBINPM.UNION_SHORT_NAME = "%%%";
        } else {
            DCRBINPM.UNION_SHORT_NAME = DCCSBINM.VAL.ORG_SNM;
        }
        T000_STARTBR_DCTBINPM();
        if (pgmRtn) return;
        T000_READNEXT_DCTBINPM();
        if (pgmRtn) return;
        B040_01_01_OUT_TITLE();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (WS_TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B040_01_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            WS_CNT += 1;
            T000_READNEXT_DCTBINPM();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTBINPM();
        if (pgmRtn) return;
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "A");
        CEP.TRC(SCCGWA, DCRBINPM.KEY.BIN);
        DCCSBINO.VAL.KEY.BIN = DCRBINPM.KEY.BIN;
        CEP.TRC(SCCGWA, "B");
        DCCSBINO.VAL.ORG_SNM = DCRBINPM.UNION_SHORT_NAME;
        CEP.TRC(SCCGWA, "C");
        DCCSBINO.VAL.ORG_NM = DCRBINPM.UNION_NAME;
        CEP.TRC(SCCGWA, "D");
        DCCSBINO.VAL.DIG_MTH = DCRBINPM.CHK_DIG_MTH;
        CEP.TRC(SCCGWA, "E");
        DCCSBINO.VAL.PIN_MTH = DCRBINPM.PIN_MTH;
        CEP.TRC(SCCGWA, "F");
        DCCSBINO.VAL.CVV_TYP = DCRBINPM.CVV_TYP;
        CEP.TRC(SCCGWA, "GGGGGGGGGGG");
        DCCSBINO.VAL.CARD_LEN = DCRBINPM.CARD_LEN;
        DCCSBINO.VAL.SEG_NUM = DCRBINPM.SEG_NUM;
        DCCSBINO.VAL.SEG1_LEN = DCRBINPM.SEG1_LEN;
        DCCSBINO.VAL.SEG1_RMK = DCRBINPM.SEG1_RMK;
        DCCSBINO.VAL.CEID_EXP = DCRBINPM.CEID_EXP;
        CEP.TRC(SCCGWA, "AAKJHYAAAAAA");
        CEP.TRC(SCCGWA, DCCSBINO.VAL.KEY.BIN);
        CEP.TRC(SCCGWA, DCCSBINO.VAL);
        CEP.TRC(SCCGWA, DCCSBINO.VAL.SEG1_RMK);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DCCSBINO.VAL);
        SCCMPAG.DATA_LEN = 276;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 332;
        if (DCCSBINM.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRBINPM;
        }
        if (DCCSBINM.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRBINPO;
        }
        if (DCCSBINM.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRBINPO;
            BPCPNHIS.INFO.NEW_DAT_PT = DCRBINPM;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        DCCSBINO.VAL.KEY.BIN = DCRBINPM.KEY.BIN;
        DCCSBINO.VAL.ORG_SNM = DCRBINPM.UNION_SHORT_NAME;
        DCCSBINO.VAL.ORG_NM = DCRBINPM.UNION_NAME;
        DCCSBINO.VAL.DIG_MTH = DCRBINPM.CHK_DIG_MTH;
        DCCSBINO.VAL.PIN_MTH = DCRBINPM.PIN_MTH;
        DCCSBINO.VAL.CVV_TYP = DCRBINPM.CVV_TYP;
        DCCSBINO.VAL.CARD_LEN = DCRBINPM.CARD_LEN;
        DCCSBINO.VAL.SEG_NUM = DCRBINPM.SEG_NUM;
        DCCSBINO.VAL.SEG1_LEN = DCRBINPM.SEG1_LEN;
        DCCSBINO.VAL.SEG1_RMK = DCRBINPM.SEG1_RMK;
        DCCSBINO.VAL.CEID_EXP = DCRBINPM.CEID_EXP;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = DCCSBINO.VAL;
        SCCFMT.DATA_LEN = 276;
        CEP.TRC(SCCGWA, DCCSBINO.VAL);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "BIN, UNION_SHORT_NAME, UNION_NAME, CHK_DIG_MTH, PIN_MTH, CVV_TYP, CARD_LEN, SEG_NUM, SEG1_LEN, SEG1_RMK, CEID_EXP, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BINPM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRBINPM, DCTBINPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BINPM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTBINPM_UPD() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.upd = true;
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BINPM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "UNION_SHORT_NAME, UNION_NAME, CHK_DIG_MTH, PIN_MTH, CVV_TYP, CARD_LEN, SEG_NUM, SEG1_LEN, SEG1_RMK, CEID_EXP, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTBINPM_RD.where = "BIN = :DCRBINPM.KEY.BIN";
        IBS.REWRITE(SCCGWA, DCRBINPM, this, DCTBINPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BINPM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        IBS.DELETE(SCCGWA, DCRBINPM, DCTBINPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BINPM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_BR.rp = new DBParm();
        DCTBINPM_BR.rp.TableName = "DCTBINPM";
        DCTBINPM_BR.rp.where = "BIN LIKE :DCRBINPM.KEY.BIN "
            + "AND UNION_SHORT_NAME LIKE :DCRBINPM.UNION_SHORT_NAME";
        DCTBINPM_BR.rp.order = "BIN";
        IBS.STARTBR(SCCGWA, DCRBINPM, this, DCTBINPM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BINPM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTBINPM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRBINPM, this, DCTBINPM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BINPM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_ENDBR_DCTBINPM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTBINPM_BR);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
