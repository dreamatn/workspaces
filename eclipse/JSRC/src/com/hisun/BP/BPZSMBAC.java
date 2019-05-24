package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMBAC {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_PARM_BRW = "BP-PARM-BROWSE      ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "BV PRODUCT PARM MAINTAIN";
    String K_CPY_BPRPBACT = "BPRPBACT";
    char K_ALL_3 = '3';
    char K_ALL_2 = '2';
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSMBAC_WS_BACT_KEY WS_BACT_KEY = new BPZSMBAC_WS_BACT_KEY();
    BPZSMBAC_WS_BACT_DETAIL WS_BACT_DETAIL = new BPZSMBAC_WS_BACT_DETAIL();
    char WS_TBL_BACT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRPBACT BPRPBACT = new BPRPBACT();
    BPRPBACT BPROBACT = new BPRPBACT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCOBACO BPCOBACO = new BPCOBACO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    SCCGWA SCCGWA;
    BPCSBACT BPCSBACT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBACT BPCSBACT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBACT = BPCSBACT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMBAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCUCNGM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBACT.FUNC);
        if (BPCSBACT.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBACT.FUNC == 'A') {
            B020_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBACT.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBACT.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBACT.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSBACT.FUNC != 'B') {
            B060_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBACT);
        IBS.init(SCCGWA, BPCPRMM);
        CEP.TRC(SCCGWA, BPCSBACT.FUNC_CODE);
        CEP.TRC(SCCGWA, BPCSBACT);
        if (BPCSBACT.FUNC_CODE == 'A') {
            BPRPBACT.KEY.TYP = "BVACT";
        } else {
            BPRPBACT.KEY.TYP = "BVACT";
            WS_BACT_KEY.WS_BACT_CODE = BPCSBACT.CODE;
            WS_BACT_KEY.WS_BACT_STAT = BPCSBACT.STAT;
            WS_BACT_KEY.WS_BACT_BV_KIND = BPCSBACT.BV_KIND;
            BPCPRMM.EFF_DT = BPCSBACT.EFF_DATE;
            BPRPBACT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
            CEP.TRC(SCCGWA, WS_BACT_KEY);
            CEP.TRC(SCCGWA, BPCSBACT.EFF_DATE);
            BPCPRMM.FUNC = '3';
            BPCPRMM.DAT_PTR = BPRPBACT;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
                && BPCSBACT.FUNC == 'I') {
                WS_BACT_KEY.WS_BACT_STAT = K_ALL_3;
                BPRPBACT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
                BPCPRMM.DAT_PTR = BPRPBACT;
                S000_CALL_BPZPRMM();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
                    && BPCSBACT.FUNC == 'I') {
                    WS_BACT_KEY.WS_BACT_STAT = BPCSBACT.STAT;
                    WS_BACT_KEY.WS_BACT_BV_KIND = K_ALL_2;
                    BPRPBACT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
                    BPCPRMM.DAT_PTR = BPRPBACT;
                    S000_CALL_BPZPRMM();
                    if (pgmRtn) return;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                    if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
                        && BPCSBACT.FUNC == 'I') {
                        WS_BACT_KEY.WS_BACT_STAT = K_ALL_3;
                        WS_BACT_KEY.WS_BACT_BV_KIND = K_ALL_2;
                        BPRPBACT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
                        BPCPRMM.DAT_PTR = BPRPBACT;
                        S000_CALL_BPZPRMM();
                        if (pgmRtn) return;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BACT_REC_NOTFND;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
        }
        BPCUCNGM.FUNC = 'Q';
        BPCUCNGM.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
        BPCUCNGM.KEY.CNTR_TYPE = "BVF";
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPBACT.KEY.CD);
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBACT);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        WS_BACT_KEY.WS_BACT_CODE = BPCSBACT.CODE;
        WS_BACT_KEY.WS_BACT_STAT = BPCSBACT.STAT;
        WS_BACT_KEY.WS_BACT_BV_KIND = BPCSBACT.BV_KIND;
        BPCPRMM.EFF_DT = BPCSBACT.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSBACT.EXP_DATE;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_BACT_KEY);
        CEP.TRC(SCCGWA, BPRPBACT.KEY.CD);
        BPRPBACT.KEY.TYP = "BVACT";
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        BPRPBACT.DATA_LEN = 46;
        BPCPRMM.DAT_PTR = BPRPBACT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCUCNGM.FUNC = 'A';
        BPCUCNGM.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
        BPCUCNGM.KEY.CNTR_TYPE = "BVF";
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPBACT;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBACT);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        WS_BACT_KEY.WS_BACT_CODE = BPCSBACT.CODE;
        WS_BACT_KEY.WS_BACT_STAT = BPCSBACT.STAT;
        WS_BACT_KEY.WS_BACT_BV_KIND = BPCSBACT.BV_KIND;
        BPRPBACT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
        BPCPRMM.EFF_DT = BPCSBACT.EFF_DATE;
        BPRPBACT.KEY.TYP = "BVACT";
        BPCPRMM.DAT_PTR = BPRPBACT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BACT_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRPBACT, BPROBACT);
        WS_BACT_KEY.WS_BACT_CODE = BPCSBACT.CODE;
        WS_BACT_KEY.WS_BACT_STAT = BPCSBACT.STAT;
        WS_BACT_KEY.WS_BACT_BV_KIND = BPCSBACT.BV_KIND;
        BPCPRMM.EFF_DT = BPCSBACT.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSBACT.EXP_DATE;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPRPBACT.KEY.CD);
        BPRPBACT.KEY.TYP = "BVACT";
        BPCPRMM.FUNC = '2';
        BPRPBACT.DATA_LEN = 46;
        BPCPRMM.DAT_PTR = BPRPBACT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCUCNGM.FUNC = 'U';
        BPCUCNGM.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
        BPCUCNGM.KEY.CNTR_TYPE = "BVF";
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPBACT;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROBACT;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRPBACT;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBACT);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        WS_BACT_KEY.WS_BACT_CODE = BPCSBACT.CODE;
        WS_BACT_KEY.WS_BACT_STAT = BPCSBACT.STAT;
        WS_BACT_KEY.WS_BACT_BV_KIND = BPCSBACT.BV_KIND;
        BPRPBACT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
        BPCPRMM.EFF_DT = BPCSBACT.EFF_DATE;
        BPRPBACT.KEY.TYP = "BVACT";
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPRPBACT.KEY.CD);
        BPCPRMM.DAT_PTR = BPRPBACT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BACT_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_BACT_KEY.WS_BACT_CODE = BPCSBACT.CODE;
        WS_BACT_KEY.WS_BACT_STAT = BPCSBACT.STAT;
        WS_BACT_KEY.WS_BACT_BV_KIND = BPCSBACT.BV_KIND;
        BPRPBACT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
        BPRPBACT.KEY.TYP = "BVACT";
        BPCPRMM.FUNC = '1';
        BPRPBACT.DATA_LEN = 46;
        BPCPRMM.DAT_PTR = BPRPBACT;
        B040_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCUCNGM.FUNC = 'D';
        BPCUCNGM.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
        BPCUCNGM.KEY.CNTR_TYPE = "BVF";
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
    }
    public void B040_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPBACT;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBACT);
        IBS.init(SCCGWA, BPCPRMB);
        IBS.init(SCCGWA, WS_BACT_KEY);
        WS_BACT_KEY.WS_BACT_CODE = BPCSBACT.CODE;
        WS_BACT_KEY.WS_BACT_STAT = BPCSBACT.STAT;
        WS_BACT_KEY.WS_BACT_BV_KIND = BPCSBACT.BV_KIND;
        BPRPBACT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
        BPRPBACT.KEY.TYP = "BVACT";
        BPCPRMB.FUNC = '0';
        BPCPRMB.DAT_PTR = BPRPBACT;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        BPCPRMB.FUNC = '1';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPRPBACT.KEY.CD, WS_BACT_KEY);
        B050_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        for (WS_CNT = 1; !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
            && BPRPBACT.KEY.TYP.equalsIgnoreCase("BVACT") 
            && !(!WS_BACT_KEY.WS_BACT_CODE.equalsIgnoreCase(BPCSBACT.CODE) 
            || BPCSBACT.CODE.trim().length() <= 0) 
            && (WS_BACT_KEY.WS_BACT_STAT == BPCSBACT.STAT 
            || BPCSBACT.STAT == ' ') 
            && (WS_BACT_KEY.WS_BACT_BV_KIND == BPCSBACT.BV_KIND 
            || BPCSBACT.BV_KIND == ' ') 
            && WS_CNT <= 5000 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B050_03_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCPRMB.FUNC = '1';
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, BPRPBACT.KEY.CD, WS_BACT_KEY);
            CEP.TRC(SCCGWA, WS_BACT_KEY);
        }
        CEP.TRC(SCCGWA, WS_BACT_DETAIL);
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void B050_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 105;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 7;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_BACT_DETAIL.WS_CODE = WS_BACT_KEY.WS_BACT_CODE;
        WS_BACT_DETAIL.WS_STAT = WS_BACT_KEY.WS_BACT_STAT;
        WS_BACT_DETAIL.WS_BV_KIND = WS_BACT_KEY.WS_BACT_BV_KIND;
        WS_BACT_DETAIL.WS_BACT_DESC = BPRPBACT.DESC;
        WS_BACT_DETAIL.WS_BACT_CDESC = BPRPBACT.CDSC;
        WS_BACT_DETAIL.WS_BACT_EFF_DATE = BPRPBACT.DATA_TXT.EFF_DT;
        CEP.TRC(SCCGWA, WS_BACT_DETAIL.WS_BACT_EFF_DATE);
        WS_BACT_DETAIL.WS_BACT_EXP_DATE = BPRPBACT.DATA_TXT.EXP_DT;
        CEP.TRC(SCCGWA, WS_BACT_DETAIL.WS_BACT_EXP_DATE);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BACT_DETAIL);
        SCCMPAG.DATA_LEN = 105;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOBACO.CODE);
        CEP.TRC(SCCGWA, BPCOBACO.STAT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSBACT.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOBACO;
        SCCFMT.DATA_LEN = 120;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRPBACT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
        BPRPBACT.DATA_TXT.ACC_CD = 0;
        BPCUCNGM.DATA[1-1].GLMST = BPCSBACT.ACC_CD;
        BPRPBACT.DATA_TXT.SUP_FLG = BPCSBACT.SUP_FLG;
        BPRPBACT.DESC = BPCSBACT.DESC;
        BPRPBACT.CDSC = BPCSBACT.CDSC;
        BPRPBACT.DATA_TXT.EFF_DT = BPCSBACT.EFF_DATE;
        BPRPBACT.DATA_TXT.EXP_DT = BPCSBACT.EXP_DATE;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCSBACT.FUNC == 'I') {
            BPCOBACO.FUNC = BPCSBACT.FUNC_CODE;
        } else {
            BPCOBACO.FUNC = BPCSBACT.FUNC;
        }
        BPCOBACO.PARM_TP = BPRPBACT.KEY.TYP;
        IBS.CPY2CLS(SCCGWA, BPRPBACT.KEY.CD, WS_BACT_KEY);
        BPCOBACO.DESC = BPRPBACT.DESC;
        BPCOBACO.CDSC = BPRPBACT.CDSC;
        BPCOBACO.CODE = WS_BACT_KEY.WS_BACT_CODE;
        BPCOBACO.STAT = WS_BACT_KEY.WS_BACT_STAT;
        BPCOBACO.BV_KIND = WS_BACT_KEY.WS_BACT_BV_KIND;
        CEP.TRC(SCCGWA, BPCUCNGM.DATA[1-1].GLMST);
        BPCOBACO.ACC_CD = BPCUCNGM.DATA[1-1].GLMST;
        BPCOBACO.SUP_FLG = BPRPBACT.DATA_TXT.SUP_FLG;
        BPCOBACO.EFF_DATE = BPRPBACT.DATA_TXT.EFF_DT;
        BPCOBACO.EXP_DATE = BPRPBACT.DATA_TXT.EXP_DT;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
