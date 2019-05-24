package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.VT.WFCCAPV;
import com.hisun.WF.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class FXZSBFXT {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String FXRBFXT = "FXRBFXT ";
    int MAX_ROW = 99;
    String HIS_ADD_RMKS = "ADD BOOK FX RATE TICKET";
    String HIS_MOD_RMKS = "CHANGE BOOK FX RATE TICKET";
    String HIS_DEL_RMKS = "DELETE BOOK FX RATE TICKET";
    String INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String 999 = "999";
    FXZSBFXT_WS_OUTPUT_DATA WS_OUTPUT_DATA = new FXZSBFXT_WS_OUTPUT_DATA();
    FXZSBFXT_WS_VARIABLES WS_VARIABLES = new FXZSBFXT_WS_VARIABLES();
    FXZSBFXT_WS_CONDITION_FLAG WS_CONDITION_FLAG = new FXZSBFXT_WS_CONDITION_FLAG();
    FXCMSG_ERROR_MSG ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    FXRBFXT FXRBFXT = new FXRBFXT();
    FXCRBFXT FXCRBFXT = new FXCRBFXT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCPORLO BPCPORLO = new BPCPORLO();
    BPCPRGST BPCPRGST = new BPCPRGST();
    DDCSCINM DDCSCINM = new DDCSCINM();
    FXCOBFXT FXCOBFXT = new FXCOBFXT();
    WFCCAPV WFCCAPV = new WFCCAPV();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    FXRLLMT FXRLLMT = new FXRLLMT();
    FXRLLMT FXRLLMT = new FXRLLMT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGAPV SCCGAPV;
    FXCSBFXT FXCSBFXT;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA, FXCSBFXT FXCSBFXT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FXCSBFXT = FXCSBFXT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FXZSBFXT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) SC_AREA.APVL_AREA_PTR;
        SCCGMSG = (SCCGMSG) SC_AREA.MSG_AREA_PTR;
        CEP.TRC(SCCGWA, SCCGMSG.SUP1_ID);
        CEP.TRC(SCCGWA, SCCGMSG.SUP2_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP2_ID);
        CEP.TRC(SCCGWA, SCCGAPV.TYPE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXCSBFXT.FUNC);
        CEP.TRC(SCCGWA, FXCSBFXT);
        if (FXCSBFXT.FUNC == 'Q') {
            B001_INQ_FXTBFXT_PROC();
            if (pgmRtn) return;
        } else if (FXCSBFXT.FUNC == 'A') {
            CEP.TRC(SCCGWA, FXCSBFXT.TRA_AC);
            B002_ADD_FXTBFXT_PROC();
            if (pgmRtn) return;
        } else if (FXCSBFXT.FUNC == 'U') {
            B003_UPD_EXTBFXT_PROC();
            if (pgmRtn) return;
        } else if (FXCSBFXT.FUNC == 'D') {
            B004_DEL_FXTBFXT_PROC();
            if (pgmRtn) return;
        } else if (FXCSBFXT.FUNC == 'E') {
            B004_DEL_APRV_FXTBFXT_PROC();
            if (pgmRtn) return;
        } else if (FXCSBFXT.FUNC == 'F') {
            B004_ADD_APRV_FXTBFXT_PROC();
            if (pgmRtn) return;
        } else if (FXCSBFXT.FUNC == 'B') {
            B005_BRW_FXTBFXT_PROC();
            if (pgmRtn) return;
        } else if (FXCSBFXT.FUNC == 'O') {
            B006_OUTSTANDING_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + FXCSBFXT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B001_INQ_FXTBFXT_PROC() throws IOException,SQLException,Exception {
        B030_CHK_RECORD_PROC();
        if (pgmRtn) return;
        if (FXCRBFXT.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.FX_REC_NOTFND);
        } else if (FXCRBFXT.RETURN_INFO == 'F') {
            B031_TRAN_DATA_PROC();
            if (pgmRtn) return;
        } else if (FXCRBFXT.RETURN_INFO == 'O') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B002_ADD_FXTBFXT_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_PROC();
        if (pgmRtn) return;
        B020_GET_SEQUENCE_NO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, FXCSBFXT.KEY.TIK_NO);
        IBS.init(SCCGWA, FXCRBFXT);
        IBS.init(SCCGWA, FXRBFXT);
        FXRBFXT.KEY.TIK_NO = FXCSBFXT.KEY.TIK_NO;
        FXCRBFXT.FUNC = 'I';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, FXCRBFXT.RETURN_INFO);
        if (FXCRBFXT.RETURN_INFO == 'F') {
            FXCRBFXT.FUNC = 'R';
            S000_CALL_FXZRBFXT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "DELETE-DUP-REC");
            FXCRBFXT.FUNC = 'D';
            S000_CALL_FXZRBFXT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, FXCRBFXT);
        IBS.init(SCCGWA, FXRBFXT);
        FXCRBFXT.FUNC = 'A';
        FXRBFXT.KEY.TIK_NO = FXCSBFXT.KEY.TIK_NO;
        FXCSBFXT.STATUS = 'P';
        FXRBFXT.STATUS = FXCSBFXT.STATUS;
        FXRBFXT.CI_NO = FXCSBFXT.CI_NO;
        FXRBFXT.TRA_AC = FXCSBFXT.TRA_AC;
        FXRBFXT.CI_CNM = FXCSBFXT.CI_CNM;
        FXRBFXT.CI_ENM = FXCSBFXT.CI_ENM;
        FXRBFXT.ID_TYP = FXCSBFXT.ID_TYP;
        FXRBFXT.ID_NO = FXCSBFXT.ID_NO;
        FXRBFXT.CNTY_CD = FXCSBFXT.CNTY_CD;
        FXRBFXT.CNTY_FLG = FXCSBFXT.CNTY_FLG;
        FXRBFXT.PROD_CD = FXCSBFXT.PROD_CD;
        FXRBFXT.PROD_NM = FXCSBFXT.PROD_NM;
        FXRBFXT.B_S_FLG = FXCSBFXT.B_S_FLG;
        FXRBFXT.B_CS_FLG = FXCSBFXT.B_CS_FLG;
        FXRBFXT.BUY_CCY = FXCSBFXT.BUY_CCY;
        FXRBFXT.BUY_AMT = FXCSBFXT.BUY_AMT;
        FXRBFXT.S_CS_FLG = FXCSBFXT.S_CS_FLG;
        FXRBFXT.SELL_CCY = FXCSBFXT.SELL_CCY;
        FXRBFXT.SELL_AMT = FXCSBFXT.SELL_AMT;
        FXRBFXT.EX_CODE = FXCSBFXT.EX_CODE;
        FXRBFXT.ORDER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        FXRBFXT.EX_GROUP = FXCSBFXT.EX_GROUP;
        FXRBFXT.SYS_RATE = FXCSBFXT.SYS_RATE;
        FXRBFXT.PRE_RATE = FXCSBFXT.PRE_RATE;
        FXRBFXT.SPREAD = FXCSBFXT.SPREAD;
        FXRBFXT.EX_RATE = FXCSBFXT.EX_RATE;
        FXRBFXT.RATE_TM = FXCSBFXT.RATE_TM;
        FXRBFXT.VALUE_DT = FXCSBFXT.VALUE_DT;
        FXRBFXT.END_DT = FXCSBFXT.OPT_END_DT;
        FXRBFXT.BEFF_TM = FXCSBFXT.BEFF_TM;
        FXRBFXT.TRA_TM = SCCGWA.COMM_AREA.TR_TIME;
        FXRBFXT.RMK = FXCSBFXT.RMK;
        FXRBFXT.CRE_DT = FXCSBFXT.CRE_DT;
        FXRBFXT.CRE_TM = SCCGWA.COMM_AREA.TR_TIME;
        FXRBFXT.CRE_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, FXRBFXT.CRE_DT);
        CEP.TRC(SCCGWA, FXRBFXT.CRE_TM);
        CEP.TRC(SCCGWA, FXRBFXT.CRE_TLR);
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
        if (FXCRBFXT.RETURN_INFO == 'F') {
        } else if (FXCRBFXT.RETURN_INFO == 'D') {
            CEP.ERR(SCCGWA, ERROR_MSG.FX_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        B033_OUTPUT_DEL_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
    }
    public void B020_GET_SEQUENCE_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "TIKNO";
        BPCSGSEQ.CODE = "SEQ1";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCSGSEQ.AC_DATE);
        BPCSGSEQ.RUN_MODE = 'O';
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_VARIABLES.TIK_NO = JIBS_tmp_str[0].substring(8 - 1, 8 + 8 - 1);
        CEP.TRC(SCCGWA, WS_VARIABLES.TIK_NO);
        if (FXCSBFXT.KEY.TIK_NO == null) FXCSBFXT.KEY.TIK_NO = "";
        JIBS_tmp_int = FXCSBFXT.KEY.TIK_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) FXCSBFXT.KEY.TIK_NO += " ";
        FXCSBFXT.KEY.TIK_NO = "FX" + FXCSBFXT.KEY.TIK_NO.substring(2);
        if (WS_VARIABLES.TIK_NO == null) WS_VARIABLES.TIK_NO = "";
        JIBS_tmp_int = WS_VARIABLES.TIK_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_VARIABLES.TIK_NO += " ";
        if (FXCSBFXT.KEY.TIK_NO == null) FXCSBFXT.KEY.TIK_NO = "";
        JIBS_tmp_int = FXCSBFXT.KEY.TIK_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) FXCSBFXT.KEY.TIK_NO += " ";
        FXCSBFXT.KEY.TIK_NO = FXCSBFXT.KEY.TIK_NO.substring(0, 3 - 1) + WS_VARIABLES.TIK_NO.substring(3 - 1, 3 + 6 - 1) + FXCSBFXT.KEY.TIK_NO.substring(3 + 6 - 1);
        CEP.TRC(SCCGWA, FXCSBFXT.KEY.TIK_NO);
    }
    public void B021_HOLIDAY_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOQCAL.CCY = FXCSBFXT.BUY_CCY;
        B022_CHK_CALC_DAY();
        if (pgmRtn) return;
        if (BPCOCLWD.DATE1_FLG != 'H') {
            IBS.init(SCCGWA, BPCOQCAL);
            BPCOQCAL.CCY = FXCSBFXT.SELL_CCY;
            B022_CHK_CALC_DAY();
            if (pgmRtn) return;
        }
    }
    public void B022_CHK_CALC_DAY() throws IOException,SQLException,Exception {
        BPCOQCAL.BK = SCCGWA.COMM_AREA.TR_BANK;
        BPCOQCAL.FUNC = '1';
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOQCAL.CAL_CODE);
        CEP.TRC(SCCGWA, BPCOQCAL.RC.RC_CODE);
        if (BPCOQCAL.RC.RC_CODE == 0) {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.DATE1 = FXCSBFXT.VALUE_DT;
            BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
            BPCOCLWD.DAYS = 2;
            CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOCLWD.RC);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE1_FLG);
        }
    }
    public void B030_CHK_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCRBFXT);
        IBS.init(SCCGWA, FXRBFXT);
        CEP.TRC(SCCGWA, FXCSBFXT.KEY.TIK_NO);
        FXRBFXT.KEY.TIK_NO = FXCSBFXT.KEY.TIK_NO;
        CEP.TRC(SCCGWA, FXRBFXT.KEY.TIK_NO);
        FXCRBFXT.FUNC = 'I';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
    }
    public void B003_UPD_EXTBFXT_PROC() throws IOException,SQLException,Exception {
        B040_READUPD_PROC();
        if (pgmRtn) return;
        if (FXCRBFXT.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.FX_REC_NOTFND);
        } else if (FXCRBFXT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        B050_UPDATE_PROC();
        if (pgmRtn) return;
        if (FXCRBFXT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B040_READUPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRBFXT);
        IBS.init(SCCGWA, FXCRBFXT);
        FXRBFXT.KEY.TIK_NO = FXCSBFXT.KEY.TIK_NO;
        FXCRBFXT.FUNC = 'R';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
    }
    public void B050_UPDATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRBFXT);
        IBS.init(SCCGWA, FXCRBFXT);
        FXRBFXT.KEY.TIK_NO = FXCSBFXT.KEY.TIK_NO;
        FXRBFXT.STATUS = FXCSBFXT.STATUS;
        FXRBFXT.CI_NO = FXCSBFXT.CI_NO;
        FXRBFXT.TRA_AC = FXCSBFXT.TRA_AC;
        FXRBFXT.CI_CNM = FXCSBFXT.CI_CNM;
        FXRBFXT.PROD_CD = FXCSBFXT.PROD_CD;
        FXRBFXT.PROD_NM = FXCSBFXT.PROD_NM;
        FXRBFXT.INPUT_CCY = FXCSBFXT.INPUT_CCY;
        FXRBFXT.B_CS_FLG = FXCSBFXT.B_CS_FLG;
        FXRBFXT.BUY_CCY = FXCSBFXT.BUY_CCY;
        FXRBFXT.BUY_AMT = FXCSBFXT.BUY_AMT;
        FXRBFXT.S_CS_FLG = FXCSBFXT.S_CS_FLG;
        FXRBFXT.SELL_CCY = FXCSBFXT.SELL_CCY;
        FXRBFXT.SELL_AMT = FXCSBFXT.SELL_AMT;
        FXRBFXT.EX_CODE = FXCSBFXT.EX_CODE;
        FXRBFXT.SYS_RATE = FXCSBFXT.SYS_RATE;
        FXRBFXT.PRE_RATE = FXCSBFXT.PRE_RATE;
        FXRBFXT.EX_RATE = FXCSBFXT.EX_RATE;
        FXRBFXT.VALUE_DT = FXCSBFXT.VALUE_DT;
        FXRBFXT.END_DT = FXCSBFXT.OPT_END_DT;
        FXRBFXT.RMK = FXCSBFXT.RMK;
        FXCRBFXT.FUNC = 'U';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
    }
    public void B004_DEL_FXTBFXT_PROC() throws IOException,SQLException,Exception {
        B040_READUPD_PROC();
        if (pgmRtn) return;
        if (FXCRBFXT.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.FX_REC_NOTFND);
        } else if (FXCRBFXT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        if (FXRBFXT.STATUS == 'C' 
            || FXRBFXT.STATUS == 'R' 
            || FXRBFXT.STATUS == 'D') {
            CEP.ERR(SCCGWA, ERROR_MSG.FX_TICKET_STATUS_ERR);
        }
        IBS.init(SCCGWA, FXCRBFXT);
        FXRBFXT.KEY.TIK_NO = FXCSBFXT.KEY.TIK_NO;
        CEP.TRC(SCCGWA, FXCSBFXT.RMK);
        FXRBFXT.RMK = FXCSBFXT.RMK;
        FXRBFXT.STATUS = 'R';
        FXRBFXT.UPD_DT = SCCGWA.COMM_AREA.TR_DATE;
        FXRBFXT.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
        FXRBFXT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        FXCRBFXT.FUNC = 'U';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
        if (FXCRBFXT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        B033_OUTPUT_DEL_DATA();
        if (pgmRtn) return;
    }
    public void B004_DEL_APRV_FXTBFXT_PROC() throws IOException,SQLException,Exception {
        B040_READUPD_PROC();
        if (pgmRtn) return;
        if (FXCRBFXT.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.FX_REC_NOTFND);
        } else if (FXCRBFXT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, FXCRBFXT);
        FXRBFXT.KEY.TIK_NO = FXCSBFXT.KEY.TIK_NO;
        CEP.TRC(SCCGWA, FXCSBFXT.RMK);
        FXRBFXT.RMK = FXCSBFXT.RMK;
        FXRBFXT.STATUS = 'D';
        FXRBFXT.SUP1_DT = SCCGWA.COMM_AREA.AC_DATE;
        FXRBFXT.SUP1_TM = SCCGWA.COMM_AREA.TR_TIME;
        FXRBFXT.SUP1 = SCCGWA.COMM_AREA.TL_ID;
        FXCRBFXT.FUNC = 'U';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
        if (FXCRBFXT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        B033_OUTPUT_DEL_DATA();
        if (pgmRtn) return;
    }
    public void B004_ADD_APRV_FXTBFXT_PROC() throws IOException,SQLException,Exception {
        B040_READUPD_PROC();
        if (pgmRtn) return;
        if (FXCRBFXT.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.FX_REC_NOTFND);
        } else if (FXCRBFXT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, FXCRBFXT);
        FXRBFXT.KEY.TIK_NO = FXCSBFXT.KEY.TIK_NO;
        FXRBFXT.BUY_AMT = FXCSBFXT.BUY_AMT;
        FXRBFXT.SELL_AMT = FXCSBFXT.SELL_AMT;
        FXRBFXT.RMK = FXCSBFXT.RMK;
        FXRBFXT.STATUS = 'U';
        FXRBFXT.SPREAD = FXCSBFXT.SPREAD;
        FXRBFXT.EX_RATE = FXCSBFXT.EX_RATE;
        FXRBFXT.VALUE_DT = SCCGWA.COMM_AREA.AC_DATE;
        FXRBFXT.END_DT = SCCGWA.COMM_AREA.AC_DATE;
        FXRBFXT.SUP1_DT = SCCGWA.COMM_AREA.AC_DATE;
        FXRBFXT.SUP1_TM = SCCGWA.COMM_AREA.TR_TIME;
        FXRBFXT.SUP1 = SCCGWA.COMM_AREA.TL_ID;
        FXRBFXT.TRA_TM = SCCGWA.COMM_AREA.TR_TIME;
        FXCRBFXT.FUNC = 'U';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
        if (FXCRBFXT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        B033_OUTPUT_DEL_DATA();
        if (pgmRtn) return;
    }
    public void B005_BRW_FXTBFXT_PROC() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B010_STABR_FXTBFXT_PROC();
        if (pgmRtn) return;
        B010_READ_NEXT_BFXT_PROC();
        if (pgmRtn) return;
        while (FXCRBFXT.RETURN_INFO != 'E' 
            && SCCMPAG.FUNC != 'E') {
            B030_COMPAIR_BR();
            if (pgmRtn) return;
            if (BPCPRGST.LVL_RELATION == 'A') {
                CEP.TRC(SCCGWA, "MPAGE HZ");
                R000_TRANS_REL_MPAGE_OUTPUT();
                if (pgmRtn) return;
            }
            B010_READ_NEXT_BFXT_PROC();
            if (pgmRtn) return;
        }
        B010_END_BROWSE_BFXT_PROC();
        if (pgmRtn) return;
    }
    public void B006_OUTSTANDING_PROC() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B010_STABR_EXTBFXT_OUTS();
        if (pgmRtn) return;
        B010_READ_NEXT_BFXT_PROC();
        if (pgmRtn) return;
        while (FXCRBFXT.RETURN_INFO != 'E') {
            R000_TRANS_REL_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B010_READ_NEXT_BFXT_PROC();
            if (pgmRtn) return;
        }
        B010_END_BROWSE_BFXT_PROC();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 931;
        SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 9;
        CEP.TRC(SCCGWA, SCCMPAG.MAX_COL_NO);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_REL_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 931;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_COMPAIR_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, FXRBFXT.ORDER_BR);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = FXRBFXT.ORDER_BR;
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == FXRBFXT.ORDER_BR) {
            BPCPRGST.FLAG = 'Y';
            BPCPRGST.LVL_RELATION = 'A';
        } else {
            S000_CALL_BPZPRGST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPRGST.FLAG);
        CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
    }
    public void B020_FIND_UNDERLING_PRO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPORLO);
        BPCPORLO.NEXT_CALL_FLG = 'Y';
        BPCPORLO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        while (BPCPORLO.NEXT_CALL_FLG != 'N') {
            S000_CALL_BPZPORLO();
            if (pgmRtn) return;
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPORLO.BNK);
        CEP.TRC(SCCGWA, BPCPORLO.BR);
        CEP.TRC(SCCGWA, BPCPORLO.FLAG);
        CEP.TRC(SCCGWA, BPCPORLO.UNDER_LVL);
        CEP.TRC(SCCGWA, BPCPORLO.LAST_BR);
        CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
        CEP.TRC(SCCGWA, BPCPORLO.NEXT_CALL_FLG);
        CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAA");
        if (BPCPORLO.SUB_NUM > 0) {
            for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= BPCPORLO.SUB_NUM; WS_VARIABLES.I += 1) {
                CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[WS_VARIABLES.I-1].SUB_BR);
                CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[WS_VARIABLES.I-1].ATTR);
                CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[WS_VARIABLES.I-1].TYP);
                CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[WS_VARIABLES.I-1].LVL);
            }
        }
    }
    public void B033_OUTPUT_DEL_DATA() throws IOException,SQLException,Exception {
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "FX317";
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 931;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B031_TRAN_DATA_PROC() throws IOException,SQLException,Exception {
        B031_OUTPUT_FORMAT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "FX314";
        SCCFMT.DATA_PTR = FXCOBFXT;
        SCCFMT.DATA_LEN = 983;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B031_OUTPUT_FORMAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCOBFXT);
        FXCOBFXT.TIK_NO = FXRBFXT.KEY.TIK_NO;
        FXCOBFXT.ORDER_BR = FXRBFXT.ORDER_BR;
        CEP.TRC(SCCGWA, FXCOBFXT.ORDER_BR);
        FXCOBFXT.STATUS = FXRBFXT.STATUS;
        FXCOBFXT.CI_NO = FXRBFXT.CI_NO;
        FXCOBFXT.TRA_AC = FXRBFXT.TRA_AC;
        CEP.TRC(SCCGWA, FXRBFXT.CI_NO);
        FXCOBFXT.CI_CNM = FXRBFXT.CI_CNM;
        FXCOBFXT.CI_ENM = FXRBFXT.CI_ENM;
        FXCOBFXT.CNTY_CD = FXRBFXT.CNTY_CD;
        FXCOBFXT.CNTY_FLG = FXRBFXT.CNTY_FLG;
        FXCOBFXT.PROD_CD = FXRBFXT.PROD_CD;
        FXCOBFXT.PROD_NM = FXRBFXT.PROD_NM;
        FXCOBFXT.B_S_FLG = FXRBFXT.B_S_FLG;
        FXCOBFXT.B_CS_FLG = FXRBFXT.B_CS_FLG;
        FXCOBFXT.BUY_CCY = FXRBFXT.BUY_CCY;
        FXCOBFXT.BUY_AMT = FXRBFXT.BUY_AMT;
        FXCOBFXT.S_CS_FLG = FXRBFXT.S_CS_FLG;
        FXCOBFXT.SELL_CCY = FXRBFXT.SELL_CCY;
        FXCOBFXT.SELL_AMT = FXRBFXT.SELL_AMT;
        FXCOBFXT.EX_CODE = FXRBFXT.EX_CODE;
        FXCOBFXT.EX_GROUP = FXRBFXT.EX_GROUP;
        FXCOBFXT.SYS_RATE = FXRBFXT.SYS_RATE;
        FXCOBFXT.PRE_RATE = FXRBFXT.PRE_RATE;
        FXCOBFXT.SPREAD = FXRBFXT.SPREAD;
        CEP.TRC(SCCGWA, FXCOBFXT.SPREAD);
        FXCOBFXT.EX_RATE = FXRBFXT.EX_RATE;
        FXCOBFXT.VALUE_DT = FXRBFXT.VALUE_DT;
        FXCOBFXT.OPT_END_DT = FXRBFXT.END_DT;
        FXCOBFXT.BEFF_TM = FXRBFXT.BEFF_TM;
        FXCOBFXT.RMK = FXRBFXT.RMK;
        FXCOBFXT.CRT_TLR = FXRBFXT.CRE_TLR;
        FXCOBFXT.CRE_DT = FXRBFXT.CRE_DT;
        FXCOBFXT.UPD_TLR = FXRBFXT.UPD_TLR;
        FXCOBFXT.SUP1 = FXRBFXT.SUP1;
    }
    public void B210_SET_WF_AP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WFCCAPV);
        WFCCAPV.FLG_INFO.FLG = 'C';
        WFCCAPV.AP_REF = FXRBFXT.KEY.TIK_NO;
        S000_CALL_WFZCAPV();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.TIK_NO1 = FXRBFXT.KEY.TIK_NO;
        WS_OUTPUT_DATA.STATUS = FXRBFXT.STATUS;
        WS_OUTPUT_DATA.CI_NO = FXRBFXT.CI_NO;
        WS_OUTPUT_DATA.TRA_AC = FXRBFXT.TRA_AC;
        WS_OUTPUT_DATA.CI_CNM = FXRBFXT.CI_CNM;
        WS_OUTPUT_DATA.CI_ENM = FXRBFXT.CI_ENM;
        WS_OUTPUT_DATA.ID_TYP = FXRBFXT.ID_TYP;
        WS_OUTPUT_DATA.ID_NO = FXRBFXT.ID_NO;
        WS_OUTPUT_DATA.CNTY_CD = FXRBFXT.CNTY_CD;
        WS_OUTPUT_DATA.CNTY_FLG = FXRBFXT.CNTY_FLG;
        WS_OUTPUT_DATA.PROD_CD = FXRBFXT.PROD_CD;
        WS_OUTPUT_DATA.PROD_NM = FXRBFXT.PROD_NM;
        WS_OUTPUT_DATA.B_CS_FLG = FXRBFXT.B_CS_FLG;
        WS_OUTPUT_DATA.BUY_CCY = FXRBFXT.BUY_CCY;
        WS_OUTPUT_DATA.BUY_AMT = FXRBFXT.BUY_AMT;
        WS_OUTPUT_DATA.S_CS_FLG = FXRBFXT.S_CS_FLG;
        WS_OUTPUT_DATA.SELL_CCY = FXRBFXT.SELL_CCY;
        WS_OUTPUT_DATA.SELL_AMT = FXRBFXT.SELL_AMT;
        WS_OUTPUT_DATA.EX_CODE = FXRBFXT.EX_CODE;
        WS_OUTPUT_DATA.EX_GROUP = FXRBFXT.EX_GROUP;
        WS_OUTPUT_DATA.SYS_RATE = FXRBFXT.SYS_RATE;
        WS_OUTPUT_DATA.PRE_RATE = FXRBFXT.PRE_RATE;
        WS_OUTPUT_DATA.SPREAD = FXRBFXT.SPREAD;
        WS_OUTPUT_DATA.EX_RATE = FXRBFXT.EX_RATE;
        WS_OUTPUT_DATA.VALUE_DT = FXRBFXT.VALUE_DT;
        WS_OUTPUT_DATA.OPT_END_DT = FXRBFXT.END_DT;
        WS_OUTPUT_DATA.BEFF_TM = FXRBFXT.BEFF_TM;
        WS_OUTPUT_DATA.CRT_TLR = FXRBFXT.CRE_TLR;
        WS_OUTPUT_DATA.UPD_TLR = FXRBFXT.UPD_TLR;
        WS_OUTPUT_DATA.SUP1 = FXRBFXT.SUP1;
        WS_OUTPUT_DATA.CRE_DT = FXRBFXT.CRE_DT;
    }
    public void B010_STABR_FXTBFXT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCRBFXT);
        IBS.init(SCCGWA, FXRBFXT);
        B010_1_TRANS_DATA();
        if (pgmRtn) return;
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        FXCRBFXT.FUNC = 'B';
        FXCRBFXT.OPT = 'B';
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
    }
    public void B010_STABR_EXTBFXT_OUTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCRBFXT);
        IBS.init(SCCGWA, FXRBFXT);
        FXCRBFXT.FUNC = 'B';
        FXCRBFXT.OPT = 'O';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
    }
    public void B010_READ_NEXT_BFXT_PROC() throws IOException,SQLException,Exception {
        FXCRBFXT.FUNC = 'B';
        FXCRBFXT.OPT = 'R';
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
    }
    public void B010_END_BROWSE_BFXT_PROC() throws IOException,SQLException,Exception {
        FXCRBFXT.FUNC = 'B';
        FXCRBFXT.OPT = 'E';
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
    }
    public void B010_1_TRANS_DATA() throws IOException,SQLException,Exception {
        FXRBFXT.KEY.TIK_NO = FXCSBFXT.KEY.TIK_NO;
        FXRBFXT.CI_NO = FXCSBFXT.CI_NO;
        FXRBFXT.ID_TYP = FXCSBFXT.ID_TYP;
        FXRBFXT.ID_NO = FXCSBFXT.ID_NO;
        FXRBFXT.TRA_AC = FXCSBFXT.TRA_AC;
        FXRBFXT.PROD_CD = FXCSBFXT.PROD_CD;
        FXRBFXT.STATUS = FXCSBFXT.STATUS;
        FXRBFXT.CRE_DT = FXCSBFXT.CRE_DT;
        FXRBFXT.SUP1_DT = FXCSBFXT.SUP1_DT;
        FXRBFXT.ORDER_BR = FXCSBFXT.ORDER_BR;
        CEP.TRC(SCCGWA, FXRBFXT.CI_NO);
        CEP.TRC(SCCGWA, FXRBFXT.CRE_DT);
        CEP.TRC(SCCGWA, FXRBFXT.SUP1_DT);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, INQ_ORG_LOW, BPCPORLO);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
    }
    public void S000_CALL_BPZPQCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CAL-CODE", BPCOQCAL);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSGSEQ.RC);
        }
    }
    public void S000_CALL_FXZRBFXT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "KKKKK");
        CEP.TRC(SCCGWA, FXCRBFXT.REC_LEN);
        IBS.CALLCPN(SCCGWA, "FX-R-MAIN-BFXT", FXCRBFXT);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
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
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void S000_CALL_WFZCAPV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "WF-GEN-GWA-GAPV", WFCCAPV);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
