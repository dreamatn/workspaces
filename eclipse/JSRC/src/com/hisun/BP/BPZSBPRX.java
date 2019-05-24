package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;
import com.hisun.SM.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBPRX {
    int JIBS_tmp_int;
    BPZSBPRX_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSBPRX_WS_TEMP_VARIABLE();
    BPZSBPRX_WK_DATA WK_DATA = new BPZSBPRX_WK_DATA();
    BPZSBPRX_WS_DATA WS_DATA = new BPZSBPRX_WS_DATA();
    BPZSBPRX_WK_TEMP WK_TEMP = new BPZSBPRX_WK_TEMP();
    BPZSBPRX_WS_TEMP WS_TEMP = new BPZSBPRX_WS_TEMP();
    char WS_SEQ_FLG = ' ';
    BPCXP10 BPCXP10 = new BPCXP10();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT SMRTRTT;
    BPCSBPRX BPCSBPRX;
    public void MP(SCCGWA SCCGWA, BPCSBPRX BPCSBPRX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBPRX = BPCSBPRX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSBPRX return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        BPCRMBPM.RC.RC_MMO = "BP";
        BPCRMBPM.PTR = BPRPARM;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if ((BPCSBPRX.TYPE.trim().length() > 0) 
            && (BPCSBPRX.FUNC != 'A')) {
            B005_INQ_PROC();
        }
        B010_SET_SUB();
        B014_OUTPUT_FOR_PRINT();
    }
    public void B005_INQ_PROC() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'I';
        BPRPARM.KEY.TYPE = BPCSBPRX.TYPE;
        BPRPARM.KEY.CODE = BPCSBPRX.CODE;
        BPRPARM.EFF_DATE = BPCSBPRX.EFF_DATE;
        S000_CALL_BPZRMBPM();
        if (BPCRMBPM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPRPARM);
        }
    }
    public void B010_SET_SUB() throws IOException,SQLException,Exception {
        if (BPCSBPRX.TX_CD == null) BPCSBPRX.TX_CD = "";
        JIBS_tmp_int = BPCSBPRX.TX_CD.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) BPCSBPRX.TX_CD += " ";
        if (BPCSBPRX.TX_CD.substring(0, 3).trim().length() == 0) SCCSUBS.AP_CODE = 0;
        else SCCSUBS.AP_CODE = Short.parseShort(BPCSBPRX.TX_CD.substring(0, 3));
        if (BPCSBPRX.TX_CD == null) BPCSBPRX.TX_CD = "";
        JIBS_tmp_int = BPCSBPRX.TX_CD.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) BPCSBPRX.TX_CD += " ";
        if (BPCSBPRX.TX_CD.substring(4 - 1, 4 + 4 - 1).trim().length() == 0) SCCSUBS.TR_CODE = 0;
        else SCCSUBS.TR_CODE = Short.parseShort(BPCSBPRX.TX_CD.substring(4 - 1, 4 + 4 - 1));
        CEP.TRC(SCCGWA, SCCSUBS.AP_CODE);
        CEP.TRC(SCCGWA, SCCSUBS.TR_CODE);
        S000_CALL_SCZSUBS();
    }
    public void B014_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        BPCXP10.FUNC = BPCSBPRX.FUNC;
        BPCXP10.TYPE = BPCSBPRX.TYPE;
        BPCXP10.CODE = BPRPARM.KEY.CODE;
        if (BPCSBPRX.FUNC == 'A') {
            BPCXP10.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCXP10.EXP_DATE = 99991231;
        } else {
            BPCXP10.EFF_DATE = BPRPARM.EFF_DATE;
            BPCXP10.EXP_DATE = BPRPARM.EXP_DATE;
        }
        BPCXP10.DESC = BPRPARM.DESC;
        BPCXP10.CDESC = BPRPARM.CDESC;
        BPCXP10.VAL_TEXT = BPRPARM.BLOB_VAL;
        if (BPCSBPRX.TYPE.equalsIgnoreCase("CCY")) {
            S000_DEVSOS();
        }
        if (BPCSBPRX.TYPE.equalsIgnoreCase("BKAI")) {
            S000_DEVLXC();
        }
        CEP.TRC(SCCGWA, BPCXP10.FUNC);
        CEP.TRC(SCCGWA, BPCXP10.TYPE);
        CEP.TRC(SCCGWA, BPCXP10.CODE);
        CEP.TRC(SCCGWA, BPCXP10.EFF_DATE);
        CEP.TRC(SCCGWA, BPCXP10.EXP_DATE);
        CEP.TRC(SCCGWA, BPCXP10.DESC);
        CEP.TRC(SCCGWA, BPCXP10.CDESC);
        CEP.TRC(SCCGWA, BPCXP10.VAL_LEN);
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = BPCXP10;
        SCCFMT.DATA_LEN = ( 9647 ) - ( 9500 ) + BPCXP10.VAL_LEN;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_DEVSOS() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, WK_DATA);
        BPCXP10.VAL_TEXT = " ";
        WS_DATA.WS_CCY = WK_DATA.WK_CCY;
        WS_DATA.WS_CCY_CD = WK_DATA.WK_CCY_CD;
        WS_DATA.WS_CUR_NM = WK_DATA.WK_CUR_NM;
        WS_DATA.WS_EFF_DT = WK_DATA.WK_EFF_DT;
        WS_DATA.WS_EXP_DT = WK_DATA.WK_EXP_DT;
        WS_DATA.WS_CNTY_CD = WK_DATA.WK_CNTY_CD;
        WS_DATA.WS_CITY_CD = WK_DATA.WK_CITY_CD;
        WS_DATA.WS_UNIT_CURU_NAME = WK_DATA.WK_UNIT_CURU_NAME;
        WS_DATA.WS_CENT_CURU_NAME = WK_DATA.WK_CENT_CURU_NAME;
        WS_DATA.WS_RGN_CCY = WK_DATA.WK_RGN_CCY;
        WS_DATA.WS_DEC_MTH = WK_DATA.WK_DEC_MTH;
        WS_DATA.WS_CASH_MTH = WK_DATA.WK_CASH_MTH;
        WS_DATA.WS_RND_MTH = WK_DATA.WK_RND_MTH;
        WS_DATA.WS_TR_FLG = WK_DATA.WK_TR_FLG;
        WS_DATA.WS_CASH_FLG = WK_DATA.WK_CASH_FLG;
        WS_DATA.WS_CHGU_MTH = WK_DATA.WK_CHGU_MTH;
        WS_DATA.WS_EXH_FLG = WK_DATA.WK_EXH_FLG;
        WS_DATA.WS_CALR_STD = WK_DATA.WK_CALR_STD;
        WS_DATA.WS_CAL_CD = WK_DATA.WK_CAL_CD;
        WS_DATA.WS_UPT_DT = WK_DATA.WK_UPT_DT;
        WS_DATA.WS_UPT_TLR = WK_DATA.WK_UPT_TLR;
        WS_DATA.WS_ISR_DAYS = WK_DATA.WK_ISR_DAYS;
        WS_DATA.WS_BAL_DAYS = WK_DATA.WK_BAL_DAYS;
        WS_DATA.WS_CHG_CCY = WK_DATA.WK_CHG_CCY;
        WS_DATA.WS_CUR_CNM = WK_DATA.WK_CUR_CNM;
        WS_DATA.WS_HANG_LMT_AMT = WK_DATA.WK_HANG_LMT_AMT;
        CEP.TRC(SCCGWA, WK_DATA.WK_ISR_DAYS);
        CEP.TRC(SCCGWA, WS_DATA.WS_ISR_DAYS);
        CEP.TRC(SCCGWA, WK_DATA.WK_BAL_DAYS);
        CEP.TRC(SCCGWA, WS_DATA.WS_BAL_DAYS);
        CEP.TRC(SCCGWA, WK_DATA.WK_CHG_CCY);
        CEP.TRC(SCCGWA, WS_DATA.WS_CHG_CCY);
        BPCXP10.VAL_TEXT = " ";
        BPCXP10.VAL_TEXT = IBS.CLS2CPY(SCCGWA, WS_DATA);
        BPCXP10.VAL_LEN = 224;
    }
    public void S000_DEVLXC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, WK_TEMP);
        BPCXP10.VAL_TEXT = " ";
        WS_TEMP.WS_CLR_MTH = WK_TEMP.WK_CLR_MTH;
        WS_TEMP.WS_CLR_AC = WK_TEMP.WK_CLR_AC;
        WS_TEMP.WS_CCY_FLG = WK_TEMP.WK_CCY_FLG;
        WS_TEMP.WS_LOC_CCY2 = WK_TEMP.WK_LOC_CCY2;
        WS_TEMP.WS_ONL_PVCH = WK_TEMP.WK_ONL_PVCH;
        WS_TEMP.WS_ONL_GVCH = WK_TEMP.WK_ONL_GVCH;
        WS_TEMP.WS_EOY_MMDD = WK_TEMP.WK_EOY_MMDD;
        WS_TEMP.WS_GL_MEG = WK_TEMP.WK_GL_MEG;
        WS_TEMP.WS_GL_SET = WK_TEMP.WK_GL_SET;
        WS_TEMP.WS_BACK_DT = WK_TEMP.WK_BACK_DT;
        WS_TEMP.WS_FORWARD_DT = WK_TEMP.WK_FORWARD_DT;
        WS_TEMP.WS_CLOSE_DT = WK_TEMP.WK_CLOSE_DT;
        WS_TEMP.WS_CYC_FLG = WK_TEMP.WK_CYC_FLG;
        WS_TEMP.WS_RT_UP_FLG = WK_TEMP.WK_RT_UP_FLG;
        WS_TEMP.WS_ACCR_CYCLE = WK_TEMP.WK_ACCR_CYCLE;
        WS_TEMP.WS_ACCR_DAY = WK_TEMP.WK_ACCR_DAY;
        WS_TEMP.WS_ACCR_CNT = WK_TEMP.WK_ACCR_CNT;
        WS_TEMP.WS_IN_ITM = WK_TEMP.WK_IN_ITM;
        WS_TEMP.WS_OUT_ITM = WK_TEMP.WK_OUT_ITM;
        WS_TEMP.WS_RES_ITM = WK_TEMP.WK_RES_ITM;
        WS_TEMP.WS_UNIT_ITM = WK_TEMP.WK_UNIT_ITM;
        WS_TEMP.WS_YEAR_END_MTH = WK_TEMP.WK_YEAR_END_MTH;
        BPCXP10.VAL_TEXT = " ";
        BPCXP10.VAL_TEXT = IBS.CLS2CPY(SCCGWA, WS_TEMP);
        BPCXP10.VAL_LEN = 88;
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void S000_CALL_SCZSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
