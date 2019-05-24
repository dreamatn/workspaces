package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQPRD;
import com.hisun.CI.CICACCU;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class LNOT8120 {
    boolean pgmRtn = false;
    String K_FMT_CD = "LN812";
    LNOT8120_WS_ERR_MSG WS_ERR_MSG = new LNOT8120_WS_ERR_MSG();
    LNOT8120_WS_OUT_DATA WS_OUT_DATA = new LNOT8120_WS_OUT_DATA();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    LNCBALLM LNCBALLM = new LNCBALLM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    LNCICSTS LNCICSTS = new LNCICSTS();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    CICACCU CICACCU = new CICACCU();
    LNRICTL LNRICTL = new LNRICTL();
    LNRWOFF LNRWOFF = new LNRWOFF();
    LNRCONT LNRCONT = new LNRCONT();
    LNRAGRE LNRAGRE = new LNRAGRE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNB8120_AWA_8120 LNB8120_AWA_8120;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT8120 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8120_AWA_8120>");
        LNB8120_AWA_8120 = (LNB8120_AWA_8120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_GET_CONT_INF();
        if (pgmRtn) return;
        B200_GET_PROD_INF();
        if (pgmRtn) return;
        B300_GET_CI_INF();
        if (pgmRtn) return;
        B400_GET_LOAN_STS();
        if (pgmRtn) return;
        B500_GET_BALZ_INF();
        if (pgmRtn) return;
        B600_GET_WOFF_INF();
        if (pgmRtn) return;
        B700_FMT_OUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_GET_CONT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB8120_AWA_8120.LOAN_AC;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
    }
    public void B200_GET_PROD_INF() throws IOException,SQLException,Exception {
    }
    public void B300_GET_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        IBS.init(SCCGWA, LNCRAGRE);
        LNCRAGRE.FUNC = 'I';
        LNRAGRE.KEY.CONTRACT_NO = LNB8120_AWA_8120.LOAN_AC;
        S000_CALL_LNZRAGRE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.ENTY_TYP = '1';
        CICACCU.DATA.AGR_NO = LNRAGRE.PAPER_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
    }
    public void B400_GET_LOAN_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICSTS);
        LNCICSTS.COMM_DATA.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
        S000_CALL_LNZICSTS();
        if (pgmRtn) return;
    }
    public void B500_GET_BALZ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNB8120_AWA_8120.LOAN_AC;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
    }
    public void B600_GET_WOFF_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNB8120_AWA_8120.LOAN_AC;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNRWOFF);
            LNRWOFF.KEY.CONTRACT_NO = LNB8120_AWA_8120.LOAN_AC;
            LNRWOFF.KEY.TYPE = '1';
            T000_READ_LNTWOFF();
            if (pgmRtn) return;
        }
    }
    public void B700_FMT_OUT_PROC() throws IOException,SQLException,Exception {
        WS_OUT_DATA.WS_O_CTA_NO = LNB8120_AWA_8120.LOAN_AC;
        WS_OUT_DATA.WS_O_BOOK_BR = LNRCONT.BOOK_BR;
        WS_OUT_DATA.WS_O_CI_NO = CICACCU.DATA.CI_NO;
        WS_OUT_DATA.WS_O_CI_CNM = CICACCU.DATA.CI_CNM;
        WS_OUT_DATA.WS_O_PROD_CD = LNRCONT.PROD_CD;
        WS_OUT_DATA.WS_O_PROD_DE = BPCPQPRD.PRDT_NAME;
        WS_OUT_DATA.WS_O_CCY = LNRCONT.CCY;
        WS_OUT_DATA.WS_O_VAL_DT = LNRCONT.START_DATE;
        WS_OUT_DATA.WS_O_DUE_DT = LNRCONT.MAT_DATE;
        WS_OUT_DATA.WS_O_STS = LNCICSTS.COMM_DATA.STS;
        WS_OUT_DATA.WS_O_WOFF_NP = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[02-1].LOAN_BAL + LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[05-1].LOAN_BAL;
        WS_OUT_DATA.WS_O_WOFF_OP = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[06-1].LOAN_BAL;
        WS_OUT_DATA.WS_O_WOFF_NI = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[15-1].LOAN_BAL + LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL;
        WS_OUT_DATA.WS_O_WOFF_OI = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[22-1].LOAN_BAL;
        WS_OUT_DATA.WS_O_WOFF_O = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[42-1].LOAN_BAL;
        WS_OUT_DATA.WS_O_WOFF_L = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[52-1].LOAN_BAL;
