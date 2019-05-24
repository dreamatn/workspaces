package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSHCHK {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_PARM_BRW = "BP-PARM-BROWSE      ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY       ";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY       ";
    String CPN_BP_I_INQ_CNCI = "BP-Q-CNTY-CITY-IFO  ";
    String K_HIS_REMARKS = "HOLIDAY CHECK CODE MAINTAIN";
    String K_CPY_BPRHCHK = "BPRHCHK";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 50;
    int WS_INIT_EXP_DATE = 99991221;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSHCHK_WS_HCHK_KEY WS_HCHK_KEY = new BPZSHCHK_WS_HCHK_KEY();
    BPZSHCHK_WS_HCHK_DETAIL WS_HCHK_DETAIL = new BPZSHCHK_WS_HCHK_DETAIL();
    String WS_TMP_CNTY_CD = " ";
    String WS_TMP_CITY_CD = " ";
    char WS_TBL_HCHK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRHCHK BPRHCHK = new BPRHCHK();
    BPRHCHK BPROHCHK = new BPRHCHK();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCOHCKO BPCOHCKO = new BPCOHCKO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCIQCIT BPCIQCIT = new BPCIQCIT();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    BPCQCNCI BPCQCNCI = new BPCQCNCI();
    SCCGWA SCCGWA;
    BPCSHCHK BPCSHCHK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSHCHK BPCSHCHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSHCHK = BPCSHCHK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSHCHK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCIQCIT);
        IBS.init(SCCGWA, BPCIQCNT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSHCHK.FUNC == 'I') {
            B070_CHECK_INPUT();
            if (pgmRtn) return;
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSHCHK.FUNC == 'A') {
            B070_CHECK_INPUT();
            if (pgmRtn) return;
            B020_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSHCHK.FUNC == 'M') {
            B070_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSHCHK.FUNC == 'D') {
            B070_CHECK_INPUT();
            if (pgmRtn) return;
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSHCHK.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSHCHK.FUNC != 'B') {
            B060_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B070_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSHCHK.CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HCHKCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSHCHK.FUNC == 'M' 
            || BPCSHCHK.FUNC == 'A') {
            WS_TMP_CNTY_CD = BPCSHCHK.CNTY_CD1;
            WS_TMP_CITY_CD = BPCSHCHK.CITY_CD1;
            B070_01_CHK_PARM_VALID();
            if (pgmRtn) return;
            WS_TMP_CNTY_CD = BPCSHCHK.CNTY_CD2;
            WS_TMP_CITY_CD = BPCSHCHK.CITY_CD2;
            B070_01_CHK_PARM_VALID();
            if (pgmRtn) return;
            WS_TMP_CNTY_CD = BPCSHCHK.CNTY_CD3;
            WS_TMP_CITY_CD = BPCSHCHK.CITY_CD3;
            B070_01_CHK_PARM_VALID();
            if (pgmRtn) return;
            WS_TMP_CNTY_CD = BPCSHCHK.CNTY_CD4;
            WS_TMP_CITY_CD = BPCSHCHK.CITY_CD4;
            B070_01_CHK_PARM_VALID();
            if (pgmRtn) return;
        }
    }
    public void B070_01_CHK_PARM_VALID() throws IOException,SQLException,Exception {
        if (WS_TMP_CNTY_CD.trim().length() > 0 
            || WS_TMP_CNTY_CD.trim().length() == 0 
            && WS_TMP_CITY_CD.trim().length() > 0) {
            B070_01_00_CALL_QCNCI();
            if (pgmRtn) return;
        }
    }
    public void B070_01_00_CALL_QCNCI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNCI);
        BPCQCNCI.INPUT_DAT.CNTY_CD = WS_TMP_CNTY_CD;
        BPCQCNCI.INPUT_DAT.CITY_CD = WS_TMP_CITY_CD;
        CEP.TRC(SCCGWA, BPCQCNCI.INPUT_DAT.CNTY_CD);
        CEP.TRC(SCCGWA, BPCQCNCI.INPUT_DAT.CITY_CD);
        S000_CALL_BPZQCNCI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCNCI.RC.RC_CODE);
        if (BPCQCNCI.RC.RC_CODE > 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNCI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_01_01_CALL_QCNTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIQCNT);
        BPCIQCNT.INPUT_DAT.CNTY_CD = WS_TMP_CNTY_CD;
        S000_CALL_BPZIQCNT();
        if (pgmRtn) return;
        if (BPCIQCNT.RC.RC_CODE > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_01_02_CALL_QCITY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIQCIT);
        BPCIQCIT.INPUT_DAT.CNTY_CD = WS_TMP_CNTY_CD;
        BPCIQCIT.INPUT_DAT.CITY_CD = WS_TMP_CITY_CD;
        S000_CALL_BPZIQCIT();
        if (pgmRtn) return;
        if (BPCIQCIT.RC.RC_CODE > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CITY_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRHCHK);
        IBS.init(SCCGWA, BPCPRMM);
        if (BPCSHCHK.FUNC_CODE == 'A') {
            BPRHCHK.KEY.TYP = "HCHKC";
        } else {
            BPRHCHK.KEY.TYP = "HCHKC";
            WS_HCHK_KEY.WS_HCHK_CD = BPCSHCHK.CD;
            BPRHCHK.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_HCHK_KEY);
            BPRHCHK.DATA_TXT.EFF_DT = BPCSHCHK.EFF_DATE;
            BPCPRMM.EFF_DT = BPCSHCHK.EFF_DATE;
            BPCPRMM.FUNC = '3';
            CEP.TRC(SCCGWA, BPRHCHK.KEY.CD);
            CEP.TRC(SCCGWA, BPRHCHK.DATA_TXT.EFF_DT);
            BPCPRMM.DAT_PTR = BPRHCHK;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HCHK_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRHCHK);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        WS_HCHK_KEY.WS_HCHK_CD = BPCSHCHK.CD;
        BPRHCHK.DATA_TXT.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.EXP_DT = WS_INIT_EXP_DATE;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPRHCHK.KEY.TYP = "HCHKC";
        BPRHCHK.DATA_LEN = 67;
        BPCPRMM.DAT_PTR = BPRHCHK;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRHCHK;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRHCHK);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        WS_HCHK_KEY.WS_HCHK_CD = BPCSHCHK.CD;
        BPRHCHK.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_HCHK_KEY);
        BPCPRMM.EFF_DT = BPCSHCHK.EFF_DATE;
        BPRHCHK.DATA_TXT.EFF_DT = BPCSHCHK.EFF_DATE;
        BPRHCHK.KEY.TYP = "HCHKC";
        BPCPRMM.DAT_PTR = BPRHCHK;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HCHK_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRHCHK, BPROHCHK);
        WS_HCHK_KEY.WS_HCHK_CD = BPCSHCHK.CD;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPRHCHK.KEY.TYP = "HCHKC";
        BPCPRMM.EFF_DT = BPCSHCHK.EFF_DATE;
        BPRHCHK.DATA_TXT.EFF_DT = BPCSHCHK.EFF_DATE;
        BPCPRMM.FUNC = '2';
        BPRHCHK.DATA_LEN = 67;
        BPCPRMM.DAT_PTR = BPRHCHK;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRHCHK;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROHCHK;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRHCHK;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRHCHK);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        WS_HCHK_KEY.WS_HCHK_CD = BPCSHCHK.CD;
        BPRHCHK.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_HCHK_KEY);
        BPCPRMM.EFF_DT = BPCSHCHK.EFF_DATE;
        BPRHCHK.DATA_TXT.EFF_DT = BPCSHCHK.EFF_DATE;
        BPRHCHK.KEY.TYP = "HCHKC";
        BPCPRMM.DAT_PTR = BPRHCHK;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HCHK_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_HCHK_KEY.WS_HCHK_CD = BPCSHCHK.CD;
        BPRHCHK.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_HCHK_KEY);
        BPCPRMM.EFF_DT = BPCSHCHK.EFF_DATE;
        CEP.TRC(SCCGWA, BPRHCHK.KEY.CD);
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        BPRHCHK.KEY.TYP = "HCHKC";
        BPCPRMM.FUNC = '1';
        BPRHCHK.DATA_LEN = 67;
        BPCPRMM.DAT_PTR = BPRHCHK;
        B040_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRHCHK;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRHCHK);
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, WS_HCHK_KEY);
        BPRHCHK.KEY.TYP = "HCHKC";
        BPRPARM.KEY.TYPE = BPRHCHK.KEY.TYP;
        BPRPARM.KEY.CODE = BPCSHCHK.CD;
        BPCRMBPM.FUNC = 'S';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        B050_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_TBL_HCHK_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
            if (WS_TBL_HCHK_FLAG != 'N') {
                CEP.TRC(SCCGWA, "KIA");
                CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
                if (BPCSHCHK.CD.trim().length() == 0) {
                    B050_03_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                }
                if (BPCSHCHK.CD.trim().length() > 0 
                    && BPCSHCHK.CD.equalsIgnoreCase(BPRPARM.KEY.CODE)) {
                    B050_03_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                }
            }
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
        SCCMPAG.MAX_COL_NO = 77;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_HCHK_DETAIL.WS_CD = BPRPARM.KEY.CODE;
        CEP.TRC(SCCGWA, WS_HCHK_DETAIL.WS_CD);
        if (BPRPARM.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE 
            || BPRPARM.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_HCHK_DETAIL.WS_USE_FLAG = 'N';
        } else {
            WS_HCHK_DETAIL.WS_USE_FLAG = 'Y';
        }
        WS_HCHK_DETAIL.WS_HCHK_EFF_DATE = BPRPARM.EFF_DATE;
        WS_HCHK_DETAIL.WS_HCHK_EXP_DATE = BPRPARM.EXP_DATE;
        WS_HCHK_DETAIL.WS_HCHK_DESC = BPRPARM.DESC;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_HCHK_DETAIL);
        SCCMPAG.DATA_LEN = 77;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOHCKO);
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSHCHK.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOHCKO;
        SCCFMT.DATA_LEN = 152;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRHCHK.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_HCHK_KEY);
        BPRHCHK.DESC = BPCSHCHK.DESC;
        BPRHCHK.CDESC = BPCSHCHK.CDESC;
        BPRHCHK.DATA_TXT.RESI_FLG = BPCSHCHK.RESI_FLG;
        BPRHCHK.DATA_TXT.CUR_FLG = BPCSHCHK.CUR_FLG;
        BPRHCHK.DATA_TXT.BRAN_FLG = BPCSHCHK.BRAN_FLG;
        BPRHCHK.DATA_TXT.CNTY_CD1 = BPCSHCHK.CNTY_CD1;
        BPRHCHK.DATA_TXT.CITY_CD1 = BPCSHCHK.CITY_CD1;
        BPRHCHK.DATA_TXT.CNTY_CD2 = BPCSHCHK.CNTY_CD2;
        BPRHCHK.DATA_TXT.CITY_CD2 = BPCSHCHK.CITY_CD2;
        BPRHCHK.DATA_TXT.CNTY_CD3 = BPCSHCHK.CNTY_CD3;
        BPRHCHK.DATA_TXT.CITY_CD3 = BPCSHCHK.CITY_CD3;
        BPRHCHK.DATA_TXT.CNTY_CD4 = BPCSHCHK.CNTY_CD4;
        BPRHCHK.DATA_TXT.CITY_CD4 = BPCSHCHK.CITY_CD4;
        BPRHCHK.DATA_TXT.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRHCHK.DATA_TXT.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCOHCKO.CD = BPRHCHK.KEY.CD;
        BPCOHCKO.DESC = BPRHCHK.DESC;
        BPCOHCKO.CDSC = BPRHCHK.CDESC;
        BPCOHCKO.CNTY_FLG = BPRHCHK.DATA_TXT.RESI_FLG;
        BPCOHCKO.CUR_FLG = BPRHCHK.DATA_TXT.CUR_FLG;
        BPCOHCKO.BRAN_FLG = BPRHCHK.DATA_TXT.BRAN_FLG;
        BPCOHCKO.CNTY_CD1 = BPRHCHK.DATA_TXT.CNTY_CD1;
        BPCOHCKO.CITY_CD1 = BPRHCHK.DATA_TXT.CITY_CD1;
        BPCOHCKO.CNTY_CD2 = BPRHCHK.DATA_TXT.CNTY_CD2;
        BPCOHCKO.CITY_CD2 = BPRHCHK.DATA_TXT.CITY_CD2;
        BPCOHCKO.CNTY_CD3 = BPRHCHK.DATA_TXT.CNTY_CD3;
        BPCOHCKO.CITY_CD3 = BPRHCHK.DATA_TXT.CITY_CD3;
        BPCOHCKO.CNTY_CD4 = BPRHCHK.DATA_TXT.CNTY_CD4;
        BPCOHCKO.CITY_CD4 = BPRHCHK.DATA_TXT.CITY_CD4;
        BPCOHCKO.UPT_DT = BPRHCHK.DATA_TXT.UPT_DT;
        BPCOHCKO.UPT_TLR = BPRHCHK.DATA_TXT.UPT_TLR;
        BPCOHCKO.EFF_DT = BPRHCHK.DATA_TXT.EFF_DT;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPARM.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
        CEP.TRC(SCCGWA, BPRPARM.EFF_DATE);
        CEP.TRC(SCCGWA, BPCRMBPM.FUNC);
        BPCRMBPM.PTR = BPRPARM;
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RETURN_INFO != 'F') {
            if (BPCRMBPM.RETURN_INFO == 'L' 
                || BPCRMBPM.RETURN_INFO == 'N') {
                WS_TBL_HCHK_FLAG = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_TBL_HCHK_FLAG = 'Y';
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNCI, BPCQCNCI);
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNTY, BPCIQCNT);
    }
    public void S000_CALL_BPZIQCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CITY, BPCIQCIT);
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
