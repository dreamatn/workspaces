package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZQSCHT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_PRINCIPAL = 'P';
    char K_INTEREST = 'I';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    LNZQSCHT_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZQSCHT_WS_TEMP_VARIABLE();
    String WS_CONTRACT_NO = " ";
    LNZQSCHT_WS_OUTPUT_LIST WS_OUTPUT_LIST = new LNZQSCHT_WS_OUTPUT_LIST();
    char WS_SCHT_TYPE = ' ';
    short WS_TERM_NO_P = 0;
    short WS_TERM_NO_I = 0;
    char WS_EXIST_SCHE_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNRSCHT LNRSCHT = new LNRSCHT();
    LNCRSCHT LNCRSCHT = new LNCRSCHT();
    SCCGWA SCCGWA;
    LNB5210_AWA_5210 LNB5210_AWA_5210;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNCQSCHT LNCQSCHT;
    public void MP(SCCGWA SCCGWA, LNCQSCHT LNCQSCHT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCQSCHT = LNCQSCHT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZQSCHT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5210_AWA_5210>");
        LNB5210_AWA_5210 = (LNB5210_AWA_5210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_CONTRACT_NO = " ";
    }
    public void B200_GET_MIN_TERM_NO() throws IOException,SQLException,Exception {
        WS_SCHT_TYPE = K_INTEREST;
        R000_GET_MIN_TERM_NO();
        if (pgmRtn) return;
        WS_TERM_NO_I = LNCRSCHT.TERM_MIN;
        CEP.TRC(SCCGWA, LNCRSCHT.TERM_MIN);
        WS_SCHT_TYPE = K_PRINCIPAL;
        R000_GET_MIN_TERM_NO();
        if (pgmRtn) return;
        WS_TERM_NO_P = LNCRSCHT.TERM_MIN;
        CEP.TRC(SCCGWA, LNCRSCHT.TERM_MIN);
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        B200_GET_MIN_TERM_NO();
        if (pgmRtn) return;
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B300_01_STARTBR_LNTSCHT();
        if (pgmRtn) return;
        B300_02_READNEXT_LNTSCHT();
        if (pgmRtn) return;
        while (LNCRSCHT.RETURN_INFO != 'E' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_DATA_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B300_02_READNEXT_LNTSCHT();
            if (pgmRtn) return;
        }
        B300_03_ENDBR_LNTSCHT();
        if (pgmRtn) return;
    }
    public void B300_01_STARTBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSCHT);
        IBS.init(SCCGWA, LNCRSCHT);
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'H';
        LNRSCHT.KEY.TRAN_SEQ = LNCQSCHT.COMM_DATA.SEQ_NO;
        if (LNCQSCHT.COMM_DATA.CONTRACT_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCQSCHT.COMM_DATA.CONTRACT_NO);
        LNRSCHT.KEY.TYPE = LNCQSCHT.COMM_DATA.REPY_TYP;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B300_02_READNEXT_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'R';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B300_03_ENDBR_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'E';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_LIST);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 1;
        SCCMPAG.MAX_COL_NO = 169;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANSFER_OUTPUT_DATA();
        if (pgmRtn) return;
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_LIST);
        SCCMPAG.DATA_LEN = 169;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANSFER_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_LIST);
        WS_OUTPUT_LIST.WS_LST_REPY_MTH = LNRSCHT.KEY.TYPE;
        if (LNRSCHT.KEY.TYPE == K_PRINCIPAL) {
            WS_OUTPUT_LIST.WS_LST_TERM_NO = WS_TERM_NO_P;
            WS_TERM_NO_P = (short) (WS_TERM_NO_P + 1);
        } else {
            WS_OUTPUT_LIST.WS_LST_TERM_NO = WS_TERM_NO_I;
            WS_TERM_NO_I = (short) (WS_TERM_NO_I + 1);
        }
        WS_OUTPUT_LIST.WS_LST_VAL_DT = LNRSCHT.VAL_DTE;
        WS_OUTPUT_LIST.WS_LST_DUE_DT = LNRSCHT.DUE_DTE;
        WS_OUTPUT_LIST.WS_LST_ALL_IN_RAT = LNRSCHT.ALL_IN_RATE;
        WS_OUTPUT_LIST.WS_LST_REPY_AMT = LNRSCHT.AMOUNT;
        WS_OUTPUT_LIST.WS_LST_RMK = LNRSCHT.REMARK;
    }
    public void R000_GET_MIN_TERM_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'E';
        LNRSCHT.KEY.TRAN_SEQ = LNCQSCHT.COMM_DATA.SEQ_NO;
        if (LNCQSCHT.COMM_DATA.CONTRACT_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCQSCHT.COMM_DATA.CONTRACT_NO);
        LNRSCHT.KEY.SUB_CTA_NO = "" + 0;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.KEY.TYPE = WS_SCHT_TYPE;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void S000_CALL_SRC_LNZRSCHT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSCHT", LNCRSCHT);
        if (LNCRSCHT.RC.RC_CODE != 0) {
            if (LNCRSCHT.RETURN_INFO == 'N' 
                || LNCRSCHT.RETURN_INFO == 'E') {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRSCHT.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCQSCHT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCQSCHT=");
            CEP.TRC(SCCGWA, LNCQSCHT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
