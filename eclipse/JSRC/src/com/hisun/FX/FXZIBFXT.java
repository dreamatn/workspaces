package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class FXZIBFXT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "02H71";
    String K_OUTPUT_FMT1 = "CIX01";
    String K_FXRBFXT = "FXRBFXT ";
    char K_NORMAL = 'N';
    char K_USED = 'U';
    char K_PART_USED = 'P';
    String WS_ERR_MSG = " ";
    double WS_R_BUY_AMT = 0;
    double WS_R_SELL_AMT = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    FXCRBFXT FXCRBFXT = new FXCRBFXT();
    CICOCUST CICOCUST = new CICOCUST();
    FXRBFXT FXRBFXT = new FXRBFXT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    FXCIBFXT FXCIBFXT;
    public void MP(SCCGWA SCCGWA, FXCIBFXT FXCIBFXT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FXCIBFXT = FXCIBFXT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FXZIBFXT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_PROC();
        if (pgmRtn) return;
        if (FXCIBFXT.FUNC == 'I') {
            B002_INQ_FXTBFXT_PROC();
            if (pgmRtn) return;
        } else if (FXCIBFXT.FUNC == 'U') {
            B003_UPDATE_FXTBFXT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + FXCIBFXT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B001_CHECK_PROC() throws IOException,SQLException,Exception {
        if (FXCIBFXT.KEY.TIK_NO.equalsIgnoreCase("0") 
            || FXCIBFXT.KEY.TIK_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TICKET_NO_INVALID);
        }
    }
    public void B002_INQ_FXTBFXT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCRBFXT);
        IBS.init(SCCGWA, FXRBFXT);
        FXRBFXT.KEY.TIK_NO = FXCIBFXT.KEY.TIK_NO;
        CEP.TRC(SCCGWA, FXRBFXT.KEY.TIK_NO);
        FXCRBFXT.FUNC = 'I';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (FXCRBFXT.RETURN_INFO == 'F') {
            FXCIBFXT.RETURN_INFO = 'F';
            B010_RETURN_DATA_PROC();
            if (pgmRtn) return;
        } else if (FXCRBFXT.RETURN_INFO == 'N') {
            FXCIBFXT.RETURN_INFO = 'N';
        } else {
            FXCIBFXT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_RETURN_DATA_PROC() throws IOException,SQLException,Exception {
        FXCIBFXT.ORDER_BR = FXRBFXT.ORDER_BR;
        FXCIBFXT.STATUS = FXRBFXT.STATUS;
        CEP.TRC(SCCGWA, FXRBFXT.STATUS);
        FXCIBFXT.CI_NO = FXRBFXT.CI_NO;
        CEP.TRC(SCCGWA, FXRBFXT.CI_NO);
        FXCIBFXT.CI_CNM = FXRBFXT.CI_CNM;
        FXCIBFXT.CI_ENM = FXRBFXT.CI_ENM;
        FXCIBFXT.ID_TYP = FXRBFXT.ID_TYP;
        FXCIBFXT.ID_NO = FXRBFXT.ID_NO;
        FXCIBFXT.TRA_AC = FXRBFXT.TRA_AC;
        FXCIBFXT.PROD_CD = FXRBFXT.PROD_CD;
        FXCIBFXT.PROD_NM = FXRBFXT.PROD_NM;
        FXCIBFXT.INPUT_CCY = FXRBFXT.INPUT_CCY;
        FXCIBFXT.B_CS_FLG = FXRBFXT.B_CS_FLG;
        FXCIBFXT.BUY_CCY = FXRBFXT.BUY_CCY;
        FXCIBFXT.BUY_AMT = FXRBFXT.BUY_AMT;
        FXCIBFXT.S_CS_FLG = FXRBFXT.S_CS_FLG;
        FXCIBFXT.SELL_CCY = FXRBFXT.SELL_CCY;
        FXCIBFXT.SELL_AMT = FXRBFXT.SELL_AMT;
        FXCIBFXT.EX_CODE = FXRBFXT.EX_CODE;
        FXCIBFXT.EX_GROUP = FXRBFXT.EX_GROUP;
        FXCIBFXT.SYS_RATE = FXRBFXT.SYS_RATE;
        FXCIBFXT.PRE_RATE = FXRBFXT.PRE_RATE;
        FXCIBFXT.SPREAD = FXRBFXT.SPREAD;
        FXCIBFXT.EX_RATE = FXRBFXT.EX_RATE;
        FXCIBFXT.VALUE_DT = FXRBFXT.VALUE_DT;
        FXCIBFXT.OPT_END_DT = FXRBFXT.END_DT;
        FXCIBFXT.BEFF_TM = FXRBFXT.BEFF_TM;
        FXCIBFXT.TRA_TM = FXRBFXT.TRA_TM;
        CEP.TRC(SCCGWA, FXRBFXT.BEFF_TM);
        FXCIBFXT.RMK = FXRBFXT.RMK;
        FXCIBFXT.TS = FXRBFXT.TS;
        FXCIBFXT.SUP1_DT = FXRBFXT.SUP1_DT;
        FXCIBFXT.SUP1_TM = FXRBFXT.SUP1_TM;
    }
    public void B003_UPDATE_FXTBFXT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXCIBFXT.KEY.TIK_NO);
        B020_READUPD_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READUPD-END");
        if (FXCRBFXT.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_NOFND);
        } else if (FXCRBFXT.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "READ-NORMAL");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, "UPDATE-PROC-START");
            B030_UPDATE_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "UPDATE-PROC-END");
            CEP.TRC(SCCGWA, FXRBFXT.STATUS);
        }
        if (FXCRBFXT.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_FXRBFXT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRBFXT);
        IBS.init(SCCGWA, FXCRBFXT);
        FXRBFXT.KEY.TIK_NO = FXCIBFXT.KEY.TIK_NO;
        FXCRBFXT.FUNC = 'R';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GET BFXT-STS");
        CEP.TRC(SCCGWA, FXRBFXT.STATUS);
    }
    public void B030_UPDATE_PROC() throws IOException,SQLException,Exception {
        FXRBFXT.STATUS = 'C';
        FXRBFXT.KEY.TIK_NO = FXCIBFXT.KEY.TIK_NO;
        FXRBFXT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, FXCRBFXT);
        FXCRBFXT.FUNC = 'U';
        FXCRBFXT.REC_PTR = FXRBFXT;
        FXCRBFXT.REC_LEN = 1434;
        S000_CALL_FXZRBFXT();
        if (pgmRtn) return;
    }
    public void S000_CALL_FXZRBFXT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-R-MAIN-BFXT", FXCRBFXT);
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
