package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSIBAC {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_BRW = "BP-PARM-BROWSE      ";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "IBAC CODE INFO MAINTAIN";
    String K_CPY_BPRPIBAC = "BPRPIBAC";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSIBAC_WS_IBAC_HEAD WS_IBAC_HEAD = new BPZSIBAC_WS_IBAC_HEAD();
    BPZSIBAC_WS_IBAC_KEY WS_IBAC_KEY = new BPZSIBAC_WS_IBAC_KEY();
    BPZSIBAC_WS_IBAC_DETAIL WS_IBAC_DETAIL = new BPZSIBAC_WS_IBAC_DETAIL();
    char WS_TBL_IBAC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRPIBAC BPRPIBAC = new BPRPIBAC();
    BPRPIBAC BPROIBAC = new BPRPIBAC();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCOACIB BPCOACIB = new BPCOACIB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCSIBAC BPCSIBAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSIBAC BPCSIBAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIBAC = BPCSIBAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIBAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSIBAC.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSIBAC.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSIBAC.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSIBAC.FUNC == 'D') {
            B060_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSIBAC.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSIBAC.FUNC != 'B') {
            B040_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPIBAC);
        IBS.init(SCCGWA, BPCPRMM);
        if (BPCSIBAC.FUNC_CODE == 'A') {
            BPRPIBAC.KEY.TYP = "IBAC";
        } else {
            BPRPIBAC.KEY.TYP = "IBAC";
            WS_IBAC_KEY.WS_IBAC_CCY = BPCSIBAC.CCY;
            WS_IBAC_KEY.WS_IBAC_SWIFT = BPCSIBAC.SWIFT;
            BPCPRMM.EFF_DT = BPCSIBAC.EFF_DATE;
            BPRPIBAC.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_IBAC_KEY);
            BPCPRMM.FUNC = '3';
            BPCPRMM.DAT_PTR = BPRPIBAC;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_IBAC_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPIBAC);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        BPCPRMM.EFF_DT = BPCSIBAC.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSIBAC.EXP_DATE;
        WS_IBAC_KEY.WS_IBAC_CCY = BPCSIBAC.CCY;
        WS_IBAC_KEY.WS_IBAC_SWIFT = BPCSIBAC.SWIFT;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPRPIBAC.KEY.TYP = "IBAC";
        BPRPIBAC.DATA_LEN = 124;
        BPCPRMM.DAT_PTR = BPRPIBAC;
        CEP.TRC(SCCGWA, BPRPIBAC.DATA_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPIBAC;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPIBAC);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        BPCPRMM.EFF_DT = BPCSIBAC.EFF_DATE;
        WS_IBAC_KEY.WS_IBAC_CCY = BPCSIBAC.CCY;
        WS_IBAC_KEY.WS_IBAC_SWIFT = BPCSIBAC.SWIFT;
        BPRPIBAC.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_IBAC_KEY);
        BPRPIBAC.KEY.TYP = "IBAC";
        BPCPRMM.DAT_PTR = BPRPIBAC;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_IBAC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCPRMM.TS.equalsIgnoreCase("Y") 
            && BPCSIBAC.EFF_DATE != BPCPRMM.EFF_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARAMETER_EFFECTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRPIBAC, BPROIBAC);
        BPCPRMM.EFF_DT = BPCSIBAC.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSIBAC.EXP_DATE;
        WS_IBAC_KEY.WS_IBAC_CCY = BPCSIBAC.CCY;
        WS_IBAC_KEY.WS_IBAC_SWIFT = BPCSIBAC.SWIFT;
        BPRPIBAC.KEY.TYP = "IBAC";
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '2';
        BPRPIBAC.DATA_LEN = 124;
        BPCPRMM.DAT_PTR = BPRPIBAC;
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
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPIBAC;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROIBAC;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRPIBAC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSIBAC.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOACIB;
        SCCFMT.DATA_LEN = 226;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPIBAC);
        IBS.init(SCCGWA, BPCPRMB);
        IBS.init(SCCGWA, WS_IBAC_KEY);
        CEP.TRC(SCCGWA, "START");
        CEP.TRC(SCCGWA, BPCSIBAC.CCY);
        CEP.TRC(SCCGWA, BPCSIBAC.SWIFT);
        if (BPCSIBAC.CCY.trim().length() == 0 
            && BPCSIBAC.SWIFT.trim().length() > 0) {
            WS_IBAC_KEY.WS_IBAC_CCY = "%%%";
            WS_IBAC_KEY.WS_IBAC_SWIFT = BPCSIBAC.SWIFT;
            CEP.TRC(SCCGWA, "START-SW");
            CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_CCY);
            CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_SWIFT);
            BPRPIBAC.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_IBAC_KEY);
            CEP.TRC(SCCGWA, BPRPIBAC.KEY.CD);
            BPRPIBAC.KEY.TYP = "IBAC";
            BPCPRMB.FUNC = '3';
            BPCPRMB.DAT_PTR = BPRPIBAC;
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "SSSSSSSSSSSSSS");
            BPCPRMB.FUNC = '4';
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPRMB.RC);
            IBS.CPY2CLS(SCCGWA, BPRPIBAC.KEY.CD, WS_IBAC_KEY);
            CEP.TRC(SCCGWA, BPRPIBAC);
            CEP.TRC(SCCGWA, "123123");
            CEP.TRC(SCCGWA, "111111");
            CEP.TRC(SCCGWA, WS_IBAC_KEY);
            CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_CCY);
            CEP.TRC(SCCGWA, BPCSIBAC.CCY);
            CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_SWIFT);
            CEP.TRC(SCCGWA, BPCSIBAC.SWIFT);
            CEP.TRC(SCCGWA, BPRPIBAC.KEY.CD);
            CEP.TRC(SCCGWA, BPRPIBAC.KEY.TYP);
            B050_04_OUTPUT_TITLE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "OUTOUTOUT");
            CEP.TRC(SCCGWA, BPCPRMB.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            for (WS_CNT = 1; !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
                && BPRPIBAC.KEY.TYP.equalsIgnoreCase("IBAC") 
                && BPCSIBAC.SWIFT.equalsIgnoreCase(BPCSIBAC.SWIFT) 
                && WS_CNT <= 5000; WS_CNT += 1) {
                CEP.TRC(SCCGWA, BPRPIBAC.DATA_TXT);
                B050_05_OUTPUT_DETAIL();
                if (pgmRtn) return;
                BPCPRMB.FUNC = '4';
                S000_CALL_BPZPRMB();
                if (pgmRtn) return;
                IBS.CPY2CLS(SCCGWA, BPRPIBAC.KEY.CD, WS_IBAC_KEY);
                CEP.TRC(SCCGWA, WS_IBAC_KEY);
                CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_CCY);
                CEP.TRC(SCCGWA, BPCSIBAC.CCY);
                CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_SWIFT);
                CEP.TRC(SCCGWA, BPCSIBAC.SWIFT);
                CEP.TRC(SCCGWA, BPRPIBAC.KEY.TYP);
            }
            CEP.TRC(SCCGWA, "ENDENDEND");
            BPCPRMB.FUNC = '5';
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
        } else {
            WS_IBAC_KEY.WS_IBAC_CCY = BPCSIBAC.CCY;
            WS_IBAC_KEY.WS_IBAC_SWIFT = BPCSIBAC.SWIFT;
            BPRPIBAC.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_IBAC_KEY);
            BPRPIBAC.KEY.TYP = "IBAC";
            BPCPRMB.FUNC = '0';
            BPCPRMB.DAT_PTR = BPRPIBAC;
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "FFFFFFFFFFFFFFFF");
            CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_CCY);
            CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_SWIFT);
            CEP.TRC(SCCGWA, BPRPIBAC.KEY.CD);
            BPCPRMB.FUNC = '1';
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, BPRPIBAC.KEY.CD, WS_IBAC_KEY);
            CEP.TRC(SCCGWA, BPRPIBAC);
            CEP.TRC(SCCGWA, "222222");
            CEP.TRC(SCCGWA, WS_IBAC_KEY);
            CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_CCY);
            CEP.TRC(SCCGWA, BPCSIBAC.CCY);
            CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_SWIFT);
            CEP.TRC(SCCGWA, BPCSIBAC.SWIFT);
            CEP.TRC(SCCGWA, BPRPIBAC.KEY.CD);
            B050_01_OUTPUT_TITLE();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            for (WS_CNT = 1; !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
                && BPRPIBAC.KEY.TYP.equalsIgnoreCase("IBAC") 
                && !(!WS_IBAC_KEY.WS_IBAC_CCY.equalsIgnoreCase(BPCSIBAC.CCY) 
                || BPCSIBAC.CCY.trim().length() <= 0) 
                && !(!WS_IBAC_KEY.WS_IBAC_SWIFT.equalsIgnoreCase(BPCSIBAC.SWIFT) 
                || BPCSIBAC.SWIFT.trim().length() <= 0) 
                && WS_CNT <= 5000 
                && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
                CEP.TRC(SCCGWA, BPRPIBAC.DATA_TXT);
                B050_03_OUTPUT_DETAIL();
                if (pgmRtn) return;
                BPCPRMB.FUNC = '1';
                S000_CALL_BPZPRMB();
                if (pgmRtn) return;
                IBS.CPY2CLS(SCCGWA, BPRPIBAC.KEY.CD, WS_IBAC_KEY);
                CEP.TRC(SCCGWA, WS_IBAC_KEY);
                CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_CCY);
                CEP.TRC(SCCGWA, BPCSIBAC.CCY);
                CEP.TRC(SCCGWA, WS_IBAC_KEY.WS_IBAC_SWIFT);
                CEP.TRC(SCCGWA, BPCSIBAC.SWIFT);
            }
            BPCPRMB.FUNC = '2';
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
        }
    }
    public void B050_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 204;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_MAX_COL;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_02_OUTPUT_HEADLINE() throws IOException,SQLException,Exception {
        WS_IBAC_HEAD.WS_IBAC_IBAC = "IBAC";
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_IBAC_HEAD);
        SCCMPAG.DATA_LEN = 5;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_IBAC_DETAIL);
        WS_IBAC_DETAIL.WS_IBAC_DET_CCY = WS_IBAC_KEY.WS_IBAC_CCY;
        WS_IBAC_DETAIL.WS_IBAC_DET_SWIFT = WS_IBAC_KEY.WS_IBAC_SWIFT;
        WS_IBAC_DETAIL.WS_IBAC_IB_AC = BPRPIBAC.DATA_TXT.IB_AC;
        WS_IBAC_DETAIL.WS_IBAC_AC_NAME = BPRPIBAC.DATA_TXT.AC_NAME;
        WS_IBAC_DETAIL.WS_IBAC_DESC = BPRPIBAC.DESC;
        WS_IBAC_DETAIL.WS_IBAC_CDESC = BPRPIBAC.CDSC;
        WS_IBAC_DETAIL.WS_IBAC_EFF_DATE = BPRPIBAC.DATA_TXT.EFF_DT;
        WS_IBAC_DETAIL.WS_IBAC_EXP_DATE = BPRPIBAC.DATA_TXT.EXP_DT;
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_DET_CCY);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_DET_SWIFT);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_IB_AC);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_DESC);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_CDESC);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_EFF_DATE);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_EXP_DATE);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_IBAC_DETAIL);
        SCCMPAG.DATA_LEN = 204;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_04_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 204;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_MAX_COL;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_05_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_IBAC_DETAIL);
        WS_IBAC_DETAIL.WS_IBAC_DET_CCY = WS_IBAC_KEY.WS_IBAC_CCY;
        WS_IBAC_DETAIL.WS_IBAC_DET_SWIFT = WS_IBAC_KEY.WS_IBAC_SWIFT;
        WS_IBAC_DETAIL.WS_IBAC_IB_AC = BPRPIBAC.DATA_TXT.IB_AC;
        WS_IBAC_DETAIL.WS_IBAC_AC_NAME = BPRPIBAC.DATA_TXT.AC_NAME;
        WS_IBAC_DETAIL.WS_IBAC_DESC = BPRPIBAC.DESC;
        WS_IBAC_DETAIL.WS_IBAC_CDESC = BPRPIBAC.CDSC;
        WS_IBAC_DETAIL.WS_IBAC_EFF_DATE = BPRPIBAC.DATA_TXT.EFF_DT;
        WS_IBAC_DETAIL.WS_IBAC_EXP_DATE = BPRPIBAC.DATA_TXT.EXP_DT;
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_DET_CCY);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_DET_SWIFT);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_IB_AC);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_DESC);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_CDESC);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_EFF_DATE);
        CEP.TRC(SCCGWA, WS_IBAC_DETAIL.WS_IBAC_EXP_DATE);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_IBAC_DETAIL);
        SCCMPAG.DATA_LEN = 204;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPIBAC);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        WS_IBAC_KEY.WS_IBAC_CCY = BPCSIBAC.CCY;
        WS_IBAC_KEY.WS_IBAC_SWIFT = BPCSIBAC.SWIFT;
        BPRPIBAC.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_IBAC_KEY);
        BPCPRMM.EFF_DT = BPCSIBAC.EFF_DATE;
        BPRPIBAC.KEY.TYP = "IBAC";
        BPCPRMM.DAT_PTR = BPRPIBAC;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_IBAC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCPRMM.TS.equalsIgnoreCase("Y")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARAMETER_EFFECTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_IBAC_KEY.WS_IBAC_CCY = BPCSIBAC.CCY;
        WS_IBAC_KEY.WS_IBAC_SWIFT = BPCSIBAC.SWIFT;
        BPRPIBAC.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_IBAC_KEY);
        BPRPIBAC.KEY.TYP = "IBAC";
        BPCPRMM.FUNC = '1';
        BPRPIBAC.DATA_LEN = 124;
        BPCPRMM.DAT_PTR = BPRPIBAC;
        B060_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B060_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPIBAC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIBAC.AC_NAME);
        BPRPIBAC.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_IBAC_KEY);
        BPRPIBAC.DESC = BPCSIBAC.IBAC_DESC;
        BPRPIBAC.CDSC = BPCSIBAC.IBAC_CDSC;
        BPRPIBAC.DATA_TXT.IB_AC = BPCSIBAC.IB_AC;
        BPRPIBAC.DATA_TXT.AC_NAME = BPCSIBAC.AC_NAME;
        BPRPIBAC.DATA_TXT.EFF_DT = BPCSIBAC.EFF_DATE;
        BPRPIBAC.DATA_TXT.EXP_DT = BPCSIBAC.EXP_DATE;
        CEP.TRC(SCCGWA, BPRPIBAC.DATA_TXT.AC_NAME);
        BPRPIBAC.DATA_TXT.UPD_DT = BPCSIBAC.UPD_DT;
        BPRPIBAC.DATA_TXT.UPD_TLR = BPCSIBAC.UPD_TLR;
        CEP.TRC(SCCGWA, "DATA-PARAMETER123");
        CEP.TRC(SCCGWA, BPRPIBAC.KEY.CD);
        CEP.TRC(SCCGWA, BPRPIBAC.DESC);
        CEP.TRC(SCCGWA, BPRPIBAC.CDSC);
        CEP.TRC(SCCGWA, BPRPIBAC.DATA_TXT.IB_AC);
        CEP.TRC(SCCGWA, BPRPIBAC.DATA_TXT.AC_NAME);
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCSIBAC.FUNC == 'I') {
            BPCOACIB.FUNC = BPCSIBAC.FUNC_CODE;
        } else {
            BPCOACIB.FUNC = BPCSIBAC.FUNC;
        }
        BPCOACIB.PARM_TP = BPRPIBAC.KEY.TYP;
        IBS.CPY2CLS(SCCGWA, BPRPIBAC.KEY.CD, WS_IBAC_KEY);
        BPCOACIB.DESC = BPRPIBAC.DESC;
        BPCOACIB.CDSC = BPRPIBAC.CDSC;
        BPCOACIB.CCY = WS_IBAC_KEY.WS_IBAC_CCY;
        BPCOACIB.SWIFT = WS_IBAC_KEY.WS_IBAC_SWIFT;
        BPCOACIB.IB_AC = BPRPIBAC.DATA_TXT.IB_AC;
        BPCOACIB.AC_NAME = BPRPIBAC.DATA_TXT.AC_NAME;
        CEP.TRC(SCCGWA, BPCOACIB.AC_NAME);
        BPCOACIB.EFF_DATE = BPCPRMM.EFF_DT;
        BPCOACIB.EXP_DATE = BPCPRMM.EXP_DT;
        BPCOACIB.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOACIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
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
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_BRW, BPCPRMB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (BPCPRMB.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
