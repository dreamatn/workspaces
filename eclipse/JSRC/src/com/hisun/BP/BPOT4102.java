package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4102 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP242";
    String CPN_ASMT_MAINTAIN = "BP-S-MAINT-ASMT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSASMT BPCSASMT = new BPCSASMT();
    BPRBKPM BPRBKPM = new BPRBKPM();
    SCCGWA SCCGWA;
    BPB4100_AWA_4100 BPB4100_AWA_4100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4102 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4100_AWA_4100>");
        BPB4100_AWA_4100 = (BPB4100_AWA_4100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        S000_UPD_PROC_CONTINUE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        R000_CHECK_RESULT_PROC();
    }
    public void S000_UPD_PROC_CONTINUE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSASMT);
        BPCSASMT.INFO.FUNC = 'U';
        BPCSASMT.DAT.DAT_TXT.BOOK_TYP = BPB4100_AWA_4100.BOOK_TYP;
        BPCSASMT.DAT.KEY.BOOK_FLG = BPB4100_AWA_4100.BOOK_FLG;
        BPCSASMT.DAT.DAT_TXT.BOOK_TYP = BPB4100_AWA_4100.BOOK_TYP;
        BPCSASMT.DAT.DAT_TXT.COA_FLG = BPB4100_AWA_4100.COA_FLG;
        BPCSASMT.DAT.DAT_TXT.ITM_LEN = BPB4100_AWA_4100.ITM_LEN;
        BPCSASMT.DAT.DAT_TXT.STS = BPB4100_AWA_4100.STS;
        BPCSASMT.DAT.DAT_TXT.REAL_SUS_ITM = BPB4100_AWA_4100.REAL_SUS;
        BPCSASMT.DAT.DAT_TXT.MEMO_SUS_ITM = BPB4100_AWA_4100.MEMO_SUS;
        BPCSASMT.DAT.DAT_TXT.PL_ITM = BPB4100_AWA_4100.PL_ITM;
        BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM = BPB4100_AWA_4100.CRS_BR_I;
        BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M = BPB4100_AWA_4100.CRS_BR_M;
        BPCSASMT.EFF_DT = BPB4100_AWA_4100.EFF_DATE;
        BPCSASMT.EXP_DT = BPB4100_AWA_4100.EXP_DATE;
        BPCSASMT.DAT.DAT_TXT.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPCSASMT.DAT.DAT_TXT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSASMT.DAT.DAT_TXT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCSASMT.DAT.DAT_TXT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCSASMT.DAT.DAT_TXT.SEQ1 = BPB4100_AWA_4100.SEQ1;
        BPCSASMT.DAT.DAT_TXT.SEQ2 = BPB4100_AWA_4100.SEQ2;
        BPCSASMT.DAT.DAT_TXT.SEQ3 = BPB4100_AWA_4100.SEQ3;
        BPCSASMT.DAT.DAT_TXT.SEQ4 = BPB4100_AWA_4100.SEQ4;
        BPCSASMT.DAT.DAT_TXT.SEQ5 = BPB4100_AWA_4100.SEQ5;
        BPCSASMT.DAT.DAT_TXT.LR_ITM = BPB4100_AWA_4100.LR_ITM;
        BPCSASMT.DAT.DAT_TXT.JS_ITM = BPB4100_AWA_4100.JS_ITM;
        BPCSASMT.DAT.DAT_TXT.DH_ITM = BPB4100_AWA_4100.DH_ITM;
        BPCSASMT.DAT.DAT_TXT.WH_ITM = BPB4100_AWA_4100.WH_ITM;
        BPCSASMT.DAT.DAT_TXT.FZ_DATE = BPB4100_AWA_4100.FZ_DATE;
        BPCSASMT.DAT.DAT_TXT.SEQ6 = BPB4100_AWA_4100.SEQ6;
        BPCSASMT.DAT.DAT_TXT.SEQ7 = BPB4100_AWA_4100.SEQ7;
        BPCSASMT.DAT.DAT_TXT.SEQ8 = BPB4100_AWA_4100.SEQ8;
        BPCSASMT.DAT.DAT_TXT.SEQ9 = BPB4100_AWA_4100.SEQ9;
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.BOOK_TYP);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.KEY.BOOK_FLG);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.BOOK_TYP);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.COA_FLG);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.ITM_LEN);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.STS);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.REAL_SUS_ITM);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.MEMO_SUS_ITM);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.PL_ITM);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M);
        CEP.TRC(SCCGWA, BPB4100_AWA_4100.SEQ1);
        CEP.TRC(SCCGWA, BPB4100_AWA_4100.SEQ2);
        CEP.TRC(SCCGWA, BPB4100_AWA_4100.SEQ3);
        CEP.TRC(SCCGWA, BPB4100_AWA_4100.SEQ4);
        CEP.TRC(SCCGWA, BPB4100_AWA_4100.SEQ5);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.LR_ITM);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.JS_ITM);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.DH_ITM);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.WH_ITM);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.FZ_DATE);
        CEP.TRC(SCCGWA, BPB4100_AWA_4100.SEQ6);
        CEP.TRC(SCCGWA, BPB4100_AWA_4100.SEQ7);
        CEP.TRC(SCCGWA, BPB4100_AWA_4100.SEQ8);
        CEP.TRC(SCCGWA, BPB4100_AWA_4100.SEQ9);
        BPCSASMT.INFO.FUNC = 'U';
        S000_CALL_BPZSASMT();
    }
    public void S000_CALL_BPZSASMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ASMT_MAINTAIN, BPCSASMT);
    }
    public void R000_CHECK_RESULT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "===555");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}