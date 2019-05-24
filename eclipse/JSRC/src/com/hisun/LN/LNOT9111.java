package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT9111 {
    int JIBS_tmp_int;
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    char K_ERROR = 'E';
    String K_HIS_REMARKS = "DEBIT TRADE";
    String K_PROD_CD = "DEBIT";
    String K_HIS_CPB_NM1 = "DEBIT";
    String K_HIS_RMKS = "DEBIT TRADE";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCOWLAD LNCOWLAD = new LNCOWLAD();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    CICSSTC CICSSTC = new CICSSTC();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCUTACH LNCUTACH = new LNCUTACH();
    SCCGWA SCCGWA;
    LNB9101_AWA_9101 LNB9101_AWA_9101;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT9111 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB9101_AWA_9101>");
        LNB9101_AWA_9101 = (LNB9101_AWA_9101) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANCHE_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB9101_AWA_9101);
        CEP.TRC(SCCGWA, LNB9101_AWA_9101.PI_TYPE);
        CEP.TRC(SCCGWA, LNB9101_AWA_9101.PI_DL);
        CEP.TRC(SCCGWA, LNB9101_AWA_9101.CCY);
        CEP.TRC(SCCGWA, LNB9101_AWA_9101.AMT);
        CEP.TRC(SCCGWA, LNB9101_AWA_9101.CTA_NO);
        CEP.TRC(SCCGWA, LNB9101_AWA_9101.SPE_FLG);
        CEP.TRC(SCCGWA, LNB9101_AWA_9101.VAL_DT);
        CEP.TRC(SCCGWA, LNB9101_AWA_9101.MMO);
        if (LNB9101_AWA_9101.PI_TYPE == ' ') {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_AMT_TYP_I;
            if (LNB9101_AWA_9101.PI_TYPE == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+LNB9101_AWA_9101.PI_TYPE);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB9101_AWA_9101.CCY.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_MUST_I_CCY;
            if (LNB9101_AWA_9101.CCY.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB9101_AWA_9101.CCY);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB9101_AWA_9101.AMT == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_DRAW_AMT;
            WS_FLD_NO = (short) LNB9101_AWA_9101.AMT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB9101_AWA_9101.CTA_NO.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            if (LNB9101_AWA_9101.CTA_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB9101_AWA_9101.CTA_NO);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB9101_AWA_9101.VAL_DT == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_VAL_DT_M_INPUT;
            if (LNB9101_AWA_9101.CTA_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB9101_AWA_9101.CTA_NO);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB9101_AWA_9101.MMO == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_TRADE_ID_I;
            WS_FLD_NO = LNB9101_AWA_9101.MMO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_INPUT;
            S000_ERR_MSG_PROC_LAST();
        }
        R00_CHECK_ERROR();
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUTACH);
        LNCUTACH.COMM_DATA.PI_TYPE = LNB9101_AWA_9101.PI_TYPE;
        LNCUTACH.COMM_DATA.PI_DETAIL = LNB9101_AWA_9101.PI_DL;
        LNCUTACH.COMM_DATA.CCY = LNB9101_AWA_9101.CCY;
        LNCUTACH.COMM_DATA.AMT = LNB9101_AWA_9101.AMT;
        LNCUTACH.COMM_DATA.CTA_NO = LNB9101_AWA_9101.CTA_NO;
        LNCUTACH.COMM_DATA.SPE_FLG = LNB9101_AWA_9101.SPE_FLG;
        LNCUTACH.COMM_DATA.VAL_DT = LNB9101_AWA_9101.VAL_DT;
        LNCUTACH.COMM_DATA.MMO = "" + LNB9101_AWA_9101.MMO;
        JIBS_tmp_int = LNCUTACH.COMM_DATA.MMO.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) LNCUTACH.COMM_DATA.MMO = "0" + LNCUTACH.COMM_DATA.MMO;
        LNCUTACH.COMM_DATA.DBCR_IND = 'C';
        S000_CALL_LNZUTACH();
    }
    public void S000_CALL_LNZUTACH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-TRFN-VCH", LNCUTACH);
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LNT85";
        SCCFMT.DATA_PTR = LNCUTACH.COMM_DATA;
        SCCFMT.DATA_LEN = 132;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_MSGID);
        CEP.ERRC(SCCGWA, WS_MSGID, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
