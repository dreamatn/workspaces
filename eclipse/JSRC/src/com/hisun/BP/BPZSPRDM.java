package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSPRDM {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "CASH PARAMETER MAINTAIN";
    String K_CPY_BPRCPROD = "BPRCPROD";
    char K_ALL = '4';
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_PARM_BRW = "BP-PARM-BROWSE      ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    int K_MAX_CNT = 5000;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_INFO = " ";
    BPZSPRDM_WS_CPROD_KEY WS_CPROD_KEY = new BPZSPRDM_WS_CPROD_KEY();
    BPZSPRDM_WS_OUT_HEAD WS_OUT_HEAD = new BPZSPRDM_WS_OUT_HEAD();
    BPZSPRDM_WS_OUT_DETAIL WS_OUT_DETAIL = new BPZSPRDM_WS_OUT_DETAIL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRCPROD BPRCPROD = new BPRCPROD();
    BPRCPROD BPROPROD = new BPRCPROD();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRPROD BPRPROD = new BPRPROD();
    BPCOPRDI BPCOPRDI = new BPCOPRDI();
    BPCPPRDQ BPCPPRDQ = new BPCPPRDQ();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    SCCGWA SCCGWA;
    BPCSPRDM BPCSPRDM;
    public void MP(SCCGWA SCCGWA, BPCSPRDM BPCSPRDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSPRDM = BPCSPRDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSPRDM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSPRDM);
        CEP.TRC(SCCGWA, BPCSPRDM.DATA_INFO.FUNC);
        if (BPCSPRDM.DATA_INFO.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPRDM.DATA_INFO.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPRDM.DATA_INFO.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPRDM.DATA_INFO.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPRDM.DATA_INFO.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSPRDM.DATA_INFO.FUNC != 'B') {
            B060_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCUCNGM.FUNC = 'Q';
        BPCUCNGM.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
        BPCUCNGM.KEY.CNTR_TYPE = "CAS";
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCUCNGM.FUNC = 'A';
        BPCUCNGM.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
        BPCUCNGM.KEY.CNTR_TYPE = "CAS";
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '4';
        IBS.CLONE(SCCGWA, BPRCPROD, BPROPROD);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '2';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCUCNGM.FUNC = 'U';
        BPCUCNGM.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
        BPCUCNGM.KEY.CNTR_TYPE = "CAS";
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '4';
        IBS.CLONE(SCCGWA, BPRCPROD, BPROPROD);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '1';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCUCNGM.FUNC = 'D';
        BPCUCNGM.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
        BPCUCNGM.KEY.CNTR_TYPE = "CAS";
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        B060_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCPROD);
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        WS_CPROD_KEY.WS_CPROD_CCY = BPCSPRDM.DATA_INFO.CCY;
        WS_CPROD_KEY.WS_CPROD_STAT = BPCSPRDM.DATA_INFO.STAT;
        WS_CPROD_KEY.WS_CPROD_CS_KIND = BPCSPRDM.DATA_INFO.CS_KIND;
        BPRCPROD.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
        BPRPARM.KEY.CODE = BPRCPROD.KEY.CD;
        BPRPARM.KEY.TYPE = "CPROD";
        CEP.TRC(SCCGWA, BPCSPRDM.DATA_INFO.CCY);
        CEP.TRC(SCCGWA, BPCSPRDM.DATA_INFO.STAT);
        CEP.TRC(SCCGWA, BPCSPRDM.DATA_INFO.CS_KIND);
        CEP.TRC(SCCGWA, WS_CPROD_KEY);
        BPRCPROD.KEY.TYP = "CPROD";
        BPCRMBPM.FUNC = 'S';
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRMBPM);
        CEP.TRC(SCCGWA, BPRCPROD);
        IBS.CPY2CLS(SCCGWA, BPRCPROD.KEY.CD, WS_CPROD_KEY);
        CEP.TRC(SCCGWA, BPRCPROD.KEY.CD);
        B050_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1111111111111");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
        for (WS_CNT = 1; !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
            && !(!BPRCPROD.KEY.TYP.equalsIgnoreCase("CPROD")) 
            && !(!BPCSPRDM.DATA_INFO.CCY.equalsIgnoreCase(BPRCPROD.DATA_TXT.CCY) 
            || BPCSPRDM.DATA_INFO.CCY.trim().length() <= 0) 
            && (BPCSPRDM.DATA_INFO.STAT == BPRCPROD.DATA_TXT.STAT 
            || BPCSPRDM.DATA_INFO.STAT == ' ') 
            && (BPCSPRDM.DATA_INFO.CS_KIND == BPRCPROD.DATA_TXT.CS_KIND 
            || BPCSPRDM.DATA_INFO.CS_KIND == ' ') 
            && WS_CNT <= K_MAX_CNT 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CPROD_KEY.WS_CPROD_CCY);
            CEP.TRC(SCCGWA, BPCSPRDM.DATA_INFO.CCY);
            CEP.TRC(SCCGWA, BPRCPROD.DATA_TXT.CCY);
            B050_03_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, BPRCPROD.KEY.CD, WS_CPROD_KEY);
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B050_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 102;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_02_OUTPUT_HEADLINE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_HEAD);
        SCCMPAG.DATA_LEN = 1;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_OUT_DETAIL.WS_OUT_CCY = WS_CPROD_KEY.WS_CPROD_CCY;
        WS_OUT_DETAIL.WS_OUT_STAT = WS_CPROD_KEY.WS_CPROD_STAT;
        WS_OUT_DETAIL.WS_OUT_CS_KIND = WS_CPROD_KEY.WS_CPROD_CS_KIND;
        WS_OUT_DETAIL.WS_OUT_DESC = BPRCPROD.DESC;
        WS_OUT_DETAIL.WS_OUT_CDESC = BPRCPROD.CDESC;
        WS_OUT_DETAIL.WS_OUT_EFF_DATE = BPRCPROD.DATA_TXT.EFF_DATE;
        WS_OUT_DETAIL.WS_OUT_EXP_DATE = BPRCPROD.DATA_TXT.EXP_DATE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_DETAIL);
        SCCMPAG.DATA_LEN = 102;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.OLD_DAT_PT = null;
        BPCPNHIS.INFO.NEW_DAT_PT = null;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCPROD;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRCPROD;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = null;
        BPCPNHIS.INFO.NEW_DAT_PT = null;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCPROD;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROPROD;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRCPROD;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPROPROD);
        CEP.TRC(SCCGWA, BPRCPROD);
        CEP.TRC(SCCGWA, BPROPROD.DATA_TXT);
        CEP.TRC(SCCGWA, BPRCPROD.DATA_TXT);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.OLD_DAT_PT = null;
        BPCPNHIS.INFO.NEW_DAT_PT = null;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCPROD;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROPROD;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOPRDI);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSPRDM.DATA_INFO.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOPRDI;
        SCCFMT.DATA_LEN = 132;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRCPROD);
        IBS.init(SCCGWA, BPCUCNGM);
        BPRCPROD.KEY.TYP = "CPROD";
        BPCPRMM.EFF_DT = BPCSPRDM.DATA_INFO.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSPRDM.DATA_INFO.EXP_DATE;
        WS_CPROD_KEY.WS_CPROD_CCY = BPCSPRDM.DATA_INFO.CCY;
        CEP.TRC(SCCGWA, "111");
        CEP.TRC(SCCGWA, BPCSPRDM.DATA_INFO.CS_KIND);
        WS_CPROD_KEY.WS_CPROD_STAT = BPCSPRDM.DATA_INFO.STAT;
        WS_CPROD_KEY.WS_CPROD_CS_KIND = BPCSPRDM.DATA_INFO.CS_KIND;
        BPRCPROD.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
        BPRCPROD.DATA_TXT.CCY = BPCSPRDM.DATA_INFO.CCY;
        BPRCPROD.DATA_TXT.STAT = BPCSPRDM.DATA_INFO.STAT;
        BPRCPROD.DATA_TXT.CS_KIND = BPCSPRDM.DATA_INFO.CS_KIND;
        BPRCPROD.DESC = BPCSPRDM.DATA_INFO.DESC;
        BPRCPROD.CDESC = BPCSPRDM.DATA_INFO.CDESC;
        BPCUCNGM.DATA[1-1].GLMST = BPCSPRDM.DATA_INFO.ACCT_CD;
        BPCUCNGM.DATA[2-1].GLMST = BPCSPRDM.DATA_INFO.PL_CD;
        BPRCPROD.DATA_TXT.ACCT_CD = 0;
        BPRCPROD.DATA_TXT.PL_CD = 0;
        BPRCPROD.DATA_TXT.AC_SEQ = BPCSPRDM.DATA_INFO.AC_SEQ;
        BPRCPROD.DATA_TXT.ACCT_LVL = BPCSPRDM.DATA_INFO.ACCT_LVL;
        BPRCPROD.DATA_TXT.ACCT_TYP = BPCSPRDM.DATA_INFO.ACCT_TYP;
        BPRCPROD.DATA_TXT.SUP_FLG = BPCSPRDM.DATA_INFO.SUP_FLG;
        BPRCPROD.DATA_TXT.EFF_DATE = BPCSPRDM.DATA_INFO.EFF_DATE;
        BPRCPROD.DATA_TXT.EXP_DATE = BPCSPRDM.DATA_INFO.EXP_DATE;
        BPRCPROD.DATA_LEN = 54;
        CEP.TRC(SCCGWA, BPCPRMM);
        CEP.TRC(SCCGWA, BPRCPROD);
        CEP.TRC(SCCGWA, BPCSPRDM);
        CEP.TRC(SCCGWA, WS_CPROD_KEY);
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCSPRDM.DATA_INFO.FUNC == 'I') {
            BPCOPRDI.FUNC = BPCSPRDM.DATA_INFO.FUNC_CODE;
        } else {
            BPCOPRDI.FUNC = BPCSPRDM.DATA_INFO.FUNC;
        }
        BPCOPRDI.CCY = WS_CPROD_KEY.WS_CPROD_CCY;
        BPCOPRDI.STAT = WS_CPROD_KEY.WS_CPROD_STAT;
        BPCOPRDI.CS_KIND = WS_CPROD_KEY.WS_CPROD_CS_KIND;
        BPCOPRDI.TYPE = BPRCPROD.KEY.TYP;
        BPCOPRDI.DESC = BPRCPROD.DESC;
        BPCOPRDI.CDESC = BPRCPROD.CDESC;
        BPCOPRDI.ACCT_CD = BPCUCNGM.DATA[1-1].GLMST;
        BPCOPRDI.PL_CD = BPCUCNGM.DATA[2-1].GLMST;
        BPCOPRDI.AC_SEQ = BPRCPROD.DATA_TXT.AC_SEQ;
        BPCOPRDI.ACCT_LVL = BPRCPROD.DATA_TXT.ACCT_LVL;
        BPCOPRDI.ACCT_TYP = BPRCPROD.DATA_TXT.ACCT_TYP;
        BPCOPRDI.SUP_FLG = BPRCPROD.DATA_TXT.SUP_FLG;
        BPCOPRDI.EFF_DATE = BPCPRMM.EFF_DT;
        BPCOPRDI.EXP_DATE = BPCPRMM.EXP_DT;
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RETURN_INFO == 'N' 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            CEP.TRC(SCCGWA, BPRCPROD);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            BPRCPROD.KEY.TYP = BPRPARM.KEY.TYPE;
            BPRCPROD.KEY.CD = BPRPARM.KEY.CODE;
            BPRCPROD.DESC = BPRPARM.DESC;
            BPRCPROD.CDESC = BPRPARM.CDESC;
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, BPRCPROD.DATA_TXT);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCPRMM.DAT_PTR = BPRCPROD;
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_INFO = " ";
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PROD_NOTFND;
                if (WS_INFO == null) WS_INFO = "";
                JIBS_tmp_int = WS_INFO.length();
                for (int i=0;i<240-JIBS_tmp_int;i++) WS_INFO += " ";
                WS_INFO = "T" + WS_INFO.substring(1);
                if (WS_INFO == null) WS_INFO = "";
                JIBS_tmp_int = WS_INFO.length();
                for (int i=0;i<240-JIBS_tmp_int;i++) WS_INFO += " ";
                WS_INFO = WS_INFO.substring(0, 2 - 1) + "CPROD" + WS_INFO.substring(2 + "CPROD".length() - 1);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM     ", BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_INFO);
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
