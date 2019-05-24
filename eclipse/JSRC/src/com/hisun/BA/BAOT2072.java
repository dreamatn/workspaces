package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2072 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BAT03";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ERR_FLG = ' ';
    BAOT2072_WS_OUT_DATA WS_OUT_DATA = new BAOT2072_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCFMT SCCFMT = new SCCFMT();
    BACURECF BACURECF = new BACURECF();
    BACURCRE BACURCRE = new BACURCRE();
    BARMST1 BARMST1 = new BARMST1();
    BACFMST1 BACFMST1 = new BACFMST1();
    BARCLCT BARCLCT = new BARCLCT();
    BACFCLCT BACFCLCT = new BACFCLCT();
    BACUBINF BACUBINF = new BACUBINF();
    SCCGWA SCCGWA;
    BAB2072_AWA_2072 BAB2072_AWA_2072;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAOT2072 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2072_AWA_2072>");
        BAB2072_AWA_2072 = (BAB2072_AWA_2072) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.ID_NO);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.DQ_DT);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.CD_AMT);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.ENCR);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.C_BR);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.C_REC_DT);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.C_PYE_DT);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.C_PYE_AC);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.C_PYE_NM);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.C_PYE_BR);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.C_PYE_BN);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.PAY_MTH);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.PAY_CHNL);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.RTN_RSN);
        CEP.TRC(SCCGWA, BAB2072_AWA_2072.REMK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, BACFMST1);
        BACFMST1.FUNC = 'T';
        BARMST1.BILL_NO = BAB2072_AWA_2072.ID_NO;
        CEP.TRC(SCCGWA, "AAAAAA");
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        if (BARMST1.BILL_TYP != 'P') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_BILL_TYP_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BARMST1.BLK_STS == '0') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_BLKSTS_NOT_AVAIL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BARCLCT);
        IBS.init(SCCGWA, BACFCLCT);
        BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BACFCLCT.FUNC = 'L';
        S000_CALL_BAZFCLCT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BARCLCT.CLCT_STS);
        CEP.TRC(SCCGWA, BARMST1.BILL_STS);
        if ((BACFCLCT.RETURN_INFO == 'F' 
            && BARCLCT.CLCT_STS == '0') 
            || BACFCLCT.RETURN_INFO == 'N') {
            if (BAB2072_AWA_2072.ID_NO.trim().length() == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ID_NO_M_INPUT;
                WS_FLD_NO = BAB2072_AWA_2072.ID_NO_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (BAB2072_AWA_2072.CD_AMT == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_CD_AMT_M_INPUT;
                WS_FLD_NO = BAB2072_AWA_2072.CD_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (BAB2072_AWA_2072.C_PYE_AC.trim().length() == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_C_PYE_AC_M_INPUT;
                WS_FLD_NO = BAB2072_AWA_2072.C_PYE_AC_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (BAB2072_AWA_2072.C_PYE_NM.trim().length() == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_C_PYE_NM;
                WS_FLD_NO = BAB2072_AWA_2072.C_PYE_NM_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "000");
            CEP.TRC(SCCGWA, BAB2072_AWA_2072.RTN_RSN);
            if (BAB2072_AWA_2072.RTN_RSN.trim().length() == 0) {
                CEP.TRC(SCCGWA, "AAA");
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_RTN_RSN_M_IN;
                WS_FLD_NO = BAB2072_AWA_2072.RTN_RSN_NO;
                CEP.TRC(SCCGWA, "1111");
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (BARMST1.BILL_STS != '1') {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_BILLSTS_NOT_AVAI;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (BAB2072_AWA_2072.CD_AMT != BARMST1.BILL_AMT) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_CD_AMT_UNMATCH;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (WS_ERR_FLG == 'Y') {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_INPUT_ERR;
                S000_ERR_MSG_PROC_LAST();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
            B200_MAIN_PROCESS_1();
            if (pgmRtn) return;
            B300_OUTPUT_PROCESS_1();
            if (pgmRtn) return;
        } else {
            if (BARMST1.BILL_STS != '1' 
                && BARMST1.BILL_STS != '3') {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_BILLSTS_NOT_AVAI;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BARCLCT.CLCT_STS == '1') {
                B200_MAIN_PROCESS_2();
                if (pgmRtn) return;
                B300_OUTPUT_PROCESS_2();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_MAIN_PROCESS_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACURECF);
        BACURECF.DATA.ID_NO = BAB2072_AWA_2072.ID_NO;
        BACURECF.DATA.DQ_DT = BAB2072_AWA_2072.DQ_DT;
        BACURECF.DATA.CD_AMT = BAB2072_AWA_2072.CD_AMT;
        BACURECF.DATA.ENCR = BAB2072_AWA_2072.ENCR;
        BACURECF.DATA.C_BR = BAB2072_AWA_2072.C_BR;
        BACURECF.DATA.C_REC_DT = BAB2072_AWA_2072.C_REC_DT;
        BACURECF.DATA.C_PYE_DT = BAB2072_AWA_2072.C_PYE_DT;
        BACURECF.DATA.C_PYE_AC = BAB2072_AWA_2072.C_PYE_AC;
        BACURECF.DATA.C_PYE_NM = BAB2072_AWA_2072.C_PYE_NM;
        BACURECF.DATA.C_PYE_BR = BAB2072_AWA_2072.C_PYE_BR;
        BACURECF.DATA.C_PYE_BN = BAB2072_AWA_2072.C_PYE_BN;
        BACURECF.DATA.PAY_MTH = BAB2072_AWA_2072.PAY_MTH;
        BACURECF.DATA.PAY_CHNL = BAB2072_AWA_2072.PAY_CHNL;
        BACURECF.DATA.RTN_RSN = BAB2072_AWA_2072.RTN_RSN;
        BACURECF.DATA.REMK = BAB2072_AWA_2072.REMK;
        S000_CALL_BAZURECF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BACURECF.RC);
    }
    public void B200_MAIN_PROCESS_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACURCRE);
        BACURCRE.DATA.ID_NO = BAB2072_AWA_2072.ID_NO;
        BACURCRE.DATA.RTN_RSN = BAB2072_AWA_2072.RTN_RSN;
        BACURCRE.DATA.REMK = BAB2072_AWA_2072.REMK;
        S000_CALL_BAZURCRE();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_PROCESS_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_OUT_DATA.WS_OUT_CLCT_STS = BACURECF.DATA.CLCT_STS;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_CLCT_STS);
    }
    public void B300_OUTPUT_PROCESS_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_OUT_DATA.WS_OUT_CLCT_STS = BACURCRE.DATA.CLCT_STS;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_CLCT_STS);
    }
    public void S000_CALL_BAZUBINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-BILL-INF", BACUBINF);
        if (BACUBINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUBINF.RC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZURECF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-CLCT-RECF", BACURECF);
        if (BACURECF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACURECF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZURCRE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-CLCT-RCRE", BACURCRE);
        if (BACURCRE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACURCRE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        if (BACFMST1.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFCLCT() throws IOException,SQLException,Exception {
        BACFCLCT.REC_PTR = BARCLCT;
        BACFCLCT.REC_LEN = 1021;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFCLCT", BACFCLCT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        WS_ERR_FLG = 'Y';
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
