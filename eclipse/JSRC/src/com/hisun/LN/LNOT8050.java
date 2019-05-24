package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8050 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNRRATH LNRRATH = new LNRRATH();
    LNCRRATH LNCRRATH = new LNCRRATH();
    LNCSRATH LNCSRATH = new LNCSRATH();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRCONT LNRCONT = new LNRCONT();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    LNB8050_AWA_8050 LNB8050_AWA_8050;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT8050 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8050_AWA_8050>");
        LNB8050_AWA_8050 = (LNB8050_AWA_8050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        LNCRRATH.RC.RC_MMO = "LN";
        LNCRRATH.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CALL_SVR_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB8050_AWA_8050.LOAN_AC.trim().length() > 0) {
            IBS.init(SCCGWA, LNRCONT);
            IBS.init(SCCGWA, LNCRCONT);
            LNRCONT.KEY.CONTRACT_NO = LNB8050_AWA_8050.LOAN_AC;
            LNCRCONT.FUNC = 'I';
            S000_CALL_LNZRCONT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB8050_AWA_8050.LOAN_AC);
        if (LNB8050_AWA_8050.LOAN_AC.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB8050_AWA_8050.INT_TYP);
        if (LNB8050_AWA_8050.INT_TYP != ' ') {
            if (LNB8050_AWA_8050.INT_TYP != 'N' 
                && LNB8050_AWA_8050.INT_TYP != 'O' 
                && LNB8050_AWA_8050.INT_TYP != 'L' 
                && LNB8050_AWA_8050.INT_TYP != 'P') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_INT_RATE_TYP_N_EXIST;
                WS_FLD_NO = LNB8050_AWA_8050.INT_TYP_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSRATH);
        LNCSRATH.INT_TYP = LNB8050_AWA_8050.INT_TYP;
        LNCSRATH.CONTRACT_NO = LNB8050_AWA_8050.LOAN_AC;
        LNCSRATH.MAT_DT = LNB8050_AWA_8050.MATU_DT;
        LNCSRATH.SRT_DT = LNB8050_AWA_8050.START_DT;
        CEP.TRC(SCCGWA, LNB8050_AWA_8050.PAGE_ROW);
        CEP.TRC(SCCGWA, LNB8050_AWA_8050.PAGE_NUM);
        if (LNB8050_AWA_8050.PAGE_ROW == 0) {
            LNCSRATH.PAGE_ROW = 25;
        } else {
            if (LNB8050_AWA_8050.PAGE_ROW > 25) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                LNCSRATH.PAGE_ROW = LNB8050_AWA_8050.PAGE_ROW;
            }
        }
        if (LNB8050_AWA_8050.PAGE_NUM == 0) {
            LNCSRATH.PAGE_NUM = 1;
        } else {
            LNCSRATH.PAGE_NUM = LNB8050_AWA_8050.PAGE_NUM;
        }
        CEP.TRC(SCCGWA, LNCSRATH.INT_TYP);
        CEP.TRC(SCCGWA, LNCSRATH.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNB8050_AWA_8050.MATU_DT);
        CEP.TRC(SCCGWA, LNB8050_AWA_8050.START_DT);
        CEP.TRC(SCCGWA, LNB8050_AWA_8050.PAGE_ROW);
        CEP.TRC(SCCGWA, LNB8050_AWA_8050.PAGE_NUM);
        S000_CALL_LNZSRATH();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRATH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-BRW-RATH", LNCSRATH);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
